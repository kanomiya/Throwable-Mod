/*
 * Copyright (c) 2017 和宮 葵(Kanomiya Aoi)
 */
package com.kanomiya.mcmod.throwable;

import net.minecraft.creativetab.CreativeTabs;

/**
 * Created by 和宮 葵(Kanomiya) in 2017/03.
 */
public class ItemSlingShot extends ItemThrower {

    public ItemSlingShot() {
        setUnlocalizedName(ThrowableMod.MODID + ".sling_shot");
        setCreativeTab(CreativeTabs.COMBAT);
        setMaxStackSize(1);
        setMaxDamage(128);
    }

    @Override
    public float getVelocityIncreasePerTick() {
        return 0.8F /20F;
    }

    @Override
    public float getMaxVelocity() {
        return 1.0F;
    }

    @Override
    public float getHitDamage(float velocityRate) {
        return 2.0F *velocityRate;
    }

    @Override
    public float getInAccuracy() {
        return 1.5F;
    }
}
