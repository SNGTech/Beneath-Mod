package com.sngtech.beneathMod.world.features.structures;

import com.sngtech.beneathMod.Main;

import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Main.MODID)
public class StructurePieceInit 
{
	public static IStructurePieceType TEST;
	
	public static void registerPieces()
	{
		TEST = IStructurePieceType.register(TestPieces.Piece::new, "test");
	}
}
