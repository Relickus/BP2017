/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.util.ArrayList;

/**
 *
 * @author Vojta
 */
public class Result {
    
    private double accuracy;
    private ArrayList<Coordinates> resultArr;

    public Result() {
    }
    
    
    public double getAccuracy() {
        return accuracy;
    }

    public ArrayList<Coordinates> getResultArr() {
        return resultArr;
    }
    
    public boolean isEmpty(){
        
        return resultArr == null || resultArr.isEmpty();
    }
    
}
