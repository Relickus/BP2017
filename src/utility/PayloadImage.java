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

    /**
     * constructor
     * @param url Image location
     */
    public PayloadImage(String url) {
        super(url);
        this.path = url;        
    }
    
    /**
     * sets special coordinates for reference image of a challenge
     */
    public void setIsReferenceImage(){
        coordinates = new Coordinates(-1,-1);
    }

    /**
     * setter for absolute path of this image
     * @param path absolute path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * image path getter
     * @return image path
     */
    public String getPath() {
        return path;
    }

    /**
     * a getter for absolute path of
     * @return
     */
    public String getAbsolutePath() {
        return new File(path).toString().substring(6);
    }
    
    /**
     * sets coordinates of this image in the payload grid
     * @param coordinates
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * image coordinates in the payload grid getter
     * @return
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }
    
    /**
     * Returns the actual correct class of the image
     * @return Imageclass of the image
     */
    public ImageClass getCorrectClass(){      
        return correctClass;
    }

    /**
     * Sets an actual correct class to this image
     * @param imgclass
     */
    public void setCorrectClass(ImageClass imgclass) {
        
        correctClass = imgclass;
    }
}
