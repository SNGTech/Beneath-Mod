package com.sngtech.beneathMod.world.biomes;

import com.sngtech.beneathMod.init.BlockInit;
import com.sngtech.beneathMod.world.gen.features.ModBiomeFeatures;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;

public class AMPlainsBiome extends Biome
{
	protected static final SurfaceBuilderConfig AM_PLAINS_SURFACE = new SurfaceBuilderConfig(BlockInit.CRACKED_ROCKS.getDefaultState(), Blocks.PODZOL.getDefaultState(), Blocks.CLAY.getDefaultState());
	
	@SuppressWarnings("static-access")
	public AMPlainsBiome() 
	{
		super((new Biome.Builder()).surfaceBuilder(new ConfiguredSurfaceBuilder<SurfaceBuilderConfig>(SurfaceBuilder.DEFAULT, AM_PLAINS_SURFACE)).precipitation(Biome.RainType.NONE).category(Biome.Category.DESERT).depth(0.125F).scale(0.05F).temperature(1000.8F).downfall(0.4F).waterColor(4159204).waterFogColor(329011).parent((String)null));
		this.addStructure(Feature.MINESHAFT, new MineshaftConfig(0.004D, MineshaftStructure.Type.NORMAL));
		DefaultBiomeFeatures.addCarvers(this);
	    DefaultBiomeFeatures.addStoneVariants(this);
	    DefaultBiomeFeatures.addOres(this);
	    ModBiomeFeatures.addDriedGrass(this);
	    ModBiomeFeatures.addLowDriedLakes(this);
	    this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
	    this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
	    new BiomeManager().addBiome(BiomeType.DESERT, new BiomeEntry(this, 10));
	}
	
	@Override
	public int getSkyColorByTemp(float currentTemperature) 
	{
		return 0xE0B041;
	}
}
