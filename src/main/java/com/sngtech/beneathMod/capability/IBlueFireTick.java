package com.sngtech.beneathMod.capability;

import net.minecraft.entity.Entity;

public interface IBlueFireTick 
{
	boolean isBurning();
	
	void setBlueFireTick(final int seconds);
	
	int getBlueFireTick();
	
	void tick(Entity entity);
}
