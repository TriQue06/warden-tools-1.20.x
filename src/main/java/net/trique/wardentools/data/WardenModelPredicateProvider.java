package net.trique.wardentools.data;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.trique.wardentools.item.WardenItems;

public class WardenModelPredicateProvider {
    public static void regModModels(){
        registerBow(WardenItems.ECHO_SHRIEKER);
    }

    private static void registerBow(Item bow){
        ModelPredicateProviderRegistry.register(bow, Identifier.of("pull"),
                (stack, world, entity, seed)->{
                    if (entity == null){
                        return 0.0f;
                    }
                    if(entity.getActiveItem() != stack){
                        return 0.0f;
                    }
                    return (float)(stack.getMaxUseTime(entity) - entity.getItemUseTimeLeft()) / 20.0f;
                });
        ModelPredicateProviderRegistry.register(bow, Identifier.of("pulling"),
                (stack, world,entity,seed) -> entity != null && entity.isUsingItem()
                        && entity.getActiveItem() == stack ? 1.0f : 0.0f);
    }
}
