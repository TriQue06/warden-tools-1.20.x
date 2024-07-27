package net.trique.wardentools;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import net.trique.wardentools.data.WardenItemTagProvider;
import net.trique.wardentools.data.WardenModelProvider;
import net.trique.wardentools.data.WardenRecipeGenerator;
import net.trique.wardentools.data.WardenWorldGenerator;
import net.trique.wardentools.world.WardenConfiguredFeatures;
import net.trique.wardentools.world.WardenPlacedFeatures;

public class WardenToolsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(WardenModelProvider::new);
		pack.addProvider(WardenWorldGenerator::new);
		pack.addProvider(WardenRecipeGenerator::new);
		pack.addProvider(WardenItemTagProvider::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, WardenConfiguredFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, WardenPlacedFeatures::bootstrap);
	}
}