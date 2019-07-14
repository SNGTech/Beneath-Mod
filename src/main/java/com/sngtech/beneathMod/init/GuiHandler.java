package com.sngtech.beneathMod.init;

import com.sngtech.beneathMod.Main;
import com.sngtech.beneathMod.guis.CrateScreen;
import com.sngtech.beneathMod.guis.PlacerScreen;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Main.MODID)
public class GuiHandler
{
	public static void registerScreens()
	{
		ScreenManager.registerFactory(ContainerInit.CRATE, CrateScreen::new);
		ScreenManager.registerFactory(ContainerInit.PLACER, PlacerScreen::new);
	}
}
