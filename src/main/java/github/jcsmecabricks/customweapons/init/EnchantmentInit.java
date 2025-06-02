package github.jcsmecabricks.customweapons.init;

import com.mojang.serialization.MapCodec;
import github.jcsmecabricks.customweapons.CustomWeapons;
import github.jcsmecabricks.customweapons.effects.LightningEnchantmentEffect;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class EnchantmentInit {
    public static final RegistryKey<Enchantment> BOLTING = RegistryKey.of(RegistryKeys.ENCHANTMENT, CustomWeapons.id("bolting"));

    public static final MapCodec<LightningEnchantmentEffect> LIGHTNING_EFFECT = register("lightning", LightningEnchantmentEffect.CODEC);

    private static <T extends EnchantmentEntityEffect> MapCodec<T> register(String name, MapCodec<T> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, CustomWeapons.id(name), codec);
    }

    public static void load() {}
}