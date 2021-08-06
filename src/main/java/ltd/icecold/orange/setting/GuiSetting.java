package ltd.icecold.orange.setting;

import com.google.common.collect.Lists;
import ltd.icecold.orange.bean.*;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.List;

public class GuiSetting extends Setting{


    public GuiSetting() {
        super("gui");
    }

    @Override
    public void readSetting() {
        super.readSetting();
        for (YamlConfiguration yamlConfiguration:this.configurations){
            GuiDesignBean guiDesignBean = new GuiDesignBean();
            guiDesignBean.setX(yamlConfiguration.getInt("x"));
            guiDesignBean.setY(yamlConfiguration.getInt("y"));
            guiDesignBean.setGui(yamlConfiguration.getString("gui"));
            guiDesignBean.setWidth(yamlConfiguration.getInt("width"));
            guiDesignBean.setHigh(yamlConfiguration.getInt("high"));
            List<GuiImageBean> images = Lists.newArrayList();
            for (String imageString : yamlConfiguration.getStringList("image")) {
                images.add(image.get(imageString));
            }
            guiDesignBean.setImage(images);

            List<GuiTextBean> texts = Lists.newArrayList();
            for (String textString : yamlConfiguration.getStringList("text")) {
                texts.add(text.get(textString));
            }
            guiDesignBean.setText(texts);

            List<GuiButtonBean> buttons = Lists.newArrayList();
            for (String buttonString : yamlConfiguration.getStringList("buttons")) {
                buttons.add(button.get(buttonString));
            }
            guiDesignBean.setButtons(buttons);

            Setting.gui.put(yamlConfiguration.getName(),guiDesignBean);
        }
    }
}
