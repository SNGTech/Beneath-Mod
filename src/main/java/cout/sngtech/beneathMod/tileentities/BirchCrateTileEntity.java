package cout.sngtech.beneathMod.tileentities;

import cout.sngtech.beneathMod.init.TileEntityInit;
import net.minecraft.tileentity.TileEntityType;

public class BirchCrateTileEntity extends CrateTileEntity
{
	public BirchCrateTileEntity(TileEntityType<?> type) 
	{
		super(type);
	}
	
	public BirchCrateTileEntity() 
	{
		this(TileEntityInit.BIRCH_CRATE);
		containerRegistryName = "container.birch_crate";
	}
}
