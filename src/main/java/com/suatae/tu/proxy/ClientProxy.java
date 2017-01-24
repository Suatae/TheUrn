package com.suatae.tu.proxy;

import com.suatae.tu.client.renders.TESR_Urn;
import com.suatae.tu.client.renders.ir.IRUrn;
import com.suatae.tu.common.block.BlockReg;
import com.suatae.tu.common.block.TileEntityUrn;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy{
	
	TileEntitySpecialRenderer	Urn	= new TESR_Urn() {};

	@Override
	public void registerRenders() {

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityUrn.class, Urn);

		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockReg.blockUrn), new IRUrn(
				Urn, new TileEntityUrn()));
	}
	
	@Override
	public EntityPlayer getClientPlayer() {
		return Minecraft.getMinecraft().thePlayer;
	}


}
