package cout.sngtech.beneathMod.blocks.tileentities;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;

public abstract class AbstractCrateBlock extends ContainerBlock
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
