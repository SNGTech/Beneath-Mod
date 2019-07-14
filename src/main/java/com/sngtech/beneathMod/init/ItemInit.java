package com.sngtech.beneathMod.init;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.sngtech.beneathMod.Main;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Main.MODID)
public class ItemInit 
{
	//Minerals
	public static final Item ROCK = null;
	public static final Item IRON_ORE_ROCK = null;
	public static final Item COPPER_ORE_ROCK = null;
	public static final Item BAUXITE_ORE_ROCK = null;
	
	//Spawn Eggs
	public static final Item COLD_CREEPER_SPAWN_EGG = null;
	
	//Item Blocks
	public static final Item CRACKED_ROCKS = null;
	public static final Item DECAYED_GRASS = null;
	
	public static final Item CARVED_STONE_BRICKS = null;
	public static final Item LINE_CHISELED_STONE_BRICKS = null;
	
	//public static final Item sandy_bricks = new ItemBlock(BlockInit.line_chiseled_stone_bricks, new Item.Properties().group(Main.beneath_blocks)).setRegistryName(new ResourceLocation(Main.MODID, "line_chiseled_stone_bricks"));
	
	public static final Item RAW_LIMESTONE = null;
	public static final Item COPPER_ORE = null;
	public static final Item BAUXITE_ORE = null;
	
	public static final Item TESSERACT_CHAMBER = null;
	
	public static final Item OAK_CRATE = null;
	public static final Item SPRUCE_CRATE = null;
	public static final Item BIRCH_CRATE = null;
	public static final Item JUNGLE_CRATE = null;
	public static final Item ACACIA_CRATE = null;
	public static final Item DARK_OAK_CRATE = null;
	public static final Item PLACER = null;
	
	public static final Item NUCLEAR_TNT = null;
	
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents
	{
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> e)
		{
			e.getRegistry().registerAll
			(
				//Minerals
				setup(new Item(new Item.Properties().group(Main.BENEATH_ITEMS)), "rock"),
				setup(new Item(new Item.Properties().group(Main.BENEATH_ITEMS)), "iron_ore_rock"),
				setup(new Item(new Item.Properties().group(Main.BENEATH_ITEMS)), "copper_ore_rock"),
				setup(new Item(new Item.Properties().group(Main.BENEATH_ITEMS)), "bauxite_ore_rock"),
				
				//Spawn Eggs
				setup(new SpawnEggItem(EntityInit.COLD_CREEPER, 6790088, 3295581, new Item.Properties().group(ItemGroup.MISC)), "cold_creeper_spawn_egg"),
				
				//Item Blocks
				setup(new BlockItem(BlockInit.CRACKED_ROCKS, new Item.Properties().group(Main.BENEATH_BLOCKS)), "cracked_rocks"),
				setup(new BlockItem(BlockInit.DECAYED_GRASS, new Item.Properties().group(Main.BENEATH_BLOCKS)), "decayed_grass"),
				
				setup(new BlockItem(BlockInit.CARVED_STONE_BRICKS, new Item.Properties().group(Main.BENEATH_BLOCKS)), "carved_stone_bricks"),
				setup(new BlockItem(BlockInit.LINE_CHISELED_STONE_BRICKS, new Item.Properties().group(Main.BENEATH_BLOCKS)), "line_chiseled_stone_bricks"),
				
				setup(new BlockItem(BlockInit.RAW_LIMESTONE, new Item.Properties().group(Main.BENEATH_BLOCKS)), "raw_limestone"),
				setup(new BlockItem(BlockInit.COPPER_ORE, new Item.Properties().group(Main.BENEATH_BLOCKS)), "copper_ore"),
				setup(new BlockItem(BlockInit.BAUXITE_ORE, new Item.Properties().group(Main.BENEATH_BLOCKS)), "bauxite_ore"),
				
				setup(new BlockItem(BlockInit.TESSERACT_CHAMBER, new Item.Properties().group(Main.BENEATH_BLOCKS)), "tesseract_chamber"),
				
				setup(new BlockItem(BlockInit.OAK_CRATE, new Item.Properties().group(Main.BENEATH_BLOCKS)), "oak_crate"),
				setup(new BlockItem(BlockInit.SPRUCE_CRATE, new Item.Properties().group(Main.BENEATH_BLOCKS)), "spruce_crate"),
				setup(new BlockItem(BlockInit.BIRCH_CRATE, new Item.Properties().group(Main.BENEATH_BLOCKS)), "birch_crate"),
				setup(new BlockItem(BlockInit.JUNGLE_CRATE, new Item.Properties().group(Main.BENEATH_BLOCKS)), "jungle_crate"),
				setup(new BlockItem(BlockInit.ACACIA_CRATE, new Item.Properties().group(Main.BENEATH_BLOCKS)), "acacia_crate"),
				setup(new BlockItem(BlockInit.DARK_OAK_CRATE, new Item.Properties().group(Main.BENEATH_BLOCKS)), "dark_oak_crate"),
				
				setup(new BlockItem(BlockInit.PLACER, new Item.Properties().group(Main.BENEATH_BLOCKS)), "placer"),
				
				setup(new BlockItem(BlockInit.NUCLEAR_TNT, new Item.Properties().group(Main.BENEATH_BLOCKS)), "nuclear_tnt")
			);
			
			Main.logger.debug("Registered Items");
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
}
