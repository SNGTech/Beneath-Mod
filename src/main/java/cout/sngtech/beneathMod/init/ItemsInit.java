package cout.sngtech.beneathMod.init;

import cout.sngtech.beneathMod.Main;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ItemsInit 
{
	//Minerals
	public static Item rock;
	public static Item iron_ore_rock;
	public static Item copper_ore_rock;
	public static Item bauxite_ore_rock;
	
	
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents
	{
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> e)
		{
			e.getRegistry().registerAll
			(
				//Minerals
				rock = new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(new ResourceLocation(Main.MODID, "rock")),
				iron_ore_rock = new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(new ResourceLocation(Main.MODID, "iron_ore_rock")),
				copper_ore_rock = new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(new ResourceLocation(Main.MODID, "copper_ore_rock")),
				bauxite_ore_rock = new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(new ResourceLocation(Main.MODID, "bauxite_ore_rock"))
			);
			
			Main.logger.info("Registered Items");
		}
	}
}
