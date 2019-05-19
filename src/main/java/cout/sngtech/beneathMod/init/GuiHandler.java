package cout.sngtech.beneathMod.init;

import cout.sngtech.beneathMod.guis.GuiCrate;
import cout.sngtech.beneathMod.tileentities.TileEntityOakCrate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class GuiHandler
{
	public static GuiScreen openGui(FMLPlayMessages.OpenContainer openContainer)
    {
        BlockPos pos = openContainer.getAdditionalData().readBlockPos();

        if (openContainer.getId().equals(openContainer.getId()))
        {
        	return new GuiCrate((InventoryPlayer)Minecraft.getInstance().player.inventory, (TileEntityOakCrate)Minecraft.getInstance().world.getTileEntity(new BlockPos(pos.getX(), pos.getY(), pos.getZ())), (EntityPlayer) Minecraft.getInstance().player);
        }

        return null;
    }
}
