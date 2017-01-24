package com.suatae.tu.common.block;

import com.suatae.tu.common.block.BlockUrn;
import com.suatae.tu.common.block.TileEntityUrn;
import com.suatae.tu.lib.Ref;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

@GameRegistry.ObjectHolder(Ref.MOD_ID)
public class BlockReg {
	
	public static final Block blockUrn = new BlockUrn();

	public static void init(){
		GameRegistry.registerBlock(blockUrn, "blockUrn");
		GameRegistry.registerTileEntity(TileEntityUrn.class, "TileEntityUrn");
	}
}
