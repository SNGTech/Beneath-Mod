package cout.sngtech.beneathMod.blocks;

import java.util.Random;

import cout.sngtech.beneathMod.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBauxiteOre extends Block
{
	public BlockBauxiteOre(Properties properties) 
	{
		super(properties);
	}
	
	@Override
	public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune) 
	{
		return ItemInit.BAUXITE_ORE_ROCK;
	}
	
	@Override
	public int quantityDropped(IBlockState state, Random random) 
	{
		return 1;
	}
}
