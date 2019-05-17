package cout.sngtech.beneathMod.guis;

import cout.sngtech.beneathMod.Main;
import cout.sngtech.beneathMod.containers.ContainerCrate;
import cout.sngtech.beneathMod.tileentities.TileEntityOakCrate;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiCrate extends GuiContainer
{
	private static final ResourceLocation GUI_CRATE = new ResourceLocation(Main.MODID + ":textures/gui/container/crate.png");
	private final InventoryPlayer playerInventory;
	private final TileEntityOakCrate tileentity;
	
	public GuiCrate(InventoryPlayer playerInv, TileEntityOakCrate crateInventory, EntityPlayer player) 
	{
		super(new ContainerCrate(playerInv, crateInventory, player));
		this.playerInventory = playerInv;
		this.tileentity = crateInventory;
	}
	
	@Override
	public void render(int mouseX, int mouseY, float partialTicks) 
	{
		this.drawDefaultBackground();
		super.render(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) 
	{
		this.fontRenderer.drawString(this.tileentity.getDisplayName().getUnformattedComponentText(), 8, 6, 4210752);
		this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedComponentText(), 8, this.ySize - 111, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color4f(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(GUI_CRATE);
		int i = (this.width - this.xSize) / 2;
	    int j = (this.height - this.ySize) / 2;
	    this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
	}
}
