package com.sngtech.beneathMod.entities;

import javax.annotation.Nullable;

import com.sngtech.beneathMod.entities.ai.ColdCreeperSwellGoal;
import com.sngtech.beneathMod.world.explosions.ColdCreeperExplosion;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ForgeEventFactory;

public class ColdCreeperEntity extends MonsterEntity
{
	private static final DataParameter<Integer> STATE = EntityDataManager.createKey(CreeperEntity.class, DataSerializers.VARINT);
	private static final DataParameter<Boolean> POWERED = EntityDataManager.createKey(CreeperEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IGNITED = EntityDataManager.createKey(CreeperEntity.class, DataSerializers.BOOLEAN);
	private int lastActiveTime;
	private int timeSinceIgnited;
	private int fuseTime = 20;
	private int explosionRadius = 3;

   public ColdCreeperEntity(EntityType<? extends ColdCreeperEntity> type, World worldIn) 
   {
      super(type, worldIn);
   }

   protected void registerGoals() 
   {
      this.goalSelector.addGoal(1, new SwimGoal(this));
      this.goalSelector.addGoal(2, new ColdCreeperSwellGoal(this));
      this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, OcelotEntity.class, 6.0F, 1.0D, 1.2D));
      this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, CatEntity.class, 6.0F, 1.0D, 1.2D));
      this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
      this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
      this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
      this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
      this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
      this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
   }

   protected void registerAttributes()
   {
      super.registerAttributes();
      this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
   }

   /**
    * The maximum height from where the entity is alowed to jump (used in pathfinder)
    */
   public int getMaxFallHeight()
   {
      return this.getAttackTarget() == null ? 3 : 3 + (int)(this.getHealth() - 1.0F);
   }

   public void fall(float distance, float damageMultiplier) 
   {
      super.fall(distance, damageMultiplier);
      this.timeSinceIgnited = (int)((float)this.timeSinceIgnited + distance * 1.5F);
      if (this.timeSinceIgnited > this.fuseTime - 5) 
      {
         this.timeSinceIgnited = this.fuseTime - 5;
      }

   }

   protected void registerData() 
   {
      super.registerData();
      this.dataManager.register(STATE, -1);
      this.dataManager.register(POWERED, false);
      this.dataManager.register(IGNITED, false);
   }

   public void writeAdditional(CompoundNBT compound) 
   {
      super.writeAdditional(compound);
      if (this.dataManager.get(POWERED)) 
      {
         compound.putBoolean("powered", true);
      }

      compound.putShort("Fuse", (short)this.fuseTime);
      compound.putByte("ExplosionRadius", (byte)this.explosionRadius);
      compound.putBoolean("ignited", this.hasIgnited());
   }

   /**
    * (abstract) Protected helper method to read subclass entity data from NBT.
    */
   public void readAdditional(CompoundNBT compound) 
   {
      super.readAdditional(compound);
      this.dataManager.set(POWERED, compound.getBoolean("powered"));
      if (compound.contains("Fuse", 99)) 
      {
         this.fuseTime = compound.getShort("Fuse");
      }

      if (compound.contains("ExplosionRadius", 99)) 
      {
         this.explosionRadius = compound.getByte("ExplosionRadius");
      }

      if (compound.getBoolean("ignited")) 
      {
         this.ignite();
      }

   }

   /**
    * Called to update the entity's position/logic.
    */
   public void tick()
   {
      if (this.isAlive()) 
      {
         this.lastActiveTime = this.timeSinceIgnited;
         if (this.hasIgnited()) 
         {
            this.setColdCreeperState(1);
         }

         int i = this.getColdCreeperState();
         if (i > 0 && this.timeSinceIgnited == 0) 
         {
            this.playSound(SoundEvents.ENTITY_CREEPER_PRIMED, 1.0F, 0.5F);
         }

         this.timeSinceIgnited += i;
         if (this.timeSinceIgnited < 0) 
         {
            this.timeSinceIgnited = 0;
         }

         if (this.timeSinceIgnited >= this.fuseTime) 
         {
            this.timeSinceIgnited = this.fuseTime;
            this.explode();
         }
      }

      super.tick();
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) 
   {
      return SoundEvents.ENTITY_CREEPER_HURT;
   }

   protected SoundEvent getDeathSound() 
   {
      return SoundEvents.ENTITY_CREEPER_DEATH;
   }

   public boolean attackEntityAsMob(Entity entityIn) 
   {
      return true;
   }

   /**
    * Returns true if the creeper is powered by a lightning bolt.
    */
   public boolean getPowered() 
   {
      return this.dataManager.get(POWERED);
   }

   /**
    * Params: (Float)Render tick. Returns the intensity of the creeper's flash when it is ignited.
    */
   @OnlyIn(Dist.CLIENT)
   public float getColdCreeperFlashIntensity(float partialTicks) 
   {
      return MathHelper.lerp(partialTicks, (float)this.lastActiveTime, (float)this.timeSinceIgnited) / (float)(this.fuseTime - 2);
   }

   /**
    * Returns the current state of creeper, -1 is idle, 1 is 'in fuse'
    */
   public int getColdCreeperState()
   {
      return this.dataManager.get(STATE);
   }

   /**
    * Sets the state of creeper, -1 to idle and 1 to be 'in fuse'
    */
   public void setColdCreeperState(int state) 
   {
      this.dataManager.set(STATE, state);
   }

   /**
    * Called when a lightning bolt hits the entity.
    */
   public void onStruckByLightning(LightningBoltEntity lightningBolt) 
   {
      super.onStruckByLightning(lightningBolt);
      this.dataManager.set(POWERED, true);
   }

   protected boolean processInteract(PlayerEntity player, Hand hand)
   {
      ItemStack itemstack = player.getHeldItem(hand);
      if (itemstack.getItem() == Items.FLINT_AND_STEEL) {
         this.world.playSound(player, this.posX, this.posY, this.posZ, SoundEvents.ITEM_FLINTANDSTEEL_USE, this.getSoundCategory(), 1.0F, this.rand.nextFloat() * 0.4F + 0.8F);
         player.swingArm(hand);
         if (!this.world.isRemote) {
            this.ignite();
            itemstack.damageItem(1, player, (p_213625_1_) -> {
               p_213625_1_.sendBreakAnimation(hand);
            });
            return true;
         }
      }

      return super.processInteract(player, hand);
   }
   
   private void explode()
   {
      if (!this.world.isRemote)
      {
    	 ColdCreeperExplosion.Mode explosionMode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this) ? ColdCreeperExplosion.Mode.DESTROY : ColdCreeperExplosion.Mode.NONE;
   	   	 float f = this.getPowered() ? 2.0F : 1.0F;
         this.dead = true;
         this.createExplosion(this.world, this, this.posX, this.posY, this.posZ, (float)this.explosionRadius * f, explosionMode);
         this.remove();
      }
   }
   
   private ColdCreeperExplosion createExplosion(World p_i50007_1_, @Nullable Entity p_i50007_2_, double p_i50007_3_, double p_i50007_5_, double p_i50007_7_, float p_i50007_9_, ColdCreeperExplosion.Mode p_i50007_11_) 
   {
	   ColdCreeperExplosion coldCreeperExplosion = new ColdCreeperExplosion(p_i50007_1_, p_i50007_2_, p_i50007_3_, p_i50007_5_, p_i50007_7_, p_i50007_9_, p_i50007_11_);
	   if (ForgeEventFactory.onExplosionStart(this.world, coldCreeperExplosion)) 
		   return coldCreeperExplosion;

	   coldCreeperExplosion.doExplosionA();
	   coldCreeperExplosion.doExplosionB(true);
	   return coldCreeperExplosion;
   }

   public boolean hasIgnited() 
   {
      return this.dataManager.get(IGNITED);
   }

   public void ignite() 
   {
      this.dataManager.set(IGNITED, true);
   }

   /**
    * Returns true if an entity is able to drop its skull due to being blown up by this creeper.
    *  
    * Does not test if this creeper is charged; the caller must do that. However, does test the doMobLoot gamerule.
    */
   public boolean ableToCauseSkullDrop() 
   {
      return false;
   }
}
