package com.sngtech.beneathMod.init;

import com.sngtech.beneathMod.Main;
import com.sngtech.beneathMod.entities.ColdCreeperEntity;
import com.sngtech.beneathMod.entities.renderer.ColdCreeperRenderer;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Main.MODID)
public class EntityInit 
{
	public static final EntityType<ColdCreeperEntity> COLD_CREEPER = setup("cold_creeper", EntityType.Builder.<ColdCreeperEntity>create(ColdCreeperEntity::new, EntityClassification.MONSTER).size(0.6F, 1.7F).size(0.6F, 1.7F));
	
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents
	{
		@SubscribeEvent
		public static void registerEntities(final RegistryEvent.Register<EntityType<?>> e) 
		{
			e.getRegistry().registerAll
			(
				COLD_CREEPER
			);
			
			Main.logger.debug("Entities Registered");
		}
	}
	
	public static void registerEntityRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(ColdCreeperEntity.class, ColdCreeperRenderer::new);
	}
	
	@SuppressWarnings("deprecation")
	private static <T extends Entity> EntityType<T> setup(String key, EntityType.Builder<T> builder) 
	{
		return Registry.register(Registry.ENTITY_TYPE, Main.MODID + ":" + key, builder.build(key));
	}
}
