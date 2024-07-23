package net.trique.wardentools.item.custom;

import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.TameableEntity;
import net.trique.wardentools.item.WardenItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.Set;

public class EchoStaff extends Item {

    public EchoStaff(Settings settings) {
        super(settings.attributeModifiers(createAttributeModifiers()));

    }
    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 3.0f, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, 0f, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND).build();
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(WardenItems.ECHO_INGOT);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand);
        return super.use(world, user, hand);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BLOCK;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity usr) {
        return 25;
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        super.usageTick(world, user, stack, remainingUseTicks);

        if(getMaxUseTime(stack, user) - remainingUseTicks == 1) {
            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_WARDEN_SONIC_CHARGE, SoundCategory.BLOCKS, 3.0f, 1.0f);
        }
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if(!world.isClient) {
            spawnSonicBoom(world, user);

            if(user instanceof PlayerEntity player) {
                player.getItemCooldownManager().set(this, 80);
                stack.damage(1, user, EquipmentSlot.MAINHAND);
            }
        }

        return super.finishUsing(stack, world, user);
    }
    private void spawnSonicBoom(World world, LivingEntity user) {
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.BLOCKS, 5.0f, 1.0f);

        float heightOffset = 1.6f;
        int distance = 20;
        Vec3d target = user.getPos().add(user.getRotationVector().multiply(distance));
        Vec3d source = user.getPos().add(0.0, heightOffset, 0.0);
        Vec3d offsetToTarget = target.subtract(source);
        Vec3d normalized = offsetToTarget.normalize();

        Set<Entity> hit = new HashSet<>();
        for (int particleIndex = 1; particleIndex < MathHelper.floor(offsetToTarget.length()) + 7; ++particleIndex) {
            Vec3d particlePos = source.add(normalized.multiply(particleIndex));
            ((ServerWorld) world).spawnParticles(ParticleTypes.SONIC_BOOM, particlePos.x, particlePos.y, particlePos.z, 1, 0.0, 0.0, 0.0, 0.0);

            hit.addAll(world.getEntitiesByClass(LivingEntity.class, new Box(new BlockPos((int) particlePos.getX(),
                            (int) particlePos.getY(), (int) particlePos.getZ())).expand(1),
                    it -> !(it instanceof TameableEntity helper && helper.isOwner(user))));
        }

        hit.remove(user);

        for (Entity hitTarget : hit) {
            if(hitTarget instanceof LivingEntity living) {
                living.damage(world.getDamageSources().sonicBoom(user), 16.0f);
                double vertical = 0.5 * (1.0 - living.getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE));
                double horizontal = 2.5 * (1.0 - living.getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE));
                living.addVelocity(normalized.getX() * horizontal, normalized.getY() * vertical, normalized.getZ() * horizontal);
            }
        }
    }
}