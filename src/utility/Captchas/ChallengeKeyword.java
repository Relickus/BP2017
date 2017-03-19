/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.Captchas;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.Node;
import javafx.scene.control.Label;
import resources.Constants;
import resources.ImageClass;

/**
 *
 * @author Vojta
 */
public class ChallengeKeyword extends AbstractChallenge{
    
    private String keyword;
    private ArrayList<Integer> noiseClassesArr;
    private int keywordClassIdx;

    public ChallengeKeyword() {
        NUMBER_OF_CHALLENGE_IMAGES = 9;
        keywordClassIdx = 0;
        // 2..6 correct images out of 9
        NUMBER_OF_CORRECT_IMAGES = new Random().nextInt(5) + 2;
    }
    
    public ChallengeKeyword(String keywordSpecify) {
        NUMBER_OF_CHALLENGE_IMAGES = 9;
        
        specifyKeyword(keywordSpecify);
        
        // 2..6 correct images out of 9
        NUMBER_OF_CORRECT_IMAGES = new Random().nextInt(5) + 2;
    }
    
   

    private void generateKeyword(){
        Random rand = new Random();            
        keywordClassIdx = (rand.nextInt(Constants.NUMBER_OF_CLASSES)) + 1;        
        ImageClass imgclass = ImageClass.getEnum(keywordClassIdx);                
        keyword = imgclass.printableName();
    }
    
    private void specifyKeyword(String key){
        
         ImageClass imgclass = ImageClass.getEnum(key);
        
        if(imgclass != null){
            keywordClassIdx = imgclass.getValue();
            keyword = imgclass.printableName();
        }
        else    // unknown KW
            generateKeyword();
    }
    
    /*
    *
    * generates classes for noise images in image matrix
    */
    private void generateClassIndexes(){
        
        int noiseImages = NUMBER_OF_CHALLENGE_IMAGES - NUMBER_OF_CORRECT_IMAGES;
        Random rand;
        
        noiseClassesArr = new ArrayList<>(noiseImages);        
        
        for(int i = 0; i < noiseImages;){
            
            rand = new Random();
            int idx = rand.nextInt(Constants.NUMBER_OF_CLASSES + 1);
            
            if(idx != keywordClassIdx){
                noiseClassesArr.add(idx);
                ++i;                
            }
        }
        
    }
    
    @Override
    public void createChallenge() {
        
        generateKeyword();       
    }
    

    @Override
    protected void addChallengeImage(String path) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadChallengeImages() {

        


    }

    @Override
    public Node getNode() {
        return new Label(keyword);
    }

    @Override
    public String getChallengeName() {
        return "Keyword";
    }

    @Override
    public void createPayload() {
     
        generateClassIndexes();
        loadChallengeImages();    
    
    }
    
    
   
    
    
}
