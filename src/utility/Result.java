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
    
    public void countAccuracy(){
        
        int counter=0;
        for(ClassifiedImage img : classifiedImgsArr){  
            ImageClassEnum correctClass = img.getCorrectClass();    // correctclass is never NULL
            if(correctClass.equals(img.getPredictedClass()))
                ++counter;
        }
        
        accuracy = counter/classifiedImgsArr.size();
    }
    
    public void addClassifiedImage(ClassifiedImage img){
        classifiedImgsArr.add(img);
    }
    
}
