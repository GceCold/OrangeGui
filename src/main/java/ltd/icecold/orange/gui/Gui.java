package ltd.icecold.orange.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;

@Environment(EnvType.CLIENT)
public class Gui {
    protected void drawHorizontalLine(int startX, int endX, int y, int color)
    {
        if (endX < startX)
        {
            int i = startX;
            startX = endX;
            endX = i;
        }

        drawRect(startX, y, endX + 1, y + 1, color);
    }

    protected void drawVerticalLine(int x, int startY, int endY, int color)
    {
        if (endY < startY)
        {
            int i = startY;
            startY = endY;
            endY = i;
        }

        drawRect(x, startY + 1, x + 1, endY, color);
    }

    public static void drawRect(int left, int top, int right, int bottom, int color) {
        if (left < right)
        {
            int i = left;
            left = right;
            right = i;
        }

        if (top < bottom)
        {
            int j = top;
            top = bottom;
            bottom = j;
        }

        float f3 = (float)(color >> 24 & 255) / 255.0F;
        float f = (float)(color >> 16 & 255) / 255.0F;
        float f1 = (float)(color >> 8 & 255) / 255.0F;
        float f2 = (float)(color & 255) / 255.0F;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture();
        GlStateManager.blendFuncSeparate(GlStateManager.SrcFactor.SRC_ALPHA.value, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA.value, GlStateManager.SrcFactor.ONE.value, GlStateManager.DstFactor.ZERO.value);
        GlStateManager.color4f(f, f1, f2, f3);
        bufferbuilder.begin(7, VertexFormats.POSITION);
        bufferbuilder.vertex(left, bottom, 0.0D).next();
        bufferbuilder.vertex(right, bottom, 0.0D).next();
        bufferbuilder.vertex(right, top, 0.0D).next();
        bufferbuilder.vertex(left, top, 0.0D).next();
        tessellator.draw();
        GlStateManager.enableTexture();
        GlStateManager.disableBlend();
    }


    public static void drawModalRectWithCustomSizedTexture(int x, int y, float u, float v, int width, int height, float textureWidth, float textureHeight) {
        float f = 1.0F / textureWidth;
        float f1 = 1.0F / textureHeight;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, VertexFormats.POSITION_TEXTURE);
        bufferbuilder.vertex(x, y + height, 0.0D).texture(u * f, (v + (float)height) * f1).next();
        bufferbuilder.vertex(x + width, (y + height), 0.0D).texture((u + (float)width) * f,(v + (float)height) * f1).next();
        bufferbuilder.vertex(x + width, y, 0.0D).texture((u + (float)width) * f, v * f1).next();
        bufferbuilder.vertex(x, y, 0.0D).texture(u * f, v * f1).next();
        tessellator.draw();
    }
}
