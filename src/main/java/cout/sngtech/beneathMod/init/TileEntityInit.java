package cout.sngtech.beneathMod.init;

import cout.sngtech.beneathMod.Main;
import cout.sngtech.beneathMod.tileentities.TileEntityOakCrate;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class TileEntityInit 
{
	//Storage
	public static final TileEntityType<?> oak_crate = TileEntityType.Builder.create(TileEntityOakCrate::new).build(null).setRegistryName(Main.MODID, "oak_crate");
	
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents
	{
		@SubscribeEvent
		public static void registerTileEntities(final RegistryEvent.Register<TileEntityType<?>> e)
		{
			e.getRegistry().registerAll
			(
				//Storage
				oak_crate
			);
			
			Main.logger.info("Registered Tile Entities");
		}
	}
}
