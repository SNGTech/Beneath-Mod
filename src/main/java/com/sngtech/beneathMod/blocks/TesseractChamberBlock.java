package com.sngtech.beneathMod.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class TesseractChamberBlock extends DirectionalBlock
{
   public TesseractChamberBlock(Properties properties) 
   {
	   super(properties);
   }

   @Override
   protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) 
   {
      builder.add(FACING);
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
   public void tick(BlockState state, World worldIn, BlockPos pos, Random random) 
   {
	   this.updateNeighborsInFront(worldIn, pos, state);
   }

   @Override
   @SuppressWarnings("deprecation")
   public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) 
   {
      if (stateIn.get(FACING) == facing.getOpposite()) 
      {
         this.breakBlock(worldIn, currentPos, stateIn);
      }

      return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
   }

   private void breakBlock(IWorld worldIn, BlockPos pos, BlockState state) 
   {
      if (!worldIn.isRemote() && !worldIn.getPendingBlockTicks().isTickScheduled(pos, this)) 
      {
    	 Direction direction = state.get(FACING);
         BlockPos blockpos = pos.offset(direction.getOpposite());
         worldIn.destroyBlock(blockpos, true);
      }

   }

   protected void updateNeighborsInFront(World worldIn, BlockPos pos, BlockState state) 
   {
      Direction direction = state.get(FACING);
      BlockPos blockpos = pos.offset(direction);
      worldIn.neighborChanged(blockpos, this, pos);
      worldIn.notifyNeighborsOfStateExcept(blockpos, this, direction);
   }

   @Override
   public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) 
   {
      if (state.getBlock() != oldState.getBlock()) 
      {
         if (!worldIn.isRemote() && !worldIn.getPendingBlockTicks().isTickScheduled(pos, this)) 
         {
            worldIn.setBlockState(pos, state, 18);
            this.updateNeighborsInFront(worldIn, pos, state);
         }
      }
   }

   @Override
   public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) 
   {
      if (state.getBlock() != newState.getBlock()) 
      {
         if (!worldIn.isRemote && worldIn.getPendingBlockTicks().isTickScheduled(pos, this)) 
         {
            this.updateNeighborsInFront(worldIn, pos, state);
         }
      }
   }

   @Override
   public BlockState getStateForPlacement(BlockItemUseContext context) 
   {
      return this.getDefaultState().with(FACING, context.getNearestLookingDirection());
   }
   
   @Override
   public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos) 
   {
      return false;
   }

   @Override
   public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) 
   {
      return false;
   }
   
   @Override
   public boolean isSolid(BlockState state) 
   {
      return false;
   }
   
   public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) 
   {
      return 1;
   }
   
   @Override
   public BlockRenderLayer getRenderLayer() 
   {
      return BlockRenderLayer.CUTOUT_MIPPED;
   }
}
