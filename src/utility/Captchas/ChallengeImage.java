/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.Captchas;
;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import resources.Constants;

/**
 *
 * @author Vojta
 */
public class ChallengeImage extends AbstractChallenge{
    
    private Image refImg;
    

    public ChallengeImage() {        
        NUMBER_OF_CHALLENGE_IMAGES = 9;
    }
  
    
    public void setImage(String path){
        
        // load Image to refImg
    }

    @Override
    public void getChallenge() {
        
        if(refImg == null){   
            int classIndex;
            Random rand = new Random();
            classIndex = rand.nextInt() % Constants.NUMBER_OF_CLASSES;
            
            int refImgIndex, maxIndex= 0;
            rand = new Random();
            maxIndex = //get number of images of current class on HDD;
            
            refImgIndex = rand.nextInt() % maxIndex;
            
            try {            
                File file = new File("c:\\mypic.jpg");
                refImg = ImageIO.read(file);
            } catch (IOException ex) {
                Logger.getLogger(ChallengeImage.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        
    }
    
    private void loadRefImage(){
        
        
        
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
    protected void loadChallengeImages(String path) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
