/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.Captchas;
;
import javafx.scene.Node;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import resources.Constants;
import resources.ImageClass;

/**
 *
 * @author Vojta
 */
public class ChallengeImage extends AbstractChallenge{
    
    private Image refImg;
    private int refImgClassIdx;

    public ChallengeImage() {        
        NUMBER_OF_CHALLENGE_IMAGES = 9;
    }
  
    
    public void setImage(String path){
        
        // load Image to refImg
    }

    @Override
    public void createChallenge() {
        // loads reference image
     
        loadRefImage();
        
    }
    
    private void generateRefImgIndex(){
        Random rand = new Random();            
        refImgClassIdx = (rand.nextInt(Constants.NUMBER_OF_CLASSES)) + 1;        
        ImageClass imgclass = ImageClass.getEnum(refImgClassIdx);  
    }
    
    public void specifyImage(String path){
        
       
            
        
    }
    
    public void setRefImgIndex(int idx){        
        refImgClassIdx = idx;               
    }
        
    private void loadRefImage(){
        
        
        // FIX THIS LATER ON COMPLETE DATASET
        // respect user-specified keyword!!!
        
        if(new Random().nextBoolean() == true)
            refImg = new Image("file:src/htmlCAPTCHAs/a.jpg");
        else
            refImg = new Image("file:src/htmlCAPTCHAs/b.JPG");
        
       // MyImageLoader loader = new MyImageLoader();
        
        //refImg = loader.getReferenceImage(refImgClassIdx);
        //imgArr = loader.getImageMatrix(refImgClassIdx,NUMBER_OF_CHALLENGE_IMAGES,NUMBER_OF_CORRECT_IMAGES);
        
        
    }
    
    private void fillIndexArr(){
        
        Random rand = new Random();
        
        for(int i = 0 ; i < NUMBER_OF_CHALLENGE_IMAGES; ++i){
            
           // rand.nextInt() % 
        }
        
    }

    @Override
    protected void addChallengeImage(String path) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    private void loadChallengeImages(String path) {
//
//        File  directory = new File("resource");
//
//        for (File file : directory.listFiles())
//        {
//            if(file.getName().toLowerCase().endsWith(".png")
//                    || file.getName().toLowerCase().endsWith(".jpg")
//                    || file.getName().toLowerCase().endsWith(".jpeg")
//                    || file.getName().toLowerCase().endsWith(".bmp"))
//            {
//                
//                //if( index je ten kterej sem si vygeneroval)
//                
//                try {
//                    imgArr.add(ImageIO.read(file));
//                } catch (IOException ex) {
//                    Logger.getLogger(ChallengeImage.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                
//            }
//        }
//    }

    @Override
    protected void loadChallengeImages() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Node getNode() {

           return new ImageView(refImg); 

    }

    @Override
    public String getChallengeName() {
        return "Reference image";
    }

    @Override
    public void createPayload() {

        

    }
    
    
    
    
    
}
