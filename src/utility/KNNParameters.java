/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Vojta
 */
public class KNNParameters extends SolverParameters{
    
    
    protected int K;
    protected boolean weightedVotes;
    protected static final Integer [] availableKs = new Integer[]{1, 3, 5, 7, 9, 11, 15, 21, 33};
    protected static final AbstractDistance [] availableDistances = new AbstractDistance[]{
        new EucleidianDistance(),
        new ManhattanDistance()
    };
    
    protected AbstractDistance measureDistance;

    public KNNParameters(Object k, AbstractDistance dist, boolean weights) {

        this.K = (Integer)k;
        this.measureDistance = dist;
        this.weightedVotes = weights;         
    }

    public KNNParameters() {   // default parameters
            
        this.K = 5;
        this.measureDistance = new EucleidianDistance();
        this.weightedVotes = false;  
    }
    
    public void setK(int K) {
        
        // even K's don't make sense for unweighted voting
        if( K%2 == 0 )
            ++K;
        
        this.K = K;
    }

    public void setWeightedVotes(boolean weightedVotes) {
        this.weightedVotes = weightedVotes;
    }
    
    
//    public void setCrossFolding(boolean crossFolding) {
//        this.crossFolding = crossFolding;
//    }

    public void setMeasureDistance(AbstractDistance measureDistance) {
        this.measureDistance = measureDistance;
    }

    public int getK() {
        return K;
    }

    public boolean isWeightedVotes() {
        return weightedVotes;
    }

    public AbstractDistance getMeasureDistance() {
        return measureDistance;
    }

    @Override
    public String toString() {
        return " K: " + K + ", distance: " + measureDistance.getName() 
             + ", weighting: " + weightedVotes/* + ", crossfolding: " + crossFolding*/;
    }
    
    public static ArrayList<Integer> getAvailableKs(){
        return new ArrayList<>(Arrays.asList(availableKs));
    }
    
    public static ArrayList<AbstractDistance>getAvailableDistances(){        
        return new ArrayList<>(Arrays.asList(availableDistances));
    }
    
}
