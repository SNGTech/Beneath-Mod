package com.sngtech.beneathMod.init;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.sngtech.beneathMod.Main;
import com.sngtech.beneathMod.blocks.AMPortalBlock;
import com.sngtech.beneathMod.blocks.BlueFireBlock;
import com.sngtech.beneathMod.blocks.DecayedGrassBlock;
import com.sngtech.beneathMod.blocks.ModDoorBlock;
import com.sngtech.beneathMod.blocks.ModStairsBlock;
import com.sngtech.beneathMod.blocks.NuclearTNTBlock;
import com.sngtech.beneathMod.blocks.TesseractChamberBlock;
import com.sngtech.beneathMod.blocks.tileentities.PlacerBlock;
import com.sngtech.beneathMod.blocks.tileentities.crates.AcaciaCrateBlock;
import com.sngtech.beneathMod.blocks.tileentities.crates.BirchCrateBlock;
import com.sngtech.beneathMod.blocks.tileentities.crates.DarkOakCrateBlock;
import com.sngtech.beneathMod.blocks.tileentities.crates.JungleCrateBlock;
import com.sngtech.beneathMod.blocks.tileentities.crates.OakCrateBlock;
import com.sngtech.beneathMod.blocks.tileentities.crates.SpruceCrateBlock;
import com.sngtech.beneathMod.blocks.tileentities.dryingracks.DecayedPlanksDryingRackBlock;

import net.minecraft.block.Block;
import net.minecraft.block.LogBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Main.MODID)
public class BlockInit 
{
	//Terrain
	public static final Block DECAYED_GRASS = null;
	public static final Block CRACKED_ROCKS = null;
	
	//Logs
	public static final Block DECAYED_LOG = null;
	
	//Woods
	public static final Block DECAYED_WOOD = null;
	
	//Planks
	public static final Block DECAYED_PLANKS = null;
	
	//Slabs
	public static final Block DECAYED_PLANKS_SLAB = null;
	
	//Stairs
	public static final Block DECAYED_PLANKS_STAIRS = null;
	
	//Decorations
	public static final Block CARVED_STONE_BRICKS = null;
	public static final Block LINE_CHISELED_STONE_BRICKS = null;
	
	//Minerals
	public static final Block RAW_LIMESTONE = null;
	public static final Block COPPER_ORE = null;
	public static final Block BAUXITE_ORE = null;
	
	//Functional Blocks
	public static final Block TESSERACT_CHAMBER = null;
	
	//Tile Entities
	public static final Block OAK_CRATE = null;
	public static final Block SPRUCE_CRATE = null;
	public static final Block BIRCH_CRATE = null;
	public static final Block JUNGLE_CRATE = null;
	public static final Block ACACIA_CRATE = null;
	public static final Block DARK_OAK_CRATE = null;
	public static final Block DECAYED_PLANKS_DRYING_RACK = null;
	public static final Block PLACER = null;
	
	//Doors 
	public static final Block DECAYED_WOOD_DOOR = null;
	
	//Portal Blocks
	public static final Block AM_PORTAL = null;
	
	//TNT
	public static final Block NUCLEAR_TNT = null;
	
