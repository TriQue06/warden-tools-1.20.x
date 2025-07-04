package net.trique.wardentools.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.trique.wardentools.WardenTools;

public class ModParticles {
    public static final SimpleParticleType SHRIEK_PARTICLE = FabricParticleTypes.simple();

    public static void regParticles(){
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(WardenTools.MOD_ID,"shriek_particle"),
                SHRIEK_PARTICLE);
    }
}
