package com.sngtech.beneathMod.entities.renderer.layers;

import com.mojang.blaze3d.platform.GlStateManager;
import com.sngtech.beneathMod.Main;
import com.sngtech.beneathMod.entities.ColdCreeperEntity;
import com.sngtech.beneathMod.entities.models.ColdCreeperModel;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class ColdCreeperChargeLayer extends LayerRenderer<ColdCreeperEntity, ColdCreeperModel<ColdCreeperEntity>> 
{
   private static final ResourceLocation LIGHTNING_TEXTURE = new ResourceLocation(Main.MODID, "textures/entity/creeper/cold_creeper_armor.png");
   private final ColdCreeperModel<ColdCreeperEntity> coldCreeperModel = new ColdCreeperModel<>(2.0F);

   public ColdCreeperChargeLayer(IEntityRenderer<ColdCreeperEntity,ColdCreeperModel<ColdCreeperEntity>> coldCreeper) 
   {
      super(coldCreeper);
   }

   @Override
   public void render(ColdCreeperEntity entityIn, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_, float p_212842_6_, float p_212842_7_, float p_212842_8_) 
   {
      if (entityIn.getPowered()) 
      {
    	  boolean flag = entityIn.isInvisible();
          GlStateManager.depthMask(!flag);
          this.bindTexture(LIGHTNING_TEXTURE);
          GlStateManager.matrixMode(5890);
          GlStateManager.loadIdentity();
          float f = (float)entityIn.ticksExisted + p_212842_4_;
          GlStateManager.translatef(f * 0.01F, f * 0.01F, 0.0F);
          GlStateManager.matrixMode(5888);
          GlStateManager.enableBlend();
          float f1 = 0.5F;
          GlStateManager.color4f(0.5F, 0.5F, 0.5F, 1.0F);
          GlStateManager.disableLighting();
          GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
          this.getEntityModel().setModelAttributes(this.coldCreeperModel);
          GameRenderer gamerenderer = Minecraft.getInstance().gameRenderer;
          gamerenderer.setupFogColor(true);
          this.coldCreeperModel.render(entityIn, p_212842_2_, p_212842_3_, p_212842_5_, p_212842_6_, p_212842_7_, p_212842_8_);
          gamerenderer.setupFogColor(false);
          GlStateManager.matrixMode(5890);
          GlStateManager.loadIdentity();
          GlStateManager.matrixMode(5888);
          GlStateManager.enableLighting();
          GlStateManager.disableBlend();
          GlStateManager.depthMask(true);
      }
   }

   @Override
   public boolean shouldCombineTextures() 
   {
      return false;
   }
}
