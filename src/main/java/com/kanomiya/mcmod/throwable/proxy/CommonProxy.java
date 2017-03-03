/*
 * Copyright (c) 2017 和宮 葵(Kanomiya Aoi)
 */
package com.kanomiya.mcmod.throwable.proxy;

import com.kanomiya.mcmod.throwable.EntityStoneNugget;
import com.kanomiya.mcmod.throwable.ThrowableMod;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import static com.kanomiya.mcmod.throwable.ThrowableMod.MODID;
import static com.kanomiya.mcmod.throwable.ThrowableModItems.SLING_SHOT;
import static com.kanomiya.mcmod.throwable.ThrowableModItems.STONE_NUGGET;

/**
 * Created by 和宮 葵(Kanomiya) in 2017/03.
 */
public class CommonProxy {
    public void init() {
        GameRegistry.register(STONE_NUGGET, new ResourceLocation(MODID, "stone_nugget"));
        GameRegistry.register(SLING_SHOT, new ResourceLocation(MODID, "sling_shot"));

        GameRegistry.addShapelessRecipe(new ItemStack(STONE_NUGGET, 9), Item.getItemFromBlock(Blocks.COBBLESTONE));

        GameRegistry.addShapedRecipe(new ItemStack(Item.getItemFromBlock(Blocks.COBBLESTONE)), new Object[]{
                "NNN",
                "NNN",
                "NNN",
                'N', STONE_NUGGET,
        });

        GameRegistry.addShapedRecipe(new ItemStack(SLING_SHOT), new Object[]{
                "STS",
                " S ",
                'S', Items.STICK,
                'T', Items.STRING,
        });


        EntityRegistry.registerModEntity(new ResourceLocation(MODID, "stone_nugget"), EntityStoneNugget.class, "stone_nugget", 0, ThrowableMod.INSTANCE, 64, 8, true);

        OreDictionary.registerOre("nuggetStone", STONE_NUGGET);
    }
}
