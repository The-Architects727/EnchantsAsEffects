package net.architects.enchantmenteffects.mixin;

import net.architects.enchantmenteffects.effects.ModEffects;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.FluidTags;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    int expirienceBar;

    @Inject(method = "getBlockBreakingSpeed", at = @At(value = "RETURN"), cancellable = true)
    private void AquaAffinityEffect(BlockState block, CallbackInfoReturnable<Float> cir) {
        PlayerEntity entity = PlayerEntity.class.cast(this);
        if (entity.isSubmergedIn(FluidTags.WATER) && entity.hasStatusEffect(ModEffects.AQUA_AFFINITY)) {
            float f = entity.inventory.getBlockBreakingSpeed(block);
            if (f > 1.0F) {
                int i = EnchantmentHelper.getEfficiency(entity);
                ItemStack itemStack = entity.getMainHandStack();
                if (i > 0 && !itemStack.isEmpty()) {
                    f += (float)(i * i + 1);
                }
            }

            if (StatusEffectUtil.hasHaste(entity)) {
                f *= 1.0F + (float)(StatusEffectUtil.getHasteAmplifier(entity) + 1) * 0.2F;
            }

            if (entity.hasStatusEffect(StatusEffects.MINING_FATIGUE)) {
                float g;
                switch (entity.getStatusEffect(StatusEffects.MINING_FATIGUE).getAmplifier()) {
                    case 0:
                        g = 0.3F;
                        break;
                    case 1:
                        g = 0.09F;
                        break;
                    case 2:
                        g = 0.0027F;
                        break;
                    case 3:
                    default:
                        g = 8.1E-4F;
                }

                f *= g;
            }

            if (!entity.isOnGround()) {
                f /= 5.0F;
            }
            int amplifier = entity.getStatusEffect(ModEffects.AQUA_AFFINITY).getAmplifier() + 1;
            cir.setReturnValue(f * (amplifier * 1.05F));
        }
    }

    @Inject(method = "addExperience", at = @At(value = "HEAD"), cancellable = true)
    private void MendingEffect(int experience, CallbackInfo ci) {
        PlayerEntity entity = PlayerEntity.class.cast(this);

        if(entity.hasStatusEffect(ModEffects.MENDING)) {
            int perHealthXP = (15/(entity.getStatusEffect(ModEffects.MENDING).getAmplifier()+1));
            if (perHealthXP <=0) {
                perHealthXP = 1;
            }
            if (entity.getHealth() < entity.getMaxHealth()) {
                expirienceBar += experience;
                entity.heal(expirienceBar/((float)perHealthXP));
                expirienceBar = experience % (perHealthXP);
            } else {
                entity.addScore(experience);
                entity.experienceProgress += (float) experience / (float) entity.getNextLevelExperience();
                entity.totalExperience = MathHelper.clamp(entity.totalExperience + experience, 0, Integer.MAX_VALUE);

                while (entity.experienceProgress < 0.0F) {
                    float f = entity.experienceProgress * (float) entity.getNextLevelExperience();
                    if (entity.experienceLevel > 0) {
                        entity.addExperienceLevels(-1);
                        entity.experienceProgress = 1.0F + f / (float) entity.getNextLevelExperience();
                    } else {
                        entity.addExperienceLevels(-1);
                        entity.experienceProgress = 0.0F;
                    }
                }

                while (entity.experienceProgress >= 1.0F) {
                    entity.experienceProgress = (entity.experienceProgress - 1.0F) * (float) entity.getNextLevelExperience();
                    entity.addExperienceLevels(1);
                    entity.experienceProgress /= (float) entity.getNextLevelExperience();
                }
            }
        } else {
            entity.addScore(experience);
            entity.experienceProgress += (float) experience / (float) entity.getNextLevelExperience();
            entity.totalExperience = MathHelper.clamp(entity.totalExperience + experience, 0, Integer.MAX_VALUE);

            while (entity.experienceProgress < 0.0F) {
                float f = entity.experienceProgress * (float) entity.getNextLevelExperience();
                if (entity.experienceLevel > 0) {
                    entity.addExperienceLevels(-1);
                    entity.experienceProgress = 1.0F + f / (float) entity.getNextLevelExperience();
                } else {
                    entity.addExperienceLevels(-1);
                    entity.experienceProgress = 0.0F;
                }
            }

            while (entity.experienceProgress >= 1.0F) {
                entity.experienceProgress = (entity.experienceProgress - 1.0F) * (float) entity.getNextLevelExperience();
                entity.addExperienceLevels(1);
                entity.experienceProgress /= (float) entity.getNextLevelExperience();
            }
        }
        ci.cancel();
    }

//    @ModifyVariable(method = "addExperience", at = @At("HEAD"), ordinal = 0, argsOnly = true)
//    private int injected(int expierience) {
//        PlayerEntity entity = PlayerEntity.class.cast(this);
//        if(entity.hasStatusEffect(ModEffects.MENDING)) {
//            return expirienceBar;
//        }
//        return expierience;
//    }

//    @Inject(method = "adjustMovementForSneaking", at = @At(value = "HEAD"), cancellable = true)
//    private void SwiftSneakEffect(Vec3d movement, MovementType type, CallbackInfoReturnable<Vec3d> cir) {
//        PlayerEntity entity = PlayerEntity.class.cast(this);
//        if(entity.hasStatusEffect(ModEffects.SWIFT_SNEAK)) {
//            if (!entity.abilities.flying && movement.y <= 0.0 && (type == MovementType.SELF || type == MovementType.PLAYER) && entity.clipAtLedge() && entity.method_30263()) {
//                double d = movement.x;
//                double e = movement.z;
//                double f = 0.05;
//                double test = 0.05;
//
//                while(true) {
//                    while(d != 0.0 && entity.getWorld().isSpaceEmpty(entity, entity.getBoundingBox().offset(d, (double)(-entity.getStepHeight()), 0.0))) {
//                        if (d < test && d >= -test) {
//                            d = 0.0;
//                        } else if (d > 0.0) {
//                            d -= test;
//                        } else {
//                            d += test;
//                        }
//                    }
//
//                    while(true) {
//                        while(e != 0.0 && entity.getWorld().isSpaceEmpty(entity, entity.getBoundingBox().offset(0.0, (double)(-entity.getStepHeight()), e))) {
//                            if (e < test && e >= -test) {
//                                e = 0.0;
//                            } else if (e > 0.0) {
//                                e -= test;
//                            } else {
//                                e += test;
//                            }
//                        }
//
//                        while(true) {
//                            while(d != 0.0 && e != 0.0 && entity.getWorld().isSpaceEmpty(entity, entity.getBoundingBox().offset(d, (double)(-entity.getStepHeight()), e))) {
//                                if (d < test && d >= -test) {
//                                    d = 0.0;
//                                } else if (d > 0.0) {
//                                    d -= test;
//                                } else {
//                                    d += test;
//                                }
//
//                                if (e < test && e >= -test) {
//                                    e = 0.0;
//                                } else if (e > 0.0) {
//                                    e -= test;
//                                } else {
//                                    e += test;
//                                }
//                            }
//
//                            movement = new Vec3d(d, movement.y, e);
//                            cir.setReturnValue(movement);
//                        }
//                    }
//                }
//            } else {
//                cir.setReturnValue(movement);
//            }
//        }
//    }
}
