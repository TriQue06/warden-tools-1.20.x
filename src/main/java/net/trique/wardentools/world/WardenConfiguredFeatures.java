package net.trique.wardentools.world;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.trique.wardentools.WardenTools;
import net.trique.wardentools.block.WardenBlocks;

import java.util.List;

public class WardenConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?,?>> SCULKHYST_GEODE_KEY = registerKey("sculkhyst_geode");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        var placedFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.PLACED_FEATURE);
        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherReplaceables = new TagMatchRuleTest(BlockTags.BASE_STONE_NETHER);
        RuleTest endstoneReplaceables = new BlockMatchRuleTest(Blocks.END_STONE);

        register(context, SCULKHYST_GEODE_KEY, Feature.GEODE ,
                new GeodeFeatureConfig(new GeodeLayerConfig(BlockStateProvider.of(Blocks.AIR),
                        BlockStateProvider.of(WardenBlocks.SCULKHYST_BLOCK),
                        BlockStateProvider.of(WardenBlocks.BUDDING_SCULKHYST),
                        BlockStateProvider.of(Blocks.CALCITE),
                        BlockStateProvider.of(Blocks.SMOOTH_BASALT),
                        List.of(WardenBlocks.SMALL_SCULKHYST_BUD.getDefaultState(), WardenBlocks.MEDIUM_SCULKHYST_BUD.getDefaultState(), WardenBlocks.LARGE_SCULKHYST_BUD.getDefaultState(), WardenBlocks.SCULKHYST_CLUSTER.getDefaultState()),
                        BlockTags.FEATURES_CANNOT_REPLACE , BlockTags.GEODE_INVALID_BLOCKS),
                        new GeodeLayerThicknessConfig(1.7, 2.2, 3.2, 4.2),
                        new GeodeCrackConfig(0.95, 2.0, 2),
                        0.35, 0.083,
                        true, UniformIntProvider.create(4, 6),
                        UniformIntProvider.create(3, 4), UniformIntProvider.create(1, 2),
                        -16, 16, 0.05, 1));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(WardenTools.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}