package net.trique.wardentools;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.render.RenderLayer;
import net.trique.wardentools.block.WardenBlocks;
import net.trique.wardentools.data.WardenModelPredicateProvider;
import net.trique.wardentools.particle.ModParticles;
import net.trique.wardentools.particle.custom.ShriekParticle;

public class WardenToolsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(WardenBlocks.SCULKHYST_CLUSTER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WardenBlocks.LARGE_SCULKHYST_BUD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WardenBlocks.MEDIUM_SCULKHYST_BUD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WardenBlocks.SMALL_SCULKHYST_BUD, RenderLayer.getCutout());
        WardenModelPredicateProvider.regModModels();
        ParticleFactoryRegistry.getInstance().register(ModParticles.SHRIEK_PARTICLE, ShriekParticle.Factory::new);
    }
}