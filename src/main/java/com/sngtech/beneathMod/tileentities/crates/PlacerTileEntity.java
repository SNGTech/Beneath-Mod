package com.sngtech.beneathMod.tileentities.crates;

import java.util.Random;

import javax.annotation.Nullable;

import com.sngtech.beneathMod.containers.PlacerContainer;
import com.sngtech.beneathMod.init.TileEntityInit;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.INameable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class PlacerTileEntity extends TileEntity implements INamedContainerProvider, INameable
{
	private static final Random RNG = new Random();
	
	ItemStackHandler inventory = new ItemStackHandler(9)
	{
		@Override
		protected void onContentsChanged(int slot) 
		{
			super.onContentsChanged(slot);
			markDirty();
		}
	};
	
	private ITextComponent customName;
	
	public PlacerTileEntity() 
	{
		super(TileEntityInit.PLACER);
	}
	
	@Override
	public ITextComponent getName() 
	{
		ITextComponent itextcomponent = this.getCustomName();
		return (ITextComponent)(itextcomponent != null ? itextcomponent : new TranslationTextComponent("container.placer"));
	}

	@Nullable
    public ITextComponent getCustomName() 
    {
    	return this.customName;
    }
    
    @Override
	public ITextComponent getDisplayName() 
    {
		return this.getName();
	}
    
    public void setCustomName(@Nullable ITextComponent name) 
    {
    	this.customName = name;
    }
	
	public int getPlaceSlot() 
	{
		int i = -1;
		int j = 1;

		for(int k = 0; k < this.inventory.getSlots(); ++k) 
		{
			if (!this.inventory.getStackInSlot(k).isEmpty() && RNG.nextInt(j++) == 0) 
			{
				i = k;
			}
		}
		
		return i;
   }
	
	@Override
	public void read(CompoundNBT compound) 
	{
		super.read(compound);
		this.inventory.deserializeNBT(compound.getCompound("inventory"));
		
		if (compound.contains("CustomName", 8)) 
		{
			this.customName = ITextComponent.Serializer.fromJson(compound.getString("CustomName"));
	    }
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) 
	{
		super.write(compound);
		compound.putString("inventory", inventory.serializeNBT().toString());
		
		ITextComponent itextcomponent = this.getCustomName();
	    if (itextcomponent != null) 
	    {
	       compound.putString("CustomName", ITextComponent.Serializer.toJson(itextcomponent));
	    }
		
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
	
	public ItemStackHandler getInventory()
	{
		return this.inventory;
	}
	
	public int getInventorySize()
	{
		return this.inventory.getSlots();
	}

	@Override
	public Container createMenu(int windowId, PlayerInventory playerInv, PlayerEntity player) 
	{
		return new PlacerContainer(windowId, playerInv, player, this);
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) 
	{
		if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return LazyOptional.of(() -> this.inventory).cast();
		return super.getCapability(cap, side);
	}
}
