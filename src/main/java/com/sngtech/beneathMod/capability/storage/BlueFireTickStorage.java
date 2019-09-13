package com.sngtech.beneathMod.capability.storage;

import com.sngtech.beneathMod.capability.IBlueFireTick;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class BlueFireTickStorage implements IStorage<IBlueFireTick>
{
	public INBT writeNBT(Capability<IBlueFireTick> capability, IBlueFireTick instance, Direction side) 
	{
		CompoundNBT tag = new CompoundNBT();
		tag.putFloat("bluefiretick", instance.getBlueFireTick());
		return tag;
	}

	@Override
	public void readNBT(Capability<IBlueFireTick> capability, IBlueFireTick instance, Direction side, INBT nbt) 
	{
		CompoundNBT tag = new CompoundNBT();
		instance.setBlueFireTick(tag.getInt("bluefiretick"));
	}
}
