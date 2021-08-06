package ltd.icecold.orange;

import ltd.icecold.orange.gui.GuiDesignFrame;
import ltd.icecold.orange.network.PacketManager;
import ltd.icecold.orange.utils.Image;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.command.CommandManager;
import net.minecraft.util.Identifier;

import java.io.File;
import java.io.IOException;

public class OrangeGui implements ModInitializer {
    @Override
    public void onInitialize() {
        ClientPlayNetworking.registerGlobalReceiver(new Identifier("icecold:hud"),new PacketManager());
        ClientPlayConnectionEvents.JOIN.register((handler, sender, server)->{
            System.out.println("进入服务器");
        });
        ClientPlayConnectionEvents.DISCONNECT.register((handler, server)->{
            System.out.println("离开服务器");
        });

        if (!new File("orange/textures").exists()) new File("orange/textures").mkdirs();
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(CommandManager.literal("icehud").executes(context -> {

                System.out.println(new File("orange/gui/gui_main_background.png").getAbsolutePath());
                try {
                    Identifier testTextture = Image.imageToTexture(new File("orange/gui/gui_main_background.png").toURI().toURL());
                    //MinecraftClient.getInstance().openScreen(new GuiDesignFrame(testTextture));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return 1;
            }));
            dispatcher.register(CommandManager.literal("ice").executes(context -> {
                /**
                 System.out.println(new File("orange/gui/gui_main_background.png").getAbsolutePath());
                 try {
                 Identifier testTextture = Image.imageToTexture(new File("orange/gui/gui_main_background.png").toURI().toURL());
                 MinecraftClient.getInstance().openScreen(new GuiTest(testTextture));
                 } catch (MalformedURLException e) {
                 e.printStackTrace();
                 }*/
                MinecraftClient.getInstance().gameRenderer.tick();
                return 1;
            }));
        });

    }
}
