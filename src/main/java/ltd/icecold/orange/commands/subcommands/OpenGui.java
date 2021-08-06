package ltd.icecold.orange.commands.subcommands;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import ltd.icecold.orange.bean.GuiDesignBean;
import ltd.icecold.orange.commands.SubCommand;
import ltd.icecold.orange.network.PacketHandle;
import ltd.icecold.orange.setting.Setting;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class OpenGui implements SubCommand {
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("[OrangeGui] >§c请在游戏内执行本指令！");
            return;
        }
        if (args.length != 3){
            sender.sendMessage("[OrangeGui] >§c请输入正确指令/gui open [gui]");
            return;
        }
        if (!Setting.gui.containsKey(args[1])){
            sender.sendMessage("[OrangeGui] >§c未找到该GUI配置文件");
            return;
        }
        Player player = (Player) sender;
        Map<String, Object> data = Maps.newHashMap();
        data.put("type","OPEN_GUI");
        data.put("data",Setting.gui.get(args[1]));
        PacketHandle.send(player,new Gson().toJson(data));
    }
}
