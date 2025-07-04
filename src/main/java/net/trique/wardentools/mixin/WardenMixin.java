package net.trique.wardentools.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.world.World;
import net.trique.wardentools.effect.WardenToolsEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(WardenEntity.class)
public abstract class WardenMixin extends HostileEntity {
    protected WardenMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }


    @Inject(method = "damage", at = @At(value = "HEAD"))
    private void applyExtraDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (source.getAttacker() instanceof LivingEntity attacker) {
            StatusEffectInstance effect = attacker.getStatusEffect(WardenToolsEffects.WARDEN_SLAYER_EFFECT);
            if (effect != null) {
                this.timeUntilRegen = 0;
                super.damage(source, amount*3);
            }
        }
    }
}