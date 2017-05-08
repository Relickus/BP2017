/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.util.ArrayList;
import resources.ImageClass;

/**
 *
 * @author Vojta
 */
public class Result {
    
    private ArrayList<ClassifiedImage> classifiedImgsArr;
    private ClassifiedImage referenceImage;
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
    
    public ClassifiedImage getClassifiedRefImg(){
        return referenceImage;
    }
    
    public boolean isEmpty(){
        
        return classifiedImgsArr == null || classifiedImgsArr.isEmpty();
    }
    
    
    public void setAccuracy(double acc){
        accuracy = acc;
    }
    
    public void countAccuracy(ImageClass challengeClass,int numberOfCorrectImgs){
        
        int counter=0;
        for(ClassifiedImage img : classifiedImgsArr){  
            if(img.isEquivalentClass(challengeClass))
                ++counter;
        }
        
        accuracy = (double)counter/numberOfCorrectImgs;

    }
    
    public void addClassifiedImage(ClassifiedImage img){
        
        // coordinates -1,-1 indicate reference image, other coordinates indicate payload grid image
        if(img.getCoordinates().getRow() == -1 && img.getCoordinates().getCol() == -1 )
            referenceImage = img;
        else
            classifiedImgsArr.add(img);
    }
    
    public boolean hasReferenceImage(){
        return referenceImage != null;
    }
    
    
}
