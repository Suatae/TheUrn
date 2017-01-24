package com.suatae.tu;

import com.suatae.tu.common.block.BlockReg;
import com.suatae.tu.event.TU_EventHandler;
import com.suatae.tu.lib.Ref;
import com.suatae.tu.proxy.CommonProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Ref.MOD_ID, name = Ref.MOD_NAME, version = Ref.VERSION)

public class TheUrn {
	@Mod.Instance(Ref.MOD_ID)
	public static TheUrn instance;
	
	@SidedProxy(clientSide = Ref.CLIENTSIDE, serverSide = Ref.SERVERSIDE)
	public static CommonProxy		proxy;
	
	@Mod.EventHandler
	public static void PreLoad(FMLPreInitializationEvent event) {
		TU_EventHandler.preInit();
	}

	@Mod.EventHandler
	public static void Load(FMLInitializationEvent event) {
		BlockReg.init();
		TU_EventHandler.init();
		proxy.registerRenders();
	}

	@Mod.EventHandler
	public static void PostLoad(FMLPostInitializationEvent event) {
		TU_EventHandler.postInit();
	}

}
