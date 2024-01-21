package net.trique.wardentools.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.ArmorItem;
import net.trique.wardentools.item.WardenItems;

public class WardenModelProvider extends FabricModelProvider {
    public WardenModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(WardenItems.WARDEN_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(WardenItems.WARDEN_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(WardenItems.WARDEN_AXE, Models.HANDHELD);
        itemModelGenerator.register(WardenItems.WARDEN_HOE, Models.HANDHELD);
        itemModelGenerator.register(WardenItems.WARDEN_SWORD, Models.HANDHELD);
        itemModelGenerator.register(WardenItems.ECHO_INGOT, Models.GENERATED);
        itemModelGenerator.register(WardenItems.ECHO_APPLE, Models.GENERATED);
        itemModelGenerator.register(WardenItems.WARDEN_SOUL, Models.GENERATED);

        itemModelGenerator.registerArmor(((ArmorItem) WardenItems.WARDEN_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) WardenItems.WARDEN_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) WardenItems.WARDEN_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) WardenItems.WARDEN_BOOTS));
    }
}