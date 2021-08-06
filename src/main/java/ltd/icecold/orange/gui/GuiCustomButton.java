package ltd.icecold.orange.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.awt.*;

public class GuiCustomButton extends ButtonWidget {
    private Identifier texture1;
    private Identifier texture2;
    public GuiCustomButton(int x, int y, int width, int height, String message, Identifier texture1,Identifier texture2, PressAction onPress) {
        super(x, y, width, height, message, onPress);
        this.texture1 = texture1;
        this.texture2 = texture2;
    }

    @Override
    public void renderButton(int mouseX, int mouseY, float delta) {
        if (this.visible) {
            TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
            GlStateManager.color4f(1.0F, 1.0F, 1.0F,1.0F);
            if (this.isHovered){
                MinecraftClient.getInstance().getTextureManager().bindTexture(texture2);
            }else {
                MinecraftClient.getInstance().getTextureManager().bindTexture(texture1);
            }
            Gui.drawModalRectWithCustomSizedTexture(this.x,this.y,0,0,this.width, this.height,this.width,this.height);
            this.drawCenteredString(textRenderer, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, 0xFFFFFF);
        }
    }
}
