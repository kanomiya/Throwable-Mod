/*
 * Copyright (c) 2017 和宮 葵(Kanomiya Aoi)
 */
package com.kanomiya.mcmod.throwable;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/**
 * Created by 和宮 葵(Kanomiya) in 2017/03.
 */
public class EntityStoneNugget extends EntityThrowable {
    private float hitDamage = 2.0F;

    public EntityStoneNugget(World worldIn) {
        super(worldIn);
    }

    public EntityStoneNugget(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    public EntityStoneNugget(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    public void setHitDamage(float hitDamage) {
        this.hitDamage = hitDamage;
    }

    public float getHitDamage() {
        return hitDamage;
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (result.entityHit instanceof EntityLivingBase) {
            final EntityLivingBase thrower = getThrower();

            if (result.entityHit instanceof EntityLivingBase) {
                final EntityLivingBase entityHit = (EntityLivingBase) result.entityHit;
                EnchantmentHelper.applyThornEnchantments(entityHit, thrower);
            }
            EnchantmentHelper.applyArthropodEnchantments(thrower, result.entityHit);

            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, thrower), getHitDamage());
        }

        if (! world.isRemote)
            setDead();
    }

}
