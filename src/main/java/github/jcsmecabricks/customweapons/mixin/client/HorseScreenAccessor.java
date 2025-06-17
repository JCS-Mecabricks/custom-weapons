package github.jcsmecabricks.customweapons.mixin.client;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HorseScreen;
import net.minecraft.entity.passive.AbstractHorseEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(HorseScreen.class)
public interface HorseScreenAccessor {
    @Accessor("entity")
    AbstractHorseEntity getEntity();

    @Invoker("drawSlot")
    void callDrawSlot(DrawContext context, int x, int y);
}
