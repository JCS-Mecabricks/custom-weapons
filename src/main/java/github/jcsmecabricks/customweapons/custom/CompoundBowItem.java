package github.jcsmecabricks.customweapons.custom;

import github.jcsmecabricks.customweapons.advancement.criterion.ModCriteria;
import net.fabricmc.fabric.api.item.v1.EnchantingContext;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public class CompoundBowItem extends BowItem {
    public static final int TICKS_PER_SECOND = 20;

    public CompoundBowItem(Properties settings) {
        super(settings);
    }

    @Override
    public boolean releaseUsing(ItemStack stack, Level world, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof Player playerEntity)) {
            return false;
        }

        ItemStack projectile = playerEntity.getProjectile(stack);
        if (projectile.isEmpty()) {
            return false;
        }

        int useTime = this.getUseDuration(stack, user) - remainingUseTicks;
        float pull = getPowerForTime(useTime);
        if (pull < 0.1F) {
            return false;
        }

        List<ItemStack> list = draw(stack, projectile, playerEntity);
        if (world instanceof ServerLevel serverWorld && !list.isEmpty()) {
            int i = this.getUseDuration(stack, user) - remainingUseTicks;
            float f = getPowerForTime(i);
            this.shoot(serverWorld, playerEntity, playerEntity.getUsedItemHand(), stack, list, f * 6.0F, 1.0F, f == 1.0F, (LivingEntity)null);

            if (playerEntity instanceof ServerPlayer serverPlayer) {
                ModCriteria.SHOT_COMPOUND_BOW.trigger(serverPlayer, stack);
            }
        }

        world.playSound(
                null,
                playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(),
                SoundEvents.ARROW_SHOOT,
                SoundSource.PLAYERS,
                1.0F,
                1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + pull * 0.5F
        );

        playerEntity.awardStat(Stats.ITEM_USED.get(this));
        return true;
    }


    @Override
    protected void shootProjectile(LivingEntity shooter, Projectile projectile, int index, float speed, float divergence, float yaw, @Nullable LivingEntity target) {
        projectile.shootFromRotation(shooter, shooter.getXRot(), shooter.getYRot() + yaw, 0.0F, speed, divergence);
        if (projectile instanceof AbstractArrow persistentProjectile) {
        }
    }

    public static float getPowerForTime(int useTicks) {
        float f = (float) useTicks / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity user) {
        return 72000;
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack stack) {
        return ItemUseAnimation.BOW;
    }

    @Override
    public InteractionResult use(Level world, Player user, InteractionHand hand) {
        ItemStack itemStack = user.getItemInHand(hand);
        boolean bl = !user.getProjectile(itemStack).isEmpty();
        if (!user.isCreative() && !bl) {
            return InteractionResult.FAIL;
        } else {
            user.startUsingItem(hand);
            return InteractionResult.CONSUME;
        }
    }
    @Override
    public boolean canBeEnchantedWith(ItemStack stack, Holder<Enchantment> enchantment, EnchantingContext context) {
        return enchantment.is(Enchantments.MENDING) || super.canBeEnchantedWith(stack, enchantment, context);
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return ARROW_ONLY;
    }

    @Override
    public int getDefaultProjectileRange() {
        return 20;
    }
    public float call(ItemStack stack, Level world, LivingEntity entity, int seed) {
        if (entity == null) {
            return 0.0F;
        }

        if (entity.getUseItem() == stack) {
            int useTime = stack.getUseDuration(entity) - entity.getUseItemRemainingTicks();
            return (float) useTime / 20.0F;
        }

        return 0.0F;
    }

}
