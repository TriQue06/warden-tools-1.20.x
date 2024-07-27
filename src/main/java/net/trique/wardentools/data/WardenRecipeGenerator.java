package net.trique.wardentools.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;

import net.minecraft.data.server.recipe.RecipeJsonProvider;

import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;

import net.trique.wardentools.item.WardenItems;

import java.util.function.Consumer;

import static net.trique.wardentools.util.DatagenHelper.*;

public class WardenRecipeGenerator extends FabricRecipeProvider {

    public WardenRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offerCustomSmithingTemplateCopyingRecipe(exporter, WardenItems.WARDEN_UPGRADE_SMITHING_TEMPLATE, Items.COBBLED_DEEPSLATE,Items.DIAMOND);
        offerCustomUpgradeRecipe(exporter,WardenItems.WARDEN_UPGRADE_SMITHING_TEMPLATE,Items.NETHERITE_SWORD,WardenItems.SCULK_SHELL, RecipeCategory.COMBAT,WardenItems.WARDEN_SWORD);
        offerCustomUpgradeRecipe(exporter,WardenItems.WARDEN_UPGRADE_SMITHING_TEMPLATE,Items.NETHERITE_AXE,WardenItems.SCULK_SHELL, RecipeCategory.COMBAT,WardenItems.WARDEN_AXE);
        offerCustomUpgradeRecipe(exporter,WardenItems.WARDEN_UPGRADE_SMITHING_TEMPLATE,Items.NETHERITE_PICKAXE,WardenItems.SCULK_SHELL, RecipeCategory.TOOLS,WardenItems.WARDEN_PICKAXE);
        offerCustomUpgradeRecipe(exporter,WardenItems.WARDEN_UPGRADE_SMITHING_TEMPLATE,Items.NETHERITE_HOE,WardenItems.SCULK_SHELL, RecipeCategory.TOOLS,WardenItems.WARDEN_HOE);
        offerCustomUpgradeRecipe(exporter,WardenItems.WARDEN_UPGRADE_SMITHING_TEMPLATE,Items.NETHERITE_SHOVEL,WardenItems.SCULK_SHELL, RecipeCategory.TOOLS,WardenItems.WARDEN_SHOVEL);
        offerCustomUpgradeRecipe(exporter,WardenItems.WARDEN_UPGRADE_SMITHING_TEMPLATE,Items.NETHERITE_HELMET,WardenItems.SCULK_SHELL, RecipeCategory.COMBAT,WardenItems.WARDEN_HELMET);
        offerCustomUpgradeRecipe(exporter,WardenItems.WARDEN_UPGRADE_SMITHING_TEMPLATE,Items.NETHERITE_CHESTPLATE,WardenItems.SCULK_SHELL, RecipeCategory.COMBAT,WardenItems.WARDEN_CHESTPLATE);
        offerCustomUpgradeRecipe(exporter,WardenItems.WARDEN_UPGRADE_SMITHING_TEMPLATE,Items.NETHERITE_LEGGINGS,WardenItems.SCULK_SHELL, RecipeCategory.COMBAT,WardenItems.WARDEN_LEGGINGS);
        offerCustomUpgradeRecipe(exporter,WardenItems.WARDEN_UPGRADE_SMITHING_TEMPLATE,Items.NETHERITE_BOOTS,WardenItems.SCULK_SHELL, RecipeCategory.COMBAT,WardenItems.WARDEN_BOOTS);
        offerShapedEchoAppleRecipe(exporter);
        offerShapedEchoStaffRecipe(exporter);
    }
}