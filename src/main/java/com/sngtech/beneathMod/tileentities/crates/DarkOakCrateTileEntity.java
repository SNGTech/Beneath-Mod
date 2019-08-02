package com.sngtech.beneathMod.tileentities.crates;

import com.sngtech.beneathMod.init.TileEntityInit;

import net.minecraft.tileentity.TileEntityType;

public class DarkOakCrateTileEntity extends CrateTileEntity
{
	public DarkOakCrateTileEntity(TileEntityType<?> type) 
	{
		super(type);
	}
	
	public DarkOakCrateTileEntity() 
	{
		this(TileEntityInit.DARK_OAK_CRATE);
		containerRegistryName = "container.dark_oak_crate";
	}
}
