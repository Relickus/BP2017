/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.util.ArrayList;
import resources.ImageClass;

/**
 * a result of classification of a challenge containing all labels and scores 
 * @author Vojta
 */
public class Result {
    
    private ArrayList<ClassifiedImage> classifiedImgsArr;
    private ClassifiedImage referenceImage;
    private double accuracy;

    /**
     *
     */
    public Result() {
        this.classifiedImgsArr = new ArrayList<>();
    }
    
    /**
     * Accuracy getter
     * @return double value denoting accuracy of the result
     */
    public double getAccuracy() {
        return accuracy;
    }

    /**
     * Getter for array images classified by a solver
     * @return array of classified images
     */
    public ArrayList<ClassifiedImage> getResultArr() {
        return classifiedImgsArr;
    }
    
    /**
     * If the result solved a challenge specified by a reference image, this will return the image
     * @return a reference image
     */
    public ClassifiedImage getClassifiedRefImg(){
        return referenceImage;
    }
    
    /**
     * checks whether the result is empty
     * @return
     */
    public boolean isEmpty(){
        
        return classifiedImgsArr == null || classifiedImgsArr.isEmpty();
    }
    
    /**
     * Accuracy setter
     * @param acc double value to be set as accuracy
     */
    public void setAccuracy(double acc){
        accuracy = acc;
    }
    
    /**
     * Calculates an accuracy of the result. The calculation is described in the thesis.
     * @param challengeClass image class of task of challenge which produced this result after solving 
     * @param numberOfCorrectImgs number of correct images in the payload of the challenge
     */
    public void countAccuracy(ImageClass challengeClass,int numberOfCorrectImgs){
        
        int counter=0;
        for(ClassifiedImage img : classifiedImgsArr){  
            if(challengeClass.equivalentTo(img.getPredictedClass()) && img.getCorrectClass().equivalentTo(img.getPredictedClass()))
                ++counter;
        }
        
        accuracy = (double)counter/numberOfCorrectImgs;

    }
    
    /**
     * Adds image classified by a solver to the array of classified images 
     * @param img a classified image
     */
    public void addClassifiedImage(ClassifiedImage img){
        
        // coordinates -1,-1 indicate reference image, other coordinates indicate payload grid image
        if(img.getCoordinates().getRow() == -1 && img.getCoordinates().getCol() == -1 )
            referenceImage = img;
        else
            classifiedImgsArr.add(img);
    }
    
    /**
     * checks whether this result has a reference image in it
     */
    public boolean hasReferenceImage(){
        return referenceImage != null;
    }
    
    
}
