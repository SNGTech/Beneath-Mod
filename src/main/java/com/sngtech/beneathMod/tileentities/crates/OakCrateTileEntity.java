package com.sngtech.beneathMod.tileentities.crates;

import com.sngtech.beneathMod.init.TileEntityInit;

import net.minecraft.tileentity.TileEntityType;

public class OakCrateTileEntity extends CrateTileEntity
{
	public OakCrateTileEntity(TileEntityType<?> type) 
	{
		super(type);
	}
	
	public OakCrateTileEntity() 
	{
		this(TileEntityInit.OAK_CRATE);
		containerRegistryName = "container.oak_crate";
	}
}
