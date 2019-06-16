package cout.sngtech.beneathMod.blocks.tileentities;

import cout.sngtech.beneathMod.tileentities.TileEntityJungleCrate;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class BlockJungleCrate extends BlockCrate
{
	public BlockJungleCrate(Properties builder) 
	{
		super(builder);
	}

	@Override
	public TileEntity createTileEntity(IBlockState state, IBlockReader world) 
	{
		return new TileEntityJungleCrate();
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
			if(te instanceof TileEntityJungleCrate)
			{
				NetworkHooks.openGui((EntityPlayerMP) player, (TileEntityJungleCrate) te, buf -> buf.writeBlockPos(pos));
			}
		}
		
		return true;
	}
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) 
	{
		if (stack.hasDisplayName()) 
		{
			TileEntity tileentity = world.getTileEntity(pos);
			if (tileentity instanceof TileEntityJungleCrate) 
			{
				((TileEntityJungleCrate)tileentity).setCustomName(stack.getDisplayName());
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onReplaced(IBlockState state, World world, BlockPos pos, IBlockState newState, boolean isMoving) 
	{
		TileEntity te = world.getTileEntity(pos);
		
		if(te instanceof TileEntityJungleCrate)
		{
			ItemStack stack;
			for(int i = 0; i < ((TileEntityJungleCrate) te).getInventory().getSlots(); i++)
			{
				stack = ((TileEntityJungleCrate) te).getInventory().getStackInSlot(i);
				if(stack != null)
				{
					float f = RANDOM.nextFloat() * 0.75F + 0.125F;
					float f1 = RANDOM.nextFloat() * 0.75F;
					float f2 = RANDOM.nextFloat() * 0.75F + 0.125F;

					while(!stack.isEmpty()) 
					{
						EntityItem item = new EntityItem(world, pos.getX() + (double)f, pos.getY() + (double)f1, pos.getZ() + (double)f2, stack.split(RANDOM.nextInt(21) + 10));
						item.motionX = RANDOM.nextGaussian() * (double)0.05F;
						item.motionY = RANDOM.nextGaussian() * (double)0.05F + (double)0.2F;
						item.motionZ = RANDOM.nextGaussian() * (double)0.05F;
						world.spawnEntity(item);
					}
				}
			}
		}
			
		super.onReplaced(state, world, pos, newState, isMoving);
	}
}
