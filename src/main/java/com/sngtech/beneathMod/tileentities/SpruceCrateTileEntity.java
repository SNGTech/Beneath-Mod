package com.sngtech.beneathMod.tileentities;

import com.sngtech.beneathMod.init.TileEntityInit;

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
