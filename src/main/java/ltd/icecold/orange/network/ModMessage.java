package ltd.icecold.orange.network;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class ModMessage implements PluginMessageListener {
    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        String read = PacketHandle.read(message);
        if (validate(read)) {

        }
    }
    private static boolean validate(String json) {
        JsonElement jsonElement;
        try {
            jsonElement = new JsonParser().parse(json);
        } catch (Exception e) {
            return false;
        }
        if (jsonElement == null) {
            return false;
        }
        return jsonElement.isJsonObject();
    }
}
