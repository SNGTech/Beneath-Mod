package cout.sngtech.beneathMod.blocks;

import java.util.Random;

import cout.sngtech.beneathMod.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCopperOre extends Block
{
	public BlockCopperOre(Properties properties) 
	{
		super(properties);
	}
	
	@Override
	public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune) 
	{
		return ItemInit.copper_ore_rock;
	}
	
	@Override
	public int quantityDropped(IBlockState state, Random random) 
	{
		return 1;
	}
}
