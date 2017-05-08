package utility;

import java.io.File;
import javafx.scene.image.Image;
import resources.ImageClass;

/**
 * Wrapper class for payload images and their coordinates in the payload grid
 *
 */
public class PayloadImage extends Image {

    Coordinates coordinates;
    String path;
    ImageClass correctClass;

    public PayloadImage(String url) {
        super(url);
        this.path = url;        
    }
    
    public void setIsReferenceImage(){
        coordinates = new Coordinates(-1,-1);
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String getAbsolutePath() {
        return new File(path).toString().substring(6);
    }
    
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
    
    public ImageClass getCorrectClass(){      
        return correctClass;
    }

    public void setCorrectClass(ImageClass imgclass) {
        
        correctClass = imgclass;
    }
}
