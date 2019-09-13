package com.sngtech.beneathMod.capability.provider;

import com.sngtech.beneathMod.capability.IBlueFireTick;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class BlueFireTickProvider implements ICapabilitySerializable<INBT> 
{
	@CapabilityInject(IBlueFireTick.class)
	public static final Capability<IBlueFireTick> BLUE_FIRE_TICK_CAP = null;

	private LazyOptional<IBlueFireTick> instance = LazyOptional.of(BLUE_FIRE_TICK_CAP::getDefaultInstance);

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) 
	{
		return cap == BLUE_FIRE_TICK_CAP ? instance.cast() : LazyOptional.empty();
	}

	@Override
	public INBT serializeNBT() 
	{
		return BLUE_FIRE_TICK_CAP.getStorage().writeNBT(BLUE_FIRE_TICK_CAP, this.instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!")), null);
	}

	@Override
	public void deserializeNBT(INBT nbt) 
	{
		BLUE_FIRE_TICK_CAP.getStorage().readNBT(BLUE_FIRE_TICK_CAP, this.instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!")), null, nbt);
	}

}
