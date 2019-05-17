package cout.sngtech.beneathMod.tileentities;

import cout.sngtech.beneathMod.init.TileEntityInit;

public class TileEntityOakCrate extends TileEntityCrate
{
	public TileEntityOakCrate() 
	{
		super(TileEntityInit.oak_crate);
		setWoodType("oak");
	}
}
