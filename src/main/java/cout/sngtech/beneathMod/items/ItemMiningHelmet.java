package cout.sngtech.beneathMod.items;

import cout.sngtech.beneathMod.init.ItemInit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMiningHelmet extends ItemArmor
{
	public ItemMiningHelmet(IArmorMaterial materialIn, EntityEquipmentSlot slots, Properties builder) 
	{
		super(materialIn, slots, builder);
	}

	@Override
	public void onArmorTick(ItemStack stack, World world, EntityPlayer player) 
	{
		if(player.inventory.armorItemInSlot(3).getItem() == ItemInit.mining_helmet)
		{
			if (!world.isRemote) 
			{
	            //EntityDynamicLightSource light_source = new EntityDynamicLightSource(world, player, 1.0f);
	            //world.spawnEntity(light_source);
			}
		}
	}
}
