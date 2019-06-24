package cout.sngtech.beneathMod.tileentities;

import cout.sngtech.beneathMod.init.TileEntityInit;

public class OakCrateTileEntity extends CrateTileEntity
{
	public OakCrateTileEntity() 
	{
		type = TileEntityInit.OAK_CRATE;
		containerRegistryName = "container.oak_crate";
	}
}
