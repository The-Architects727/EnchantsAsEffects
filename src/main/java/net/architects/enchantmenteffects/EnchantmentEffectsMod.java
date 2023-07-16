package net.architects.enchantmenteffects;

import net.architects.enchantmenteffects.effects.ModEffects;
import net.architects.enchantmenteffects.potion.ModPotions;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Very important comment
public class EnchantmentEffectsMod implements ModInitializer {
	public static final String MOD_ID = "enchantmenteffects";
	public static final Logger LOGGER = LoggerFactory.getLogger("enchantmenteffects");

	@Override
	public void onInitialize() {
		ModEffects.registerEffects();
		ModPotions.registerPotions();
	}
}