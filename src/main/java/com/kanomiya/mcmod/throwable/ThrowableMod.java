/*
 * Copyright (c) 2017 和宮 葵(Kanomiya Aoi)
 */
package com.kanomiya.mcmod.throwable;

import com.kanomiya.mcmod.throwable.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by 和宮 葵(Kanomiya Aoi) in 2017/03.
 */
@Mod(modid = ThrowableMod.MODID, name = ThrowableMod.MODNAME, version = ThrowableMod.VERSION)
public class ThrowableMod {
    public static final String MODID = "com.kanomiya.mcmod.throwable";
    public static final String MODNAME = "Throwable Mod";
    public static final String VERSION = "0.1.0";

    @Mod.Instance(MODID)
    public static ThrowableMod INSTANCE;

    @SidedProxy(clientSide = MODID + ".proxy.ClientProxy", serverSide =  MODID + ".proxy.CommonProxy", modId = MODID)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.init();
    }

}
