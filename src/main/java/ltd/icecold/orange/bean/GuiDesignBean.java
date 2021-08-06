package ltd.icecold.orange.bean;

import com.google.common.collect.Maps;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.List;
import java.util.Map;

public class GuiDesignBean {
    private String gui;
    private int x;
    private int y;
    private int width;
    private int high;
    private List<GuiTextBean> text;
    private List<GuiButtonBean> buttons;
    private List<GuiSlotBean> slot;
    private List<GuiImageBean> image;

    public List<GuiImageBean> getImage() {
        return image;
    }

    public void setImage(List<GuiImageBean> image) {
        this.image = image;
    }

    public String getGui() {
        return gui;
    }

    public void setGui(String gui) {
        this.gui = gui;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public List<GuiTextBean> getText() {
        return text;
    }

    public void setText(List<GuiTextBean> text) {
        this.text = text;
    }

    public List<GuiButtonBean> getButtons() {
        return buttons;
    }

    public void setButtons(List<GuiButtonBean> buttons) {
        this.buttons = buttons;
    }

    public List<GuiSlotBean> getSlot() {
        return slot;
    }

    public void setSlot(List<GuiSlotBean> slot) {
        this.slot = slot;
    }

}
