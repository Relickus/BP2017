/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.Captchas;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Vojta
 */
public abstract class AbstractChallenge {
    
    protected ArrayList<Image> imgArr;
    protected Set<Integer> indexArr;    
    protected int NUMBER_OF_CHALLENGE_IMAGES;
   // protected Map<IMAGECLASS,Integer> imageNumbersMap;
         
    protected abstract void addChallengeImage(String path);
    
    public abstract void getChallenge();
    
    protected abstract void loadChallengeImages(String path);
    
    //protected loadNumberOfImagesInClasses(){
        
    
}
