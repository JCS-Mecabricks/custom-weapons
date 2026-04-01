package github.jcsmecabricks.customweapons.advancement.criterion;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Optional;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.ContextAwarePredicate;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.SimpleCriterionTrigger;
import net.minecraft.core.HolderGetter;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class ShotCompoundBowCriterion extends SimpleCriterionTrigger<ShotCompoundBowCriterion.Conditions> {
    public Codec<ShotCompoundBowCriterion.Conditions> codec() {
        return ShotCompoundBowCriterion.Conditions.CODEC;
    }

    public void trigger(ServerPlayer player, ItemStack stack) {
        this.trigger(player, (conditions) -> conditions.matches(stack));
    }

    public static record Conditions(Optional<ContextAwarePredicate> player, Optional<ItemPredicate> item) implements SimpleCriterionTrigger.SimpleInstance {
        public static final Codec<ShotCompoundBowCriterion.Conditions> CODEC = RecordCodecBuilder.create((instance) -> instance.group(EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(ShotCompoundBowCriterion.Conditions::player), ItemPredicate.CODEC.optionalFieldOf("item").forGetter(ShotCompoundBowCriterion.Conditions::item)).apply(instance, ShotCompoundBowCriterion.Conditions::new));

        public static Criterion<ShotCompoundBowCriterion.Conditions> create(Optional<ItemPredicate> item) {
            return ModCriteria.SHOT_COMPOUND_BOW.createCriterion(new ShotCompoundBowCriterion.Conditions(Optional.empty(), item));
        }

        public static Criterion<ShotCompoundBowCriterion.Conditions> create(HolderGetter<Item> itemRegistry, ItemLike item) {
            return ModCriteria.SHOT_COMPOUND_BOW.createCriterion(new ShotCompoundBowCriterion.Conditions(Optional.empty(), Optional.of(ItemPredicate.Builder.item().of(itemRegistry, new ItemLike[]{item}).build())));
        }

        public boolean matches(ItemStack stack) {
            return this.item.isEmpty() || ((ItemPredicate)this.item.get()).test(stack);
        }
    }
}
