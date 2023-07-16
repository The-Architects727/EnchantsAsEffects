package net.architects.enchantmenteffects.effects;

import net.architects.enchantmenteffects.EnchantmentEffectsMod;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEffects {
    public static StatusEffect DEPTH_STRIDER;
    public static StatusEffect RESPIRATION;
    public static StatusEffect AQUA_AFFINITY;
    public static StatusEffect FROST_WALKER;
    public static StatusEffect SOUL_SPEED;
    public static StatusEffect THORNS;
    public static StatusEffect MENDING;
    public static StatusEffect SWIFT_SNEAK;
    public static StatusEffect FEATHER_FALLING;

 public static StatusEffect registerRespirationEffect(String name) {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(EnchantmentEffectsMod.MOD_ID, name),
                new Respiration(StatusEffectCategory.BENEFICIAL, 5943247));
    }
public static StatusEffect registerDepthStriderEffect(String name) {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(EnchantmentEffectsMod.MOD_ID, name),
                new DepthStrider(StatusEffectCategory.BENEFICIAL, 25029));
    }
    public static StatusEffect registerAquaAffinityEffect(String name) {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(EnchantmentEffectsMod.MOD_ID, name),
                new AquaAffinity(StatusEffectCategory.BENEFICIAL, 36525));
    }
    public static StatusEffect registerFrostWalkerEffect(String name) {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(EnchantmentEffectsMod.MOD_ID, name),
                new FrostWalker(StatusEffectCategory.BENEFICIAL, 8700415));
    }
    public static StatusEffect registerSoulSpeedEffect(String name) {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(EnchantmentEffectsMod.MOD_ID, name),
                new SoulSpeed(StatusEffectCategory.BENEFICIAL, 6354426));
    }
    public static StatusEffect registerThornsEffect(String name) {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(EnchantmentEffectsMod.MOD_ID, name),
                new Thorns(StatusEffectCategory.BENEFICIAL, 2854960));
    }
    public static StatusEffect registerMendingEffect(String name) {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(EnchantmentEffectsMod.MOD_ID, name),
                new Mending(StatusEffectCategory.BENEFICIAL, 2686927));
    }
    public static StatusEffect registerSwiftSneakEffect(String name) {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(EnchantmentEffectsMod.MOD_ID, name),
                new SwiftSneak(StatusEffectCategory.BENEFICIAL, 213328));
    }
    public static StatusEffect registerFeatherFallingEffect(String name) {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(EnchantmentEffectsMod.MOD_ID, name),
                new FeatherFalling(StatusEffectCategory.BENEFICIAL, 14142652));
    }
    public static void registerEffects() {
        DEPTH_STRIDER = registerDepthStriderEffect("depth_strider");
        RESPIRATION = registerRespirationEffect("respiration");
        AQUA_AFFINITY = registerAquaAffinityEffect("aqua_affinity");
        FROST_WALKER = registerFrostWalkerEffect("frost_walker");
        SOUL_SPEED = registerSoulSpeedEffect("soul_speed");
        THORNS = registerThornsEffect("thorns");
        MENDING = registerMendingEffect("mending");
        SWIFT_SNEAK = registerSwiftSneakEffect("swift_sneak");
        FEATHER_FALLING = registerFeatherFallingEffect("feather_falling");
    }
}
