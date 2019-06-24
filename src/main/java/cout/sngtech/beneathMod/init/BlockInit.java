package cout.sngtech.beneathMod.init;

import cout.sngtech.beneathMod.Main;
import cout.sngtech.beneathMod.blocks.tileentities.BlockAcaciaCrate;
import cout.sngtech.beneathMod.blocks.tileentities.BlockBirchCrate;
import cout.sngtech.beneathMod.blocks.tileentities.BlockDarkOakCrate;
import cout.sngtech.beneathMod.blocks.tileentities.BlockJungleCrate;
import cout.sngtech.beneathMod.blocks.tileentities.OakCrateBlock;
import cout.sngtech.beneathMod.blocks.tileentities.BlockSpruceCrate;
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
				//Decorations
				new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.7f).sound(SoundType.STONE)).setRegistryName(new ResourceLocation(Main.MODID, "carved_stone_bricks")),
				new RotatedPillarBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.9f).sound(SoundType.STONE)).setRegistryName(new ResourceLocation(Main.MODID, "line_chiseled_stone_bricks")),
					
				//Minerals
				new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f).sound(SoundType.STONE)).setRegistryName(new ResourceLocation(Main.MODID, "raw_limestone")),
				new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.9f).sound(SoundType.STONE)).setRegistryName(new ResourceLocation(Main.MODID, "copper_ore")),
				new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.5f).sound(SoundType.STONE)).setRegistryName(new ResourceLocation(Main.MODID, "bauxite_ore")),
				
				//Tile Entities
				new OakCrateBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.5f).sound(SoundType.WOOD)).setRegistryName(new ResourceLocation(Main.MODID, "oak_crate")),
				new BlockSpruceCrate(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.5f).sound(SoundType.WOOD)).setRegistryName(new ResourceLocation(Main.MODID, "spruce_crate")),
				new BlockBirchCrate(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.5f).sound(SoundType.WOOD)).setRegistryName(new ResourceLocation(Main.MODID, "birch_crate")),
				new BlockJungleCrate(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.5f).sound(SoundType.WOOD)).setRegistryName(new ResourceLocation(Main.MODID, "jungle_crate")),
				new BlockAcaciaCrate(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.5f).sound(SoundType.WOOD)).setRegistryName(new ResourceLocation(Main.MODID, "acacia_crate")),
				new BlockDarkOakCrate(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.5f).sound(SoundType.WOOD)).setRegistryName(new ResourceLocation(Main.MODID, "dark_oak_crate"))
			);
			
			Main.logger.debug("Registered Blocks");
		}
	}
}
