package cout.sngtech.beneathMod.containers;

import cout.sngtech.beneathMod.tileentities.TileEntityCrate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerCrate extends Container
{
	public ContainerCrate(InventoryPlayer playerInventory, TileEntityCrate inventory, EntityPlayer player) 
	{
		playerInventory.openInventory(player);
	
		for(int k = 0; k < 3; ++k) 
		{
			for(int l = 0; l < 5; ++l)  
			{
				this.addSlot(new SlotItemHandler(inventory.getInventory(), l + k * 5, 44 + l * 18, 18 + k * 18));
			}
		}

		for(int i = 0; i < 3; ++i) 
		{
			for(int k = 0; k < 9; ++k) 
			{
				this.addSlot(new Slot(playerInventory, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
			}
		}

		for(int j = 0; j < 9; ++j) 
		{
			this.addSlot(new Slot(playerInventory, j, 8 + j * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) 
	{
		return true;
	}
	
	@Override
	public ItemStack transferStackInSlot(final EntityPlayer player, final int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		final Slot slot = this.inventorySlots.get(index);
		if ((slot != null) && slot.getHasStack()) {
			final ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			final int containerSlots = this.inventorySlots.size() - player.inventory.mainInventory.size();
			if (index < containerSlots) {
				if (!mergeItemStack(itemstack1, containerSlots, this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!mergeItemStack(itemstack1, 0, containerSlots, false)) {
				return ItemStack.EMPTY;
			}
			if (itemstack1.getCount() == 0) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}
			slot.onTake(player, itemstack1);
		}
		return itemstack;
	}
}
