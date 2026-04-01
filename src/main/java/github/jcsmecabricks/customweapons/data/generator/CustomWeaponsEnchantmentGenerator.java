package github.jcsmecabricks.customweapons.data.generator;

import github.jcsmecabricks.customweapons.effects.LightningEnchantmentEffect;
import github.jcsmecabricks.customweapons.init.EnchantmentInit;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import java.util.concurrent.CompletableFuture;

public class CustomWeaponsEnchantmentGenerator extends FabricDynamicRegistryProvider {
    public CustomWeaponsEnchantmentGenerator(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        HolderLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);

        register(entries, EnchantmentInit.BOLTING, Enchantment.enchantment(
                Enchantment.definition(
                        itemLookup.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                        20,
                        3,
                        Enchantment.dynamicCost(1, 10),
                        Enchantment.dynamicCost(1, 15),
                        7,
                        EquipmentSlotGroup.HAND
                ))
                .withEffect(EnchantmentEffectComponents.POST_ATTACK,
                        EnchantmentTarget.ATTACKER,
                        EnchantmentTarget.VICTIM,
                        new LightningEnchantmentEffect(LevelBasedValue.perLevel(0.5f, 0.15f)))
        );
    }

    private static void register(Entries entries, ResourceKey<Enchantment> key, Enchantment.Builder builder, ResourceCondition... resourceConditions) {
        entries.add(key, builder.build(key.identifier()), resourceConditions);
    }

    @Override
    public String getName() {
        return "Enchantment Generator";
    }
}
