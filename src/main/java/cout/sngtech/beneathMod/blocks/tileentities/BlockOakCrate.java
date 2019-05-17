package cout.sngtech.beneathMod.blocks.tileentities;

import cout.sngtech.beneathMod.tileentities.TileEntityOakCrate;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class BlockOakCrate extends BlockContainer
{
	public BlockOakCrate(Properties builder) 
	{
		super(builder);
	}
	
	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) 
	{
		return new TileEntityOakCrate();
	}
	
	@Override
	public boolean onBlockActivated(IBlockState state, World world, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) 
	{
		if(!world.isRemote)
		{
			TileEntityOakCrate te = (TileEntityOakCrate) world.getTileEntity(pos);
			NetworkHooks.openGui((EntityPlayerMP) player, (IInteractionObject) te, buf -> buf.writeBlockPos(pos));
		}
		
		return true;
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) 
	{
		if (stack.hasDisplayName()) 
		{
			TileEntity tileentity = worldIn.getTileEntity(pos);
			if (tileentity instanceof TileEntityOakCrate) 
			{
				((TileEntityOakCrate)tileentity).setCustomName(stack.getDisplayName());
			}
		}
	}
	
	@Override
	public void onReplaced(IBlockState state, World world, BlockPos pos, IBlockState newState, boolean isMoving) 
	{
		if (state.getBlock() != newState.getBlock()) 
		{
			TileEntity tileentity = world.getTileEntity(pos);
			if (tileentity instanceof IInteractionObject)
			{
				InventoryHelper.dropInventoryItems(world, pos, (TileEntityOakCrate)tileentity);
			}

	         super.onReplaced(state, world, pos, newState, isMoving);
	 	}
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
