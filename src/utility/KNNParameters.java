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
public class KNNParameters extends SolverParameters{
    
    
    protected int K;
    protected boolean weightedVotes;
    protected boolean crossFolding;
    
    protected AbstractDistance measureDistance;

    public KNNParameters(Object k, AbstractDistance dist, boolean weights, boolean cross) {

        this.K = (Integer)k;
        this.measureDistance = dist;
        this.weightedVotes = weights;
        this.crossFolding = cross;            
    }

    public KNNParameters() {   // default parameters
            
        this.K = 5;
        this.measureDistance = new EucleidianDistance();
        this.weightedVotes = false;
        this.crossFolding = false;        
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
    
    
    public void setCrossFolding(boolean crossFolding) {
        this.crossFolding = crossFolding;
    }

    public void setMeasureDistance(AbstractDistance measureDistance) {
        this.measureDistance = measureDistance;
    }

    public int getK() {
        return K;
    }

    public boolean isWeightedVotes() {
        return weightedVotes;
    }

    public boolean isCrossFolding() {
        return crossFolding;
    }

    public AbstractDistance getMeasureDistance() {
        return measureDistance;
    }

    @Override
    public String toString() {
        return " K: " + K + ", distance: " + measureDistance.getName() + ", weighting: " + weightedVotes + ", crossfolding: " + crossFolding;
    }
    
}
