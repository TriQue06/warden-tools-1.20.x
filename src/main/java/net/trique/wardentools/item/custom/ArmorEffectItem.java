package net.trique.wardentools.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;

public class ArmorEffectItem extends ArmorItem {
    private static final int effectDuration = StatusEffectInstance.INFINITE;
    private static final int amplifier = 0;
    private boolean appliedByArmor;
    private final RegistryEntry<StatusEffect> effect;

    public ArmorEffectItem(RegistryEntry<ArmorMaterial> material, Type type, Settings settings, RegistryEntry<StatusEffect> effect) {
        super(material, type, settings);
        this.effect = effect;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient) {
            if (entity instanceof PlayerEntity player) {
                if (hasFullSuitOfArmorOn(player)) {
                    evaluateArmorEffects(player);
                }
            }
        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }

    private boolean hasFullSuitOfArmorOn(PlayerEntity player) {
        ItemStack helmet = player.getEquippedStack(EquipmentSlot.HEAD);
        ItemStack chestplate = player.getEquippedStack(EquipmentSlot.CHEST);
        ItemStack leggings = player.getEquippedStack(EquipmentSlot.LEGS);
        ItemStack boots = player.getEquippedStack(EquipmentSlot.FEET);

        return !helmet.isEmpty() && !chestplate.isEmpty() && !leggings.isEmpty() && !boots.isEmpty();
    }

    private void evaluateArmorEffects(PlayerEntity player) {
        if (hasCorrectArmorOn(material.value(), player)) {
            addStatusEffect(player);
        }else {
            if(player.hasStatusEffect(this.effect) && appliedByArmor){
                if((player.getActiveStatusEffects().get(this.effect).getDuration() == StatusEffectInstance.INFINITE)){
                    player.removeStatusEffect(this.effect);
                    appliedByArmor = false;
                    player.addStatusEffect(new StatusEffectInstance(this.effect,300,0,false,false,false));
                }
            }
        }
    }

    private boolean hasCorrectArmorOn(ArmorMaterial material, PlayerEntity player) {
        Item boots = player.getEquippedStack(EquipmentSlot.FEET).getItem();
        Item leggings = player.getEquippedStack(EquipmentSlot.LEGS).getItem();
        Item chestplate = player.getEquippedStack(EquipmentSlot.CHEST).getItem();
        Item helmet = player.getEquippedStack(EquipmentSlot.HEAD).getItem();

        // Handle any non-armor items in armor slots
        if (!(boots instanceof ArmorItem)
                || !(leggings instanceof ArmorItem)
                || !(chestplate instanceof ArmorItem)
                || !(helmet instanceof ArmorItem)) {
            return false;
        } else {
            return ((ArmorItem)helmet).getMaterial().value() == material
                    && ((ArmorItem)chestplate).getMaterial().value() == material
                    && ((ArmorItem)leggings).getMaterial().value() == material
                    && ((ArmorItem)boots).getMaterial().value() == material;
        }
    }

    private void addStatusEffect(PlayerEntity player) {
        if (!player.hasStatusEffect(this.effect) ||
                !(player.getActiveStatusEffects().get(this.effect).getDuration() == StatusEffectInstance.INFINITE)) {
            appliedByArmor = true;
            player.addStatusEffect(new StatusEffectInstance(this.effect, effectDuration, amplifier, false, false, false));
        }
    }
}
