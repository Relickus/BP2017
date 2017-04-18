/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.util.ArrayList;
import resources.ImageClassEnum;

/**
 *
 * @author Vojta
 */
public class Result {
    
    private ArrayList<ClassifiedImage> classifiedImgsArr;
    private double accuracy;

    public Result() {
        this.classifiedImgsArr = new ArrayList<>();
    }
    
    public double getAccuracy() {
        return accuracy;
    }

    public ArrayList<ClassifiedImage> getResultArr() {
        return classifiedImgsArr;
    }
    
    public boolean isEmpty(){
        
        return classifiedImgsArr == null || classifiedImgsArr.isEmpty();
    }
    
    public void countAccuracy(ImageClassEnum challengeClass,int numberOfCorrectImgs){
        
        int counter=0;
        for(ClassifiedImage img : classifiedImgsArr){  
            if(img.matchesKeyword(challengeClass))
                ++counter;
        }
        
        accuracy = (double)counter/numberOfCorrectImgs;

    }
    
    public void addClassifiedImage(ClassifiedImage img){
        classifiedImgsArr.add(img);
    }
    
}
