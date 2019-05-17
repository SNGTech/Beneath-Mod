package cout.sngtech.beneathMod.containers;

import cout.sngtech.beneathMod.containers.slots.SlotCrate;
import cout.sngtech.beneathMod.tileentities.TileEntityCrate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerCrate extends Container
{
	private final TileEntityCrate inventory;
	
	public ContainerCrate(InventoryPlayer playerInventory, TileEntityCrate inventory, EntityPlayer player) 
	{
		this.inventory = inventory;
	      inventory.openInventory(player);
	
	      for(int k = 0; k < 3; ++k) 
	      {
	         for(int l = 0; l < 5; ++l) 
	         {
	            this.addSlot(new SlotCrate(inventory, l + k * 9, 8 + l * 18, 18 + k * 18));
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
		return this.inventory.isUsableByPlayer(player);
	}
	
	public ItemStack transferStackInSlot(EntityPlayer player, int index) 
	{
	      ItemStack itemstack = ItemStack.EMPTY;
	      Slot slot = this.inventorySlots.get(index);
	      if (slot != null && slot.getHasStack()) 
	      {
	         ItemStack itemstack1 = slot.getStack();
	         itemstack = itemstack1.copy();
	         if (index < this.inventory.getSizeInventory()) 
	         {
	            if (!this.mergeItemStack(itemstack1, this.inventory.getSizeInventory(), this.inventorySlots.size(), true)) 
	            {
	               return ItemStack.EMPTY;
	            }
	         } 
	         else if (!this.mergeItemStack(itemstack1, 0, this.inventory.getSizeInventory(), false)) 
	         {
	            return ItemStack.EMPTY;
	         }
	
	         if (itemstack1.isEmpty()) 
	         {
	            slot.putStack(ItemStack.EMPTY);
	         } 
	         else 
	         {
	            slot.onSlotChanged();
	         }
	      }
	
	      return itemstack;
	   }
	
	public void onContainerClosed(EntityPlayer player) 
	{
		super.onContainerClosed(player);
		this.inventory.closeInventory(player);
	}
}
