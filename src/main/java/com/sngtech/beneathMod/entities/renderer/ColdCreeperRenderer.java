package com.sngtech.beneathMod.entities.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import com.sngtech.beneathMod.Main;
import com.sngtech.beneathMod.entities.ColdCreeperEntity;
import com.sngtech.beneathMod.entities.models.ColdCreeperModel;
import com.sngtech.beneathMod.entities.renderer.layers.ColdCreeperChargeLayer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ColdCreeperRenderer extends MobRenderer<ColdCreeperEntity, ColdCreeperModel<ColdCreeperEntity>> 
{
   private static final ResourceLocation COLD_CREEPER_TEXTURE = new ResourceLocation(Main.MODID, "textures/entity/creeper/cold_creeper.png");

   public ColdCreeperRenderer(EntityRendererManager renderManager) 
   {
      super(renderManager, new ColdCreeperModel<>(), 0.5F);
      this.addLayer(new ColdCreeperChargeLayer(this));
   }

   @Override
   protected void preRenderCallback(ColdCreeperEntity entitylivingbase, float partialTickTime) 
   {
      float f = entitylivingbase.getColdCreeperFlashIntensity(partialTickTime);
      float f1 = 1.0F + MathHelper.sin(f * 100.0F) * f * 0.01F;
      f = MathHelper.clamp(f, 0.0F, 1.0F);
      f = f * f;
      f = f * f;
      float f2 = (1.0F + f * 0.4F) * f1;
      float f3 = (1.0F + f * 0.1F) / f1;
      GlStateManager.scalef(f2, f3, f2);
   }

   @Override
   protected int getColorMultiplier(ColdCreeperEntity entitylivingbase, float lightBrightness, float partialTickTime) 
   {
      float f = entitylivingbase.getColdCreeperFlashIntensity(partialTickTime);
      if ((int)(f * 10.0F) % 2 == 0) 
      {
         return 0;
      } 
      else 
      {
         int i = (int)(f * 0.2F * 255.0F);
         i = MathHelper.clamp(i, 0, 255);
         return i << 24 | 15268351;
      }
   }

   @Override
   protected ResourceLocation getEntityTexture(ColdCreeperEntity entity) 
   {
      return COLD_CREEPER_TEXTURE;
   }
}