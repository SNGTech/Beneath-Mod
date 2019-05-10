package cout.sngtech.beneathMod.itemgroups;

import cout.sngtech.beneathMod.init.ItemInit;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class BeneathItemsGroup extends ItemGroup 
{
	public BeneathItemsGroup() 
	{
		super("Beneath Items");
	}

	@Override
	public ItemStack createIcon() 
	{
		return new ItemStack(ItemInit.rock);
	}
}
