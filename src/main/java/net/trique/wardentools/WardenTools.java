package net.trique.wardentools;

import net.fabricmc.api.ModInitializer;

import net.trique.wardentools.block.WardenBlocks;
import net.trique.wardentools.item.WardenItemGroup;
import net.trique.wardentools.item.WardenItems;
import net.trique.wardentools.world.gen.WardenWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WardenTools implements ModInitializer {
	public static final String MOD_ID = "wardentools";
    public static final Logger LOGGER = LoggerFactory.getLogger("wardentools");

	@Override
	public void onInitialize() {
		WardenItemGroup.registerWardenGroups();
		WardenItems.registerWardenItems();
		WardenBlocks.registerWardenBlocks();
		WardenWorldGeneration.generateModWorldGen();
		LOGGER.info("Warden Tools works properly! NECO, ANNENE SELAMLAR KARDESIM! <3");
	}
}