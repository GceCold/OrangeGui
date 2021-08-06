package ltd.icecold.orange.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.UUID;

public class Image {
    public static Identifier imageToTexture(URL url){
        try {
            BufferedImage image = ImageIO.read(url);
            NativeImage nativeImage = NativeImage.read(bufferedImageToInputStream(image));
            NativeImageBackedTexture nativeImageBackedTexture = new NativeImageBackedTexture(nativeImage);
            return MinecraftClient.getInstance().getTextureManager().registerDynamicTexture(UUID.randomUUID().toString(), nativeImageBackedTexture);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static InputStream bufferedImageToInputStream(BufferedImage image) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, "png", os);
        return new ByteArrayInputStream(os.toByteArray());
    }
}
