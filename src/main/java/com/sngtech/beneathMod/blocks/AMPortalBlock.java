package com.sngtech.beneathMod.blocks;

import java.util.Random;

import com.sngtech.beneathMod.world.dimensions.AMTeleporterManager;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.ServerWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class AMPortalBlock extends Block 
{
	public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
	protected static final VoxelShape X_AABB = Block.makeCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
	protected static final VoxelShape Z_AABB = Block.makeCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);

	public AMPortalBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(AXIS, Direction.Axis.X));
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch((Direction.Axis)state.get(AXIS)) {
		case Z:
			return Z_AABB;
		case X:
		default:
			return X_AABB;
		}
	}
	
	public boolean isFullCube(BlockState state) {
		return false;
	}

	/*public boolean trySpawnPortal(IWorld worldIn, BlockPos pos) {
		FantasiaPortalBlock.Size blockportal$size = this.isPortal(worldIn, pos);
		if (blockportal$size != null) {
			blockportal$size.placePortalBlocks();
			return true;
		} else {
			return false;
		}
	}

	@Nullable
	public FantasiaPortalBlock.Size isPortal(IWorld p_201816_1_, BlockPos p_201816_2_) {
		FantasiaPortalBlock.Size blockportal$size = new FantasiaPortalBlock.Size(p_201816_1_, p_201816_2_, Direction.Axis.X);
		if (blockportal$size.isValid() && blockportal$size.portalBlockCount == 0) {
			return blockportal$size;
		} else {
			FantasiaPortalBlock.Size blockportal$size1 = new FantasiaPortalBlock.Size(p_201816_1_, p_201816_2_, Direction.Axis.Z);
			return blockportal$size1.isValid() && blockportal$size1.portalBlockCount == 0 ? blockportal$size1 : null;
		}
	}

	@SuppressWarnings("deprecation")
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		Direction.Axis enumfacing$axis = facing.getAxis();
		Direction.Axis enumfacing$axis1 = stateIn.get(AXIS);
		boolean flag = enumfacing$axis1 != enumfacing$axis && enumfacing$axis.isHorizontal();
		return flag || facingState.getBlock() == this || (new FantasiaPortalBlock.Size(worldIn, currentPos, enumfacing$axis1)).func_208508_f() ? super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos) : Blocks.AIR.getDefaultState();
	}*/

	public int quantityDropped(BlockState state, Random random) {
		return 0;
	}

	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
	
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		if (!entity.isPassenger() && !entity.isBeingRidden() && entity.isNonBoss()) {
			AMTeleporterManager.teleport(entity);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (rand.nextInt(100) == 0) {
			worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
		}

		for(int i = 0; i < 4; ++i) {
			double d0 = (double)((float)pos.getX() + rand.nextFloat());
			double d1 = (double)((float)pos.getY() + rand.nextFloat());
			double d2 = (double)((float)pos.getZ() + rand.nextFloat());
			double d3 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
			double d4 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
			double d5 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
			int j = rand.nextInt(2) * 2 - 1;
			if (worldIn.getBlockState(pos.west()).getBlock() != this && worldIn.getBlockState(pos.east()).getBlock() != this) {
				d0 = (double)pos.getX() + 0.5D + 0.25D * (double)j;
				d3 = (double)(rand.nextFloat() * 2.0F * (float)j);
			} else {
				d2 = (double)pos.getZ() + 0.5D + 0.25D * (double)j;
				d5 = (double)(rand.nextFloat() * 2.0F * (float)j);
			}

			worldIn.addParticle(ParticleTypes.TOTEM_OF_UNDYING, d0, d1, d2, d3, d4, d5);
		}
	}

	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return ItemStack.EMPTY;
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		switch(rot) {
		case COUNTERCLOCKWISE_90:
		case CLOCKWISE_90:
			switch((Direction.Axis)state.get(AXIS)) {
			case Z:
				return state.with(AXIS, Direction.Axis.X);
			case X:
				return state.with(AXIS, Direction.Axis.Z);
			default:
				return state;
			}
		default:
			return state;
		}
	}

	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(AXIS);
	}

	/*@SuppressWarnings("deprecation")
	public BlockPattern.PatternHelper createPatternHelper(IWorld worldIn, BlockPos p_181089_2_) {
		Direction.Axis enumfacing$axis = Direction.Axis.Z;
		FantasiaPortalBlock.Size blockportal$size = new FantasiaPortalBlock.Size(worldIn, p_181089_2_, Direction.Axis.X);
		LoadingCache<BlockPos, CachedBlockInfo> loadingcache = BlockPattern.createLoadingCache(worldIn, true);
		if (!blockportal$size.isValid()) {
			enumfacing$axis = Direction.Axis.X;
			blockportal$size = new FantasiaPortalBlock.Size(worldIn, p_181089_2_, Direction.Axis.Z);
		}

		if (!blockportal$size.isValid()) {
			return new BlockPattern.PatternHelper(p_181089_2_, Direction.NORTH, Direction.UP, loadingcache, 1, 1, 1);
		} else {
			int[] aint = new int[Direction.AxisDirection.values().length];
			Direction enumfacing = blockportal$size.rightDir.rotateYCCW();
			BlockPos blockpos = blockportal$size.bottomLeft.up(blockportal$size.getHeight() - 1);

			for(Direction.AxisDirection enumfacing$axisdirection : Direction.AxisDirection.values()) {
				BlockPattern.PatternHelper blockpattern$patternhelper = new BlockPattern.PatternHelper(enumfacing.getAxisDirection() == enumfacing$axisdirection ? blockpos : blockpos.offset(blockportal$size.rightDir, blockportal$size.getWidth() - 1), Direction.getFacingFromAxis(enumfacing$axisdirection, enumfacing$axis), Direction.UP, loadingcache, blockportal$size.getWidth(), blockportal$size.getHeight(), 1);

				for(int i = 0; i < blockportal$size.getWidth(); ++i) {
					for(int j = 0; j < blockportal$size.getHeight(); ++j) {
						CachedBlockInfo blockworldstate = blockpattern$patternhelper.translateOffset(i, j, 1);
						if (!blockworldstate.getBlockState().isAir()) {
							++aint[enumfacing$axisdirection.ordinal()];
						}
					}
				}
			}

			Direction.AxisDirection enumfacing$axisdirection1 = Direction.AxisDirection.POSITIVE;

			for(Direction.AxisDirection enumfacing$axisdirection2 : Direction.AxisDirection.values()) {
				if (aint[enumfacing$axisdirection2.ordinal()] < aint[enumfacing$axisdirection1.ordinal()]) {
					enumfacing$axisdirection1 = enumfacing$axisdirection2;
				}
			}

			return new BlockPattern.PatternHelper(enumfacing.getAxisDirection() == enumfacing$axisdirection1 ? blockpos : blockpos.offset(blockportal$size.rightDir, blockportal$size.getWidth() - 1), Direction.getFacingFromAxis(enumfacing$axisdirection1, enumfacing$axis), Direction.UP, loadingcache, blockportal$size.getWidth(), blockportal$size.getHeight(), 1);
		}
	}

	/*public static class Size {
		private final IWorld world;
		private final Direction.Axis axis;
		private final Direction rightDir;
		private final Direction leftDir;
		private int portalBlockCount;
		private BlockPos bottomLeft;
		private int height;
		private int width;
		
		public Size(IWorld p_i48740_1_, BlockPos p_i48740_2_, Direction.Axis p_i48740_3_) {
			this.world = p_i48740_1_;
			this.axis = p_i48740_3_;
			if (p_i48740_3_ == Direction.Axis.X) {
				this.leftDir = Direction.EAST;
				this.rightDir = Direction.WEST;
			} else {
				this.leftDir = Direction.NORTH;
				this.rightDir = Direction.SOUTH;
			}

			for(BlockPos blockpos = p_i48740_2_; p_i48740_2_.getY() > blockpos.getY() - 21 && p_i48740_2_.getY() > 0 && this.func_196900_a(p_i48740_1_.getBlockState(p_i48740_2_.down())); p_i48740_2_ = p_i48740_2_.down()) {
				;
			}

			int i = this.getDistanceUntilEdge(p_i48740_2_, this.leftDir) - 1;
			if (i >= 0) {
				this.bottomLeft = p_i48740_2_.offset(this.leftDir, i);
				this.width = this.getDistanceUntilEdge(this.bottomLeft, this.rightDir);
				if (this.width < 2 || this.width > 21) {
					this.bottomLeft = null;
					this.width = 0;
				}
			}

			if (this.bottomLeft != null) {
				this.height = this.calculatePortalHeight();
			}
		}

		protected int getDistanceUntilEdge(BlockPos p_180120_1_, Direction p_180120_2_) {
			int i;
			for(i = 0; i < 22; ++i) {
				BlockPos blockpos = p_180120_1_.offset(p_180120_2_, i);
				if (!this.func_196900_a(this.world.getBlockState(blockpos)) || this.world.getBlockState(blockpos.down()).getBlock() != Blocks.LAPIS_BLOCK) {
					break;
				}
			}

			Block block = this.world.getBlockState(p_180120_1_.offset(p_180120_2_, i)).getBlock();
			return block == Blocks.LAPIS_BLOCK ? i : 0;
		}

		public int getHeight() {
			return this.height;
		}

		public int getWidth() {
			return this.width;
		}

		protected int calculatePortalHeight() {
			label56:
				for(this.height = 0; this.height < 21; ++this.height) {
					for(int i = 0; i < this.width; ++i) {
						BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i).up(this.height);
						BlockState iblockstate = this.world.getBlockState(blockpos);
						if (!this.func_196900_a(iblockstate)) {
							break label56;
						}

						Block block = iblockstate.getBlock();
						if (block == ModBlocks.FANTASIA_PORTAL) {
							++this.portalBlockCount;
						}

						if (i == 0) {
							block = this.world.getBlockState(blockpos.offset(this.leftDir)).getBlock();
							if (block != Blocks.LAPIS_BLOCK) {
								break label56;
							}
						} else if (i == this.width - 1) {
							block = this.world.getBlockState(blockpos.offset(this.rightDir)).getBlock();
							if (block != Blocks.LAPIS_BLOCK) {
								break label56;
							}
						}
					}
				}

			for(int j = 0; j < this.width; ++j) {
				if (this.world.getBlockState(this.bottomLeft.offset(this.rightDir, j).up(this.height)).getBlock() != Blocks.LAPIS_BLOCK) {
					this.height = 0;
					break;
				}
			}

			if (this.height <= 21 && this.height >= 3) {
				return this.height;
			} else {
				this.bottomLeft = null;
				this.width = 0;
				this.height = 0;
				return 0;
			}
		}

		@SuppressWarnings("deprecation")
		protected boolean func_196900_a(BlockState p_196900_1_) {
			Block block = p_196900_1_.getBlock();
			return p_196900_1_.isAir() || block == ModBlocks.GREEN_FIRE || block == ModBlocks.FANTASIA_PORTAL;
		}
	
		public boolean isValid() {
			return this.bottomLeft != null && this.width >= 2 && this.width <= 21 && this.height >= 3 && this.height <= 21;
		}
	
		public void placePortalBlocks() {
			for(int i = 0; i < this.width; ++i) {
				BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i);
	
				for(int j = 0; j < this.height; ++j) {
					this.world.setBlockState(blockpos.up(j), BlockInit.AM_PORTAL.getDefaultState().with(AMPortalBlock.AXIS, this.axis), 18);
				}
			}
		}

		private boolean func_196899_f() {
			return this.portalBlockCount >= this.width * this.height;
		}
	
		public boolean func_208508_f() {
			return this.isValid() && this.func_196899_f();
		}
	}*/
}
