package cout.sngtech.beneathMod.init;

import cout.sngtech.beneathMod.Main;
import cout.sngtech.beneathMod.blocks.BlockBauxiteOre;
import cout.sngtech.beneathMod.blocks.BlockCopperOre;
import cout.sngtech.beneathMod.blocks.tileentities.BlockOakCrate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
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
	public static final Block carved_stone_bricks = null;
	public static final Block line_chiseled_stone_bricks = null;
	
	//Minerals
	public static final Block raw_limestone = null;
	public static final Block copper_ore = null;
	public static final Block bauxite_ore = null;
	
	//Tile Entities
	public static final Block oak_crate = null;
	
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
				new BlockRotatedPillar(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.9f).sound(SoundType.STONE)).setRegistryName(new ResourceLocation(Main.MODID, "line_chiseled_stone_bricks")),
					
				//Minerals
				new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f).sound(SoundType.STONE)).setRegistryName(new ResourceLocation(Main.MODID, "raw_limestone")),
				new BlockCopperOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.9f).sound(SoundType.STONE)).setRegistryName(new ResourceLocation(Main.MODID, "copper_ore")),
				new BlockBauxiteOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.5f).sound(SoundType.STONE)).setRegistryName(new ResourceLocation(Main.MODID, "bauxite_ore")),
				
				//Tile Entities
				new BlockOakCrate(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.5f).sound(SoundType.WOOD)).setRegistryName(new ResourceLocation(Main.MODID, "oak_crate"))
			);
			
			Main.logger.debug("Registered Blocks");
		}
	}
}
