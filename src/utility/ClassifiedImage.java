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
 * Represents a payload image classified by a solver
 * @author Vojta
 */
public class ClassifiedImage extends PayloadImage {

    private ArrayList<ImageResult> resultsArr;
    private ImageClass predictedClass;

    /**
     * Instantiates the class
     * @param payloadimage Payload image classified by a solver
     */
    public ClassifiedImage(PayloadImage payloadimage) {
        super(payloadimage.path);
        correctClass = payloadimage.correctClass;
        this.coordinates = payloadimage.coordinates;

        resultsArr = new ArrayList<>();
    }

    /**
     *  Sets a label with the best score returned by a solver as a class of this image predicted by the solver
     */
    public void setPredictedClass() {
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

    /**
     * Getter for image class of this image predicted by a solver
     * @return predicted class member variable
     */
    public ImageClass getPredictedClass() {
        return predictedClass;
    }

    /**
     * Getter for array of all results from classification by a solver
     * @return Array of results from classification
     */
    public ArrayList<ImageResult> getResultsArr() {
        return resultsArr;
    }

    /**
     * Adds a new label of the image to results array 
     * @param label Label of the image returned by a solver
     * @param score Score of the label
     */
    public void addClass(String label, double score) {
        resultsArr.add(new ImageResult(label, score));
    }

    /**
     * Checks whether an image was classified correctly by a solver
     * @return boolean value denoting if image was classified correctly
     */
    public boolean guessedRight() {
        
        if(predictedClass == null)
            return false;
        
        if (correctClass.equals(predictedClass))
            return true;
        
        if(ImageClassContainer.getSynonyms(correctClass) == null)
            return false;
        else if(ImageClassContainer.getSynonyms(correctClass).contains(predictedClass))
            return true;
        
        return false;
    }

}
