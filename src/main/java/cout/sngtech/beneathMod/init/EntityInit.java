package cout.sngtech.beneathMod.init;

import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class EntityInit 
{
	//public static final EntityType<?> dynamic_light_source = EntityType.Builder.createNothing(EntityDynamicLightSource.class).disableSerialization().disableSummoning().tracker(200, 1, true).build("dynamic_light_source").setRegistryName(Main.MODID, "dynamic_light_source");
	
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents
	{
		@SubscribeEvent
		public static void registerEntities(final RegistryEvent.Register<EntityType<?>> e) 
		{
			e.getRegistry().registerAll
			(
				//dynamic_light_source
			);
		}
	}
}
