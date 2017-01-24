package com.suatae.tu.util;

import com.suatae.tu.lib.Ref;

import net.minecraft.util.ResourceLocation;

public class ResourceUtil {
	public static ResourceLocation getLocation(String modId, String name) {
		return new ResourceLocation(modId, name);
	}

	public static ResourceLocation getLocation(String name) {
		return getLocation(Ref.MOD_ID.toLowerCase(), "textures/models/" + name + ".png");
	}

}
