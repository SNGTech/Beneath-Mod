package com.sngtech.beneathMod.blocks.tileentities;

import java.util.Random;

import com.sngtech.beneathMod.tileentities.crates.PlacerTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class PlacerBlock extends Block 
{
	public static final DirectionProperty FACING = DirectionalBlock.FACING;
	public static final BooleanProperty TRIGGERED = BlockStateProperties.TRIGGERED;
	
	public PlacerBlock(Properties properties) 
	{
		super(properties);
		this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH).with(TRIGGERED, Boolean.valueOf(false)));
	}
	
	@Override
	public int tickRate(IWorldReader worldIn) 
	{
		return 4;
	}

	@Override
	public boolean hasTileEntity(BlockState state) 
	{
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) 
	{
		return new PlacerTileEntity();
	}
	
	@Override
	public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
	{
		if(world.isRemote)
		{
			return true;
		}
		else
		{
			TileEntity te = world.getTileEntity(pos);
			if(te instanceof PlacerTileEntity)
			{
				NetworkHooks.openGui((ServerPlayerEntity) player, (PlacerTileEntity) te, (buf -> buf.writeBlockPos(pos)));
			}
		}
		
		return true;
	}
	
	protected void place(World worldIn, BlockPos pos) 
	{
		PlacerTileEntity te = (PlacerTileEntity)worldIn.getTileEntity(pos);
      	int i = te.getPlaceSlot();
      	if(i >= 0)
      	{
      		ItemStack itemstack = te.getInventory().getStackInSlot(i);
      		if(itemstack.getItem() instanceof BlockItem)
      		{
      			BlockItem blockitem = (BlockItem)itemstack.getItem();
      			Direction direction = te.getBlockState().get(FACING);
      	    	BlockPos blockpos = pos.offset(direction.getOpposite());
      	    	if(worldIn.getBlockState(blockpos) == Blocks.TALL_GRASS.getDefaultState() || worldIn.isAirBlock(blockpos))
      	    	{
      	    		if(blockitem.getBlock().getDefaultState().has(FACING))
      	    		{
      	    			worldIn.setBlockState(blockpos, blockitem.getBlock().getDefaultState().with(FACING, te.getBlockState().get(FACING)), 3);
      	    			itemstack.shrink(1);
      	    		}
      	    		else
      	    		{
      	    			worldIn.setBlockState(blockpos, blockitem.getBlock().getDefaultState(), 3);
      	    			itemstack.shrink(1);
      	    		}
      			}
      		}
      	}
	}
	
	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) 
	{
		boolean flag = worldIn.isBlockPowered(pos) || worldIn.isBlockPowered(pos.up());
      	boolean flag1 = state.get(TRIGGERED);
      	if (flag && !flag1) 
      	{
      		worldIn.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(worldIn));
      		worldIn.setBlockState(pos, state.with(TRIGGERED, Boolean.valueOf(true)), 4);
  		} 
      	else if (!flag && flag1) 
      	{
      		worldIn.setBlockState(pos, state.with(TRIGGERED, Boolean.valueOf(false)), 4);
      	}
	}

	@Override
	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) 
	{
		if (!worldIn.isRemote) 
		{
			this.place(worldIn, pos);
		}
	}
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) 
	{
		if (stack.hasDisplayName()) 
		{
			TileEntity tileentity = world.getTileEntity(pos);
			if (tileentity instanceof PlacerTileEntity) 
			{
				((PlacerTileEntity)tileentity).setCustomName(stack.getDisplayName());
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) 
	{
		TileEntity te = world.getTileEntity(pos);
		
		if (state.getBlock() != newState.getBlock())
		{
			if(te instanceof PlacerTileEntity)
			{
				ItemStack stack;
				for(int i = 0; i < ((PlacerTileEntity) te).getInventory().getSlots(); i++)
				{
					stack = ((PlacerTileEntity) te).getInventory().getStackInSlot(i);
					if(stack != null)
					{
						double d0 = (double)EntityType.ITEM.getWidth();
				        double d1 = 1.0D - d0;
				        double d2 = d0 / 2.0D;
				        double d3 = Math.floor((double)pos.getX()) + RANDOM.nextDouble() * d1 + d2;
				        double d4 = Math.floor((double)pos.getY()) + RANDOM.nextDouble() * d1;
				        double d5 = Math.floor((double)pos.getZ()) + RANDOM.nextDouble() * d1 + d2;
		
				        while(!stack.isEmpty()) 
				        {
				           ItemEntity itementity = new ItemEntity(world, d3, d4, d5, stack.split(RANDOM.nextInt(21) + 10));
				           itementity.setMotion(RANDOM.nextGaussian() * (double)0.05F, RANDOM.nextGaussian() * (double)0.05F + (double)0.2F, RANDOM.nextGaussian() * (double)0.05F);
				           world.addEntity(itementity);
				        }
					}
				}
			}
			
			world.updateComparatorOutputLevel(pos, this);
		}
			
		super.onReplaced(state, world, pos, newState, isMoving);
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
		return this.getDefaultState().with(FACING, context.getNearestLookingDirection());
	}
	
	@Override
	public boolean hasComparatorInputOverride(BlockState state) 
	{
		return true;
	}
	
	@Override
	public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos)
	{
		return Container.calcRedstone(worldIn.getTileEntity(pos));
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot)
	{
		return state.with(FACING, rot.rotate(state.get(FACING)));
	}
	
	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn)
	{
		return state.rotate(mirrorIn.toRotation(state.get(FACING)));
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) 
	{
		builder.add(FACING, TRIGGERED);
	}
	
	@Override
	public BlockRenderType getRenderType(BlockState state) 
	{
		return BlockRenderType.MODEL;
	}
	
	@Override
	public boolean isSolid(BlockState state) 
	{
		return true;
	}
}
