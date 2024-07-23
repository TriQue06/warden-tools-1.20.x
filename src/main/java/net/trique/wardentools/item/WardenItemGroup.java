package net.trique.wardentools.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.trique.wardentools.WardenTools;
import net.trique.wardentools.block.WardenBlocks;

public class WardenItemGroup {
    public static ItemGroup WARDENITEMGROUP = Registry.register(Registries.ITEM_GROUP, new Identifier(WardenTools.MOD_ID, "wardenitemgroup"),
            FabricItemGroup.builder().displayName(Text.literal("Warden Tools"))
                    .icon(() -> new ItemStack(WardenItems.WARDEN_CHESTPLATE)).entries((displayContext, entries) -> {
                        entries.add(WardenBlocks.SCULKHYST_BLOCK);
                        entries.add(WardenBlocks.BUDDING_SCULKHYST);
                        entries.add(WardenBlocks.SMALL_SCULKHYST_BUD);
                        entries.add(WardenBlocks.MEDIUM_SCULKHYST_BUD);
                        entries.add(WardenBlocks.LARGE_SCULKHYST_BUD);
                        entries.add(WardenBlocks.SCULKHYST_CLUSTER);
                        entries.add(WardenItems.ECHO_STAFF);
                        entries.add(WardenItems.WARDEN_SHOVEL);
                        entries.add(WardenItems.WARDEN_PICKAXE);
                        entries.add(WardenItems.WARDEN_AXE);
                        entries.add(WardenItems.WARDEN_HOE);
                        entries.add(WardenItems.WARDEN_SWORD);
                        entries.add(WardenItems.WARDEN_HELMET);
                        entries.add(WardenItems.WARDEN_CHESTPLATE);
                        entries.add(WardenItems.WARDEN_LEGGINGS);
                        entries.add(WardenItems.WARDEN_BOOTS);
                        entries.add(WardenItems.WARDEN_SOUL);
                        entries.add(WardenItems.SCULK_SHELL);
                        entries.add(WardenItems.ECHO_APPLE);
                        entries.add(WardenItems.WARDEN_UPGRADE_SMITHING_TEMPLATE);
                    }).build());

    public static void registerWardenGroups() {}
}