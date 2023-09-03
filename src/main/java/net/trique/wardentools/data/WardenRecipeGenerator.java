package net.trique.wardentools.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.recipe.book.RecipeCategory;
import net.trique.wardentools.block.WardenBlocks;
import net.trique.wardentools.item.WardenItems;

import java.util.List;
import java.util.function.Consumer;

public class WardenRecipeGenerator extends FabricRecipeProvider {
    public WardenRecipeGenerator(FabricDataOutput output) {
        super(output);
    }
    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
      }
}