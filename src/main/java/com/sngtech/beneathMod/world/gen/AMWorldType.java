package com.sngtech.beneathMod.world.gen;

import java.util.function.LongFunction;

import com.sngtech.beneathMod.world.biomes.layers.AMBiomeLayer;
import com.sngtech.beneathMod.world.biomes.layers.AMLayerUtil;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.layer.EdgeBiomeLayer;
import net.minecraft.world.gen.layer.ZoomLayer;

public class AMWorldType extends WorldType
{
	public AMWorldType() 
	{
		super("am_type");
	}

	@Override
	public <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> getBiomeLayer(IAreaFactory<T> parentLayer, OverworldGenSettings chunkSettings, LongFunction<C> contextFactory) 
	{
        parentLayer = (new AMBiomeLayer(getWorldType(), (AMGenSettings) chunkSettings)).apply(contextFactory.apply(200L), parentLayer);
        parentLayer = AMLayerUtil.repeat(1000L, ZoomLayer.NORMAL, parentLayer, 2, contextFactory);
        parentLayer = EdgeBiomeLayer.INSTANCE.apply(contextFactory.apply(1000L), parentLayer);
        return parentLayer;
    }
}
