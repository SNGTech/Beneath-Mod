package cout.sngtech.beneathMod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	
	public Main()
	{
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onServerStartup);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	void commonSetup(FMLCommonSetupEvent e)
	{
		logger.info("Common Setup Event Registered");
	}
	
	void clientSetup(FMLClientSetupEvent e)
	{
		logger.info("Client Setup Event Registered");
	}
	
	void onServerStartup(FMLServerStartingEvent e)
	{
		logger.info("Server Starting...");
	}
}