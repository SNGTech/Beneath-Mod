package cout.sngtech.beneathMod.containers.slots;

import cout.sngtech.beneathMod.blocks.tileentities.BlockOakCrate;
import cout.sngtech.beneathMod.tileentities.TileEntityOakCrate;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotCrate extends SlotItemHandler
{
	public SlotCrate(ItemStackHandler inventory, int index, int xPosition, int yPosition) 
	{
		super(inventory, index, xPosition, yPosition);
	}

	public boolean isItemValid(ItemStack stack)
	{
		return !(Block.getBlockFromItem(stack.getItem()) instanceof BlockOakCrate);
	}
}
