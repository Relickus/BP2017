/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.Captchas;

import java.util.ArrayList;
import java.util.Random;
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
    
    

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


    private void generateKeyword(){
        Random rand = new Random();            
        keywordClassIdx = (rand.nextInt(Constants.NUMBER_OF_CLASSES)) + 1;        
        ImageClass imgclass = ImageClass.getValue(keywordClassIdx);                
        keyword = imgclass.printableName();
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
    public void getChallenge() {
        
        generateKeyword();            
        generateClassIndexes();
        loadChallengeImages();
    }
    

    @Override
    protected void addChallengeImage(String path) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadChallengeImages() {

        


    }
   
    
    
}
