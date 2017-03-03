/*
 * Copyright (c) 2017 和宮 葵(Kanomiya Aoi)
 */
package com.kanomiya.mcmod.throwable;

import net.minecraft.creativetab.CreativeTabs;

/**
 * Created by 和宮 葵(Kanomiya) in 2017/03.
 */
public class ItemStoneNugget extends ItemThrower {

    public ItemStoneNugget() {
        setUnlocalizedName(ThrowableMod.MODID + ".stone_nugget");
        setCreativeTab(CreativeTabs.MATERIALS);
        setMaxStackSize(64);
    }

    @Override
    public float getVelocityIncreasePerTick() {
        return 0.5F /20F;
    }

    @Override
    public float getMaxVelocity() {
        return 0.8F;
    }

    @Override
    public float getHitDamage(float velocityRate) {
        return 1.0F *velocityRate;
    }

    @Override
    public float getInAccuracy() {
        return 3.0F;
    }

}
