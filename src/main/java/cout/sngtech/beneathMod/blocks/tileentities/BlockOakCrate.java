package cout.sngtech.beneathMod.blocks.tileentities;

import cout.sngtech.beneathMod.tileentities.TileEntityOakCrate;
import net.minecraft.block.Block;
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
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class BlockOakCrate extends Block
{
	public BlockOakCrate(Properties builder) 
	{
		super(builder);
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) 
	{
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(IBlockState state, IBlockReader world) 
	{
		return new TileEntityOakCrate();
	}
	
	@Override
	public boolean onBlockActivated(IBlockState state, World world, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) 
	{
		if(world.isRemote)
		{
			return true;
		}
		else
		{
			TileEntity te = world.getTileEntity(pos);
			if(te instanceof TileEntityOakCrate)
			{
				NetworkHooks.openGui((EntityPlayerMP) player, (TileEntityOakCrate) te, buf -> buf.writeBlockPos(pos));
			}
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
	
	@SuppressWarnings("deprecation")
	@Override
	public void onReplaced(IBlockState state, World world, BlockPos pos, IBlockState newState, boolean isMoving) 
	{
		TileEntity te = world.getTileEntity(pos);
		LazyOptional<IItemHandler> handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		for (int slot = 0; slot < ((IItemHandler) handler).getSlots(); slot++) 
		{
			ItemStack stack = ((IItemHandler) handler).getStackInSlot(slot);
			InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack);
		}
		
		super.onReplaced(state, world, pos, newState, isMoving);
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
