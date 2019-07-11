package com.sngtech.beneathMod.world.biomes.layers;

import java.util.function.LongFunction;

import com.google.common.collect.ImmutableList;
import com.sngtech.beneathMod.world.gen.AMGenSettings;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.LazyAreaLayerContext;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.layer.DeepOceanLayer;
import net.minecraft.world.gen.layer.EdgeLayer;
import net.minecraft.world.gen.layer.IslandLayer;
import net.minecraft.world.gen.layer.Layer;
import net.minecraft.world.gen.layer.MixRiverLayer;
import net.minecraft.world.gen.layer.RareBiomeLayer;
import net.minecraft.world.gen.layer.RemoveTooMuchOceanLayer;
import net.minecraft.world.gen.layer.RiverLayer;
import net.minecraft.world.gen.layer.SmoothLayer;
import net.minecraft.world.gen.layer.StartRiverLayer;
import net.minecraft.world.gen.layer.VoroniZoomLayer;
import net.minecraft.world.gen.layer.ZoomLayer;
import net.minecraft.world.gen.layer.traits.IAreaTransformer1;

@SuppressWarnings("deprecation")
public class AMLayerUtil
{
	protected static final int WARM_OCEAN = Registry.BIOME.getId(Biomes.WARM_OCEAN);
	protected static final int LUKEWARM_OCEAN = Registry.BIOME.getId(Biomes.LUKEWARM_OCEAN);
	protected static final int OCEAN = Registry.BIOME.getId(Biomes.OCEAN);
	protected static final int COLD_OCEAN = Registry.BIOME.getId(Biomes.COLD_OCEAN);
	protected static final int FROZEN_OCEAN = Registry.BIOME.getId(Biomes.FROZEN_OCEAN);
	protected static final int DEEP_WARM_OCEAN = Registry.BIOME.getId(Biomes.DEEP_WARM_OCEAN);
	protected static final int DEEP_LUKEWARM_OCEAN = Registry.BIOME.getId(Biomes.DEEP_LUKEWARM_OCEAN);
	protected static final int DEEP_OCEAN = Registry.BIOME.getId(Biomes.DEEP_OCEAN);
	protected static final int DEEP_COLD_OCEAN = Registry.BIOME.getId(Biomes.DEEP_COLD_OCEAN);
	protected static final int DEEP_FROZEN_OCEAN = Registry.BIOME.getId(Biomes.DEEP_FROZEN_OCEAN);

	public static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> repeat(long seed, IAreaTransformer1 parent, IAreaFactory<T> p_202829_3_, int count, LongFunction<C> contextFactory) 
	{
		IAreaFactory<T> iareafactory = p_202829_3_;
	
		for(int i = 0; i < count; ++i) 
		{
			iareafactory = parent.apply(contextFactory.apply(seed + (long)i), iareafactory);
		}

		return iareafactory;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T extends IArea, C extends IExtendedNoiseRandom<T>> ImmutableList<IAreaFactory<T>> buildOverworldProcedure(WorldType worldTypeIn, AMGenSettings settings, LongFunction<C> contextFactory) 
	{
		IAreaFactory<T> iareafactory = IslandLayer.INSTANCE.apply(contextFactory.apply(1L));
		iareafactory = ZoomLayer.FUZZY.apply(contextFactory.apply(2000L), iareafactory);
		iareafactory = ZoomLayer.NORMAL.apply(contextFactory.apply(2001L), iareafactory);
		iareafactory = RemoveTooMuchOceanLayer.INSTANCE.apply(contextFactory.apply(2L), iareafactory);
		iareafactory = ZoomLayer.NORMAL.apply(contextFactory.apply(2002L), iareafactory);
		iareafactory = DeepOceanLayer.INSTANCE.apply(contextFactory.apply(4L), iareafactory);
		iareafactory = EdgeLayer.HeatIce.INSTANCE.apply(contextFactory.apply(2L), iareafactory);
		iareafactory = repeat(1000L, ZoomLayer.NORMAL, iareafactory, 0, contextFactory);

		int i = 8;
		int j = i;
		if (settings != null) 
		{
			j = settings.getRiverSize();
		}

		i = 3;

		IAreaFactory<T> lvt_7_1_ = repeat(1000L, ZoomLayer.NORMAL, iareafactory, 0, contextFactory);
		lvt_7_1_ = StartRiverLayer.INSTANCE.apply((IExtendedNoiseRandom)contextFactory.apply(100L), lvt_7_1_);
		IAreaFactory<T> lvt_8_1_ = worldTypeIn.getBiomeLayer(iareafactory, settings, contextFactory);
		lvt_7_1_ = repeat(1000L, ZoomLayer.NORMAL, lvt_7_1_, 2, contextFactory);
		lvt_7_1_ = repeat(1000L, ZoomLayer.NORMAL, lvt_7_1_, j, contextFactory);
		lvt_7_1_ = RiverLayer.INSTANCE.apply((IExtendedNoiseRandom)contextFactory.apply(1L), lvt_7_1_);
		lvt_7_1_ = SmoothLayer.INSTANCE.apply((IExtendedNoiseRandom)contextFactory.apply(1000L), lvt_7_1_);
		lvt_8_1_ = RareBiomeLayer.INSTANCE.apply((IExtendedNoiseRandom)contextFactory.apply(1000L), lvt_8_1_);
		for(int k = 0; k < i; ++k) 
		{
			lvt_8_1_ = ZoomLayer.NORMAL.apply((IExtendedNoiseRandom)contextFactory.apply((long)(1000 + k)), lvt_8_1_);

			if (k == 1 || i == 1) 
			{
				lvt_8_1_ = ZoomLayer.NORMAL.apply((IExtendedNoiseRandom)contextFactory.apply((long)(1000L)), lvt_8_1_);
			}
		}

		lvt_8_1_ = SmoothLayer.INSTANCE.apply((IExtendedNoiseRandom)contextFactory.apply(1000L), lvt_8_1_);
		lvt_8_1_ = MixRiverLayer.INSTANCE.apply((IExtendedNoiseRandom)contextFactory.apply(100L), lvt_8_1_, lvt_7_1_);
		IAreaFactory<T> iareafactory5 = VoroniZoomLayer.INSTANCE.apply(contextFactory.apply(10L), lvt_8_1_);
		return ImmutableList.of(lvt_8_1_, iareafactory5, lvt_8_1_);
	}

	public static Layer[] buildOverworldProcedure(long seed, WorldType typeIn, AMGenSettings settings) 
	{
		int[] aint = new int[1];
		ImmutableList<IAreaFactory<LazyArea>> immutablelist = buildOverworldProcedure(typeIn, settings, (p_202825_3_) -> 
		{
			++aint[0];
			return new LazyAreaLayerContext(1, aint[0], seed);
		});
		
		Layer genlayer = new Layer(immutablelist.get(0));
		Layer genlayer1 = new Layer(immutablelist.get(1));
		Layer genlayer2 = new Layer(immutablelist.get(2));
		return new Layer[]{genlayer, genlayer1, genlayer2};
	}

	public static boolean isOcean(int biomeIn) 
	{
		return biomeIn == WARM_OCEAN || biomeIn == LUKEWARM_OCEAN || biomeIn == OCEAN || biomeIn == COLD_OCEAN || biomeIn == FROZEN_OCEAN || biomeIn == DEEP_WARM_OCEAN || biomeIn == DEEP_LUKEWARM_OCEAN || biomeIn == DEEP_OCEAN || biomeIn == DEEP_COLD_OCEAN || biomeIn == DEEP_FROZEN_OCEAN;
	}
}
