package com.suatae.tu.client.renders.ir;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

public class IRUrn implements IItemRenderer{
	
	TileEntitySpecialRenderer	render;
	private TileEntity			entity;

	public IRUrn(TileEntitySpecialRenderer render, TileEntity tileEntity) {
		this.entity = tileEntity;
		this.render = render;
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		if (type == IItemRenderer.ItemRenderType.ENTITY) {
			GL11.glTranslatef(-0.75F, -0.5F, -0.75F);
			GL11.glScalef(1.4F, 1.4F, 1.4F);
			this.render.renderTileEntityAt(this.entity, 0.0D, 0.0D, 0.0D, 0.0F);
		}
		if (type == IItemRenderer.ItemRenderType.EQUIPPED) {
			GL11.glTranslatef(-0.1F, 0F, -0.1F);
			GL11.glScalef(1.2F, 1.2F, 1.2F);
			this.render.renderTileEntityAt(this.entity, 0.0D, 0.0D, 0.0D, 0.0F);
		}
		if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
			GL11.glTranslatef(-0.1F, 0F, -0.1F);
			GL11.glScalef(1.2F, 1.2F, 1.2F);
			this.render.renderTileEntityAt(this.entity, 0.0D, 0.0D, 0.0D, 0.0F);
		}
		if (type == IItemRenderer.ItemRenderType.INVENTORY) {
			GL11.glTranslatef(-0.5F, -0.45F, -0.5F);
			GL11.glScalef(1.2F, 1.2F, 1.2F);
			this.render.renderTileEntityAt(this.entity, 0.0D, 0.0D, 0.0D, 0.0F);
		}
	}

}