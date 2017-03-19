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
    
    
    protected int K = 5;
    protected boolean weightedVotes = false;
    protected boolean crossFolding = false;
    
    protected AbstractDistance measureDistance = new EucleidianDistance();  // default distance

    
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
    
    

    
    
    
    
    
}
