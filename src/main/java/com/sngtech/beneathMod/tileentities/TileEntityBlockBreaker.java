package com.sngtech.beneathMod.tileentities;

import javax.annotation.Nullable;

import com.sngtech.beneathMod.Main;
import com.sngtech.beneathMod.init.TileEntityInit;

import net.minecraft.block.BlockFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IInteractionObject;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityBlockBreaker extends TileEntity implements IInteractionObject, ITickable
{
	private ITextComponent customName;
	
	ItemStackHandler itemstacks = new ItemStackHandler(16)
	{
		@Override
		protected void onContentsChanged(int slot) 
		{
			super.onContentsChanged(slot);
			markDirty();
		}
	};
	
	private int burnTime;
	private int maxBurnTime;
	private int breakTime;
	private int maxBreakTime;
	
	public TileEntityBlockBreaker(TileEntityType<?> tileEntityTypeIn) 
	{
		super(TileEntityInit.BLOCK_BREAKER);
	}

	@Override
	public ITextComponent getName() 
	{
		ITextComponent itextcomponent = this.getCustomName();
	    return (ITextComponent)(itextcomponent != null ? itextcomponent : new TextComponentTranslation("container.block_breaker"));
	}
    
	public boolean hasCustomName() 
	{
	      return this.customName != null;
	}
	
    @Nullable
    public ITextComponent getCustomName() 
    {
    	return this.customName;
    }
	
    public void setCustomName(@Nullable ITextComponent name) 
    {
    	this.customName = name;
    }
	
    private boolean isBurning() 
    {
        return this.burnTime > 0;
    }
    
	@Override
	public void tick() 
	{
		boolean flag = this.isBurning();
	      boolean flag1 = false;
	      if (this.isBurning()) {
	         --this.burnTime;
	      }

	      if (!this.world.isRemote) {
	         ItemStack itemstack = this.itemstacks.getStackInSlot(1);
	         if (this.isBurning() || !itemstack.isEmpty() && !this.itemstacks.getStackInSlot(0).isEmpty()) {
	            IRecipe irecipe = this.world.getRecipeManager().getRecipe(this, this.world, net.minecraftforge.common.crafting.VanillaRecipeTypes.SMELTING);
	            if (!this.isBurning() && this.canBreak(irecipe)) {
	               this.burnTime = getItemBurnTime(itemstack);
	               this.currentItemBurnTime = this.burnTime;
	               if (this.isBurning()) {
	                  flag1 = true;
	                  if (itemstack.hasContainerItem()) {
	                     this.itemstacks.setStackInSlot(1, itemstack.getContainerItem());
	                  }
	                  else
	                  if (!itemstack.isEmpty()) {
	                     Item item = itemstack.getItem();
	                     itemstack.shrink(1);
	                     if (itemstack.isEmpty()) {
	                        Item item1 = item.getContainerItem();
	                        this.itemstacks.setStackInSlot(1, item1 == null ? ItemStack.EMPTY : new ItemStack(item1));
	                     }
	                  }
	               }
	            }

	            if (this.isBurning() && this.canBreak(irecipe)) {
	               ++this.breakTime;
	               if (this.breakTime == this.maxBreakTime) {
	                  this.breakTime = 0;
	                  this.maxBreakTime = this.getBreakTime();
	                  this.breakBlock(irecipe);
	                  flag1 = true;
	               }
	            } else {
	               this.breakTime = 0;
	            }
	         } else if (!this.isBurning() && this.breakTime > 0) {
	            this.breakTime = MathHelper.clamp(this.breakTime - 2, 0, this.maxBreakTime);
	         }

	         if (flag != this.isBurning()) {
	            flag1 = true;
	            this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(BlockFurnace.LIT, Boolean.valueOf(this.isBurning())), 3);
	         }
	      }

	      if (flag1) {
	         this.markDirty();
	      }

	}
	
	private int getBreakTime()
	{
		return 200;
	}
	
	private void breakBlock()
	{
		
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer player) 
	{
		return new ContainerBlockBreaker(playerInventory, this, player);
	}

	@Override
	public String getGuiID() 
	{
		return Main.MODID + "block_breaker";
	}
		
	public int getField(int id) 
	{
		switch(id)
		{
		case 0:
			return this.burnTime;
		case 1:
			return this.maxBurnTime;
		case 2:
			return this.breakTime;
		case 3:
			return this.maxBreakTime;
			default:
				return 0;
		}
	}

	public void setField(int id, int value) 
	{
		switch(id)
		{
		case 0:
			this.burnTime = value;
			break;
		case 1:
			this.maxBurnTime = value;
			break;
		case 2:
			this.breakTime = value;
			break;
		case 3:
			this.maxBreakTime = value;
			break;
		}
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, EnumFacing side) 
	{
		if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return LazyOptional.of(() -> this.inventory).cast();
		return super.getCapability(cap, side);
	}
}
