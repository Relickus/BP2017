/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import utility.Captchas.CAPTCHA;
import utility.Solvers.Solver;

/**
 *
 * @author Vojta
 */
public class ScriptExecutor {

    private ArrayList<Solver> solvers;
    private CAPTCHA captcha;
    private Button finishButton;

    public void launchScripts(ArrayList<Solver> slvrs, CAPTCHA c) {
        this.solvers = slvrs;
        this.captcha = c;
                
        for (Solver s : solvers) {
            s.solve(captcha);
        }
    }
    
    public void setOnFinishedButton(Button btn){
        finishButton = btn;
    }
    
    public int getEstimatedTime(ArrayList<Solver> solvers){        
        int res=0;
        
        for(Solver s : solvers){
            res += s.getEstimatedTime();
        }
        
        return res;
    }
    
}
