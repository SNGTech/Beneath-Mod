package com.sngtech.beneathMod.capability;

import com.sngtech.beneathMod.Main;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.FloatNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class BlueFireTickCapability 
{
	@CapabilityInject(IBlueFireTick.class)
	public static final Capability<IBlueFireTick> BLUE_FIRE_TICK = null;
	
	public static final ResourceLocation ID = new ResourceLocation(Main.MODID, "blue_fire_tick");
	
	public static void register() 
	{
		CapabilityManager.INSTANCE.register(IBlueFireTick.class, new IStorage<IBlueFireTick>() 
		{
			@Override
			public INBT writeNBT(Capability<IBlueFireTick> capability, IBlueFireTick instance, Direction side) 
			{
				return new FloatNBT(instance.getBlueFire());
			}
	
			@Override
			public void readNBT(Capability<IBlueFireTick> capability, IBlueFireTick instance, Direction side, INBT nbt) 
			{
				instance.setBlueFire(((IntNBT) nbt).getInt());
			}
		}, () -> new BlueFireTick(null));
	}
	
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	private static class EventHandler 
	{
		@SubscribeEvent
		public static void attachCapabilities(final AttachCapabilitiesEvent<Entity> event) 
		{
			if (event.getObject() instanceof LivingEntity) 
			{
				event.addCapability(ID, new BlueFireTickProvider());
			}
		}
	}
	
	public static class BlueFireTickProvider implements ICapabilitySerializable<INBT>
	{
        private final IBlueFireTick instance = BLUE_FIRE_TICK.getDefaultInstance();
		
		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) 
		{
			return BLUE_FIRE_TICK.orEmpty(cap, LazyOptional.of(() -> this.instance));
		}

		@Override
		public INBT serializeNBT() 
		{
			return BLUE_FIRE_TICK.writeNBT(instance, null);
		}

		@Override
		public void deserializeNBT(INBT nbt) 
		{
			BLUE_FIRE_TICK.readNBT(instance, null, nbt);
		}
	}
}
