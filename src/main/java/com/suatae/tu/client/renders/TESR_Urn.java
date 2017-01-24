package com.suatae.tu.client.renders;

import org.lwjgl.opengl.GL11;

import com.suatae.tu.client.models.Urn;
import com.suatae.tu.util.ResourceUtil;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public abstract class TESR_Urn extends TileEntitySpecialRenderer{
	
	private static final ResourceLocation	Urn_t	= ResourceUtil.getLocation("Urn");
	private Urn						Urn_m;

	public TESR_Urn() {
		this.Urn_m = new Urn();
	}
	
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) {	
		GL11.glPushMatrix();
		GL11.glTranslated((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
        this.bindTexture(Urn_t);
     
		GL11.glPushMatrix();
		GL11.glRotatef(-180F, 0F, 0F, 1F);
		GL11.glScalef(1F, 1F, 1F);
		this.Urn_m.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
	
	protected int shouldrenderPass() {
		return 0;
	}

	private void adjustLightFixture(World world, int x, int y, int z, Block block) {
		Tessellator tess = Tessellator.instance;
		float brightness = block.getLightValue(world, x, y, z);
		int skyLight = world.getLightBrightnessForSkyBlocks(x, y, z, 0);
		int modulousModifier = skyLight % 65536;
		int divModifier = skyLight / 65536;
		tess.setColorOpaque_F(brightness, brightness, brightness);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, modulousModifier, divModifier);
		
	}
}
