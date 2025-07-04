package net.trique.wardentools.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.World;
import net.trique.wardentools.item.custom.ArmorEffectItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Objects;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow public abstract boolean damage(DamageSource source, float amount);

    @WrapMethod(method = "damage")
    public boolean damage(DamageSource source, float amount, Operation<Boolean> original) {
        if (source.getAttacker() instanceof WardenEntity &&
                !source.getType().msgId().equals(DamageTypes.SONIC_BOOM.getValue().getPath())){
            if(ArmorEffectItem.isCorrectMaterial){
                amount *= 0.4f;
            }
        }

        if(source.isOf(DamageTypes.SONIC_BOOM)){
            if(ArmorEffectItem.isCorrectMaterial){
                System.out.println("Work");
                amount *= 0.4f;
            }
        }
        return original.call(source,amount);
    }
}
