/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.Solvers;

import utility.Captchas.AbstractChallenge;
import utility.Captchas.CAPTCHA;
import utility.Captchas.ChallengeKeyword;

/**
 *
 * @author Vojta
 */
public class CNNSolver extends Solver {

    public CNNSolver(String name) {
        super(name);
    }


    @Override
    public void predict(CAPTCHA captcha) {
    
        //set Result object
    }

    @Override
    public void loadScript(AbstractChallenge challenge) {

            // dont allow passing in ChallengeImage object! this method is not suitable for it

    }
    
}
