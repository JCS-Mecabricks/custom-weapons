package github.jcsmecabricks.customweapons.entity.client;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class ElephantRenderState extends LivingEntityRenderState {
    public final AnimationState idlingAnimationState = new AnimationState();
    public boolean isSaddled = false;
    public boolean hasArmorOn;
    public boolean hasChest;
    public @Nullable DyeColor swag;
    public ItemStack getBodyArmor;

    public DyeColor getSwag() {
        return swag;
    }

    public ItemStack getBodyArmor() {
        return getBodyArmor;
    }
}
