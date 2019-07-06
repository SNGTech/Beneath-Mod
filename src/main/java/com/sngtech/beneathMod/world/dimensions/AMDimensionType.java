package com.sngtech.beneathMod.world.dimensions;

import java.util.function.BiFunction;

import com.sngtech.beneathMod.Main;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;

public class AMDimensionType extends ModDimension
{
	public AMDimensionType(final ResourceLocation registryName) 
	{
		this.setRegistryName(registryName);
	}

	public static DimensionType getDimensionType() 
	{
		return DimensionType.byName(new ResourceLocation(Main.MODID, "am_dimension"));
	}
	
	@Override
	public BiFunction<World, DimensionType, ? extends Dimension> getFactory() 
	{
		return AMDimension::new;
	}
}
