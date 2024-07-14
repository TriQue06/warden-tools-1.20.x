package net.trique.wardentools.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class EchoApple {
    public static final FoodComponent ECHO_APPLE = new FoodComponent.Builder()
            .snack()
            .nutrition(5)
            .saturationModifier(1.2F).
        statusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 300, 0), 1.0F).
        statusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 1200, 0), 0.5F).

        statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 1800, 0), 1.0F).
        statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 1800, 0), 1.0F).
        statusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 1800, 0), 1.0F).

        statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1800, 0), 1.0F).
        statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 1800, 0), 1.0f).

        statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1800, 1), 0.25F).
        statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 1200, 1), 0.25F).
        alwaysEdible().build();
}