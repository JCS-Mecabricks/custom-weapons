package github.jcsmecabricks.customweapons.init;

import com.mojang.serialization.MapCodec;
import github.jcsmecabricks.customweapons.CustomWeapons;
import github.jcsmecabricks.customweapons.effects.LightningEnchantmentEffect;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;

public class EnchantmentInit {
    public static final ResourceKey<Enchantment> BOLTING = ResourceKey.create(Registries.ENCHANTMENT, CustomWeapons.id("bolting"));

    public static final MapCodec<LightningEnchantmentEffect> LIGHTNING_EFFECT = register("lightning", LightningEnchantmentEffect.CODEC);

    private static <T extends EnchantmentEntityEffect> MapCodec<T> register(String name, MapCodec<T> codec) {
        return Registry.register(BuiltInRegistries.ENCHANTMENT_ENTITY_EFFECT_TYPE, CustomWeapons.id(name), codec);
    }

    public static void load() {}
}