package cout.sngtech.beneathMod.events;

import java.util.Collection;

import cout.sngtech.beneathMod.init.ItemInit;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Mod.EventBusSubscriber
public class EventHandler 
{
	@SubscribeEvent
	public static void OnBlockHarvestEvent(BlockEvent.HarvestDropsEvent e)
	{
		if(e.getState().getBlock() == Blocks.STONE)
		{
			e.getDrops().remove(0);
			e.getDrops().add(new ItemStack(ItemInit.rock));
		}
		
		else if(e.getState().getBlock() == Blocks.IRON_ORE)
		{
			e.getDrops().remove(0);
			e.getDrops().add(new ItemStack(ItemInit.iron_ore_rock));
		}
	}
}
