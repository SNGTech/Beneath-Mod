package com.sngtech.beneathMod.tileentities.dryingracks;

import java.util.Optional;

import com.sngtech.beneathMod.Main;
import com.sngtech.beneathMod.init.RecipeInit;
import com.sngtech.beneathMod.recipes.DryingRecipe;

import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Hand;
import net.minecraftforge.items.ItemStackHandler;

public class DecayedPlanksDryingRackTileEntity extends TileEntity implements ITickable
{
	ItemStackHandler itemstack = new ItemStackHandler()
	{
		@Override
		protected void onContentsChanged(int slot) 
		{
			super.onContentsChanged(slot);
			markDirty();
		}
	};
	
	private int dryingTime;
	private int dryingTimeTotal;
	
	public DecayedPlanksDryingRackTileEntity(TileEntityType<?> tileEntityTypeIn) 
	{
		super(tileEntityTypeIn);
	}
	
	public DecayedPlanksDryingRackTileEntity() 
	{
		this(null);
	}

	@Override
	public void tick() 
	{
		
	}
	
	@Override
	public void read(CompoundNBT compound) 
	{
		super.read(compound);
		this.itemstack.deserializeNBT(compound.getCompound("inventory"));
		this.dryingTime = compound.getInt("DryingTime");
		this.dryingTimeTotal = compound.getInt("DryingTimeTotal");
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) 
	{
		compound.putString("inventory", itemstack.serializeNBT().toString());
		compound.putInt("DryingTime", this.dryingTime);
		compound.putInt("DryingTimeTotal", this.dryingTimeTotal);
		
		return compound;
	}
	
	@Override
	public CompoundNBT getUpdateTag() 
	{
		return this.write(new CompoundNBT());
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) 
	{
		this.read(pkt.getNbtCompound());
	}
	
	@Override
	public SUpdateTileEntityPacket getUpdatePacket() 
	{
		return new SUpdateTileEntityPacket(this.pos, 0, this.getUpdateTag());
	}
	
	public Optional<DryingRecipe> findMatchingRecipe(ItemStack itemStack) 
	{
		return this.itemstack.getStackInSlot(0) == null ? Optional.empty() : this.world.getRecipeManager().getRecipe(RecipeInit.DRYING, new Inventory(itemStack), this.world);
	}
	
	public void addItem(ItemStack stack, int dryingTime)
	{
		if(this.itemstack.getStackInSlot(0) != null)
		{
			Main.logger.debug("hm");
			this.itemstack.setStackInSlot(0, stack.split(1));
			this.dryingTimeTotal = dryingTime;
			this.dryingTime = 0;
		}
	}
	
	public void retrieveItem(ItemStack stack, PlayerEntity player, Hand hand)
	{
		if(this.itemstack.getStackInSlot(0) != null)
		{
			player.setHeldItem(hand, this.itemstack.getStackInSlot(0));
			this.itemstack.extractItem(0, 1, false);
			this.dryingTimeTotal = 0;
			this.dryingTime = 0;
		}
	}
}
