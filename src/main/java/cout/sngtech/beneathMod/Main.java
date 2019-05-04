package cout.sngtech.beneathMod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod("Beneath")
public class Main 
{
	Logger logger = LogManager.getLogger();
	
	public Main()
	{
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
}
