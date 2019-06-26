package com.sngtech.beneathMod.init;

import com.sngtech.beneathMod.Main;
import com.sngtech.beneathMod.blocks.DecayedGrassBlock;
import com.sngtech.beneathMod.blocks.tileentities.AcaciaCrateBlock;
import com.sngtech.beneathMod.blocks.tileentities.BirchCrateBlock;
import com.sngtech.beneathMod.blocks.tileentities.DarkOakCrateBlock;
import com.sngtech.beneathMod.blocks.tileentities.JungleCrateBlock;
import com.sngtech.beneathMod.blocks.tileentities.OakCrateBlock;
import com.sngtech.beneathMod.blocks.tileentities.SpruceCrateBlock;

import net.minecraft.block.Block;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Main.MODID)
public class BlockInit 
{
	//Terrain
	public static final Block DECAYED_GRASS = null;
	public static final Block CRACKED_ROCKS = null;
	
	//Decorations
	public static final Block CARVED_STONE_BRICKS = null;
	public static final Block LINE_CHISELED_STONE_BRICKS = null;
	
	//Minerals
	public static final Block RAW_LIMESTONE = null;
	public static final Block COPPER_ORE = null;
	public static final Block BAUXITE_ORE = null;
	
	//Tile Entities
	public static final Block OAK_CRATE = null;
	public static final Block SPRUCE_CRATE = null;
	public static final Block BIRCH_CRATE = null;
	public static final Block JUNGLE_CRATE = null;
	public static final Block ACACIA_CRATE = null;
	public static final Block DARK_OAK_CRATE = null;
	
	
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents
	{
		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> e)
		{
			e.getRegistry().registerAll
			(
				//Terrain
				new DecayedGrassBlock(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(0.0f).sound(SoundType.PLANT)).setRegistryName(new ResourceLocation(Main.MODID, "decayed_grass")),
				new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.4f).sound(SoundType.STONE)).setRegistryName(new ResourceLocation(Main.MODID, "cracked_rocks")),	
				
				//Decorations
				new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.7f).sound(SoundType.STONE)).setRegistryName(new ResourceLocation(Main.MODID, "carved_stone_bricks")),
				new RotatedPillarBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.9f).sound(SoundType.STONE)).setRegistryName(new ResourceLocation(Main.MODID, "line_chiseled_stone_bricks")),
					
				//Minerals
				new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f).sound(SoundType.STONE)).setRegistryName(new ResourceLocation(Main.MODID, "raw_limestone")),
				new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.9f).sound(SoundType.STONE)).setRegistryName(new ResourceLocation(Main.MODID, "copper_ore")),
				new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.5f).sound(SoundType.STONE)).setRegistryName(new ResourceLocation(Main.MODID, "bauxite_ore")),
				
				//Tile Entities
				new OakCrateBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.5f).sound(SoundType.WOOD)).setRegistryName(new ResourceLocation(Main.MODID, "oak_crate")),
				new SpruceCrateBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.5f).sound(SoundType.WOOD)).setRegistryName(new ResourceLocation(Main.MODID, "spruce_crate")),
				new BirchCrateBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.5f).sound(SoundType.WOOD)).setRegistryName(new ResourceLocation(Main.MODID, "birch_crate")),
				new JungleCrateBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.5f).sound(SoundType.WOOD)).setRegistryName(new ResourceLocation(Main.MODID, "jungle_crate")),
				new AcaciaCrateBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.5f).sound(SoundType.WOOD)).setRegistryName(new ResourceLocation(Main.MODID, "acacia_crate")),
				new DarkOakCrateBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.5f).sound(SoundType.WOOD)).setRegistryName(new ResourceLocation(Main.MODID, "dark_oak_crate"))
			);
			
			Main.logger.debug("Registered Blocks");
		}
	}
}
