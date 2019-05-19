package cout.sngtech.beneathMod.tileentities;

import javax.annotation.Nullable;

import cout.sngtech.beneathMod.Main;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IInteractionObject;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityCrate extends TileEntity implements IInteractionObject
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
	    return (ITextComponent)(itextcomponent != null ? itextcomponent : new TextComponentTranslation("container.oak_crate"));
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
	
	@Override
	public void read(NBTTagCompound compound) 
	{
		this.inventory.deserializeNBT(compound.getCompound("inventory"));
		
		if (compound.contains("CustomName", 8)) 
		{
			this.customName = ITextComponent.Serializer.fromJson(compound.getString("CustomName"));
	    }
		
		super.read(compound);
	}
	
	@Override
	public NBTTagCompound write(NBTTagCompound compound) 
	{
		compound.setTag("inventory", inventory.serializeNBT());
		
		if (this.customName != null) 
		{
	     	compound.setString("CustomName", ITextComponent.Serializer.toJson(this.customName));
	  	}
		
		return super.write(compound);
	}
	
	@Override
	public NBTTagCompound getUpdateTag() 
	{
		return this.write(new NBTTagCompound());
	}
	
    @Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) 
    {
		this.read(pkt.getNbtCompound());
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() 
	{
		return new SPacketUpdateTileEntity(this.pos, 0, this.getUpdateTag());
	} 
	
	public ItemStackHandler getInventory()
	{
		return this.inventory;
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer player) 
	{
		return null;
	}

	@Override
	public String getGuiID() 
	{
		return Main.MODID + ":crate";
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, EnumFacing side) 
	{
		if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return LazyOptional.of(() -> this.inventory).cast();
		return super.getCapability(cap, side);
	}
}
