package net.architects.enchantmenteffects.potion;

import net.architects.enchantmenteffects.EnchantmentEffectsMod;
import net.architects.enchantmenteffects.effects.ModEffects;
import net.architects.enchantmenteffects.mixin.BrewingRecipeRegistryMixin;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModPotions {
    //BASE
    public static Potion BASE_AQUA_AFFINITY_POTION;
    public static Potion BASE_DEPTH_STRIDER_POTION;
    public static Potion BASE_FEATHER_FALLING_POTION;
    public static Potion BASE_FROST_WALKER_POTION;
    public static Potion BASE_MENDING_POTION;
    public static Potion BASE_RESPIRATION_POTION;
    public static Potion BASE_SOUL_SPEED_POTION;
    public static Potion BASE_SWIFT_SNEAK_POTION;
    public static Potion BASE_THORNS_POTION;
    //LONG
    public static Potion LONG_AQUA_AFFINITY_POTION;
    public static Potion LONG_DEPTH_STRIDER_POTION;
    public static Potion LONG_FEATHER_FALLING_POTION;
    public static Potion LONG_FROST_WALKER_POTION;
    public static Potion LONG_MENDING_POTION;
    public static Potion LONG_RESPIRATION_POTION;
    public static Potion LONG_SOUL_SPEED_POTION;
    public static Potion LONG_SWIFT_SNEAK_POTION;
    public static Potion LONG_THORNS_POTION;
    //STRONG
    public static Potion STRONG_AQUA_AFFINITY_POTION;
    public static Potion STRONG_DEPTH_STRIDER_POTION;
    public static Potion STRONG_FEATHER_FALLING_POTION;
    public static Potion STRONG_FROST_WALKER_POTION;
    public static Potion STRONG_MENDING_POTION;
    public static Potion STRONG_RESPIRATION_POTION;
    public static Potion STRONG_SOUL_SPEED_POTION;
    public static Potion STRONG_SWIFT_SNEAK_POTION;
    public static Potion STRONG_THORNS_POTION;

    public static Potion registerPotion(StatusEffect effect, int duration, int amplifier, String name) {
        return Registry.register(Registry.POTION, new Identifier(EnchantmentEffectsMod.MOD_ID, name),
                new Potion(new StatusEffectInstance(effect, duration, amplifier)));
    }

    public static void registerPotions() {
        //BASE
        BASE_AQUA_AFFINITY_POTION = registerPotion(ModEffects.AQUA_AFFINITY, 3600, 0, "base_aqua_affinity_potion");
        LONG_AQUA_AFFINITY_POTION = registerPotion(ModEffects.AQUA_AFFINITY, 9600, 0, "long_aqua_affinity_potion");
        STRONG_AQUA_AFFINITY_POTION = registerPotion(ModEffects.AQUA_AFFINITY, 1800, 2, "strong_aqua_affinity_potion");

        BASE_DEPTH_STRIDER_POTION = registerPotion(ModEffects.DEPTH_STRIDER, 3600, 0, "base_depth_strider_potion");
        LONG_DEPTH_STRIDER_POTION = registerPotion(ModEffects.DEPTH_STRIDER, 9600, 0, "long_depth_strider_potion");
        STRONG_DEPTH_STRIDER_POTION = registerPotion(ModEffects.DEPTH_STRIDER, 1800, 2, "strong_depth_strider_potion");

        BASE_FEATHER_FALLING_POTION = registerPotion(ModEffects.FEATHER_FALLING, 3600, 0, "base_feather_falling_potion");
        LONG_FEATHER_FALLING_POTION = registerPotion(ModEffects.FEATHER_FALLING, 9600, 0, "long_feather_falling_potion");
        STRONG_FEATHER_FALLING_POTION = registerPotion(ModEffects.FEATHER_FALLING, 1800, 3, "strong_feather_falling_potion");

        BASE_FROST_WALKER_POTION = registerPotion(ModEffects.FROST_WALKER, 3600, 0, "base_frost_walker_potion");
        LONG_FROST_WALKER_POTION = registerPotion(ModEffects.FROST_WALKER, 9600, 0, "long_frost_walker_potion");
        STRONG_FROST_WALKER_POTION = registerPotion(ModEffects.FROST_WALKER, 1800, 2, "strong_frost_walker_potion");

        BASE_MENDING_POTION = registerPotion(ModEffects.MENDING, 3600, 0, "base_mending_potion");
        LONG_MENDING_POTION = registerPotion(ModEffects.MENDING, 9600, 0, "long_mending_potion");
        STRONG_MENDING_POTION = registerPotion(ModEffects.MENDING, 1800, 2, "strong_mending_potion");

        BASE_RESPIRATION_POTION = registerPotion(ModEffects.RESPIRATION, 3600, 0, "base_respiration_potion");
        LONG_RESPIRATION_POTION = registerPotion(ModEffects.RESPIRATION, 9600, 0, "long_respiration_potion");
        STRONG_RESPIRATION_POTION = registerPotion(ModEffects.RESPIRATION, 3600, 2, "strong_respiration_potion");

        BASE_SOUL_SPEED_POTION = registerPotion(ModEffects.SOUL_SPEED, 3600, 0, "base_soul_speed_potion");
        LONG_SOUL_SPEED_POTION = registerPotion(ModEffects.SOUL_SPEED, 9600, 0, "long_soul_speed_potion");
        STRONG_SOUL_SPEED_POTION = registerPotion(ModEffects.SOUL_SPEED, 1800, 2, "strong_soul_speed_potion");

        BASE_SWIFT_SNEAK_POTION = registerPotion(ModEffects.SWIFT_SNEAK, 3600, 0, "base_swift_sneak_potion");
        LONG_SWIFT_SNEAK_POTION = registerPotion(ModEffects.SWIFT_SNEAK, 9600, 0, "long_swift_sneak_potion");
        STRONG_SWIFT_SNEAK_POTION = registerPotion(ModEffects.SWIFT_SNEAK, 1800, 2, "strong_swift_sneak_potion");

        BASE_THORNS_POTION = registerPotion(ModEffects.THORNS, 3600, 0, "base_thorns_potion");
        LONG_THORNS_POTION = registerPotion(ModEffects.THORNS, 9600, 0, "long_thorns_potion");
        STRONG_THORNS_POTION = registerPotion(ModEffects.THORNS, 1800, 2, "strong_thorns_potion");

        registerPotionRecipes();
    }

    private static void registerPotionRecipes() {
        //BASE
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(Potions.AWKWARD, Items.FIRE_CORAL_FAN,
                ModPotions.BASE_AQUA_AFFINITY_POTION);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(Potions.AWKWARD, Items.PRISMARINE_SHARD,
                ModPotions.BASE_DEPTH_STRIDER_POTION);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(Potions.AWKWARD, Items.FEATHER,
                ModPotions.BASE_FEATHER_FALLING_POTION);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(Potions.AWKWARD, Items.BLUE_ICE,
                ModPotions.BASE_FROST_WALKER_POTION);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(Potions.AWKWARD, Items.ENCHANTED_BOOK,
                ModPotions.BASE_MENDING_POTION);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(Potions.AWKWARD, Items.SEA_PICKLE,
                ModPotions.BASE_RESPIRATION_POTION);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(Potions.AWKWARD, Items.GILDED_BLACKSTONE,
                ModPotions.BASE_SOUL_SPEED_POTION);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(Potions.AWKWARD, Items.ECHO_SHARD,
                ModPotions.BASE_SWIFT_SNEAK_POTION);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(Potions.AWKWARD, Items.CACTUS,
                ModPotions.BASE_THORNS_POTION);

        //LONG
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(ModPotions.BASE_AQUA_AFFINITY_POTION, Items.REDSTONE,
                ModPotions.LONG_AQUA_AFFINITY_POTION);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(ModPotions.BASE_DEPTH_STRIDER_POTION, Items.REDSTONE,
                ModPotions.LONG_DEPTH_STRIDER_POTION);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(ModPotions.BASE_FEATHER_FALLING_POTION, Items.REDSTONE,
                ModPotions.LONG_FEATHER_FALLING_POTION);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(ModPotions.BASE_FROST_WALKER_POTION, Items.REDSTONE,
                ModPotions.LONG_FROST_WALKER_POTION);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(ModPotions.BASE_MENDING_POTION, Items.REDSTONE,
                ModPotions.LONG_MENDING_POTION);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(ModPotions.BASE_RESPIRATION_POTION, Items.REDSTONE,
                ModPotions.LONG_RESPIRATION_POTION);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(ModPotions.BASE_SOUL_SPEED_POTION, Items.REDSTONE,
                ModPotions.LONG_SOUL_SPEED_POTION);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(ModPotions.BASE_SWIFT_SNEAK_POTION, Items.REDSTONE,
                ModPotions.LONG_SWIFT_SNEAK_POTION);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(ModPotions.BASE_THORNS_POTION, Items.REDSTONE,
                ModPotions.LONG_THORNS_POTION);

        //STRONG
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(ModPotions.BASE_AQUA_AFFINITY_POTION, Items.GLOWSTONE_DUST,
                ModPotions.STRONG_AQUA_AFFINITY_POTION);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(ModPotions.BASE_DEPTH_STRIDER_POTION, Items.GLOWSTONE_DUST,
                ModPotions.STRONG_DEPTH_STRIDER_POTION);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(ModPotions.BASE_FEATHER_FALLING_POTION, Items.GLOWSTONE_DUST,
                ModPotions.STRONG_FEATHER_FALLING_POTION);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(ModPotions.BASE_FROST_WALKER_POTION, Items.GLOWSTONE_DUST,
                ModPotions.STRONG_FROST_WALKER_POTION);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(ModPotions.BASE_MENDING_POTION, Items.GLOWSTONE_DUST,
                ModPotions.STRONG_MENDING_POTION);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(ModPotions.BASE_RESPIRATION_POTION, Items.GLOWSTONE_DUST,
                ModPotions.STRONG_RESPIRATION_POTION);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(ModPotions.BASE_SOUL_SPEED_POTION, Items.GLOWSTONE_DUST,
                ModPotions.STRONG_SOUL_SPEED_POTION);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(ModPotions.BASE_SWIFT_SNEAK_POTION, Items.GLOWSTONE_DUST,
                ModPotions.STRONG_SWIFT_SNEAK_POTION);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(ModPotions.BASE_THORNS_POTION, Items.GLOWSTONE_DUST,
                ModPotions.STRONG_THORNS_POTION);
    }
}
