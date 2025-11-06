package github.jcsmecabricks.customweapons.custom;

import github.jcsmecabricks.customweapons.advancement.criterion.ModCriteria;
import net.fabricmc.fabric.api.item.v1.EnchantingContext;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.UseAction;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public class CompoundBowItem extends BowItem {
    public static final int TICKS_PER_SECOND = 20;

    public CompoundBowItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof PlayerEntity playerEntity)) {
            return false;
        }

        ItemStack projectile = playerEntity.getProjectileType(stack);
        if (projectile.isEmpty()) {
            return false;
        }

        int useTime = this.getMaxUseTime(stack, user) - remainingUseTicks;
        float pull = getPullProgress(useTime);
        if (pull < 0.1F) {
            return false;
        }

        List<ItemStack> list = load(stack, projectile, playerEntity);
        if (world instanceof ServerWorld serverWorld && !list.isEmpty()) {
            int i = this.getMaxUseTime(stack, user) - remainingUseTicks;
            float f = getPullProgress(i);
            this.shootAll(serverWorld, playerEntity, playerEntity.getActiveHand(), stack, list, f * 6.0F, 1.0F, f == 1.0F, (LivingEntity)null);

            if (playerEntity instanceof ServerPlayerEntity serverPlayer) {
                ModCriteria.SHOT_COMPOUND_BOW.trigger(serverPlayer, stack);
            }
        }

        world.playSound(
                null,
                playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(),
                SoundEvents.ENTITY_ARROW_SHOOT,
                SoundCategory.PLAYERS,
                1.0F,
                1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + pull * 0.5F
        );

        playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        return true;
    }


    @Override
    protected void shoot(LivingEntity shooter, ProjectileEntity projectile, int index, float speed, float divergence, float yaw, @Nullable LivingEntity target) {
        projectile.setVelocity(shooter, shooter.getPitch(), shooter.getYaw() + yaw, 0.0F, speed, divergence);
        if (projectile instanceof PersistentProjectileEntity persistentProjectile) {
        }
    }

    public static float getPullProgress(int useTicks) {
        float f = (float) useTicks / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 72000;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        boolean bl = !user.getProjectileType(itemStack).isEmpty();
        if (!user.isCreative() && !bl) {
            return ActionResult.FAIL;
        } else {
            user.setCurrentHand(hand);
            return ActionResult.CONSUME;
        }
    }
    @Override
    public boolean canBeEnchantedWith(ItemStack stack, RegistryEntry<Enchantment> enchantment, EnchantingContext context) {
        return enchantment.matchesKey(Enchantments.MENDING) || super.canBeEnchantedWith(stack, enchantment, context);
    }

    @Override
    public Predicate<ItemStack> getProjectiles() {
        return BOW_PROJECTILES;
    }

    @Override
    public int getRange() {
        return 20;
    }
    public float call(ItemStack stack, World world, LivingEntity entity, int seed) {
        if (entity == null) {
            return 0.0F;
        }

        if (entity.getActiveItem() == stack) {
            int useTime = stack.getMaxUseTime(entity) - entity.getItemUseTimeLeft();
            return (float) useTime / 20.0F;
        }

        return 0.0F;
    }

}
