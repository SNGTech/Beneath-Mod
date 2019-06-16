package cout.sngtech.beneathMod.blocks.tileentities;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;

public class BlockCrate extends Block
{
	public BlockCrate(Properties builder) 
	{
		super(builder);
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) 
	{
		return true;
	}
	
	public EnumBlockRenderType getRenderType(IBlockState state) 
	{
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
    public boolean isFullCube(IBlockState state)
    {
        return true;
    }
	
	@Override
	public boolean isSolid(IBlockState state) 
	{
		return true;
	}
	
	@Override
	public boolean hasCustomBreakingProgress(IBlockState state) 
	{
		return true;
	}
}
