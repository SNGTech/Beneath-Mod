package cout.sngtech.beneathMod.tileentities;

import cout.sngtech.beneathMod.init.TileEntityInit;

public class TileEntityOakCrate extends TileEntityCrate
{
	public TileEntityOakCrate() 
	{
		type = TileEntityInit.OAK_CRATE;
		containerRegistryName = "container.oak_crate";
	}
}
