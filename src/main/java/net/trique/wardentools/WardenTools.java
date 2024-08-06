package net.trique.wardentools;

import net.fabricmc.api.ModInitializer;

import net.trique.wardentools.block.WardenBlocks;
import net.trique.wardentools.item.WardenArmorMaterials;
import net.trique.wardentools.item.WardenItemGroup;
import net.trique.wardentools.item.WardenItems;
import net.trique.wardentools.util.SonicBoomSound;
import net.trique.wardentools.util.WardenLootTableModifiers;
import net.trique.wardentools.world.gen.WardenWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WardenTools implements ModInitializer {
	public static final String MOD_ID = "wardentools";
    public static final Logger LOGGER = LoggerFactory.getLogger("wardentools");

	@Override
	public void onInitialize() {
		SonicBoomSound.SonicBoomSound();
		WardenItems.registerWardenItems();
		WardenBlocks.registerWardenBlocks();
		WardenItemGroup.registerWardenGroups();
		WardenWorldGeneration.generateWardenWorldGen();
		WardenLootTableModifiers.replaceLootTables();
		WardenLootTableModifiers.modifyLootTables();
		WardenArmorMaterials.initialize();
		LOGGER.info("Warden Tools works properly! NECO, ANNENE SELAMLAR KARDESIM! <3");
	}
}