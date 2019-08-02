package com.sngtech.beneathMod.tileentities.crates;

import com.sngtech.beneathMod.init.TileEntityInit;

import net.minecraft.tileentity.TileEntityType;

public class JungleCrateTileEntity extends CrateTileEntity
{
	public JungleCrateTileEntity(TileEntityType<?> type) 
	{
		super(type);
	}
	
	public JungleCrateTileEntity() 
	{
		this(TileEntityInit.JUNGLE_CRATE);
		containerRegistryName = "container.jungle_crate";
	}
}
