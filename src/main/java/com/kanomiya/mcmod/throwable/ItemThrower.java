/*
 * Copyright (c) 2017 和宮 葵(Kanomiya Aoi)
 */
package com.kanomiya.mcmod.throwable;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

/**
 * Created by 和宮 葵(Kanomiya Aoi) in 2017/03.
 */
public abstract class ItemThrower extends Item {

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BOW;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 72000;
    }

    public static ItemStack findThrowableItem(ItemThrower item, EntityPlayer player) {
        if (item != null) {
            if (item.isThrowableItem(player.getHeldItem(EnumHand.MAIN_HAND))) {
                return player.getHeldItem(EnumHand.MAIN_HAND);
            }
            else if (item.isThrowableItem(player.getHeldItem(EnumHand.OFF_HAND))) {
                return player.getHeldItem(EnumHand.OFF_HAND);
            }
            else {
                int size = player.inventory.getSizeInventory();

                for (int i=0; i<size; i++) {
                    ItemStack stack = player.inventory.getStackInSlot(i);
                    if (item.isThrowableItem(stack))
                        return stack;
                }
            }
        }

        return ItemStack.EMPTY;
    }

    public boolean isThrowableItem(ItemStack stack) {
        return stack != null && ! stack.isEmpty() && stack.getItem() == ThrowableModItems.STONE_NUGGET;
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack stack = playerIn.getHeldItem(handIn);
        boolean noAmmo = findThrowableItem(this, playerIn).isEmpty();

        if (playerIn.capabilities.isCreativeMode || ! noAmmo) {
            playerIn.setActiveHand(handIn);
            return new ActionResult<>(EnumActionResult.SUCCESS, stack);
        }
        else {
            return new ActionResult<>(EnumActionResult.FAIL, stack);
        }
    }

    public float getVelocity(int charge) {
        return Math.min((float)charge *getVelocityIncreasePerTick(), getMaxVelocity());
}

    public abstract float getVelocityIncreasePerTick();
    public abstract float getMaxVelocity();
    public abstract float getHitDamage(float velocityRate);
    public abstract float getInAccuracy();

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
    {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer playerIn = (EntityPlayer) entityLiving;

            int charge = getMaxItemUseDuration(stack) -timeLeft;

            if (charge < 0)
                return;

            ItemStack ammo = findThrowableItem(this, playerIn);
            boolean noAmmo = ammo.isEmpty();

            if (playerIn.capabilities.isCreativeMode || ! noAmmo) {
                if (! worldIn.isRemote) {
                    final float velocity = Math.min(getVelocity(charge), getMaxVelocity());

                    EntityStoneNugget bullet = new EntityStoneNugget(worldIn, playerIn);
                    bullet.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0F, velocity, getInAccuracy());
                    bullet.setHitDamage(getHitDamage(velocity /getMaxVelocity()));

                    worldIn.spawnEntity(bullet);

                    if (! playerIn.capabilities.isCreativeMode) {
                        stack.damageItem(1, playerIn);

                        ammo.shrink(1);
                        if (ammo.isEmpty()) {
                            playerIn.inventory.deleteStack(ammo);
                        }
                    }
                }

                worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

                playerIn.addStat(StatList.getObjectUseStats(this));
            }
        }
    }

}
