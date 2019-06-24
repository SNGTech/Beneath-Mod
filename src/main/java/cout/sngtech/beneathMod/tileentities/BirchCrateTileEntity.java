package cout.sngtech.beneathMod.tileentities;

import cout.sngtech.beneathMod.init.TileEntityInit;

public class BirchCrateTileEntity extends CrateTileEntity
{
	public BirchCrateTileEntity() 
	{
		type = TileEntityInit.BIRCH_CRATE;
		containerRegistryName = "container.birch_crate";
	}
}
