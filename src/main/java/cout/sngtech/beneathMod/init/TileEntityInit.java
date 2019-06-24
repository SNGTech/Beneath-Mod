package cout.sngtech.beneathMod.init;

import cout.sngtech.beneathMod.Main;
import cout.sngtech.beneathMod.tileentities.AcaciaCrateTileEntity;
import cout.sngtech.beneathMod.tileentities.BirchCrateTileEntity;
import cout.sngtech.beneathMod.tileentities.DarkOakCrateTileEntity;
import cout.sngtech.beneathMod.tileentities.JungleCrateTileEntity;
import cout.sngtech.beneathMod.tileentities.OakCrateTileEntity;
import cout.sngtech.beneathMod.tileentities.SpruceCrateTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Main.MODID)
public class TileEntityInit 
{
	//Storage
	public static final TileEntityType<?> OAK_CRATE = null;
	public static final TileEntityType<?> SPRUCE_CRATE = null;
	public static final TileEntityType<?> BIRCH_CRATE = null;
	public static final TileEntityType<?> JUNGLE_CRATE = null;
	public static final TileEntityType<?> ACACIA_CRATE = null;
	public static final TileEntityType<?> DARK_OAK_CRATE = null;
	
	//Machines (Heat Operated)
	//public static final TileEntityType<?> BLOCK_BREAKER = null;
	
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents
	{
		@SubscribeEvent
		public static void registerTileEntities(final RegistryEvent.Register<TileEntityType<?>> e)
		{
			e.getRegistry().registerAll
			(
				//Storage
				TileEntityType.Builder.create(OakCrateTileEntity::new, BlockInit.OAK_CRATE).build(null).setRegistryName(Main.MODID, "oak_crate"),
				TileEntityType.Builder.create(SpruceCrateTileEntity::new, BlockInit.SPRUCE_CRATE).build(null).setRegistryName(Main.MODID, "spruce_crate"),
				TileEntityType.Builder.create(BirchCrateTileEntity::new, BlockInit.BIRCH_CRATE).build(null).setRegistryName(Main.MODID, "birch_crate"),
				TileEntityType.Builder.create(JungleCrateTileEntity::new, BlockInit.JUNGLE_CRATE).build(null).setRegistryName(Main.MODID, "jungle_crate"),
				TileEntityType.Builder.create(AcaciaCrateTileEntity::new, BlockInit.ACACIA_CRATE).build(null).setRegistryName(Main.MODID, "acacia_crate"),
				TileEntityType.Builder.create(DarkOakCrateTileEntity::new, BlockInit.DARK_OAK_CRATE).build(null).setRegistryName(Main.MODID, "dark_oak_crate")
				
				//Machines (Heat Operated)
				//TileEntityType.Builder.create(TileEntityBlockBreaker::new, BlockInit.BLOCK_BREAKER).build(null).setRegistryName(Main.MODID, "block_breaker")
			);
			
			Main.logger.debug("Registered Tile Entities");
		}
	}
}
