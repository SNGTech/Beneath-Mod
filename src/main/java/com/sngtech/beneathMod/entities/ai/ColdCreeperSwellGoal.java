package com.sngtech.beneathMod.entities.ai;

import java.util.EnumSet;

import com.sngtech.beneathMod.entities.ColdCreeperEntity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;

public class ColdCreeperSwellGoal extends Goal 
{
	private final ColdCreeperEntity coldCreeper;
   private LivingEntity livingEntity;

   public ColdCreeperSwellGoal(ColdCreeperEntity coldCreeper) 
   {
      this.coldCreeper = coldCreeper;
      this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
   }

   @Override
   public boolean shouldExecute() 
   {
      LivingEntity livingentity = this.coldCreeper.getAttackTarget();
      return this.coldCreeper.getColdCreeperState() > 0 || livingentity != null && this.coldCreeper.getDistanceSq(livingentity) < 9.0D;
   }

   @Override
   public void startExecuting() 
   {
      this.coldCreeper.getNavigator().clearPath();
      this.livingEntity = this.coldCreeper.getAttackTarget();
   }

   @Override
   public void resetTask() 
   {
      this.livingEntity = null;
   }

   @Override
   public void tick() {
      if (this.livingEntity == null) 
      {
         this.coldCreeper.setColdCreeperState(-1);
      } 
      else if (this.coldCreeper.getDistanceSq(this.livingEntity) > 49.0D) 
      {
         this.coldCreeper.setColdCreeperState(-1);
      } 
      else if (!this.coldCreeper.getEntitySenses().canSee(this.livingEntity)) 
      {
         this.coldCreeper.setColdCreeperState(-1);
      } 
      else 
      {
         this.coldCreeper.setColdCreeperState(1);
      }
   }
}
