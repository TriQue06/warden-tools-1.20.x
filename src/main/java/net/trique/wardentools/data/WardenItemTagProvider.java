package net.trique.wardentools.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.trique.wardentools.item.WardenItems;

import java.util.concurrent.CompletableFuture;

public class WardenItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public WardenItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.SWORDS).add(WardenItems.WARDEN_SWORD);
        getOrCreateTagBuilder(ItemTags.SHOVELS).add(WardenItems.WARDEN_SHOVEL);
        getOrCreateTagBuilder(ItemTags.AXES).add(WardenItems.WARDEN_AXE);
        getOrCreateTagBuilder(ItemTags.HOES).add(WardenItems.WARDEN_HOE);
        getOrCreateTagBuilder(ItemTags.PICKAXES).add(WardenItems.WARDEN_PICKAXE);
        getOrCreateTagBuilder(ItemTags.HEAD_ARMOR).add(WardenItems.WARDEN_HELMET);
        getOrCreateTagBuilder(ItemTags.CHEST_ARMOR).add(WardenItems.WARDEN_CHESTPLATE);
        getOrCreateTagBuilder(ItemTags.LEG_ARMOR).add(WardenItems.WARDEN_LEGGINGS);
        getOrCreateTagBuilder(ItemTags.FOOT_ARMOR).add(WardenItems.WARDEN_BOOTS);
        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR).add(WardenItems.WARDEN_HELMET,
                WardenItems.WARDEN_CHESTPLATE,
                WardenItems.WARDEN_LEGGINGS,
                WardenItems.WARDEN_BOOTS);
    }
}
