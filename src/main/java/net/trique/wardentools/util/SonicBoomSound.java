package net.trique.wardentools.util;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.trique.wardentools.WardenTools;

public class SonicBoomSound {
    public static RegistryEntry<SoundEvent> SONIC_BOOM_SOUND;

    public static void SonicBoomSound(){
        SONIC_BOOM_SOUND = Registry.registerReference(Registries.SOUND_EVENT, Identifier.of(WardenTools.MOD_ID,"wt_sonic_boom"),
                SoundEvent.of(SoundEvents.ENTITY_WARDEN_SONIC_BOOM.getId()));
    }
}
