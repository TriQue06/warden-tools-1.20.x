package net.trique.wardentools.util;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableSource;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.loot.LootManager;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.trique.wardentools.block.WardenBlocks;
import net.trique.wardentools.item.WardenItems;

public class WardenLootTableModifiers {
    private static final Identifier ANCIENT_CITY_ID = LootTables.ANCIENT_CITY_CHEST;
    private static final Identifier SCULK_SHRIEKER_ID = Blocks.SCULK_SHRIEKER.getLootTableId();
    private static final Identifier WARDEN_LOOT_TABLE_ID = EntityType.WARDEN.getLootTableId();

    public static void replaceLootTables(){
        LootTableEvents.REPLACE.register((ResourceManager resourceManager, LootManager lootManager, Identifier id, LootTable original, LootTableSource source)->{
            if (id.equals(WARDEN_LOOT_TABLE_ID)){
                LootTable.Builder builder = LootTable.builder()
                        .pool(LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1f))
                                .with(ItemEntry.builder(Items.SCULK_CATALYST)
                                )
                                .with(ItemEntry.builder(WardenBlocks.SCULKHYST_BLOCK)
                                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)))
                                ));
                return builder.build();
            }
            return null;
        });
    }

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if(ANCIENT_CITY_ID.equals(id)) {
                LootPool.Builder TemplatePoolBuilder = LootPool.builder();
                TemplatePoolBuilder
                        .with(ItemEntry.builder(WardenItems.WARDEN_UPGRADE_SMITHING_TEMPLATE)
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f)))
                                .conditionally(RandomChanceLootCondition.builder(0.1f)))
                        .rolls(ConstantLootNumberProvider.create(1));
                LootPool.Builder ApplePoolBuilder = LootPool.builder()
                        .with(ItemEntry.builder(WardenItems.ECHO_APPLE)
                                .conditionally(RandomChanceLootCondition.builder(0.5f))
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1f))))
                        .rolls(ConstantLootNumberProvider.create(1));

                LootPool.Builder SculkShellPoolBuilder = LootPool.builder()
                        .with(ItemEntry.builder(WardenItems.SCULK_SHELL)
                                .conditionally(RandomChanceLootCondition.builder(0.25f))
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f))))
                        .rolls(ConstantLootNumberProvider.create(1));
                tableBuilder.pool(TemplatePoolBuilder);
                tableBuilder.pool(ApplePoolBuilder);
                tableBuilder.pool(SculkShellPoolBuilder);
            }
            if (WARDEN_LOOT_TABLE_ID.equals(id)){
                LootPool.Builder SoulLootBuild = LootPool.builder();
                SoulLootBuild
                        .rolls(ConstantLootNumberProvider.create(1f))
                        .with(ItemEntry.builder(WardenItems.WARDEN_SOUL)
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1))));
                tableBuilder.pool(SoulLootBuild);
            }

            if(SCULK_SHRIEKER_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.10f))
                        .with(ItemEntry.builder(WardenItems.WARDEN_SOUL))
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1f)));
                tableBuilder.pool(poolBuilder);
            }
        });
    }
}