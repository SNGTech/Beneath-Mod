package cout.sngtech.beneathMod.tileentities;

import cout.sngtech.beneathMod.init.TileEntityInit;
import net.minecraft.tileentity.TileEntityType;

public class AcaciaCrateTileEntity extends CrateTileEntity
{
	public AcaciaCrateTileEntity(TileEntityType<?> type) 
	{
		super(type);
	}
	
	public AcaciaCrateTileEntity()
	{
		this(TileEntityInit.ACACIA_CRATE);
		containerRegistryName = "container.acacia_crate";
	}
}
