package net.trique.wardentools.util;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.trique.wardentools.block.WardenBlocks;
import net.trique.wardentools.data.WardenItemTagProvider;

import java.util.concurrent.CompletableFuture;

public class ClusterDropDatagen extends FabricBlockLootTableProvider {


    public ClusterDropDatagen(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    public LootTable.Builder createClusterDrops(Block block, Item item) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(block, ItemEntry.builder(item)
                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2)))
                .conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(WardenItemTagProvider.SCULKHYST_CLUSTER_MAX_HARVESTABLES) ))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))
                .alternatively(applyExplosionDecay(block,ItemEntry.builder(item)
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0,1)))))
        );
    }
    @Override
    public void generate() {
        addDrop(WardenBlocks.SCULKHYST_CLUSTER,createClusterDrops(WardenBlocks.SCULKHYST_CLUSTER, Items.ECHO_SHARD));
    }
}
