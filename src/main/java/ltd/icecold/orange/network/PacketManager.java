package ltd.icecold.orange.network;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import ltd.icecold.orange.bean.GuiButtonBean;
import ltd.icecold.orange.bean.GuiDesignBean;
import ltd.icecold.orange.bean.GuiImageBean;
import ltd.icecold.orange.gui.GuiCustomButton;
import ltd.icecold.orange.utils.Image;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PacketManager implements ClientPlayNetworking.PlayChannelHandler{
    private final ExecutorService executor = Executors.newFixedThreadPool(20);
    @Override
    public void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        buf.readBytes(1);
        byte[] bytes = new byte[buf.capacity() - 1];
        buf.readBytes(bytes);
        String json = new String(bytes, StandardCharsets.UTF_8);
        if (validate(json)){
            JsonObject asJsonObject = new JsonParser().parse(json).getAsJsonObject();
            if (asJsonObject.has("type") && asJsonObject.has("data")){
                String type = asJsonObject.get("type").getAsString();
                if ("OPEN_GUI".equals(type)){
                    GuiDesignBean data = new Gson().fromJson(asJsonObject.get("data"), GuiDesignBean.class);
                    CompletableFuture<FutureGuiTexture> futureGui = CompletableFuture.supplyAsync(()->{
                        List<Identifier> images = Lists.newArrayList();
                        int offsetX = data.getX() == -1?(MinecraftClient.getInstance().getWindow().getWidth()  - data.getWidth()) / 2:data.getX();
                        int offsetY = data.getY() == -1?(MinecraftClient.getInstance().getWindow().getHeight() - data.getHigh())  / 2:data.getY();
                        for (GuiImageBean guiImageBean : data.getImage()) {
                            try {
                                images.add(Image.imageToTexture(new URL(guiImageBean.getImage())));
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            }
                        }
                        for (GuiButtonBean button : data.getButtons()) {
                            try {
                                new GuiCustomButton(offsetX+button.getX(),offsetY+button.getY(),button.getWidth(),button.getHigh(),button.getName(),handleImageUrl(button.getUrl()),handleImageUrl(button.getUrl2()),(id)->{

                                });

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    },executor);
                }
            }
        }
    }

    private static Identifier handleImageUrl(String url) throws IOException {
        if (url.startsWith("[local]")){
            return Image.imageToTexture(new File(url.replace("[local]","")).toURI().toURL());
        }
        if (url.startsWith("[url]")){
            return Image.imageToTexture(new URL(url));
        }
        return null;
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

    public static class FutureGuiTexture{
        private List<GuiCustomButton> buttonList;
        private List<Identifier> images;

        public FutureGuiTexture(List<GuiCustomButton> buttonList, List<Identifier> images) {
            this.buttonList = buttonList;
            this.images = images;
        }

        public List<GuiCustomButton> getButtonList() {
            return buttonList;
        }

        public List<Identifier> getImages() {
            return images;
        }
    }
}
