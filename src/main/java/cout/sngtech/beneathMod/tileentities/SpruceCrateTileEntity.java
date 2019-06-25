package cout.sngtech.beneathMod.tileentities;

import cout.sngtech.beneathMod.init.TileEntityInit;
import net.minecraft.tileentity.TileEntityType;

public class SpruceCrateTileEntity extends CrateTileEntity
{
	public SpruceCrateTileEntity(TileEntityType<?> type) 
	{
		super(type);
	}
	
	public SpruceCrateTileEntity() 
	{
		this(TileEntityInit.SPRUCE_CRATE);
		containerRegistryName = "container.spruce_crate";
	}
}
