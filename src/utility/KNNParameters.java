/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Wrapper class for paramters for custom KNN classifier
 * @author Vojta
 */
public class KNNParameters extends SolverParameters{
    
    /**
     * K parameter
     */
    protected int K;

    /**
     * scaling factor of images  (explained in the thesis)
     */
    protected Integer scalingFactor;

    /**
     * available K parameters
     */
    protected static final Integer [] availableKs = new Integer[]{1, 3, 5, 7, 9, 11, 15, 21, 33};
    /**
     * available scaling factors
     */
    protected static final Integer [] availableScales = new Integer[]{15, 25, 35};
    /**
     *available distance functions
     */
    protected static final AbstractDistance [] availableDistances = new AbstractDistance[]{
        new EucleidianDistance(),
        new ManhattanDistance()
    };
    
    /**
     * selected distance function
     */
    protected AbstractDistance measureDistance;

    /**
     * constructor
     * @param k parameter K selected by user
     * @param dist  parameter distance function selected by user
     * @param scaling  parameter scaling factor selected by user
     */
    public KNNParameters(Object k, AbstractDistance dist, Object scaling) {

        this.K = (Integer)k;
        this.measureDistance = dist;
        this.scalingFactor = (Integer)scaling;         
    }

    /**
     *
     */
    public KNNParameters() {   // default parameters
            
        this.K = 5;
        this.measureDistance = new EucleidianDistance();
        this.scalingFactor = 15;  
    }
    
    /**
     * Parameter K setter
     * @param K
     */
    public void setK(int K) {
        
        // even K's don't make sense for unweighted voting
        if( K%2 == 0 )
            ++K;
        
        this.K = K;
    }

    /**
     * Sets a distance function to the variable
     * @param measureDistance user selected measure distance
     */

    public void setMeasureDistance(AbstractDistance measureDistance) {
        this.measureDistance = measureDistance;
    }

    /**
     * Parameter K getter
     * @return parameter K
     */
    public int getK() {
        return K;
    }

    /**
     * Distance function getter
     * @return distance function object 
     */
    public AbstractDistance getMeasureDistance() {
        return measureDistance;
    }

    /**
     * scaling factor getter
     * @return value of scaling factor
     */
    public Integer getScalingFactor(){
        return scalingFactor;
    }
    
    @Override
    public String toString() {
        return " K: " + K + ", distance: " + measureDistance.getName() 
             + ", scaling: " + scalingFactor;
    }
    
    /**
     * returns an array of available K parameters
     * @return
     */
    public static ArrayList<Integer> getAvailableKs(){
        return new ArrayList<>(Arrays.asList(availableKs));
    }
    
    /**
     * returns an array of available distance functions
     * @return
     */
    public static ArrayList<AbstractDistance>getAvailableDistances(){        
        return new ArrayList<>(Arrays.asList(availableDistances));
    }
    
    /**
     * return array of available scaling factors
     * @return
     */
    public static ArrayList<Integer>getAvailableScales(){        
        return new ArrayList<>(Arrays.asList(availableScales));
    }
    
}
