package net.trique.wardentools.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;
import net.trique.wardentools.item.WardenArmorMaterials;

public class ArmorEffectItem extends ArmorItem {
    private static final int effectDuration = StatusEffectInstance.INFINITE;
    private static final int amplifier = 0;
    private static boolean appliedByArmor;
    public static boolean isCorrectMaterial = false;
    private final RegistryEntry<StatusEffect> effect;

    public ArmorEffectItem(RegistryEntry<ArmorMaterial> material, Type type, Settings settings, RegistryEntry<StatusEffect> effect) {
        super(material, type, settings);
        this.effect = effect;
    }

    public static boolean getApplied(){
        return appliedByArmor;
    }
    public static void setApplied(boolean apl){
        appliedByArmor = apl;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient) {
            if (entity instanceof PlayerEntity player) {
                if (hasFullSuitOfArmorOn(player)) {
                    isCorrectMaterial = hasCorrectArmorOn(material.value(),player);
                    evaluateArmorEffects(player);
                }else{
                    isCorrectMaterial = hasCorrectArmorOn(material.value(),player);
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
        if (isCorrectMaterial) {
            addStatusEffect(player);
            appliedByArmor = true;
            player.removeStatusEffect(StatusEffects.DARKNESS);
        }else {
            if(player.hasStatusEffect(this.effect) && appliedByArmor){
                if((player.getActiveStatusEffects().get(this.effect).getDuration() == StatusEffectInstance.INFINITE)){
                    player.removeStatusEffect(this.effect);
                    appliedByArmor = false;
                    player.setStatusEffect(new StatusEffectInstance(this.effect,300,0,false,false,false),player.getDamageSources().generic().getSource());
                }
            }
        }
    }

    public static boolean hasCorrectArmorOn(ArmorMaterial material, PlayerEntity player) {
        Item boots = player.getEquippedStack(EquipmentSlot.FEET).getItem();
        Item leggings = player.getEquippedStack(EquipmentSlot.LEGS).getItem();
        Item chestplate = player.getEquippedStack(EquipmentSlot.CHEST).getItem();
        Item helmet = player.getEquippedStack(EquipmentSlot.HEAD).getItem();

        // Handle any non-armor items in armor slots
        if (!(boots instanceof ArmorItem)
                || !(leggings instanceof ArmorItem)
                || !(chestplate instanceof ArmorItem)
                || !(helmet instanceof ArmorItem)) {
            isCorrectMaterial = false;
            return false;
        } else if(!material.equals(WardenArmorMaterials.WARDEN.value())){
            isCorrectMaterial = false;
            return false;
        }
        else{
            return ((ArmorItem)helmet).getMaterial().value() == material
                    && ((ArmorItem)chestplate).getMaterial().value() == material
                    && ((ArmorItem)leggings).getMaterial().value() == material
                    && ((ArmorItem)boots).getMaterial().value() == material;
        }
    }

    private void addStatusEffect(PlayerEntity player) {
        if (!player.hasStatusEffect(this.effect) ||
                !(player.getActiveStatusEffects().get(this.effect).getDuration() == StatusEffectInstance.INFINITE)) {
            player.setStatusEffect(new StatusEffectInstance(this.effect, effectDuration, amplifier, false, false, false),
                    player.getDamageSources().generic().getSource());

        }
    }
}
