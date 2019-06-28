package com.sngtech.beneathMod.entities.models;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * ModelCreeper - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
public class ColdCreeperModel<T extends Entity> extends EntityModel<T>
{
    public RendererModel body;
    public RendererModel legRightBack;
    public RendererModel rightLegFront;
    public RendererModel leftLegFront;
    public RendererModel head;
    public RendererModel legLeftBack;
    public RendererModel coldCreeperArmor;

    public ColdCreeperModel()
    {
        this(0.0F);
    }
    
    public ColdCreeperModel(float size) 
    {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.leftLegFront = new RendererModel(this, 0, 16);
        this.leftLegFront.setRotationPoint(-2.0F, 18.0F, -4.0F);
        this.leftLegFront.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, size);
        this.legLeftBack = new RendererModel(this, 0, 16);
        this.legLeftBack.setRotationPoint(-2.0F, 18.0F, 4.0F);
        this.legLeftBack.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, size);
        this.head = new RendererModel(this, 0, 0);
        this.head.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, size);
        this.body = new RendererModel(this, 16, 16);
        this.body.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, size);
        this.rightLegFront = new RendererModel(this, 0, 16);
        this.rightLegFront.setRotationPoint(2.0F, 18.0F, -4.0F);
        this.rightLegFront.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, size);
        this.legRightBack = new RendererModel(this, 0, 16);
        this.legRightBack.setRotationPoint(2.0F, 18.0F, 4.0F);
        this.legRightBack.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, size);
        this.coldCreeperArmor = new RendererModel(this, 32, 0);
        this.coldCreeperArmor.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.coldCreeperArmor.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, size + 0.5F);
    }
    
    @Override
    public void render(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.head.render(scale);
        this.body.render(scale);
        this.leftLegFront.render(scale);
        this.rightLegFront.render(scale);
        this.legLeftBack.render(scale);
        this.legRightBack.render(scale);
    }

    @Override
    public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor)
    {
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.head.rotateAngleX = headPitch * 0.017453292F;
        this.leftLegFront.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.rightLegFront.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.legLeftBack.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.legRightBack.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }
}
