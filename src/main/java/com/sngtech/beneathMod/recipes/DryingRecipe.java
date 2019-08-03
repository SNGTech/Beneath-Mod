package com.sngtech.beneathMod.recipes;

import com.google.gson.JsonObject;
import com.sngtech.beneathMod.Main;
import com.sngtech.beneathMod.init.BlockInit;
import com.sngtech.beneathMod.init.RecipeInit;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class DryingRecipe implements IRecipe<IInventory>
{
	protected final ResourceLocation id;
	protected final ItemStack ingredient;
	protected final ItemStack result;
	protected final float experience;
	protected final int dryingTime;
	
	public DryingRecipe(ResourceLocation id, ItemStack input, ItemStack output, float experience, int dryingTime) 
	{
		this.id = id;
		this.ingredient = input;
		this.result = output;
		this.experience = experience;
		this.dryingTime = dryingTime;
	}
	
	@Override
	public boolean matches(IInventory inv, World worldIn) 
	{
		return this.ingredient.getItem() == inv.getStackInSlot(0).getItem();
	}

	@Override
	public ItemStack getCraftingResult(IInventory inv) 
	{
		return this.result.copy();
	}
	
	@Override
	public boolean canFit(int width, int height) 
	{
		return true;
	}

	public ItemStack getIngredient()
	{
		return this.ingredient;
	}
	
	public float getExperience()
	{
		return this.experience;
	}
	
	public int getDryingTime() 
	{
		return this.dryingTime;
	}
	
	@Override
	public ItemStack getRecipeOutput() 
	{
		return this.result;
	}

	@Override
	public ResourceLocation getId() 
	{
		return this.id;
	}
	
	@Override
	public ItemStack getIcon() 
	{
		return new ItemStack(BlockInit.DECAYED_PLANKS_DRYING_RACK);
	}

	@Override
	public IRecipeSerializer<?> getSerializer() 
	{
		return ForgeRegistries.RECIPE_SERIALIZERS.getValue(new ResourceLocation(Main.MODID, "drying"));
	}

	@Override
	public IRecipeType<?> getType() 
	{
		return RecipeInit.DRYING;
	}
	
	public static class Serializer<T extends DryingRecipe> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<DryingRecipe> 
	{
		@Override
		public DryingRecipe read(ResourceLocation recipeId, JsonObject json) 
		{
			JsonObject inputobject = json.getAsJsonObject("ingredient");
			Item inputItem = null;
			if (inputobject.has("item"))
			{
				inputItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(inputobject.get("item").getAsString()));
			}
			
			ItemStack input = null;
			if (inputItem != null)
			{
				input = new ItemStack(inputItem, JSONUtils.getInt(inputobject, "amount", 1));
			}

			JsonObject resultobject = json.getAsJsonObject("result");
			Item resultitem = null;
			if (resultobject.has("item"))
			{
				resultitem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(resultobject.get("item").getAsString()));
			}
			
			ItemStack result = null;
			if(resultitem != null)
			{
				result = new ItemStack(resultitem);
			}

			if (input != null && result != null)
			{
				float experience = JSONUtils.getFloat(json, "experience", 0.0F);
				int dryingTime = JSONUtils.getInt(json, "dryingtime", 100);
				return new DryingRecipe(recipeId, input, result, experience, dryingTime);
			} 
			else
			{
				throw new IllegalStateException("Item did not exist:" + recipeId.toString());
			}
		}

		@Override
		public DryingRecipe read(ResourceLocation recipeId, PacketBuffer buffer) 
		{
			ItemStack input = buffer.readItemStack();
			ItemStack result = buffer.readItemStack();
			float experience = buffer.readFloat();
			int dryingTime = buffer.readVarInt();
			return new DryingRecipe(recipeId, input, result, experience, dryingTime);
		}

		@Override
		public void write(PacketBuffer buffer, DryingRecipe recipe) 
		{
			buffer.writeItemStack(recipe.ingredient);
			buffer.writeItemStack(recipe.result);
			buffer.writeFloat(recipe.experience);
			buffer.writeVarInt(recipe.dryingTime);
		}
	}
}
