/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.util.ArrayList;
import java.util.Collections;
import resources.ImageClassEnum;

/**
 *
 * @author Vojta
 */
public class ClassifiedImage extends PayloadImage{
    
    private ArrayList<ImageResult> resultsArr;
    private ImageClassEnum predictedClass;
    
    public ClassifiedImage(PayloadImage payloadimage) {
        super(payloadimage.path);
        
        resultsArr = new ArrayList<>();
        
    }
    
    private void setPredictedClass(){
        // ?????????? HOW
        Collections.sort(resultsArr);
    }

    public ImageClassEnum getPredictedClass() {
        return predictedClass;
    }

    
    
    public ArrayList<ImageResult> getResultsArr() {
        return resultsArr;
    }
    
    public void addClass(String label, double score){
        resultsArr.add(new ImageResult(label,score));
    }
    
}
