package com.suatae.tu.event;

import net.minecraftforge.common.MinecraftForge;

public class TU_EventHandler {

public static void preInit() {}
	
	public static void init() {
		MinecraftForge.EVENT_BUS.register(new UrnEvent());
	}
	
	public static void postInit() {}
}
