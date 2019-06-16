package cout.sngtech.beneathMod.tileentities;

import cout.sngtech.beneathMod.init.TileEntityInit;

public class TileEntityJungleCrate extends TileEntityCrate
{
	public TileEntityJungleCrate() 
	{
		type = TileEntityInit.JUNGLE_CRATE;
		containerRegistryName = "container.jungle_crate";
	}
}
