package com.sngtech.beneathMod.blocks.tileentities.crates;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public abstract class AbstractCrateBlock extends Block
{
	public AbstractCrateBlock(Properties builder) 
	{
		super(builder);
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) 
	{
		return true;
	}
	
	public TileEntity createTileEntity(BlockState state, IBlockReader world) 
	{
		return null;
	}
	
	public BlockRenderType getRenderType(BlockState state) 
	{
		return BlockRenderType.MODEL;
	}
	
	@Override
	public boolean isSolid(BlockState state) 
	{
		return true;
	}
	
	@Override
	public boolean hasCustomBreakingProgress(BlockState state) 
	{
		return true;
	}
}
