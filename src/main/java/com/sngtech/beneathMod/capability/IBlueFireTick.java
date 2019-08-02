package com.sngtech.beneathMod.capability;

import net.minecraft.entity.Entity;

public interface IBlueFireTick 
{
	boolean isBurning();
	
	void setBlueFire(final int seconds);
	
	int getBlueFire();
	
	void tick(Entity entity);
}
