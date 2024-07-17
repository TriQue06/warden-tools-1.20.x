package net.trique.wardentools.util;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.trique.wardentools.block.WardenBlocks;
import net.trique.wardentools.item.WardenItems;

public class WardenLootTableModifiers {
    private static final Identifier ANCIENT_CITY_ID = LootTables.ANCIENT_CITY_CHEST.getValue();
    // ID for modifying vanilla loot table
    private static final Identifier WARDEN_LOOT_TABLE_ID = EntityType.WARDEN.getLootTableId().getValue();
    // Key for replacing vanilla loot table
    private static final RegistryKey<LootTable> WARDEN_LOOT_TABLE_KEY = EntityType.WARDEN.getLootTableId();

    public static void replaceLootTables(){
        LootTableEvents.REPLACE.register((key, tableBuilder, source, registries)->{
            if (key.equals(WARDEN_LOOT_TABLE_KEY)){
                LootTable.Builder builder = LootTable.builder()
                        .pool(LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1f))
                                .with(ItemEntry.builder(Items.SCULK_CATALYST)
                                )
                                .with(ItemEntry.builder(WardenBlocks.SCULKHYST_BLOCK)
                                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)))
                                )
                                .conditionally(KilledByPlayerLootCondition.builder()));
                tableBuilder = builder.build();
                return tableBuilder;
            }
            return null;
        });
    }

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if(ANCIENT_CITY_ID.equals(key.getValue())) {
                LootPool.Builder TemplatePoolBuilder = LootPool.builder();
                TemplatePoolBuilder
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(WardenItems.WARDEN_UPGRADE_SMITHING_TEMPLATE)
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f)))
                                .conditionally(RandomChanceLootCondition.builder(0.6f))
                        );
                LootPool.Builder ApplePoolBuilder = LootPool.builder()
                        .with(ItemEntry.builder(WardenItems.ECHO_APPLE)
                                .conditionally(RandomChanceLootCondition.builder(0.25f))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))
                        .rolls(ConstantLootNumberProvider.create(1));
                tableBuilder.pool(TemplatePoolBuilder);
                tableBuilder.pool(ApplePoolBuilder);
            }

            if (WARDEN_LOOT_TABLE_ID.equals(key.getValue())){

                LootPool.Builder SoulLootBuild = LootPool.builder();
                LootPool.Builder ShardLootBuild = LootPool.builder();
                SoulLootBuild
                        .rolls(ConstantLootNumberProvider.create(1f))
                        .with(ItemEntry.builder(WardenItems.WARDEN_SOUL)
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)))
                                .conditionally(KilledByPlayerLootCondition.builder())
                                .conditionally(RandomChanceLootCondition.builder(0.33f)));
                ShardLootBuild
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(Items.ECHO_SHARD)
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)))
                                .conditionally(KilledByPlayerLootCondition.builder())
                                .conditionally(RandomChanceLootCondition.builder(0.66f)))
                        .rolls(ConstantLootNumberProvider.create(1));

                tableBuilder.pool(ShardLootBuild);
                tableBuilder.pool(SoulLootBuild);
            }
        });
    }
}