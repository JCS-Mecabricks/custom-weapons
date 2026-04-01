package github.jcsmecabricks.customweapons.util;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class DeadEyeManager {
    private int value = 10; // like foodLevel
    private boolean active;
    private int tickTimer;

    public void tick(ServerPlayer player) {
        ServerLevel world = player.level();

        if (active) {
            // drain Dead Eye slowly
            if (++tickTimer >= 10) {
                tickTimer = 0;
                value = Math.max(value - 1, 0);
                DeadEyeData.setDeadEye((IEntityDataSaver) player, value);

                // deactivate if empty
                if (value <= 0) {
                    active = false;
                    DeadEyeData.setDeadEyeActive((IEntityDataSaver) player, false);
                }
            }

            // apply slowdown only while active
            if (value > 0) {
                applySlowEffect(world, player);
            }
        } else {
            tickTimer = 0; // reset timer when inactive
        }
    }

    private void applySlowEffect(ServerLevel world, ServerPlayer player) {
        world.getEntitiesOfClass(net.minecraft.world.entity.LivingEntity.class,
                player.getBoundingBox().inflate(10),
                e -> e != player).forEach(e -> {
            e.setDeltaMovement(e.getDeltaMovement().scale(0.5));
        });
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return this.active;
    }

    public int getValue() {
        return this.value;
    }
}
