package tile;
import java.awt.image.BufferedImage;

public class Tile {
    public BufferedImage image;
    public boolean collision;

    Tile(BufferedImage img, boolean collision){
        this.image = img;
        this.collision = collision;
    }
}
