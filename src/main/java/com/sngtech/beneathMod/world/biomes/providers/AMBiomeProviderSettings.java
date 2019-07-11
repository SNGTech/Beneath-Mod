package com.sngtech.beneathMod.world.biomes.providers;

import com.sngtech.beneathMod.world.gen.AMGenSettings;
import com.sngtech.beneathMod.world.gen.AMWorldType;

import net.minecraft.world.biome.provider.IBiomeProviderSettings;
import net.minecraft.world.storage.WorldInfo;

public class AMBiomeProviderSettings implements IBiomeProviderSettings
{
	private WorldInfo worldInfo;
    private AMGenSettings genSettings;

    public AMBiomeProviderSettings setWorldInfo(WorldInfo worldInfo) 
    {
    	worldInfo.setGenerator(new AMWorldType());
        this.worldInfo = worldInfo;
        return this;
    }

    public AMBiomeProviderSettings setGeneratorSettings(AMGenSettings genSettings) 
    {
        this.genSettings = genSettings;
        return this;
    }

    public WorldInfo getWorldInfo() 
    {
    	worldInfo.setGenerator(new AMWorldType());
        return worldInfo;
    }

    public AMGenSettings getGeneratorSettings() 
    {
        return genSettings;
    }
}
