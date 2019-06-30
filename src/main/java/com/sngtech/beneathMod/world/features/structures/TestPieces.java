package com.sngtech.beneathMod.world.features.structures;

import java.util.List;
import java.util.Random;

import com.sngtech.beneathMod.Main;
import com.sngtech.beneathMod.init.BlockInit;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class TestPieces 
{
   private static final ResourceLocation PIECE = new ResourceLocation(Main.MODID, "test");
   private static final BlockPos STRUCTURE_OFFSET = new BlockPos(4, 0, 15);

   public static void func_207617_a(TemplateManager manager, BlockPos pos, Rotation rot, List<StructurePiece> pieces, Random rand, NoFeatureConfig config)
   {
      pieces.add(new TestPieces.Piece(manager, PIECE, pos, rot, 0));
   }

   public static class Piece extends TemplateStructurePiece 
   {
      private final ResourceLocation resourceLocation;
      private final Rotation rot;

      public Piece(TemplateManager p_i49313_1_, ResourceLocation p_i49313_2_, BlockPos p_i49313_3_, Rotation p_i49313_4_, int p_i49313_5_) 
      {
         super(StructurePieceInit.TEST, 0);
         this.resourceLocation = p_i49313_2_;
         BlockPos blockpos = TestPieces.STRUCTURE_OFFSET;
         this.templatePosition = p_i49313_3_.add(blockpos.getX(), blockpos.getY() - p_i49313_5_, blockpos.getZ());
         this.rot = p_i49313_4_;
         this.func_207614_a(p_i49313_1_);
      }

      public Piece(TemplateManager p_i50566_1_, CompoundNBT p_i50566_2_) 
      {
         super(StructurePieceInit.TEST, p_i50566_2_);
         this.resourceLocation = new ResourceLocation(p_i50566_2_.getString("Template"));
         this.rot = Rotation.valueOf(p_i50566_2_.getString("Rot"));
         this.func_207614_a(p_i50566_1_);
      }

      private void func_207614_a(TemplateManager p_207614_1_) 
      {
         Template template = p_207614_1_.getTemplateDefaulted(this.resourceLocation);
         PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rot).setMirror(Mirror.NONE).setCenterOffset(TestPieces.STRUCTURE_OFFSET).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
         this.setup(template, this.templatePosition, placementsettings);
      }

      /**
       * (abstract) Helper method to read subclass data from NBT
       */
      protected void readAdditional(CompoundNBT tagCompound) 
      {
         super.readAdditional(tagCompound);
         tagCompound.putString("Template", this.resourceLocation.toString());
         tagCompound.putString("Rot", this.rot.name());
      }

      protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand, MutableBoundingBox sbb) 
      {
         /*if ("chest".equals(function)) {
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
            TileEntity tileentity = worldIn.getTileEntity(pos.down());
            if (tileentity instanceof ChestTileEntity) {
               ((ChestTileEntity)tileentity).setLootTable(LootTables.CHESTS_IGLOO_CHEST, rand.nextLong());
            }

         }*/
      }

      /**
       * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes Mineshafts at
       * the end, it adds Fences...
       */
      @SuppressWarnings("deprecation")
	public boolean addComponentParts(IWorld worldIn, Random randomIn, MutableBoundingBox structureBoundingBoxIn, ChunkPos p_74875_4_) 
      {
         PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rot).setMirror(Mirror.NONE).setCenterOffset(TestPieces.STRUCTURE_OFFSET).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
         BlockPos blockpos = TestPieces.STRUCTURE_OFFSET;
         BlockPos blockpos1 = this.templatePosition.add(Template.transformedBlockPos(placementsettings, new BlockPos(3 - blockpos.getX(), 0, 0 - blockpos.getZ())));
         int i = worldIn.getHeight(Heightmap.Type.WORLD_SURFACE_WG, blockpos1.getX(), blockpos1.getZ());
         BlockPos blockpos2 = this.templatePosition;
         this.templatePosition = this.templatePosition.add(0, i - 90 - 1, 0);
         boolean flag = super.addComponentParts(worldIn, randomIn, structureBoundingBoxIn, p_74875_4_);
         if (this.resourceLocation.equals(TestPieces.STRUCTURE_OFFSET)) 
         {
            BlockPos blockpos3 = this.templatePosition.add(Template.transformedBlockPos(placementsettings, new BlockPos(3, 0, 5)));
            BlockState blockstate = worldIn.getBlockState(blockpos3.down());
            if (!blockstate.isAir() && blockstate.getBlock() != Blocks.LADDER) 
            {
               worldIn.setBlockState(blockpos3, BlockInit.CRACKED_ROCKS.getDefaultState(), 3);
               Main.logger.debug("Structure Added: " + this.templatePosition.getX() + ", " + this.templatePosition.getY() + ", " + this.templatePosition.getZ());
            }
         }

         this.templatePosition = blockpos2;
         return flag;
      }
   }
}
