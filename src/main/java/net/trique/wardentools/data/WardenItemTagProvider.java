package net.trique.wardentools.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.trique.wardentools.WardenTools;
import net.trique.wardentools.item.WardenItems;

import java.util.concurrent.CompletableFuture;

public class WardenItemTagProvider extends FabricTagProvider.ItemTagProvider{
    public static final TagKey<Item> SCULKHYST_CLUSTER_MAX_HARVESTABLES = TagKey.of(RegistryKeys.ITEM, Identifier.of(WardenTools.MOD_ID,"sculkhyst_cluster_max_harvestables"));


    public WardenItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ItemTags.SWORDS).add(WardenItems.WARDEN_SWORD);
        getOrCreateTagBuilder(ItemTags.SHOVELS).add(WardenItems.WARDEN_SHOVEL);
        getOrCreateTagBuilder(ItemTags.AXES).add(WardenItems.WARDEN_AXE);
        getOrCreateTagBuilder(ItemTags.HOES).add(WardenItems.WARDEN_HOE);
        getOrCreateTagBuilder(ItemTags.PICKAXES).add(WardenItems.WARDEN_PICKAXE);
        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR).add(WardenItems.WARDEN_HELMET,
                WardenItems.WARDEN_CHESTPLATE,
                WardenItems.WARDEN_LEGGINGS,
                WardenItems.WARDEN_BOOTS);
        getOrCreateTagBuilder(SCULKHYST_CLUSTER_MAX_HARVESTABLES).addTag(ItemTags.HOES);
    }
}
