package github.jcsmecabricks.customweapons.advancement.criterion;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Optional;

public class ShotCompoundBowCriterion extends AbstractCriterion<ShotCompoundBowCriterion.Conditions> {
    public Codec<ShotCompoundBowCriterion.Conditions> getConditionsCodec() {
        return ShotCompoundBowCriterion.Conditions.CODEC;
    }

    public void trigger(ServerPlayerEntity player, ItemStack stack) {
        this.trigger(player, (conditions) -> conditions.matches(stack));
    }

    public static record Conditions(Optional<LootContextPredicate> player, Optional<ItemPredicate> item) implements AbstractCriterion.Conditions {
        public static final Codec<ShotCompoundBowCriterion.Conditions> CODEC = RecordCodecBuilder.create((instance) -> instance.group(EntityPredicate.LOOT_CONTEXT_PREDICATE_CODEC.optionalFieldOf("player").forGetter(ShotCompoundBowCriterion.Conditions::player), ItemPredicate.CODEC.optionalFieldOf("item").forGetter(ShotCompoundBowCriterion.Conditions::item)).apply(instance, ShotCompoundBowCriterion.Conditions::new));

        public static AdvancementCriterion<ShotCompoundBowCriterion.Conditions> create(Optional<ItemPredicate> item) {
            return ModCriteria.SHOT_COMPOUND_BOW.create(new ShotCompoundBowCriterion.Conditions(Optional.empty(), item));
        }

        public static AdvancementCriterion<ShotCompoundBowCriterion.Conditions> create(RegistryEntryLookup<Item> itemRegistry, ItemConvertible item) {
            return ModCriteria.SHOT_COMPOUND_BOW.create(new ShotCompoundBowCriterion.Conditions(Optional.empty(), Optional.of(ItemPredicate.Builder.create().items(itemRegistry, new ItemConvertible[]{item}).build())));
        }

        public boolean matches(ItemStack stack) {
            return this.item.isEmpty() || ((ItemPredicate)this.item.get()).test(stack);
        }
    }
}
