/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.util.ArrayList;
import utility.Captchas.CAPTCHA;
import utility.Solvers.Solver;

/**
 * This class executes external scripts
 * @author Vojta
 */
public class ScriptExecutor {

    private ArrayList<Solver> solvers;
    private CAPTCHA captcha;

    /**
     * Lanches selected solver scripts from array
     * @param arr Array of scripts
     * @param c captcha to be solved
     * @param pleaseWaitDialog loading dialog
     * @return
     */
    public boolean launchScripts(ArrayList<Solver> arr, CAPTCHA c, PleaseWaitDialog pleaseWaitDialog) {
        this.solvers = arr;
        this.captcha = c;
        int ctr = 0;         
        
        for (Solver s : solvers) {
            pleaseWaitDialog.updateProgress((double)ctr/solvers.size());
            try{
                s.solve(captcha,pleaseWaitDialog);
            }catch( SolutionInterruptedException e){
                return false;
            }
            ++ctr;
        }
        return true;
    }
    
    /**
     * returns a time estimation of computation
     * @param solvers array of solvers
     * @return
     */
    public int getEstimatedTime(ArrayList<Solver> solvers){        
        int res=0;
        
        for(Solver s : solvers){
            res += s.getEstimatedTime();
        }
        
        return res;
    }
    
}
