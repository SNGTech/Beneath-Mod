package com.sngtech.beneathMod.capability;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;

public class BlueFireTick implements IBlueFireTick
{
	private int blueFire = 0;
	
	@Override
	public boolean isBurning() 
	{
		return this.blueFire > 0;
	}

	@Override
	public void setBlueFireTick(int seconds) 
	{
		int i = seconds * 20;
		
		if(this.blueFire < i)
		{
			this.blueFire = i;
		}
	}
	
	@Override
	public int getBlueFireTick() 
	{
		return (int) this.blueFire;
	}

	@Override
	public void tick(Entity e) 
	{
		if(this.blueFire < 0)
		{
			blueFire = 0;
		}
		if (this.blueFire % 20 == 0) 
		{
			e.attackEntityFrom(DamageSource.ON_FIRE, 3.0F);
		}
		
		--blueFire;
	}
}
