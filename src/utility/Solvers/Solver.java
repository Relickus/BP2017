/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.Solvers;

import utility.Captchas.AbstractChallenge;
import utility.Captchas.CAPTCHA;
import utility.Result;
import utility.SolverParameters;

/**
 *
 * @author Vojta
 */
public abstract class Solver {
    
    protected final String name;
    protected Result result;
    protected SolverParameters parameters;
    
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
    
    
    public SolverParameters getParameters(){        
        return parameters;
    }
    
    public abstract void predict(CAPTCHA captcha);
    public abstract void loadScript( AbstractChallenge challenge);

}
