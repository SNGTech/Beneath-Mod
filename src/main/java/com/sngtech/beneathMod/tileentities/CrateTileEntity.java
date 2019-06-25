package com.sngtech.beneathMod.tileentities;

import javax.annotation.Nullable;

import com.sngtech.beneathMod.containers.CrateContainer;
import com.sngtech.beneathMod.utils.ModItemStackHelper;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.INameable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class CrateTileEntity extends TileEntity implements INamedContainerProvider, INameable
{
	ItemStackHandler inventory = new ItemStackHandler(15)
	{
		@Override
		protected void onContentsChanged(int slot) 
		{
			super.onContentsChanged(slot);
			markDirty();
		}
	};
	
	private ITextComponent customName;
	protected String containerRegistryName;
	
	public CrateTileEntity(final TileEntityType<?> type) 
	{
		super(type);
	}
	
	public CrateTileEntity() 
	{
		this(null);
	}
	
	@Override
	public ITextComponent getName() 
	{
		ITextComponent itextcomponent = this.getCustomName();
		return (ITextComponent)(itextcomponent != null ? itextcomponent : new TranslationTextComponent(containerRegistryName));
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
    
    @Override
	public ITextComponent getDisplayName() {
		return this.getName();
	}
	
    public void setCustomName(@Nullable ITextComponent name) 
    {
    	this.customName = name;
    }
	
	@Override
	public void read(CompoundNBT compound) 
	{
		super.read(compound);
		this.inventory.deserializeNBT(compound.getCompound("inventory"));
		ModItemStackHelper.loadAllItems(compound, inventory);
		
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
		ModItemStackHelper.saveAllItems(compound, inventory);
		
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
		return new CrateContainer(windowId, playerInv, player, this);
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) 
	{
		if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return LazyOptional.of(() -> this.inventory).cast();
		return super.getCapability(cap, side);
	}
}
