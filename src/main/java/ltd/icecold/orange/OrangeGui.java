package ltd.icecold.orange;

import ltd.icecold.orange.bean.GuiButtonBean;
import ltd.icecold.orange.bean.GuiImageBean;
import ltd.icecold.orange.commands.CommandHandle;
import ltd.icecold.orange.network.ModMessage;
import ltd.icecold.orange.setting.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

public final class OrangeGui extends JavaPlugin {

    private static OrangeGui instance;
    public static final String CHANNEL = "OrangeGui:message";

    @Override
    public void onEnable() {
        instance = this;
        ConfigurationSerialization.registerClass(GuiButtonBean.class);
        Bukkit.getPluginCommand("music").setExecutor(new CommandHandle());
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, CHANNEL);
        Bukkit.getMessenger().registerIncomingPluginChannel(this, CHANNEL, new ModMessage());
        new TextSetting();
        new ImageSetting();
        new ButtonSetting();
        new GuiSetting();
    }

    @Override
    public void onDisable() {
    }

    public static OrangeGui getInstance() {
        return instance;
    }
}
