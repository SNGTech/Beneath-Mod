package cout.sngtech.beneathMod.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.items.ItemStackHandler;

public class ModItemStackHelper 
{
	public static CompoundNBT saveAllItems(CompoundNBT tag, ItemStackHandler list) 
	{
		return saveAllItems(tag, list, true);
	}

	public static CompoundNBT saveAllItems(CompoundNBT tag, ItemStackHandler list, boolean saveEmpty) 
	{
		ListNBT listnbt = new ListNBT();

		for(int i = 0; i < list.getSlots(); ++i) 
		{
			ItemStack itemstack = list.getStackInSlot(i);
			if (!itemstack.isEmpty()) {
				CompoundNBT compoundnbt = new CompoundNBT();
				compoundnbt.putByte("Slot", (byte)i);
				itemstack.write(compoundnbt);
				listnbt.add(compoundnbt);
			}
		}

		if (!listnbt.isEmpty() || saveEmpty) 
		{
			tag.put("Items", listnbt);
		}

		return tag;
	}

	public static void loadAllItems(CompoundNBT tag, ItemStackHandler list) 
	{
	   ListNBT listnbt = tag.getList("Items", 10);

	   for(int i = 0; i < listnbt.size(); ++i) 
	   {
		   CompoundNBT compoundnbt = listnbt.getCompound(i);
		   int j = compoundnbt.getByte("Slot") & 255;
		   if (j >= 0 && j < list.getSlots()) 
		   {
			   list.setStackInSlot(j, ItemStack.read(compoundnbt));
		   }
	   }
	}
}
