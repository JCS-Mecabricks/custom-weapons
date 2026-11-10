
package github.jcsmecabricks.customweapons.advancement.criterion;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Optional;

import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.advancements.triggers.SimpleCriterionTrigger;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

public class ShotCompoundBowCriterion extends SimpleCriterionTrigger<ShotCompoundBowCriterion.Conditions> {

    public Codec<ShotCompoundBowCriterion.Conditions> codec() {
        return ShotCompoundBowCriterion.Conditions.CODEC;
    }

    public void trigger(ServerPlayer player, ItemStack stack) {
        this.trigger(player, conditions -> conditions.matches(stack));
    }

    public static record Conditions(
            Optional<Holder<LootItemCondition>> player,
            Optional<ItemPredicate> item
    ) implements SimpleCriterionTrigger.SimpleInstance {

        public static final Codec<Conditions> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        LootItemCondition.CODEC.optionalFieldOf("player")
                                .forGetter(Conditions::player),
                        ItemPredicate.CODEC.optionalFieldOf("item")
                                .forGetter(Conditions::item)
                ).apply(instance, Conditions::new)
        );

        public static Criterion<Conditions> create(Optional<ItemPredicate> item) {
            return ModCriteria.SHOT_COMPOUND_BOW.createCriterion(
                    new Conditions(Optional.empty(), item)
            );
        }

        public static Criterion<Conditions> create(
                HolderGetter<Item> itemRegistry,
                ItemLike item
        ) {
            return ModCriteria.SHOT_COMPOUND_BOW.createCriterion(
                    new Conditions(
                            Optional.empty(),
                            Optional.of(
                                    ItemPredicate.Builder.item()
                                            .of(itemRegistry, item)
                                            .build()
                            )
                    )
            );
        }

        public boolean matches(ItemStack stack) {
            return item.isEmpty() || item.get().test(stack);
        }
    }
}
