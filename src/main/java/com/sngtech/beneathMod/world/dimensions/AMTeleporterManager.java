package com.sngtech.beneathMod.world.dimensions;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;

public class AMTeleporterManager 
{
	public static void teleport(Entity entity)
	{
        if(!entity.world.isRemote)
        {
            DimensionType type = AMDimensionType.getDimensionType();
            if(entity.getRidingEntity() == null && !entity.isBeingRidden())
            {
            	entity.setPortal(new BlockPos(entity.posX, entity.posY, entity.posZ));
            	
                if (entity.timeUntilPortal > 0) 
                {
                    entity.timeUntilPortal = 10;
                }
                else if(entity.dimension != type)
                {
                    entity.timeUntilPortal = 10;
                    entity.changeDimension(type);
                }
                else if(entity.dimension == type)
                {
                    entity.timeUntilPortal = 10;
                    entity.changeDimension(DimensionType.OVERWORLD);
                }
            }
        }
    }
}
