/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import resources.ImageClass;

/**
 * Class aggregating an individual result from set of results obtained from classification of an image by a solver
 * @author Vojta
 */
public class ImageResult implements Comparable<ImageResult>{
    
    String label;
    double score;

    /**
     * Instantiates the class
     * @param label Label of a classified image
     * @param score A solver's confidence with the label  
     */
    public ImageResult(String label, double score) {
        this.label = label;
        this.score = score;
    }

    /**
     * Getter for this result's label
     * @return The label string
     */
    public String getLabel() {
        return label;
    }

    /**
     * Getter for this result's confidence
     * @return The confidence of the bundled label
     */
    public double getScore() {
        return score;
    }

    @Override
    public int compareTo(ImageResult o) {
         return (int)(o.score*1000 - this.score*1000);  // *1000 in order to preserve floating point precision
    }
    
    /**
     * Checks whether the label in this result matches given image class.
     * Note that the label variable may not be any of the defined image classes 
     * as different services may return labels different from our defined image classes
     * @param imageClass Image class to be compared to this result's label
     * @return boolean value denoting if the described condition holds
     */
    public boolean matchesImageClass(ImageClass imageClass){        
        return label.equalsIgnoreCase(imageClass.getName());        
    }    
    
    
}
