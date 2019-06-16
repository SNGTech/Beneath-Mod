package cout.sngtech.beneathMod.tileentities;

import cout.sngtech.beneathMod.init.TileEntityInit;

public class TileEntityBirchCrate extends TileEntityCrate
{
	public TileEntityBirchCrate() 
	{
		type = TileEntityInit.BIRCH_CRATE;
		containerRegistryName = "container.birch_crate";
	}
}
