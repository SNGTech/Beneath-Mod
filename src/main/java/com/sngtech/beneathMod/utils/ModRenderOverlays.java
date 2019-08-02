package com.sngtech.beneathMod.utils;

import com.mojang.blaze3d.platform.GlStateManager;
import com.sngtech.beneathMod.Main;
import com.sngtech.beneathMod.init.BlockInit;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ModRenderOverlays 
{
	public static void renderEntityOnBlueFire(Entity entity, EntityRenderer<?> renderer, double x, double y, double z, float partialTicks) 
	{
		GlStateManager.disableLighting();
		AtlasTexture atlastexture = Minecraft.getInstance().getTextureMap();
		TextureAtlasSprite textureatlassprite = atlastexture.getSprite(new ResourceLocation(Main.MODID, "textures/block/blue_fire_layer_0"));
		TextureAtlasSprite textureatlassprite1 = atlastexture.getSprite(new ResourceLocation(Main.MODID, "textures/block/blue_fire_layer_1"));
		GlStateManager.pushMatrix();
		GlStateManager.translatef((float)x, (float)y, (float)z);
		float f = entity.getWidth() * 1.4F;
		GlStateManager.scalef(f, f, f);
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		float f1 = 0.5F;
		float f2 = 0.0F;
		float f3 = entity.getHeight() / f;
		float f4 = (float)(entity.posY - entity.getBoundingBox().minY);
		GlStateManager.rotatef(-renderer.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
		GlStateManager.translatef(0.0F, 0.0F, -0.3F + (float)((int)f3) * 0.02F);
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		float f5 = 0.0F;
		int i = 0;
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);

		while(f3 > 0.0F) 
		{
			TextureAtlasSprite textureatlassprite2 = i % 2 == 0 ? textureatlassprite : textureatlassprite1;
			renderer.bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
			float f6 = textureatlassprite2.getMinU();
			float f7 = textureatlassprite2.getMinV();
			float f8 = textureatlassprite2.getMaxU();
			float f9 = textureatlassprite2.getMaxV();
			if (i / 2 % 2 == 0) {
				float f10 = f8;
				f8 = f6;
				f6 = f10;
			}

			bufferbuilder.pos((double)(f1 - 0.0F), (double)(0.0F - f4), (double)f5).tex((double)f8, (double)f9).endVertex();
			bufferbuilder.pos((double)(-f1 - 0.0F), (double)(0.0F - f4), (double)f5).tex((double)f6, (double)f9).endVertex();
			bufferbuilder.pos((double)(-f1 - 0.0F), (double)(1.4F - f4), (double)f5).tex((double)f6, (double)f7).endVertex();
			bufferbuilder.pos((double)(f1 - 0.0F), (double)(1.4F - f4), (double)f5).tex((double)f8, (double)f7).endVertex();
			f3 -= 0.45F;
			f4 -= 0.45F;
			f1 *= 0.9F;
         	f5 += 0.03F;
         	++i;
		}

		tessellator.draw();
		GlStateManager.popMatrix();
		GlStateManager.enableLighting();
	}
	
	@SuppressWarnings("deprecation")
	public static boolean collidedwithBlueFire(World world, AxisAlignedBB bb) 
	{
		int i = MathHelper.floor(bb.minX);
      	int j = MathHelper.ceil(bb.maxX);
      	int k = MathHelper.floor(bb.minY);
      	int l = MathHelper.ceil(bb.maxY);
      	int i1 = MathHelper.floor(bb.minZ);
      	int j1 = MathHelper.ceil(bb.maxZ);
      	if (world.isAreaLoaded(i, k, i1, j, l, j1)) 
      	{
    	  try (BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain()) 
    	  {
        	 for(int k1 = i; k1 < j; ++k1) 
        	 {
            	for(int l1 = k; l1 < l; ++l1) 
            	{
            	   for(int i2 = i1; i2 < j1; ++i2) 
            	   {
                	  BlockState state = world.getBlockState(blockpos$pooledmutableblockpos.setPos(k1, l1, i2));
                     	if (state == BlockInit.BLUE_FIRE.getDefaultState()) 
                     	{
                     		boolean flag = true;
                        	return flag;
                     	}
                  	}
               	}
            }

            	return false;
         	}
      	} 
      	else 
      	{
    	  return false;
      	}
	}
}
