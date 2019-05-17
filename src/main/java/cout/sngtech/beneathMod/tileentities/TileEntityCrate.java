package cout.sngtech.beneathMod.tileentities;

import cout.sngtech.beneathMod.Main;
import cout.sngtech.beneathMod.containers.ContainerCrate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IInteractionObject;

public class TileEntityCrate extends TileEntityLockableLoot implements IInteractionObject
{
	private NonNullList<ItemStack> contents = NonNullList.withSize(15, ItemStack.EMPTY);
	protected String woodType;
	
	protected TileEntityCrate(TileEntityType<?> type) 
	{
		super(type);
	}

	@Override
	public ITextComponent getName() 
	{
		ITextComponent itextcomponent = this.getCustomName();
	    return (ITextComponent)(itextcomponent != null ? itextcomponent : new TextComponentTranslation("container." + getWoodType() + ".crate"));
	}
	
	@Override
    public void read(NBTTagCompound compound)
    {
        super.read(compound);

        this.contents = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);

        if (!this.checkLootAndRead(compound))
        {
            ItemStackHelper.loadAllItems(compound, this.contents);
        }

        if (compound.contains("CustomName", 8))
        {
            this.customName = ITextComponent.Serializer.fromJson(compound.getString("CustomName"));
        }
    }

    @Override
    public NBTTagCompound write(NBTTagCompound compound)
    {
        super.write(compound);
        if (!this.checkLootAndWrite(compound))
        {
            ItemStackHelper.saveAllItems(compound, this.contents);
        }

        ITextComponent itextcomponent = this.getCustomName();
        if (itextcomponent != null)
        {
            compound.setString("CustomName", ITextComponent.Serializer.toJson(itextcomponent));
        }

        return compound;
    }

	@Override
	public int getSizeInventory() 
	{
		return this.contents.size();
	}

	@Override
	public boolean isEmpty() 
	{
		for(ItemStack itemstack : this.contents) 
		{
			if (!itemstack.isEmpty()) 
	      	{
				return false;
	      	}
	    }

		return true;
	}

	@Override
	public int getInventoryStackLimit() 
	{
		return 64;
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer player) 
	{
		return new ContainerCrate(playerInventory, this, player);
	}

	@Override
	public String getGuiID() 
	{
		return Main.MODID + ":crate";
	}

	@Override
	protected NonNullList<ItemStack> getItems() 
	{
		return this.contents;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> items) 
	{
		this.contents = items;
	}
	
	public String getWoodType() 
	{
		return woodType;
	}

	public void setWoodType(String woodType) 
	{
		this.woodType = woodType;
	}
}
