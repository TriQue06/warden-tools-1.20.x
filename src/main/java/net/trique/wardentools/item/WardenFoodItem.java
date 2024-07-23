package net.trique.wardentools.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class WardenFoodItem {
    public static final FoodComponent ECHO_APPLE = (new FoodComponent.Builder()).hunger(5).saturationModifier(1.2F).
        statusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 100, 0), 1.0F).
        statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 1800, 0), 1.0F).
        statusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 1800, 0), 1.0F).
        statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1800, 0), 1.0F).
        statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 1800, 0), 1.0f).
        statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 1800, 0), 1.0F).
        alwaysEdible().build();

    public static final FoodComponent SCULK_SHELL = (new FoodComponent.Builder()).hunger(10).saturationModifier(3.0F).
        statusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 200, 0), 1.0F).
        statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1200, 2), 1.0F).
        statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 1200, 2), 1.0F).
        statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 1200, 2), 1.0F).
        alwaysEdible().build();
}