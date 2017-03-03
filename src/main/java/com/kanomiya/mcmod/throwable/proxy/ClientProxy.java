/*
 * Copyright (c) 2017 和宮 葵(Kanomiya Aoi)
 */
package com.kanomiya.mcmod.throwable.proxy;

import com.kanomiya.mcmod.throwable.EntityStoneNugget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

import static com.kanomiya.mcmod.throwable.ThrowableModItems.SLING_SHOT;
import static com.kanomiya.mcmod.throwable.ThrowableModItems.STONE_NUGGET;

/**
 * Created by 和宮 葵(Kanomiya) in 2017/03.
 */
public class ClientProxy extends CommonProxy {
    @Override
    public void init() {
        super.init();

        ModelLoader.setCustomModelResourceLocation(STONE_NUGGET, 0, new ModelResourceLocation(STONE_NUGGET.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(SLING_SHOT, 0, new ModelResourceLocation(SLING_SHOT.getRegistryName(), "inventory"));

        Minecraft mc = Minecraft.getMinecraft();

        RenderingRegistry.registerEntityRenderingHandler(EntityStoneNugget.class, renderManager -> new RenderSnowball(renderManager, STONE_NUGGET, mc.getRenderItem()));
    }
}
