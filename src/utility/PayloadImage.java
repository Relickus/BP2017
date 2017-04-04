package utility;

import javafx.scene.image.Image;

/**
 * Wrapper class for payload images and their coordinates in the payload grid
 *
 */
public class PayloadImage extends Image {

    Coordinates coordinates;
    String path;

    public PayloadImage(String url) {
        super(url);
        this.path = url;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

}
