
    package assets;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AssetManager {
    private static final Map<String, BufferedImage> images = new HashMap<>();

    public static BufferedImage loadImage(String path) {
        if (!images.containsKey(path)) {
            try {
                images.put(path, ImageIO.read(Objects.requireNonNull(AssetManager.class.getResource(path))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return images.get(path);
    }
}

