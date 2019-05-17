package cout.sngtech.beneathMod.containers.slots;

import cout.sngtech.beneathMod.blocks.tileentities.BlockCrate;
import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotCrate extends Slot
{
	public SlotCrate(IInventory inventoryIn, int index, int xPosition, int yPosition) 
	{
		super(inventoryIn, index, xPosition, yPosition);
	}

	public boolean isItemValid(ItemStack stack)
	{
		return !(Block.getBlockFromItem(stack.getItem()) instanceof BlockCrate);
	}
}
