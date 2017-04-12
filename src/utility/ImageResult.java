/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

/**
 *
 * @author Vojta
 */
public class ImageResult implements Comparable<ImageResult>{
    
    String label;
    double score;

    public ImageResult(String label, double score) {
        this.label = label;
        this.score = score;
    }

    public String getLabel() {
        return label;
    }

    public double getScore() {
        return score;
    }

    @Override
    public int compareTo(ImageResult o) {
         return (int)(o.score*1000 - this.score*1000);  // *1000 in order to preserve floating point precision
    }
    
        
    
}
