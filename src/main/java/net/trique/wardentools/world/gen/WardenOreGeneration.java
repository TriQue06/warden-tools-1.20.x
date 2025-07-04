package net.trique.wardentools.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.trique.wardentools.world.WardenPlacedFeatures;

public class WardenOreGeneration {
    public static void generateOres() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.DEEP_DARK, BiomeKeys.GROVE, BiomeKeys.CHERRY_GROVE, BiomeKeys.MEADOW, BiomeKeys.JAGGED_PEAKS, BiomeKeys.SNOWY_SLOPES, BiomeKeys.STONY_PEAKS, BiomeKeys.FROZEN_PEAKS),
                GenerationStep.Feature.UNDERGROUND_ORES, WardenPlacedFeatures.SCULKHYST_GEODE_PLACED_KEY);
    }
}