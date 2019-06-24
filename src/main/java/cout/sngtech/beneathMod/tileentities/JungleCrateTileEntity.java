package cout.sngtech.beneathMod.tileentities;

import cout.sngtech.beneathMod.init.TileEntityInit;

public class JungleCrateTileEntity extends CrateTileEntity
{
	public JungleCrateTileEntity() 
	{
		type = TileEntityInit.JUNGLE_CRATE;
		containerRegistryName = "container.jungle_crate";
	}
}
