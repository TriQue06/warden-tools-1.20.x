package net.trique.wardentools.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.trique.wardentools.WardenTools;

public class WardenBlocks {

    public static final Block SCULKHYST_BLOCK = registerBlock("sculkhyst_block",
            new SculkhystBlock(AbstractBlock.Settings.create().mapColor(MapColor.CYAN).strength(1.5f).sounds(BlockSoundGroup.SCULK).requiresTool(), UniformIntProvider.create(8, 16)));

    public static final Block BUDDING_SCULKHYST = registerBlock("budding_sculkhyst",
            new BuddingSculkhystBlock(AbstractBlock.Settings.create().mapColor(MapColor.CYAN).ticksRandomly().strength(1.5f).sounds(BlockSoundGroup.SCULK).requiresTool().pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block SCULKHYST_CLUSTER = registerBlock("sculkhyst_cluster",
            new AmethystClusterBlock(7, 3, AbstractBlock.Settings.create().mapColor(MapColor.CYAN).solid().nonOpaque().ticksRandomly().sounds(BlockSoundGroup.SCULK).strength(1.5f).luminance(state -> 4).pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block LARGE_SCULKHYST_BUD = registerBlock("large_sculkhyst_bud",
            new AmethystClusterBlock(5, 3, AbstractBlock.Settings.copy(SCULKHYST_CLUSTER).sounds(BlockSoundGroup.SCULK).solid().luminance(state -> 3).pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block MEDIUM_SCULKHYST_BUD = registerBlock("medium_sculkhyst_bud",
            new AmethystClusterBlock(4, 3, AbstractBlock.Settings.copy(SCULKHYST_CLUSTER).sounds(BlockSoundGroup.SCULK).solid().luminance(state -> 2).pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block SMALL_SCULKHYST_BUD = registerBlock("small_sculkhyst_bud",
            new AmethystClusterBlock(3, 4, AbstractBlock.Settings.copy(SCULKHYST_CLUSTER).sounds(BlockSoundGroup.SCULK).solid().luminance(state -> 1).pistonBehavior(PistonBehavior.DESTROY)));
    
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(WardenTools.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        Item item = Registry.register(Registries.ITEM, new Identifier(WardenTools.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
        return item;
    }

    public static void registerWardenBlocks() {
        WardenTools.LOGGER.info("Registering Warden Blocks for " + WardenTools.MOD_ID);
    }
}
