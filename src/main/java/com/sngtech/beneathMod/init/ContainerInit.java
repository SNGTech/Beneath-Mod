package com.sngtech.beneathMod.init;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.sngtech.beneathMod.Main;
import com.sngtech.beneathMod.containers.CrateContainer;

import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.ObjectHolder;

@SuppressWarnings("rawtypes")
@ObjectHolder(Main.MODID)
public class ContainerInit<T extends Container> extends ContainerType
{
	@SuppressWarnings("unchecked")
	public ContainerInit(IFactory<T> factory) 
	{
		super(factory);
	}

	@SuppressWarnings("unchecked")
	public static final ContainerType<CrateContainer> CRATE = new ContainerInit<CrateContainer>(CrateContainer::new);
	
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents
	{
		@SubscribeEvent
		public static void registerContainers(final RegistryEvent.Register<ContainerType<?>> e)
		{
			e.getRegistry().registerAll
			(
				setup(ContainerInit.CRATE, "crate")
			);
			
			Main.logger.debug("Registered Containers");
		}
	}
	
	@Nonnull
    private static <T extends IForgeRegistryEntry<T>> T setup(@Nonnull final T entry, @Nonnull final String name) 
	{
        Preconditions.checkNotNull(name, "Name assigned to entry cannot be null!");
        return setup(entry, new ResourceLocation(Main.MODID, name));
    }

    @Nonnull
    private static <T extends IForgeRegistryEntry<T>> T setup(@Nonnull final T entry, @Nonnull final ResourceLocation registryName) 
    {
        Preconditions.checkNotNull(entry, "Entry cannot be null!");
        Preconditions.checkNotNull(registryName, "Registry Name cannot be null!");
        entry.setRegistryName(registryName);
        return entry;
    }
}
