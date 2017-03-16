/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.Solvers;

import utility.Captchas.AbstractChallenge;
import utility.Result;

/**
 *
 * @author Vojta
 */
public abstract class Solver {
    
    private final String name;
    private Result result;

    public Solver(String name) {
        this.name = name;
        this.result = new Result();
    }  
    
    //abstract void predict(AbstractChallenge challenge);  // fills Result object  

    public Result getResult() {
        return result;
    }

    public String getName() {
        return name;
    }
    
    
    
}
