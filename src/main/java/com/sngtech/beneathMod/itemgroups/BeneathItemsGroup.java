package com.sngtech.beneathMod.itemgroups;

import com.sngtech.beneathMod.init.ItemInit;

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
		return new ItemStack(ItemInit.ROCK);
	}
}
