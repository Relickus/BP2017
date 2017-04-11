package utility;

import java.io.File;
import javafx.scene.image.Image;
import resources.ImageClassEnum;

/**
 * Wrapper class for payload images and their coordinates in the payload grid
 *
 */
public class PayloadImage extends Image {

    Coordinates coordinates;
    String path;
    ImageClassEnum correctClass;

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

    public String getAbsolutePath() {
        return new File(path).toString().substring(6);
    }
    
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
    
    public ImageClassEnum getCorrectClass(){
        
        int idxStart = path.lastIndexOf('\\');   //for win system paths
        if(idxStart == -1 )
            idxStart = path.lastIndexOf('/')+1;    // for unix system paths 
        
        int idxEnd = path.indexOf('_');
        
        correctClass = ImageClassEnum.getEnum(path.substring(idxStart, idxEnd)); 
        
        return correctClass;
    }

}
