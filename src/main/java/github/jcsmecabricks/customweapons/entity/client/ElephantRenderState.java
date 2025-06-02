package github.jcsmecabricks.customweapons.entity.client;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
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
