package com.sngtech.beneathMod.tileentities.dryingracks;

import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
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
		if(itemstack.getStackInSlot(0) != null)
		{
			dryingTime = 1000;
			dryingTimeTotal = dryingTime;
			
			if(dryingTime < 0)
			{
				dryingTime = 0;
				dryingTimeTotal = dryingTime;
			}
			else
				dryingTime--;
		}
		else
		{
			dryingTime = 0;
			dryingTimeTotal = dryingTime;
		}
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
	
	public void addItem(ItemStack stack)
	{
		if(this.itemstack.getStackInSlot(0) != null)
		{
			this.itemstack.extractItem(0, 1, false);
		}

		this.itemstack.setStackInSlot(0, stack.split(1));
	}
}
