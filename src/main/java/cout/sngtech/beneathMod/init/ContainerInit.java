package cout.sngtech.beneathMod.init;

import cout.sngtech.beneathMod.Main;
import cout.sngtech.beneathMod.containers.CrateContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Main.MODID)
public class ContainerInit
{
	public static final ContainerType<CrateContainer> CRATE = null;
	
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents
	{
		@SubscribeEvent
		public static void registerContainers(final RegistryEvent.Register<ContainerType<?>> e)
		{
			e.getRegistry().registerAll
			(
				IForgeContainerType.create(CrateContainer::new).setRegistryName("crate")
			);
			
			Main.logger.debug("Registered Containers");
		}
	}
}
