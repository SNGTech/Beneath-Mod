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
	public static final Item rock = null;
	public static final Item iron_ore_rock = null;
	public static final Item copper_ore_rock = null;
	public static final Item bauxite_ore_rock = null;
	
	//Item Blocks
	public static final Item carved_stone_bricks = null;
	public static final Item line_chiseled_stone_bricks = null;
	
	//public static final Item sandy_bricks = new ItemBlock(BlockInit.line_chiseled_stone_bricks, new Item.Properties().group(Main.beneath_blocks)).setRegistryName(new ResourceLocation(Main.MODID, "line_chiseled_stone_bricks"));
	
	public static final Item raw_limestone = null;
	public static final Item copper_ore = null;
	public static final Item bauxite_ore = null;
	
	public static final Item oak_crate = null;
	
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents
	{
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> e)
		{
			e.getRegistry().registerAll
			(
				//MineralsS
				new Item(new Item.Properties().group(Main.beneath_items)).setRegistryName(new ResourceLocation(Main.MODID, "rock")),
				new Item(new Item.Properties().group(Main.beneath_items)).setRegistryName(new ResourceLocation(Main.MODID, "iron_ore_rock")),
				new Item(new Item.Properties().group(Main.beneath_items)).setRegistryName(new ResourceLocation(Main.MODID, "copper_ore_rock")),
				new Item(new Item.Properties().group(Main.beneath_items)).setRegistryName(new ResourceLocation(Main.MODID, "bauxite_ore_rock")),
				
				//Item Blocks
				new ItemBlock(BlockInit.carved_stone_bricks, new Item.Properties().group(Main.beneath_blocks)).setRegistryName(new ResourceLocation(Main.MODID, "carved_stone_bricks")),
				new ItemBlock(BlockInit.line_chiseled_stone_bricks, new Item.Properties().group(Main.beneath_blocks)).setRegistryName(new ResourceLocation(Main.MODID, "line_chiseled_stone_bricks")),
				
				new ItemBlock(BlockInit.raw_limestone, new Item.Properties().group(Main.beneath_blocks)).setRegistryName(new ResourceLocation(Main.MODID, "raw_limestone")),
				new ItemBlock(BlockInit.copper_ore, new Item.Properties().group(Main.beneath_blocks)).setRegistryName(new ResourceLocation(Main.MODID, "copper_ore")),
				new ItemBlock(BlockInit.bauxite_ore, new Item.Properties().group(Main.beneath_blocks)).setRegistryName(new ResourceLocation(Main.MODID, "bauxite_ore")),
				
				new ItemBlock(BlockInit.oak_crate, new Item.Properties().group(Main.beneath_blocks)).setRegistryName(new ResourceLocation(Main.MODID, "oak_crate"))
			);
			
			Main.logger.debug("Registered Items");
		}
	}
}
