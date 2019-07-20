package com.sngtech.beneathMod.world.gen.features;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockMatcher;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class ModOreFeatureConfig implements IFeatureConfig
{
	public final ModFillerBlockType target;
	public final int size;
	public final BlockState state;
	
	public ModOreFeatureConfig(ModFillerBlockType target, BlockState state, int size) 
	{
		this.size = size;
		this.state = state;
		this.target = target;
	}
	
	public <T> Dynamic<T> serialize(DynamicOps<T> ops) 
	{
		return new Dynamic<>(ops, ops.createMap(ImmutableMap.of(ops.createString("size"), ops.createInt(this.size), ops.createString("target"), ops.createString(this.target.func_214737_a()), ops.createString("state"), BlockState.serialize(ops, this.state).getValue())));
	}

	public static ModOreFeatureConfig deserialize(Dynamic<?> p_214641_0_) 
	{
		int i = p_214641_0_.get("size").asInt(0);
		ModFillerBlockType fillerBlockType = ModFillerBlockType.func_214736_a(p_214641_0_.get("target").asString(""));
		BlockState blockstate = p_214641_0_.get("state").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState());
		return new ModOreFeatureConfig(fillerBlockType, blockstate, i);
	}
	
	public static enum ModFillerBlockType
	{
		NATURAL_STONE("natural_stone", (p_214739_0_) -> 
		{
	         if (p_214739_0_ == null) 
	         {
	            return false;
	         } 
	         else 
	         {
	            Block block = p_214739_0_.getBlock();
	            return block == Blocks.STONE || block == Blocks.GRANITE || block == Blocks.DIORITE || block == Blocks.ANDESITE;
	         }
	      }
		),
	    NETHERRACK("netherrack", new BlockMatcher(Blocks.NETHERRACK)),
	    ENDSTONE("endstone", new BlockMatcher(Blocks.END_STONE)),
		SAND("sand", new BlockMatcher(Blocks.SAND));
		
		private static final Map<String, ModFillerBlockType> fillerTypes = Arrays.stream(values()).collect(Collectors.toMap(ModFillerBlockType::func_214737_a, (p_214740_0_) -> 
		{
			return p_214740_0_;
		}));
		private final String registryName;
      	private final Predicate<BlockState> blockstate;

      	private ModFillerBlockType(String name, Predicate<BlockState> block) 
      	{
    	  this.registryName = name;
    	  this.blockstate = block;
      	}
      	
      	public String func_214737_a() 
      	{
            return this.registryName;
         }

         public static ModFillerBlockType func_214736_a(String p_214736_0_) 
         {
            return fillerTypes.get(p_214736_0_);
         }

         public Predicate<BlockState> func_214738_b() {
            return this.blockstate;
         }
	}
}
