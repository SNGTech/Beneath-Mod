package cout.sngtech.beneathMod.itemgroups;

import cout.sngtech.beneathMod.init.BlockInit;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class BeneathBlocksGroup extends ItemGroup
{
	public BeneathBlocksGroup() 
	{
		super("Beneath Blocks");
	}

	@Override
	public ItemStack createIcon() 
	{
		return new ItemStack(Item.BLOCK_TO_ITEM.get(BlockInit.COPPER_ORE));
	}
}
