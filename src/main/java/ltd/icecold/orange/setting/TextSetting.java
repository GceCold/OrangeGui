package ltd.icecold.orange.setting;

import ltd.icecold.orange.bean.GuiImageBean;
import ltd.icecold.orange.bean.GuiTextBean;
import org.bukkit.configuration.file.YamlConfiguration;

public class TextSetting extends Setting{
    public TextSetting() {
        super("text");
    }

    @Override
    public void readSetting() {
        super.readSetting();
        for (YamlConfiguration yamlConfiguration:this.configurations){
            GuiTextBean guiTextBean = new GuiTextBean();
            guiTextBean.setX(yamlConfiguration.getInt("x"));
            guiTextBean.setY(yamlConfiguration.getInt("y"));
            guiTextBean.setText(yamlConfiguration.getStringList("text"));
            text.put(yamlConfiguration.getName(),guiTextBean);
        }
    }
}
