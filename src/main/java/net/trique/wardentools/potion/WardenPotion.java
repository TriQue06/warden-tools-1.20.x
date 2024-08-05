package net.trique.wardentools.potion;

import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.trique.wardentools.WardenTools;
import net.trique.wardentools.effect.WardenToolsEffects;
import net.trique.wardentools.item.WardenItems;

public class WardenPotion{
    public static final RegistryEntry<Potion> WARDEN_SLAYER_POTION =
            Registry.registerReference(Registries.POTION, Identifier.of(WardenTools.MOD_ID, "warden_slayer"),
                    new Potion(
                            new StatusEffectInstance(
                                    Registries.STATUS_EFFECT.getEntry(WardenToolsEffects.WARDEN_SLAYER_EFFECT.value()),
                                    1200,
                                    0
                            )
                    )
            );

    public static void RegPotion() {
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> builder.registerPotionRecipe(
                Potions.STRENGTH,
                WardenItems.WARDEN_SOUL,
                Registries.POTION.getEntry(WARDEN_SLAYER_POTION.value())
        ));
    }
}
