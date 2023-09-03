package net.trique.wardentools.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.trique.wardentools.block.WardenBlocks;
import net.trique.wardentools.item.WardenItems;

public class WardenLootTableGenerator extends FabricBlockLootTableProvider {
    public WardenLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
    }
}