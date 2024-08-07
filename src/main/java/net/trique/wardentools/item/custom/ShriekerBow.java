package net.trique.wardentools.item.custom;

import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.trique.wardentools.item.WardenItems;

import java.util.HashSet;
import java.util.Set;

public class ShriekerBow extends BowItem {
    public ShriekerBow(Settings settings) {
        super(settings.attributeModifiers(createAttributeModifiers()));
    }

    private float remainTicks;


    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 3.0f, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, 0f, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND)
                .build();
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_WARDEN_SONIC_CHARGE, SoundCategory.BLOCKS, 3.0f, 1.0f);
        return TypedActionResult.consume(itemStack);
    }

    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!world.isClient && user instanceof PlayerEntity player) {
            int i = this.getMaxUseTime(stack, user) - remainingUseTicks;
            float loadAmount = getPullProgress(i);
            remainTicks = loadAmount;
            if (!((double) loadAmount < 0.1f)) {
                if (!player.isCreative()) {
                    spawnSonicBoom(world, user);
                    player.getItemCooldownManager().set(this, 200);
                    stack.damage(1, user, EquipmentSlot.MAINHAND);
                } else {
                    System.out.println(loadAmount);
                    spawnSonicBoom(world, user);
                }
            }
        }
    }

    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(WardenItems.WARDEN_SOUL);
    }

    private void spawnSonicBoom(World world, LivingEntity user) {
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.BLOCK_SCULK_SHRIEKER_SHRIEK, SoundCategory.BLOCKS, 5.0f, 1.0f);

        float heightOffset = 1.6f;
        int distance = 30;
        Vec3d target = user.getPos().add(user.getRotationVector().multiply(distance));
        Vec3d source = user.getPos().add(0.0, heightOffset, 0.0);
        Vec3d offsetToTarget = target.subtract(source);
        Vec3d normalized = offsetToTarget.normalize();

        Set<Entity> hit = new HashSet<>();
        double expansion = 0.5;
        for (int particleIndex = 1; particleIndex < MathHelper.floor(offsetToTarget.length()); ++particleIndex) {
            Vec3d particlePos = source.add(normalized.multiply(particleIndex));
            ((ServerWorld) world).spawnParticles(ParticleTypes.SONIC_BOOM, particlePos.x, particlePos.y, particlePos.z, 1, 0.0, 0.0, 0.0, 0.0);
            hit.addAll(world.getEntitiesByClass(LivingEntity.class, new Box(new BlockPos((int) particlePos.getX(),
                            (int) particlePos.getY(), (int) particlePos.getZ())).expand(expansion),
                    it -> !(it instanceof TameableEntity helper && helper.isOwner(user))));
            expansion += 0.1;
        }

        hit.remove(user);

        for (Entity hitTarget : hit) {
            if (hitTarget instanceof LivingEntity living) {
                float distanceToTarget = user.distanceTo(living);
                float damage = finalDamage(remainTicks, distanceToTarget, living);

                System.out.println("Damage " + damage + "\nDistance " + distanceToTarget);
                living.damage(world.getDamageSources().sonicBoom(user), damage);
                double vertical = 0.5 * (1.0 - living.getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE));
                double horizontal = 2.5 * (1.0 - living.getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE));
                living.addVelocity(normalized.getX() * horizontal, normalized.getY() * vertical, normalized.getZ() * horizontal);
            }
        }
    }

    private float finalDamage(float chargingAmount, float distanceToTarget, LivingEntity target) {
        float baseDamage = 15f; // Damage if % from target Max hp is very low
        float amplifier = 20f;

        float damage = target.getMaxHealth() * (amplifier / 100);
        if (Math.floor(damage) < baseDamage) {
            damage = baseDamage;
        }

        if (Math.floor(distanceToTarget) > 1) {
            damage *= (1 - (distanceToTarget * 0.03f));
            if (damage < 1) damage = 1;
        }

        return damage * chargingAmount;
    }
}
