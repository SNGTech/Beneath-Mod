package com.sngtech.beneathMod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sngtech.beneathMod.init.GuiHandler;
import com.sngtech.beneathMod.itemgroups.BeneathBlocksGroup;
import com.sngtech.beneathMod.itemgroups.BeneathItemsGroup;
import com.sngtech.beneathMod.world.gen.features.OreGeneration;

import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Main.MODID)
public class Main 
{
	public static final String MODID = "beneath";
	public static Logger logger = LogManager.getLogger(MODID);
	
	public static final ItemGroup BENEATH_ITEMS = new BeneathItemsGroup();
	public static final ItemGroup BENEATH_BLOCKS = new BeneathBlocksGroup();
	public Main()
	{
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onServerStartup);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	void commonSetup(FMLCommonSetupEvent e)
	{
		OreGeneration.registerOreGeneration();
		logger.info("Common Setup Event Registered");
	}
	
	void clientSetup(FMLClientSetupEvent e)
	{
		GuiHandler.registerScreens();
		logger.info("Client Setup Event Registered");
	}
	
	void onServerStartup(FMLServerStartingEvent e)
	{
		logger.info("Server Starting...");
	}
}