/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.Solvers;

import java.io.IOException;
import javafx.scene.image.Image;
import utility.Captchas.AbstractChallenge;
import utility.Captchas.CAPTCHA;
import utility.KNNParameters;
import utility.PayloadImage;
import utility.Result;

/**
 *
 * @author Vojta
 */
public class KNNSolver extends Solver {

    public KNNSolver() {
        super("knn");
        this.parameters = new KNNParameters();
    }
        
    public KNNSolver(KNNParameters par) {
        super("knn");
        this.parameters = par;
    }
   
    @Override
    public void solve(CAPTCHA captcha) {

       // loadScript(captcha.getChallenge());
        
        //set Result object
    }

//    @Override
//    public void loadScript(AbstractChallenge challenge) {
//
//        // tohle vyndat ze Solver tridy? nebo do ni naopak dat pretizeny loadScript pro vsechny Challenges?
//    }

    @Override
    protected Result classifyImage(PayloadImage img) throws IOException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
