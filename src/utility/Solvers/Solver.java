/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.Solvers;

import java.io.File;
import java.io.IOException;
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
    protected SolverParameters parameters;
    protected String scriptPath;
    protected Result result;

    public Solver(String name) {
        this.name = name;
        this.result = new Result();
        setScriptPath();
    }


    public String getName() {
        return name;
    }

    public SolverParameters getParameters() {
        return parameters;
    }

    public abstract void solve(CAPTCHA captcha);
    //public abstract void loadScript( AbstractChallenge challenge);  // WHAT??

    private void setScriptPath() {
        scriptPath = Constants.SCRIPTS_FOLDER_PATH + name.replace(" ", "") + ".py";
        String tmp = new File(scriptPath).getAbsolutePath();
        scriptPath = tmp;
    }

    protected abstract void classifyImage(PayloadImage img) throws IOException;

    public abstract boolean hasParams();

    
    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
    
    
}
