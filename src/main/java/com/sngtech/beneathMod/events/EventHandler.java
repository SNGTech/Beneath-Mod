package com.sngtech.beneathMod.events;

import com.sngtech.beneathMod.Main;
import com.sngtech.beneathMod.capability.IBlueFireTick;
import com.sngtech.beneathMod.capability.provider.BlueFireTickProvider;
import com.sngtech.beneathMod.init.BiomeInit;
import com.sngtech.beneathMod.utils.ModRenderOverlays;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogDensity;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EventHandler 
{
	private static boolean isPlayerInMaterial = false;
	private static boolean isNight = false;
	static IFluidState iFluidState;
	static float t0 = 0;
	static float t1 = 0;
	
	@SubscribeEvent
	public static void onPlayerEnterVoidEvent(PlayerTickEvent e)
	{
		PlayerEntity player = e.player;
		if(player.posY <= -2)
		{
			// To be removed when layer two is created
			// SENDING PLAYER TO LAYER ONE IS ONLY TEMPORARY!!!
			player.sendMessage(new TranslationTextComponent("TODO: TRANSPORT PLAYER TO LAYER 2 OF THE WORLD!!!"));
		}
	}
	
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public static void fogDensityEvent(FogDensity event)
    {
    	iFluidState = event.getInfo().getFluidState();
    	
    	if (Minecraft.getInstance().world != null && !isPlayerInMaterial)
        {
        	ClientPlayerEntity clientplayerentity = (ClientPlayerEntity)event.getInfo().getRenderViewEntity();
        	Biome biome = clientplayerentity.world.getBiome(new BlockPos(clientplayerentity));
        	
        	if (event.getInfo().getRenderViewEntity() instanceof LivingEntity) 
        	{
                if (event.getInfo().getRenderViewEntity() instanceof ClientPlayerEntity) 
                {
                	if (biome == BiomeInit.AM_PLAINS)
            		{
						if (iFluidState.isTagged(FluidTags.WATER))
						{
							event.setDensity(0.5F);
							isPlayerInMaterial = true;
						}
						else if (iFluidState.isTagged(FluidTags.LAVA))
						{ 
							event.setDensity(2.0F);
							isPlayerInMaterial = true;
						}
						else if(isNight)
						{
							if(t1 >= 1F)
							{
								t1 = 0;
							}
							
							if(t0 <= 1F)
							{
								t0 += 0.002F;
								t0 = MathHelper.clamp(t0, 0F, 1F);
								event.setDensity(MathHelper.lerp(t0, 0.02F, 0.001F));
							}
							isPlayerInMaterial = false;
						}
						else
						{
							if(t0 >= 1F)
							{
								t0 = 0;
							}
							
							if(t1 <= 1F)
							{
								t1 += 0.002F;
								t1 = MathHelper.clamp(t1, 0F, 1F);
								event.setDensity(MathHelper.lerp(t1, 0.001F, 0.02F));
							}
							isPlayerInMaterial = false;
						}
            		}
		            else if(biome != BiomeInit.AM_PLAINS)
		            {
		            	event.setDensity(0F);
		            }
                }
        	}
        }
		//Handle Blindness Fog Event
  
		//Main.logger.debug("Fog Density: " + event.getDensity() + " : " + t0 + " : " + t1);
		event.setCanceled(true); // must cancel event for event handler to take effect
    }

    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public static void fogColorEvent(EntityViewRenderEvent.FogColors event)
    {
    	iFluidState = event.getInfo().getFluidState();
    	
    	if (Minecraft.getInstance().world != null && !isPlayerInMaterial)
        {
        	ClientPlayerEntity clientplayerentity = (ClientPlayerEntity)event.getInfo().getRenderViewEntity();
        	Biome biome = clientplayerentity.world.getBiome(new BlockPos(clientplayerentity));
        	
        	if (event.getInfo().getRenderViewEntity() instanceof LivingEntity) 
        	{
                if (event.getInfo().getRenderViewEntity() instanceof ClientPlayerEntity) 
                {
                	if (Minecraft.getInstance().world.canBlockSeeSky(new BlockPos(Minecraft.getInstance().getRenderViewEntity().posX, Minecraft.getInstance().getRenderViewEntity().posY + (double)Minecraft.getInstance().getRenderViewEntity().getEyeHeight(), Minecraft.getInstance().getRenderViewEntity().posZ)) 
                	&& biome == BiomeInit.AM_PLAINS)
            		{
            			int color = 0xD89B2C;
            			event.setRed((color >> 16 & 255) / 255.0F);
            			event.setGreen((color >> 8 & 255) / 255.0F);
            	        event.setBlue((color & 255) / 255.0F);
            	        isNight = false;
            		}
                	
                	else if(!Minecraft.getInstance().world.canBlockSeeSky(new BlockPos(Minecraft.getInstance().getRenderViewEntity().posX, Minecraft.getInstance().getRenderViewEntity().posY + (double)Minecraft.getInstance().getRenderViewEntity().getEyeHeight(), Minecraft.getInstance().getRenderViewEntity().posZ)) 
                	&& biome == BiomeInit.AM_PLAINS)
                	{
                		isNight = true;
                		int color = 0xFFFFFF;
            			event.setRed((color >> 16 & 255) / 255.0F);
            			event.setGreen((color >> 8 & 255) / 255.0F);
            	        event.setBlue((color & 255) / 255.0F);
                	}
                }
            } 
    	
	    	if (iFluidState.isTagged(FluidTags.WATER))
	    	{
	    		int color = 0x000000;
				event.setRed((color >> 16 & 255) / 255.0F);
				event.setGreen((color >> 8 & 255) / 255.0F);
		        event. setBlue((color & 255) / 255.0F);
	    	}  
	    	
	    	else if (iFluidState.isTagged(FluidTags.LAVA))
	    	{
	    		int color = 0xFF2A00;
				event.setRed((color >> 16 & 255) / 255.0F);
				event.setGreen((color >> 8 & 255) / 255.0F);
		        event.setBlue((color & 255) / 255.0F);
	    	}
	    	
	    	else if(!isPlayerInMaterial && biome == BiomeInit.AM_PLAINS)
	    	{
	    		int color = 0xD89B2C;
				event.setRed((color >> 16 & 255) / 255.0F);
				event.setGreen((color >> 8 & 255) / 255.0F);
		        event.setBlue((color & 255) / 255.0F);
	    	}
	    	
	    	else if(!isPlayerInMaterial && biome != BiomeInit.AM_PLAINS)
	    	{
	    		int color = 0xFFFFFF;
				event.setRed((color >> 16 & 255) / 255.0F);
				event.setGreen((color >> 8 & 255) / 255.0F);
		        event.setBlue((color & 255) / 255.0F);
	    	}
        }
    }
    
    @SubscribeEvent
	public static void attachCapabilities(final AttachCapabilitiesEvent<Entity> event) 
	{
		if (event.getObject() instanceof LivingEntity) 
		{
			event.addCapability(new ResourceLocation(Main.MODID, "bluefiretick"), new BlueFireTickProvider());
		}
	}
    
    @SubscribeEvent
    public static void entityTickEvent(LivingUpdateEvent e)
    {
    	Entity entity = e.getEntity();
    	if(entity instanceof LivingEntity)
    	{
    		//Main.logger.info(ModRenderOverlays.collidedwithBlueFire(entity.getEntity().world, entity.getBoundingBox().shrink(0.001D)));
    		LazyOptional<IBlueFireTick> bluefiretick = entity.getCapability(BlueFireTickProvider.BLUE_FIRE_TICK_CAP, null);
    		bluefiretick.ifPresent(data -> 
    		{
    			if(data.getBlueFireTick() <= 0 && ModRenderOverlays.collidedwithBlueFire(entity.getEntity().world, entity.getBoundingBox().shrink(0.001D)))
    			{
					data.tick(e.getEntity());
					Main.logger.info("Entity: " + e.getEntity() + " Tick: " + data.getBlueFireTick());
    			}
    		});
    	}
    }
    
    @SuppressWarnings("rawtypes")
    @SubscribeEvent
    public static void renderModFiresEvent(RenderLivingEvent.Post e)
    {
    	Entity entity = e.getEntity();
    	if(entity instanceof LivingEntity)
    	{
    		LazyOptional<IBlueFireTick> bluefiretick = entity.getCapability(BlueFireTickProvider.BLUE_FIRE_TICK_CAP, null);
    		bluefiretick.ifPresent(data -> 
    		{
    			if(data.getBlueFireTick() <= 0 && ModRenderOverlays.collidedwithBlueFire(entity.getEntity().world, entity.getBoundingBox().shrink(0.001D)))
    			{
    				data.setBlueFireTick(20);
    				data.isBurning();
    				ModRenderOverlays.renderEntityOnBlueFire(entity, e.getRenderer(), e.getX(), e.getY(), e.getZ(), 1.0F);
    			}
    		});
    	}
    }
}