	//Fires
	public static final Block BLUE_FIRE = null;
	
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents
	{
		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> e)
		{
			e.getRegistry().registerAll
			(
				//Terrain
				setup(new DecayedGrassBlock(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(0.0f).sound(SoundType.PLANT)), "decayed_grass"),
				setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.4f).sound(SoundType.STONE)), "cracked_rocks"),	
				
				//Logs
				setup(new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0f).sound(SoundType.WOOD).harvestTool(ToolType.AXE)), "decayed_log"),
				
				//Wood
				setup(new RotatedPillarBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0f).sound(SoundType.WOOD).harvestTool(ToolType.AXE)), "decayed_wood"),
				
				//Planks
				setup(new Block(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0f).sound(SoundType.WOOD).harvestTool(ToolType.AXE)), "decayed_planks"),
				
				//Slabs
				setup(new SlabBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0f).sound(SoundType.WOOD).harvestTool(ToolType.AXE)), "decayed_planks_slab"),
				
				//Decorations
				setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.7f).sound(SoundType.STONE)), "carved_stone_bricks"),
				setup(new RotatedPillarBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.9f).sound(SoundType.STONE)), "line_chiseled_stone_bricks"),
					
				//Minerals
				setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f).sound(SoundType.STONE)), "raw_limestone"),
				setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.9f).sound(SoundType.STONE)), "copper_ore"),
				setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.5f).sound(SoundType.STONE)), "bauxite_ore"),
				
				//Functional Blocks
				setup(new TesseractChamberBlock(Block.Properties.create(Material.PORTAL).hardnessAndResistance(2.5f).sound(SoundType.GLASS)), "tesseract_chamber"),
				
				//Tile Entities
				setup(new OakCrateBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.5f).sound(SoundType.WOOD)), "oak_crate"),
				setup(new SpruceCrateBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.5f).sound(SoundType.WOOD)), "spruce_crate"),
				setup(new BirchCrateBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.5f).sound(SoundType.WOOD)), "birch_crate"),
				setup(new JungleCrateBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.5f).sound(SoundType.WOOD)), "jungle_crate"),
				setup(new AcaciaCrateBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.5f).sound(SoundType.WOOD)), "acacia_crate"),
				setup(new DarkOakCrateBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.5f).sound(SoundType.WOOD)), "dark_oak_crate"),
				
				setup(new DecayedPlanksDryingRackBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0f).sound(SoundType.WOOD)), "decayed_planks_drying_rack"),
				setup(new PlacerBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.5f).sound(SoundType.STONE)), "placer"),
				
				//Doors
				setup(new ModDoorBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0f).sound(SoundType.WOOD).harvestTool(ToolType.AXE)), "decayed_wood_door"),
				
				//Portal Blocks\
				setup(new AMPortalBlock(Block.Properties.create(Material.PORTAL).doesNotBlockMovement().tickRandomly().hardnessAndResistance(-1.0F).sound(SoundType.GLASS).lightValue(11)), "am_portal"),
			
				//TNT
				setup(new NuclearTNTBlock(Block.Properties.create(Material.TNT).hardnessAndResistance(0.0f).sound(SoundType.PLANT).lightValue(13)), "nuclear_tnt"),
				
				//Fires
				setup(new BlueFireBlock(Block.Properties.create(Material.FIRE).hardnessAndResistance(0.0f).tickRandomly().sound(SoundType.CLOTH).lightValue(16)), "blue_fire")
			);
			
			Main.logger.debug("Registered Blocks");
		}
		
		@SubscribeEvent
		public static void registerPostBlocks(final RegistryEvent.Register<Block> e)
		{
			Block block0 = new Block((Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0f).sound(SoundType.WOOD).harvestTool(ToolType.AXE))).setRegistryName("decayed_planks");
			
			e.getRegistry().register
			(
				setup(new ModStairsBlock(block0.getDefaultState(), Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0f).sound(SoundType.WOOD).harvestTool(ToolType.AXE)), "decayed_planks_stairs")
			);
			
			Main.logger.debug("Registered PostBlocks");
		}
	}
	
	@Nonnull
	private static <T extends IForgeRegistryEntry<T>> T setup(@Nonnull final T entry, @Nonnull final String name) 
	{
		Preconditions.checkNotNull(name, "Name to assign to entry cannot be null!");
		return setup(entry, new ResourceLocation(Main.MODID, name));
	}

	@Nonnull
	private static <T extends IForgeRegistryEntry<T>> T setup(@Nonnull final T entry, @Nonnull final ResourceLocation registryName) 
	{
		Preconditions.checkNotNull(entry, "Entry cannot be null!");
		Preconditions.checkNotNull(registryName, "Registry name to assign to entry cannot be null!");
		entry.setRegistryName(registryName);
		return entry;
	}
}
