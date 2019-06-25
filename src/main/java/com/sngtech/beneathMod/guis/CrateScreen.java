package com.sngtech.beneathMod.guis;

import com.mojang.blaze3d.platform.GlStateManager;
import com.sngtech.beneathMod.Main;
import com.sngtech.beneathMod.containers.CrateContainer;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CrateScreen extends ContainerScreen<CrateContainer>
{
	private static final ResourceLocation GUI_CRATE = new ResourceLocation(Main.MODID + ":textures/gui/container/crate.png");

	public CrateScreen(CrateContainer crate, PlayerInventory playerInv, ITextComponent name) 
	{
		super(crate, playerInv, name);
		this.passEvents = false;
	}
	
	@Override
	public void render(int mouseX, int mouseY, float partialTicks) 
	{
		this.renderBackground();
		super.render(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) 
	{
		this.font.drawString(this.title.getUnformattedComponentText(), 8, 6, 4210752);
		this.font.drawString(this.playerInventory.getDisplayName().getUnformattedComponentText(), 8, this.ySize - 93, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color4f(1.0f, 1.0f, 1.0f, 1.0f);
		this.minecraft.getTextureManager().bindTexture(GUI_CRATE);
		int i = (this.width - this.xSize) / 2;
	    int j = (this.height - this.ySize) / 2;
	    this.blit(i, j, 0, 0, this.xSize, this.ySize);
	}
}
