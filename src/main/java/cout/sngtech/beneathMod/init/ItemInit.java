package cout.sngtech.beneathMod.init;

import cout.sngtech.beneathMod.Main;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Main.MODID)
public class ItemInit 
{
	//Minerals
	public static final Item ROCK = null;
	public static final Item IRON_ORE_ROCK = null;
	public static final Item COPPER_ORE_ROCK = null;
	public static final Item BAUXITE_ORE_ROCK = null;
	
	//Item Blocks
	public static final Item CARVED_STONE_BRICKS = null;
	public static final Item LINE_CHISELED_STONE_BRICKS = null;
	
	//public static final Item sandy_bricks = new ItemBlock(BlockInit.line_chiseled_stone_bricks, new Item.Properties().group(Main.beneath_blocks)).setRegistryName(new ResourceLocation(Main.MODID, "line_chiseled_stone_bricks"));
	
	public static final Item RAW_LIMESTONE = null;
	public static final Item COPPER_ORE = null;
	public static final Item BAUXITE_ORE = null;
	
	public static final Item OAK_CRATE = null;
	public static final Item SPRUCE_CRATE = null;
	public static final Item BIRCH_CRATE = null;
	public static final Item JUNGLE_CRATE = null;
	public static final Item ACACIA_CRATE = null;
	public static final Item DARK_OAK_CRATE = null;
	
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents
	{
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> e)
		{
			e.getRegistry().registerAll
			(
				//MineralsS
				new Item(new Item.Properties().group(Main.BENEATH_ITEMS)).setRegistryName(new ResourceLocation(Main.MODID, "rock")),
				new Item(new Item.Properties().group(Main.BENEATH_ITEMS)).setRegistryName(new ResourceLocation(Main.MODID, "iron_ore_rock")),
				new Item(new Item.Properties().group(Main.BENEATH_ITEMS)).setRegistryName(new ResourceLocation(Main.MODID, "copper_ore_rock")),
				new Item(new Item.Properties().group(Main.BENEATH_ITEMS)).setRegistryName(new ResourceLocation(Main.MODID, "bauxite_ore_rock")),
				
				//Item Blocks
				new ItemBlock(BlockInit.CARVED_STONE_BRICKS, new Item.Properties().group(Main.BENEATH_BLOCKS)).setRegistryName(new ResourceLocation(Main.MODID, "carved_stone_bricks")),
				new ItemBlock(BlockInit.LINE_CHISELED_STONE_BRICKS, new Item.Properties().group(Main.BENEATH_BLOCKS)).setRegistryName(new ResourceLocation(Main.MODID, "line_chiseled_stone_bricks")),
				
				new ItemBlock(BlockInit.RAW_LIMESTONE, new Item.Properties().group(Main.BENEATH_BLOCKS)).setRegistryName(new ResourceLocation(Main.MODID, "raw_limestone")),
				new ItemBlock(BlockInit.COPPER_ORE, new Item.Properties().group(Main.BENEATH_BLOCKS)).setRegistryName(new ResourceLocation(Main.MODID, "copper_ore")),
				new ItemBlock(BlockInit.BAUXITE_ORE, new Item.Properties().group(Main.BENEATH_BLOCKS)).setRegistryName(new ResourceLocation(Main.MODID, "bauxite_ore")),
				
				new ItemBlock(BlockInit.OAK_CRATE, new Item.Properties().group(Main.BENEATH_BLOCKS)).setRegistryName(new ResourceLocation(Main.MODID, "oak_crate")),
				new ItemBlock(BlockInit.SPRUCE_CRATE, new Item.Properties().group(Main.BENEATH_BLOCKS)).setRegistryName(new ResourceLocation(Main.MODID, "spruce_crate")),
				new ItemBlock(BlockInit.BIRCH_CRATE, new Item.Properties().group(Main.BENEATH_BLOCKS)).setRegistryName(new ResourceLocation(Main.MODID, "birch_crate")),
				new ItemBlock(BlockInit.JUNGLE_CRATE, new Item.Properties().group(Main.BENEATH_BLOCKS)).setRegistryName(new ResourceLocation(Main.MODID, "jungle_crate")),
				new ItemBlock(BlockInit.ACACIA_CRATE, new Item.Properties().group(Main.BENEATH_BLOCKS)).setRegistryName(new ResourceLocation(Main.MODID, "acacia_crate")),
				new ItemBlock(BlockInit.DARK_OAK_CRATE, new Item.Properties().group(Main.BENEATH_BLOCKS)).setRegistryName(new ResourceLocation(Main.MODID, "dark_oak_crate"))
			);
			
			Main.logger.debug("Registered Items");
		}
	}
}
