/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.Solvers;

import java.io.IOException;
import utility.Captchas.CAPTCHA;
import utility.KNNParameters;
import utility.PayloadImage;

/**
 *
 * @author Vojta
 */
public class KNNSolver extends Solver {

    public KNNSolver() {
        super("KNN",40);
        this.parameters = new KNNParameters();
    }
        
    public KNNSolver(KNNParameters par) {
        super("KNN",60);
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
    protected void classifyImage(PayloadImage img) throws IOException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasParams() {
        return true;
    }

}
