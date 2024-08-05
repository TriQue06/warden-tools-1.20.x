package net.trique.wardentools.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.trique.wardentools.WardenTools;

public class WardenToolsEffects {

    public static  RegistryEntry<StatusEffect> WARDEN_SLAYER_EFFECT;

    private static RegistryEntry<StatusEffect> registerStatuesEffect(StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT,
                Identifier.of(WardenTools.MOD_ID, "warden_slayer"),
                statusEffect
        );
    }

    public static void regEffect(){
        WARDEN_SLAYER_EFFECT =
                    registerStatuesEffect(new WardenSlayerEffect(StatusEffectCategory.BENEFICIAL,0x009295));
        WardenTools.LOGGER.info("Register Warden effect");
    }
}
