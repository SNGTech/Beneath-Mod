package cout.sngtech.beneathMod.init;

import cout.sngtech.beneathMod.guis.GuiCrate;
import cout.sngtech.beneathMod.tileentities.TileEntityOakCrate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class GuiHandler
{
	public static GuiScreen openGui(FMLPlayMessages.OpenContainer openContainer)
    {
        BlockPos pos = openContainer.getAdditionalData().readBlockPos();

        if (openContainer.getId().equals(openContainer.getId()))
        {
        	return new GuiCrate(Minecraft.getInstance().player.inventory, (TileEntityOakCrate)Minecraft.getInstance().world.getTileEntity(new BlockPos(pos.getX(), pos.getY(), pos.getZ())), Minecraft.getInstance().player);
        }

        return null;
    }
}
