package net.trique.wardentools.util;

import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;
import net.trique.wardentools.WardenTools;
import net.trique.wardentools.item.WardenItems;

import java.util.function.Consumer;

public class DatagenHelper {
    public static void offerCustomUpgradeRecipe(Consumer<RecipeJsonProvider> exporter, Item template, Item input, Item itemMaterialUpgrade, RecipeCategory category, Item result) {
        SmithingTransformRecipeJsonBuilder.create(Ingredient.ofItems(template), Ingredient.ofItems(input), Ingredient.ofItems(itemMaterialUpgrade), category, result).
                criterion(RecipeProvider.hasItem(itemMaterialUpgrade), RecipeProvider.conditionsFromItem(itemMaterialUpgrade)).offerTo(exporter, Identifier.of(WardenTools.MOD_ID,RecipeProvider.getItemPath(result)+ "_smithing")  );
    }

    public static void offerCustomSmithingTemplateCopyingRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible template, ItemConvertible duplicationMaterial, ItemConvertible resource) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, template, 2).input('#', duplicationMaterial).input('C', resource).input('S', template).pattern("#S#").pattern("#C#").pattern("###").
                criterion(RecipeProvider.hasItem(template), RecipeProvider.conditionsFromItem(template)).offerTo(exporter);
    }
    public static void offerShapedEchoAppleRecipe(Consumer<RecipeJsonProvider> exporter){
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, WardenItems.ECHO_APPLE,1).input('e', Items.ECHO_SHARD).input(
                'a',()-> Items.APPLE).pattern(" e ").pattern("eae").pattern(" e ").criterion(RecipeProvider.hasItem(Items.ECHO_SHARD),
                RecipeProvider.conditionsFromItem(Items.ECHO_SHARD)).offerTo(exporter);
    }
    public static void offerShapedEchoStaffRecipe(Consumer<RecipeJsonProvider> exporter){
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, WardenItems.ECHO_STAFF,1).input('s', WardenItems.WARDEN_SOUL).input(
                'w',()-> Items.STICK).input('e', WardenItems.ECHO_INGOT).pattern("s").pattern("w").pattern("e").criterion(RecipeProvider.hasItem(WardenItems.WARDEN_SOUL),
                RecipeProvider.conditionsFromItem(WardenItems.WARDEN_SOUL)).offerTo(exporter);
    }

    public static void offerShapedEchoIngotRecipe(Consumer<RecipeJsonProvider> exporter){
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, WardenItems.ECHO_INGOT,1).input('n', Items.NETHERITE_INGOT).input(
                's',()-> WardenItems.WARDEN_SOUL).input('e', Items.ECHO_SHARD).pattern("ene").pattern("nsn").pattern("ene").criterion(RecipeProvider.hasItem(WardenItems.WARDEN_SOUL),
                RecipeProvider.conditionsFromItem(WardenItems.WARDEN_SOUL)).offerTo(exporter);
    }
}