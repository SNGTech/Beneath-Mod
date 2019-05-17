package cout.sngtech.beneathMod.blocks.tileentities;

import cout.sngtech.beneathMod.tileentities.TileEntityCrate;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class BlockCrate extends BlockContainer
{
	public BlockCrate(Properties builder) 
	{
		super(builder);
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) 
	{
		return null;
	}
	
	@Override
	public boolean onBlockActivated(IBlockState state, World world, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) 
	{
		if(!world.isRemote)
		{
			TileEntityCrate te = (TileEntityCrate) world.getTileEntity(pos);
			NetworkHooks.openGui((EntityPlayerMP) player, te, buf -> buf.writeBlockPos(pos));
		}
		
		return true;
	}
}
