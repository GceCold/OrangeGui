package ltd.icecold.orange.setting;

import com.google.common.collect.Maps;
import ltd.icecold.orange.bean.GuiButtonBean;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.Map;

public class ButtonSetting extends Setting{


    public ButtonSetting() {
        super("button");
    }

    @Override
    public void readSetting() {
        super.readSetting();
        for (YamlConfiguration yamlConfiguration:this.configurations){
            GuiButtonBean buttonBean = (GuiButtonBean)yamlConfiguration.get("");
            button.put(yamlConfiguration.getName(),buttonBean);
        }
    }
}
