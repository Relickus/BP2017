/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.Solvers;

import utility.Captchas.AbstractChallenge;
import utility.Captchas.CAPTCHA;

/**
 *
 * @author Vojta
 */
public class OnlineSolver extends Solver{

    public OnlineSolver(String name) {
        super(name);
    }

    public void predict(CAPTCHA captcha) {
        //set Result object
    }

    @Override
    public void loadScript(AbstractChallenge challenge) {

    }
    
}
