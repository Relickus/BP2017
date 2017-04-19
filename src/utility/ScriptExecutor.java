/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.util.ArrayList;
import javafx.concurrent.Task;
import utility.Captchas.CAPTCHA;
import utility.Solvers.Solver;

/**
 *
 * @author Vojta
 */
public class ScriptExecutor {

    private ArrayList<Solver> solvers;
    private CAPTCHA captcha;

    public void launchScripts(ArrayList<Solver> arr, CAPTCHA c, Task<Boolean> task, PleaseWaitDialog pleaseWaitDialog) {
        this.solvers = arr;
        this.captcha = c;
        int ctr = 0;         
        
        for (Solver s : solvers) {
            pleaseWaitDialog.updateProgress((double)ctr/solvers.size());
            s.solve(captcha,task,pleaseWaitDialog);
            ++ctr;
        }
    }
    
    
    public int getEstimatedTime(ArrayList<Solver> solvers){        
        int res=0;
        
        for(Solver s : solvers){
            res += s.getEstimatedTime();
        }
        
        return res;
    }
    
}
