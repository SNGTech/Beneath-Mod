package cout.sngtech.beneathMod.tileentities;

import javax.annotation.Nullable;

import cout.sngtech.beneathMod.containers.CrateContainer;
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

public class TileEntityCrate extends TileEntity implements INamedContainerProvider, INameable
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
	protected static TileEntityType<?> type;
	
	public TileEntityCrate() 
	{
		super(type);
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
		this.inventory.deserializeNBT(compound.getCompound("inventory"));
		
		if (compound.contains("CustomName", 8)) 
		{
			this.customName = ITextComponent.Serializer.fromJson(compound.getString("CustomName"));
	    }
		
		super.read(compound);
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) 
	{
		compound.putString("inventory", inventory.serializeNBT().toString());
		
		ITextComponent itextcomponent = this.getCustomName();
	    if (itextcomponent != null) 
	    {
	       compound.putString("CustomName", ITextComponent.Serializer.toJson(itextcomponent));
	    }
		
		return super.write(compound);
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
		return new CrateContainer(windowId, playerInv, player, this, this.getCustomName().toString());
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) 
	{
		if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return LazyOptional.of(() -> this.inventory).cast();
		return super.getCapability(cap, side);
	}
}
