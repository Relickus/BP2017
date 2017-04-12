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
public class ClassifiedImage extends PayloadImage {

    private ArrayList<ImageResult> resultsArr;
    private ImageClassEnum predictedClass;

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
            for (ImageClassEnum e : ImageClassEnum.values()) {
                String lookforclass = e.name().toLowerCase();
                if (r.label.contains(lookforclass)) {
                    predictedClass = e;
                    return;
                }
            }
        }

        // none of my imageclasses were in prediction 
    }

    public ImageClassEnum getPredictedClass() {
        return predictedClass;
    }

    public ArrayList<ImageResult> getResultsArr() {
        return resultsArr;
    }

    public void addClass(String label, double score) {
        resultsArr.add(new ImageResult(label, score));
    }

    public boolean guessedRight() {
        return correctClass.equals(predictedClass);
    }

    public boolean matchesKeyword(String keyword) {

        if(predictedClass == null)
            return false;
        
        return keyword.equals(predictedClass.name().toLowerCase());
    }

}
