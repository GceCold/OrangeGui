package ltd.icecold.orange.mixins;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(value = InGameHud.class)
public abstract class RenderOverlayMixin extends DrawableHelper {
    @Shadow public abstract TextRenderer getFontRenderer();

    @Shadow @Final private MinecraftClient client;

    @Inject(method = "render", at = @At("HEAD"))
    private void onDraw(float tickDelta, CallbackInfo ci) {

        //this.hudInfo.draw(matrixStack);
    }
}
