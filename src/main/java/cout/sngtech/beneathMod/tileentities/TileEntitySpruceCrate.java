package cout.sngtech.beneathMod.tileentities;

import cout.sngtech.beneathMod.init.TileEntityInit;

public class TileEntitySpruceCrate extends TileEntityCrate
{
	public TileEntitySpruceCrate() 
	{
		type = TileEntityInit.SPRUCE_CRATE;
		containerRegistryName = "container.spruce_crate";
	}
}
