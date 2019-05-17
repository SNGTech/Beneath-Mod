package cout.sngtech.beneathMod.init;

import cout.sngtech.beneathMod.Main;
import cout.sngtech.beneathMod.items.ItemMiningHelmet;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ItemInit 
{
	//Minerals
	public static final Item rock = new Item(new Item.Properties().group(Main.beneath_items)).setRegistryName(new ResourceLocation(Main.MODID, "rock"));
	public static final Item iron_ore_rock = new Item(new Item.Properties().group(Main.beneath_items)).setRegistryName(new ResourceLocation(Main.MODID, "iron_ore_rock"));
	public static final Item copper_ore_rock = new Item(new Item.Properties().group(Main.beneath_items)).setRegistryName(new ResourceLocation(Main.MODID, "copper_ore_rock"));
	public static final Item bauxite_ore_rock = new Item(new Item.Properties().group(Main.beneath_items)).setRegistryName(new ResourceLocation(Main.MODID, "bauxite_ore_rock"));
	
	//Wearables
	public static final Item mining_helmet = new ItemMiningHelmet(ArmorMaterialInit.mining_helmet, EntityEquipmentSlot.HEAD, new Item.Properties().group(Main.beneath_items)).setRegistryName(new ResourceLocation(Main.MODID, "mining_helmet"));
	
	//Item Blocks
	public static final Item carved_stone_bricks = new ItemBlock(BlockInit.carved_stone_bricks, new Item.Properties().group(Main.beneath_blocks)).setRegistryName(new ResourceLocation(Main.MODID, "carved_stone_bricks"));
	public static final Item line_chiseled_stone_bricks = new ItemBlock(BlockInit.line_chiseled_stone_bricks, new Item.Properties().group(Main.beneath_blocks)).setRegistryName(new ResourceLocation(Main.MODID, "line_chiseled_stone_bricks"));
	
	public static final Item raw_limestone = new ItemBlock(BlockInit.raw_limestone, new Item.Properties().group(Main.beneath_blocks)).setRegistryName(new ResourceLocation(Main.MODID, "raw_limestone"));
	public static final Item copper_ore = new ItemBlock(BlockInit.copper_ore, new Item.Properties().group(Main.beneath_blocks)).setRegistryName(new ResourceLocation(Main.MODID, "copper_ore"));
	public static final Item bauxite_ore = new ItemBlock(BlockInit.bauxite_ore, new Item.Properties().group(Main.beneath_blocks)).setRegistryName(new ResourceLocation(Main.MODID, "bauxite_ore"));
	
	public static final Item oak_crate = new ItemBlock(BlockInit.oak_crate, new Item.Properties().group(Main.beneath_blocks)).setRegistryName(new ResourceLocation(Main.MODID, "oak_crate"));
	
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents
	{
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> e)
		{
			e.getRegistry().registerAll
			(
				//MineralsS
				rock,
				iron_ore_rock,
				copper_ore_rock,
				bauxite_ore_rock,
				
				//Wearables
				mining_helmet,
				
				//Item Blocks
				carved_stone_bricks,
				line_chiseled_stone_bricks,
				
				raw_limestone,
				copper_ore,
				bauxite_ore,
				
				oak_crate
			);
			
			Main.logger.info("Registered Items");
		}
	}
}
