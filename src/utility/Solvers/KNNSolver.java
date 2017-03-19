/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.Solvers;

import utility.Captchas.AbstractChallenge;
import utility.Captchas.CAPTCHA;
import utility.KNNParameters;

/**
 *
 * @author Vojta
 */
public class KNNSolver extends Solver {

    public KNNSolver(String name) {
        super(name);
        this.parameters = new KNNParameters();
    }

   
    @Override
    public void predict(CAPTCHA captcha) {

        loadScript(captcha.getChallenge());
        
        //set Result object
    }

    @Override
    public void loadScript(AbstractChallenge challenge) {

        // tohle vyndat ze Solver tridy? nebo do ni naopak dat pretizeny loadScript pro vsechny Challenges?
    }
    
}
