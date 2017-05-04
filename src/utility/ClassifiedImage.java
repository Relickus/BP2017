/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.util.ArrayList;
import java.util.Collections;
import resources.ImageClass;
import resources.ImageClassContainer;

/**
 *
 * @author Vojta
 */
public class ClassifiedImage extends PayloadImage {

    private ArrayList<ImageResult> resultsArr;
    private ImageClass predictedClass;

    public ClassifiedImage(PayloadImage payloadimage) {
        super(payloadimage.path);
        correctClass = payloadimage.correctClass;
        this.coordinates = payloadimage.coordinates;

        resultsArr = new ArrayList<>();
    }

    public void setPredictedClass() {
        // ?????????? HOW
        Collections.sort(resultsArr);

        for (ImageResult r : resultsArr) {
            for (ImageClass e : ImageClassContainer.getClassesArr()) {
                if (r.matchesImageClass(e)) {    
                    predictedClass = e;
                    return;
                }
                //search in synonyms of currently checked class
                if(ImageClassContainer.getSynonyms(e) != null)
                    for(ImageClass syn : ImageClassContainer.getSynonyms(e)){
                        if(r.matchesImageClass(syn)){
                            predictedClass = syn;       // !!!!!!!!!!! NOW predictedclass != correctclass
                            return;
                        }
                    }    
            }
        }

        // none of my imageclasses were in prediction 
    }

    public ImageClass getPredictedClass() {
        return predictedClass;
    }

    public ArrayList<ImageResult> getResultsArr() {
        return resultsArr;
    }

    public void addClass(String label, double score) {
        resultsArr.add(new ImageResult(label, score));
    }

    public boolean guessedRight() {
        
        if(predictedClass == null)
            return false;
        
        if (correctClass.equals(predictedClass))
            return true;
        
        if(ImageClassContainer.getSynonyms(correctClass).contains(predictedClass))
            return true;
        
        return false;
    }

    public boolean isEquivalentClass(ImageClass oth) {

        if(predictedClass == null)
            return false;
        
        // comparison of values instead of names because synonyms have same values
        return oth.getValue() == predictedClass.getValue();
    }

}
