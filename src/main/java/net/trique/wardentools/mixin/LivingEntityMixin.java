package net.trique.wardentools.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.trique.wardentools.item.WardenArmorMaterials;
import net.trique.wardentools.item.custom.ArmorEffectItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Shadow public abstract boolean isMobOrPlayer();

    @Inject(method = "onStatusEffectApplied",at = @At(value = "RETURN"))
    private void overrideEffect(StatusEffectInstance effect, Entity source, CallbackInfo ci){
        if(source instanceof PlayerEntity player){
            if(player.hasStatusEffect(StatusEffects.RESISTANCE) && ArmorEffectItem.getApplied()){
                System.out.println("Work");
                ArmorEffectItem.setApplied(false);
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "canHaveStatusEffect", cancellable = true)
    private void canHaveDarkness(StatusEffectInstance effect, CallbackInfoReturnable<Boolean> cir){
        if((Object)this instanceof PlayerEntity player){
            if(ArmorEffectItem.hasCorrectArmorOn(WardenArmorMaterials.WARDEN.value(), player)
                    && effect.getEffectType().equals(StatusEffects.DARKNESS)){
                cir.setReturnValue(false);
            }
        }
    }

}
