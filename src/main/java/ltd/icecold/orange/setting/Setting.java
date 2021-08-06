package ltd.icecold.orange.setting;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import ltd.icecold.orange.OrangeGui;
import ltd.icecold.orange.bean.*;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;
import java.util.Map;

public abstract class Setting {
    public static final Map<String, GuiButtonBean> button = Maps.newConcurrentMap();
    public static final Map<String, GuiDesignBean> gui = Maps.newConcurrentMap();
    public static final Map<String, GuiHudBean> hud = Maps.newConcurrentMap();
    public static final Map<String, GuiImageBean> image = Maps.newConcurrentMap();
    public static final Map<String, GuiSlotBean> slot = Maps.newConcurrentMap();
    public static final Map<String, GuiTextBean> text = Maps.newConcurrentMap();

    private String component;
    public List<YamlConfiguration> configurations = Lists.newArrayList();

    public Setting(String component) {
        this.component = component;
        writeSetting();
        readSetting();
    }

    public void readSetting(){
        List<File> files = getFiles(OrangeGui.getInstance().getDataFolder() + "/" + this.component);
        List<YamlConfiguration> ymls = Lists.newArrayList();
        for (File file:files){
            ymls.add(YamlConfiguration.loadConfiguration(file));
        }
        this.configurations = ymls;
    }

    protected void writeSetting(){
        if (!new File(OrangeGui.getInstance().getDataFolder(),this.component+"/example.yml").exists())
        OrangeGui.getInstance().saveResource(this.component+"/example.yml",false);
    }

    protected List<File> getFiles(String path){
        File pathFile = new File(path);
        List<File> result = Lists.newArrayList();
        File[] files = pathFile.listFiles();
        if (files == null) return Lists.newArrayList();
        for (File file : files) {
            if (!file.isFile()){
                result.addAll(getFiles(file.getAbsolutePath()));
            }
            result.add(file);
        }
        return result;
    }
}
