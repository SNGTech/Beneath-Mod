package com.sngtech.beneathMod.init;

import com.sngtech.beneathMod.Main;
import com.sngtech.beneathMod.capability.BlueFireTick;
import com.sngtech.beneathMod.capability.IBlueFireTick;
import com.sngtech.beneathMod.capability.storage.BlueFireTickStorage;

import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Main.MODID)
public class CapabilityHandler
{
	public static void registerCapabilities()
	{
		CapabilityManager.INSTANCE.register(IBlueFireTick.class, new BlueFireTickStorage(), BlueFireTick::new);
	}
}
