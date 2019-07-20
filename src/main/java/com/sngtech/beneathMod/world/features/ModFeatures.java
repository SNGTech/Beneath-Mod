package com.sngtech.beneathMod.world.features;

import com.sngtech.beneathMod.Main;
import com.sngtech.beneathMod.world.features.structures.TestStructure;
import com.sngtech.beneathMod.world.gen.features.ModOreFeature;
import com.sngtech.beneathMod.world.gen.features.ModOreFeatureConfig;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.GameData;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Main.MODID)
public class ModFeatures 
{
	public static final Feature<ModOreFeatureConfig> MOD_ORE = new ModOreFeature(ModOreFeatureConfig::deserialize);
	public static final Structure<NoFeatureConfig> TEST = new TestStructure(NoFeatureConfig::deserialize);
	
	@SubscribeEvent
    public static void registerFeatures(IForgeRegistry<Feature<?>> event) 
	{
		generic(event).add("mod_ore", MOD_ORE);
		generic(event).add("test", TEST);
	}
	
	public static <T extends IForgeRegistryEntry<T>> Generic<T> generic(IForgeRegistry<T> registry) 
	{
		return new Generic<>(registry);
	}
	
	public static class Generic<T extends IForgeRegistryEntry<T>> 
	{
		private final IForgeRegistry<T> registry;

		private Generic(IForgeRegistry<T> registry) 
		{
			this.registry = registry;
		}

		public Generic<T> add(String name, T entry) 
		{
			ResourceLocation registryName = GameData.checkPrefix(name, false);
			entry.setRegistryName(registryName);
			this.registry.register(entry);
			return this;
        }
    }
}
