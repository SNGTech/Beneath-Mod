package com.sngtech.beneathMod.world.features.structures;

import java.util.function.Function;

import com.mojang.datafixers.Dynamic;
import com.sngtech.beneathMod.world.features.ModFeatures;

import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.ScatteredStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class TestStructure extends ScatteredStructure<NoFeatureConfig>
{
	public TestStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i51449_1_) 
	{
		super(p_i51449_1_);
	}

	@Override
	protected int getSeedModifier() 
	{
		return 15630482;
	}

	@Override
	public IStartFactory getStartFactory() 
	{
		return TestStructure.Start::new;
	}

	@Override
	public String getStructureName() 
	{
		return "Test Structure";
	}

	@Override
	public int getSize() 
	{
		return 3;
	}

	public static class Start extends StructureStart 
	{
		public Start(Structure<?> p_i50460_1_, int p_i50460_2_, int p_i50460_3_, Biome p_i50460_4_, MutableBoundingBox p_i50460_5_, int p_i50460_6_, long p_i50460_7_) 
		{
			super(p_i50460_1_, p_i50460_2_, p_i50460_3_, p_i50460_4_, p_i50460_5_, p_i50460_6_, p_i50460_7_);
		}

		public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn) 
		{
			NoFeatureConfig nofeatureconfig = (NoFeatureConfig)generator.getStructureConfig(biomeIn, ModFeatures.TEST);
			Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
			BlockPos blockpos = new BlockPos(chunkX * 16, 90, chunkZ * 16);
			TestPieces.func_207617_a(templateManagerIn, blockpos, rotation, this.components, this.rand, nofeatureconfig);
			this.recalculateStructureSize();
		}
	}
}
