package com.sngtech.beneathMod.tileentities.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import com.sngtech.beneathMod.Main;
import com.sngtech.beneathMod.blocks.tileentities.dryingracks.DecayedPlanksDryingRackBlock;
import com.sngtech.beneathMod.tileentities.dryingracks.DryingRackTileEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ForgeHooksClient;

@SuppressWarnings("deprecation")
@OnlyIn(Dist.CLIENT)
public class DryingRackRenderer<T extends TileEntity> extends TileEntityRenderer<T>
{
	@Override
	public void render(T tileEntityIn, double x, double y, double z, float partialTicks, int destroyStage) 
	{
		if(tileEntityIn instanceof DryingRackTileEntity)
		{
			Direction direction = ((DryingRackTileEntity) tileEntityIn).getBlockState().get(DecayedPlanksDryingRackBlock.FACING);
			ItemStack itemstack = ((DryingRackTileEntity) tileEntityIn).getInventory().getStackInSlot(0);
			
		    if (itemstack != ItemStack.EMPTY) 
		    {
		       Main.logger.debug("renderer");
		       GlStateManager.pushMatrix();
		       GlStateManager.translatef((float)x + 0.5F, (float)y + 0.44921875F, (float)z + 0.5F);
		       Direction direction1 = Direction.byHorizontalIndex((direction.getHorizontalIndex()) % 4);
		       GlStateManager.rotatef(-direction1.getHorizontalAngle(), 0.0F, 1.0F, 0.0F);
		       GlStateManager.rotatef(90.0F, 1.0F, 0.0F, 0.0F);
		       GlStateManager.translatef(-0.3125F, -0.3125F, 0.0F);
		       GlStateManager.scalef(0.375F, 0.375F, 0.375F);
		       
		       IBakedModel model = Minecraft.getInstance().getItemRenderer().getItemModelWithOverrides(itemstack, tileEntityIn.getWorld(), null);
			   model = ForgeHooksClient.handleCameraTransforms(model, TransformType.NONE, false);
		       Minecraft.getInstance().getItemRenderer().renderItem(itemstack, model);
		       GlStateManager.popMatrix();
		    }
		}
	}
}
