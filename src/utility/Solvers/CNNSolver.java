/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.Solvers;

import java.io.IOException;
import utility.Captchas.CAPTCHA;
import utility.PayloadImage;

/**
 *
 * @author Vojta
 */
public class CNNSolver extends Solver {

    public CNNSolver() {
        super("CNN",50);
    }


   
//    @Override
//    public void loadScript(AbstractChallenge challenge) {
//
//            // dont allow passing in ChallengeImage object! this method is not suitable for it
//
//    }

    @Override
    protected void classifyImage(PayloadImage img) throws IOException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasParams() {
        return false;
    }

    
    
}
