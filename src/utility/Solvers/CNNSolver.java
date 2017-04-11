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
import utility.Captchas.ChallengeKeyword;
import utility.PayloadImage;
import utility.Result;

/**
 *
 * @author Vojta
 */
public class CNNSolver extends Solver {

    public CNNSolver() {
        super("CNN");
    }


    @Override
    public void solve(CAPTCHA captcha) {
    
        //set Result object
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
