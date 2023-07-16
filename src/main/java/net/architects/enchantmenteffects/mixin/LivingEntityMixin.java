package net.architects.enchantmenteffects.mixin;

import net.architects.enchantmenteffects.effects.ModEffects;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.FluidState;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;
import java.util.UUID;

import static net.minecraft.entity.LivingEntity.SOUL_SPEED_BOOST_ID;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow public abstract void enterCombat();

    private static final UUID SWIFT_SNEAK_BOOST_ID = UUID.fromString("0458d90e-1a1b-11ee-be56-0242ac120002");
    private Random random = new Random();
    private boolean swiftSneakApplied = false;

    @Inject(method = "travel", at = @At(value = "HEAD"))
    private void ModEffects(Vec3d movementInput, CallbackInfo ci) {
        LivingEntity entity = LivingEntity.class.cast(this);
        //Swift Sneak
        if (entity.hasStatusEffect(ModEffects.SWIFT_SNEAK)) {
            int i = entity.getStatusEffect(ModEffects.SWIFT_SNEAK).getAmplifier() + 1;
            if (!entity.getLandingBlockState().isAir()) {

                if (entity.isInSneakingPose() && !swiftSneakApplied) {
                    EntityAttributeInstance entityAttributeInstance = entity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
                    if (entityAttributeInstance == null) {
                        return;
                    }
                    entityAttributeInstance.addTemporaryModifier(new EntityAttributeModifier(SWIFT_SNEAK_BOOST_ID, "Swift Sneak boost", (double) (0.03F * (1.0F + (float) (i) * 0.35F)), EntityAttributeModifier.Operation.ADDITION));
                    swiftSneakApplied = true;
                } else if (!entity.isSneaking() && swiftSneakApplied) {
                    swiftSneakApplied = false;
                    EntityAttributeInstance entityAttributeInstance = entity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
                    if (entityAttributeInstance != null) {
                        if (entityAttributeInstance.getModifier(SWIFT_SNEAK_BOOST_ID) != null) {
                            entityAttributeInstance.removeModifier(SWIFT_SNEAK_BOOST_ID);
                        }

                    }
                }
            }
        }
        //Depth Strider
        if (entity.hasStatusEffect(ModEffects.DEPTH_STRIDER)) {
            int amplifier = entity.getStatusEffect(ModEffects.DEPTH_STRIDER).getAmplifier() + 1;
            if (entity.isLogicalSideForUpdatingMovement()) {

                double d = 0.08;
                boolean bl = entity.getVelocity().y <= 0.0;
                FluidState fluidState = entity.world.getFluidState(entity.getBlockPos());
                float f;
                double e;
                if (entity.isTouchingWater() && entity.shouldSwimInFluids() && !entity.canWalkOnFluid(fluidState)) {
                    e = entity.getY();
                    f = entity.isSprinting() ? 0.9F : entity.getBaseMovementSpeedMultiplier();
                    float g = 0.02F;
                    float h = (float)EnchantmentHelper.getDepthStrider(entity) + amplifier;
                    if (h > 3.0F) {
                        h = 3.0F;
                    }

                    if (!entity.isOnGround()) {
                        h *= 0.5F;
                    }

                    if (h > 0.0F) {
                        f += (0.54600006F - f) * h / 3.0F;
                        g += (entity.getMovementSpeed() - g) * h / 3.0F;
                    }

                    if (entity.hasStatusEffect(StatusEffects.DOLPHINS_GRACE)) {
                        f += 0.96F;
                    }
                    ;
                    entity.updateVelocity(g, movementInput);
//                    entity.sendMessage(Text.of("movementInput x1: " + movementInput.x + ", movementInput y1: " + movementInput.y + ", movementInput z1: " + movementInput.z));
                    entity.move(MovementType.SELF, entity.getVelocity());
                    Vec3d vec3d = entity.getVelocity();
                    entity.sendMessage(Text.of("x: " + entity.getVelocity().getX() + ", y: " + entity.getVelocity().getY() + ", z: " + entity.getVelocity().getZ()));
//                    entity.sendMessage(Text.of("f: " + f));
                    if (entity.horizontalCollision && entity.isClimbing()) {
                        vec3d = new Vec3d(vec3d.x, 0.2, vec3d.z);
                    }
                    entity.setVelocity(vec3d.multiply((double)f, 0.800000011920929, (double)f));
//                    entity.sendMessage(Text.of("x2: " + vec3d.x + ", y2: " + vec3d.y + ", z2: " + vec3d.z));
                    Vec3d vec3d2 = entity.applyFluidMovingSpeed(d, bl, entity.getVelocity());
                    entity.setVelocity(vec3d2);
                    if (entity.horizontalCollision && entity.doesNotCollide(vec3d2.x, vec3d2.y + 0.6000000238418579 - entity.getY() + e, vec3d2.z)) {
                        entity.setVelocity(vec3d2.x, 0.30000001192092896, vec3d2.z);
                    }
                }
//                FluidState fluidState = entity.getWorld().getFluidState(entity.getBlockPos());
//                float f;
//                double e;
//                if (entity.isTouchingWater() && !entity.canWalkOnFluid(fluidState)) {
//                    e = entity.getY();
//                    f = entity.isSprinting() ? 0.5F : entity.getBaseMovementSpeedMultiplier();
//                    float g = 0.02F;
//                    float h = amplifier;
////                    (LOG10(A7)*(A7^1.1))/A7
//
//
//                    if (!entity.isOnGround()) {
//                        h *= 0.5F;
//                    }
//
//                    if (h > 0.0F) {
//                        f += (0.600006F - f) * h / 3.0F;
//                        g += (entity.getMovementSpeed() - g) * h / 3.0F;
//                    }
//
//
//
//                    if (entity.hasStatusEffect(StatusEffects.DOLPHINS_GRACE)) {
//                        f = 0.96F;
//                    }
//
//                    if ((float)EnchantmentHelper.getDepthStrider(entity) > 0.0) {
//                        f += ((float)EnchantmentHelper.getDepthStrider(entity) / 10);
//                    }
//
//                    entity.updateVelocity(g, movementInput);
//                    entity.move(MovementType.SELF, entity.getVelocity());
//                    Vec3d vec3d = entity.getVelocity();
//                    if (entity.horizontalCollision && entity.isClimbing()) {
//                        vec3d = new Vec3d(vec3d.x, 0.2, vec3d.z);
//                    }
//
//                    entity.setVelocity(vec3d.multiply((double) f, 0.800000011920929, (double) f));
//                    Vec3d vec3d2 = entity.applyFluidMovingSpeed(d, bl, entity.getVelocity());
//                    entity.setVelocity(vec3d2);
//                    if (entity.horizontalCollision && entity.doesNotCollide(vec3d2.x, vec3d2.y + 0.6000000238418579 - entity.getY() + e, vec3d2.z)) {
//                        entity.setVelocity(vec3d2.x, 0.30000001192092896, vec3d2.z);
//                    }
//
//                }
            }
        }
    }

    @Inject(method = "getNextAirUnderwater", at = @At(value = "RETURN"), cancellable = true)
    private void WaterBreathing(int air, CallbackInfoReturnable<Integer> cir) {
        LivingEntity entity = LivingEntity.class.cast(this);
        int i = EnchantmentHelper.getRespiration(entity);
        if(entity.hasStatusEffect(ModEffects.RESPIRATION)) {
            int j = entity.getStatusEffect(ModEffects.RESPIRATION).getAmplifier();
            boolean check = (i + j > 0 && random.nextInt(i + j + 1) > 0);
            int air2 = (check ? air : air - 1);
            cir.setReturnValue(air2);
        }

    }

    private int getChance() {
        if (random.nextBoolean()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Inject(method = "applyMovementEffects", at = @At(value = "RETURN"))
    private void FrostWalker(BlockPos pos, CallbackInfo ci) {
        LivingEntity entity = LivingEntity.class.cast(this);
        if(entity.hasStatusEffect(ModEffects.FROST_WALKER)) {
            int i = entity.getStatusEffect(ModEffects.FROST_WALKER).getAmplifier() + 1;
            if (i > 0) {
                FrostWalkerEnchantment.freezeWater(entity, entity.getWorld(), pos, i);
            }

            if (entity.shouldRemoveSoulSpeedBoost(entity.getLandingBlockState())) {
                entity.removeSoulSpeedBoost();
            }

            entity.addSoulSpeedBoostIfNeeded();
        }
    }

    @Inject(method = "addSoulSpeedBoostIfNeeded", at = @At(value = "RETURN"))
    private void SoulSpeed(CallbackInfo ci) {
        LivingEntity entity = LivingEntity.class.cast(this);
        if(entity.hasStatusEffect(ModEffects.SOUL_SPEED)) {
            if (!entity.getLandingBlockState().isAir()) {
                int i = entity.getStatusEffect(ModEffects.SOUL_SPEED).getAmplifier() + 1;
                if (i > 0 && entity.isOnSoulSpeedBlock()) {
                    EntityAttributeInstance entityAttributeInstance = entity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
                    if (entityAttributeInstance == null) {
                        return;
                    }
                    entityAttributeInstance.addTemporaryModifier(new EntityAttributeModifier(SOUL_SPEED_BOOST_ID, "Soul speed boost", (double) (0.03F * (1.0F + (float) i * 0.35F)), EntityAttributeModifier.Operation.ADDITION));

                }
            }
        }
    }

    @Inject(method = "damage", at = @At(value = "RETURN"))
    private void Thorns(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = LivingEntity.class.cast(this);
        Entity attacker = source.getAttacker();
        if(entity.hasStatusEffect(ModEffects.THORNS)) {
            if (attacker != null) {
                if (attacker instanceof LivingEntity) {
                    int i = entity.getStatusEffect(ModEffects.THORNS).getAmplifier();
                    LivingEntity livingEntity2 = (LivingEntity) attacker;
                    livingEntity2.damage(DamageSource.thorns(entity), ((amount/2)+(i)));
//                    if(i > (livingEntity2.getMaxHealth() * 2)) {
//                        if(random.nextInt(100) == 99) {
//                            entity.sendMessage(Text.of("Obliteration"));
//                        }
//                    }
                }
            }
        }
    }
    @Inject(method = "computeFallDamage", at = @At(value = "RETURN"), cancellable = true)
    private void FeatherFalling(float fallDistance, float damageMultiplier, CallbackInfoReturnable<Integer> cir) {
        LivingEntity entity = LivingEntity.class.cast(this);
        if(entity.hasStatusEffect(ModEffects.FEATHER_FALLING)) {
            int i = entity.getStatusEffect(ModEffects.FEATHER_FALLING).getAmplifier() + 1;
            StatusEffectInstance statusEffectInstance = entity.getStatusEffect(StatusEffects.JUMP_BOOST);
            float f = statusEffectInstance == null ? 0.0F : (float)(statusEffectInstance.getAmplifier() + 1);
            cir.setReturnValue(MathHelper.ceil((fallDistance - 3.0F - f - (i * 2)) * damageMultiplier));
        }
    }
}
