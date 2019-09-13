package com.sngtech.beneathMod.init;

import net.minecraft.item.Food;

public class FoodInit 
{
	public static final Food DRIED_FLESH = (new Food.Builder()).hunger(2).saturation(0.1F).meat().build();
}
