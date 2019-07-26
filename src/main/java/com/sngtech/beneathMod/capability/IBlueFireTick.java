package com.sngtech.beneathMod.capability;

public interface IBlueFireTick 
{
	boolean isBurning();
	
	void setBlueFire(final int seconds);
	
	int getBlueFire();
	
	void tick();
}
