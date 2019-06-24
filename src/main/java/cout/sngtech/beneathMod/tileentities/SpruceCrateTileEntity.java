package cout.sngtech.beneathMod.tileentities;

import cout.sngtech.beneathMod.init.TileEntityInit;

public class SpruceCrateTileEntity extends CrateTileEntity
{
	public SpruceCrateTileEntity() 
	{
		type = TileEntityInit.SPRUCE_CRATE;
		containerRegistryName = "container.spruce_crate";
	}
}
