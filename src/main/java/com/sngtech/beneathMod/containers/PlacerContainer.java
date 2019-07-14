package com.sngtech.beneathMod.containers;

import com.sngtech.beneathMod.init.ContainerInit;
import com.sngtech.beneathMod.tileentities.PlacerTileEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class PlacerContainer extends Container 
{
	static PlacerTileEntity placer = new PlacerTileEntity();
	static PlayerEntity player;
	
	public PlacerContainer(int windowId, PlayerInventory playerInventory)
    {
        this(windowId, playerInventory, player, placer);
    }
	
	public PlacerContainer(int windowId, PlayerInventory playerInventory, PlayerEntity player, PlacerTileEntity inventory) 
	{
		super(ContainerInit.PLACER, windowId);
		playerInventory.openInventory(player);
	
		for(int k = 0; k < 3; ++k) 
		{
			for(int l = 0; l < 3; ++l)  
			{
				this.addSlot(new SlotItemHandler(inventory.getInventory(), l + k * 3, 62 + l * 18, 17 + k * 18));
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
	public boolean canInteractWith(PlayerEntity player) 
	{
		return true;
	}
	
	@Override
	public ItemStack transferStackInSlot(final PlayerEntity player, final int index) {
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

