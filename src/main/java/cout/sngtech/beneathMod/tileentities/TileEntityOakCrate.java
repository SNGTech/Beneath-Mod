package cout.sngtech.beneathMod.tileentities;

import cout.sngtech.beneathMod.containers.ContainerCrate;
import cout.sngtech.beneathMod.init.TileEntityInit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class TileEntityOakCrate extends TileEntityCrate
{
	public TileEntityOakCrate() 
	{
		type = TileEntityInit.oak_crate;
		containerRegistryName = "container.oak_crate";
	}
	
	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer player) 
	{
		return new ContainerCrate(playerInventory, this, player);
	}
}
