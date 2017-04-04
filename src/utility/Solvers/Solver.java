/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.Solvers;

import java.io.IOException;
import javafx.scene.image.Image;
import resources.Constants;
import utility.Captchas.CAPTCHA;
import utility.PayloadImage;
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
    protected String scriptPath;
    
    public Solver(String name) {
        this.name = name;
        this.result = new Result();        
        setScriptPath();        
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
    
    public abstract void solve(CAPTCHA captcha);
    //public abstract void loadScript( AbstractChallenge challenge);  // WHAT??

    private void setScriptPath(){        
        scriptPath = Constants.SCRIPTS_FOLDER_PATH + name.replace(" ", "") + ".py";        
    }
    
    protected abstract Result classifyImage(PayloadImage img) throws IOException;
}
