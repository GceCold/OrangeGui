package ltd.icecold.orange.gui;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.platform.GlStateManager;
import ltd.icecold.orange.bean.GuiDesignBean;
import ltd.icecold.orange.bean.GuiImageBean;
import ltd.icecold.orange.bean.GuiTextBean;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Environment(EnvType.CLIENT)
public class GuiDesignFrame extends Screen {
    private List<GuiCustomButton> buttonList;
    private List<Identifier> images;
    private GuiDesignBean guiDesignBean;
    private Identifier background;
    public GuiDesignFrame(List<GuiCustomButton> buttonList,List<Identifier> images,Identifier background, GuiDesignBean guiDesignBean) {
        super(new LiteralText("OrangeGui-"+UUID.randomUUID().toString()));
        this.buttonList = buttonList;
        this.images = images;
        this.guiDesignBean = guiDesignBean;
        this.background = background;
    }

    @Override
    public void render(int mouseX, int mouseY, float delta) {
        this.renderBackground();
        super.render(mouseX, mouseY, delta);
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;

        int offsetX = guiDesignBean.getX() == -1?(this.width  - guiDesignBean.getWidth()) / 2:guiDesignBean.getX();
        int offsetY = guiDesignBean.getY() == -1?(this.height - guiDesignBean.getHigh())  / 2:guiDesignBean.getY();

        Pattern pattern = Pattern.compile("\\[.*?\\]");

        for (GuiTextBean guiTextBean : guiDesignBean.getText()) {
            for (int i = 0; i < guiTextBean.getText().size(); i++) {
                String textWithColor = guiTextBean.getText().get(i);
                Matcher matcher = pattern.matcher(textWithColor);
                List<Integer> colors = Lists.newArrayList();
                while (matcher.find()){
                    String group = matcher.group();
                    colors.add(Integer.parseInt(group.replace("[","").replace("]","")));
                }
                String[] textSplit = textWithColor.replaceAll("\\[.*?\\]", "$*****").split("\\$\\*\\*\\*\\*\\*");
                List<String> textList = Lists.newArrayList(textSplit);
                textList.removeAll(Collections.singletonList(""));
                textList.removeAll(Collections.singletonList(null));
                for (int j=0;j < textList.size();j++){
                    textRenderer.draw(textList.get(j),offsetX + guiTextBean.getX(),offsetY + guiTextBean.getY()+j*textRenderer.fontHeight,colors.get(j));
                }
            }
        }
        for (int i = 0; i < guiDesignBean.getImage().size(); i++) {
            GuiImageBean guiImageBean = guiDesignBean.getImage().get(i);
            MinecraftClient.getInstance().getTextureManager().bindTexture(images.get(i));
            Gui.drawModalRectWithCustomSizedTexture(offsetX + guiImageBean.getX(),offsetY+guiImageBean.getY(),0,0,guiImageBean.getWidth(),guiImageBean.getHigh(),guiImageBean.getWidth(),guiImageBean.getHigh());
        }

    }



    @Override
    public void init(MinecraftClient client, int width, int height) {
        super.init(client, width, height);
        this.buttons.addAll(this.buttonList);
    }


    @Override
    public void renderBackground() {
        int offsetX = (this.width - guiDesignBean.getWidth()) / 2, offsetY = (this.height - guiDesignBean.getHigh()) / 2;
        GlStateManager.color4f(1.0F, 1.0F, 1.0F,1.0F);
        MinecraftClient.getInstance().getTextureManager().bindTexture(background);
        Gui.drawModalRectWithCustomSizedTexture(guiDesignBean.getX() == -1?offsetX:guiDesignBean.getX(),guiDesignBean.getY() == -1?offsetY:guiDesignBean.getY(),0,0,guiDesignBean.getWidth(),guiDesignBean.getHigh(),guiDesignBean.getWidth(),guiDesignBean.getHigh());
    }
}
