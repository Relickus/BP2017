/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.Captchas;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.Node;
import javafx.scene.control.Label;
import resources.Constants;
import resources.ImageClass;
import resources.ImageClassContainer;
import utility.Solvers.ClarifaiSolver;
import utility.Solvers.GoogleSolver;
import utility.Solvers.ImaggaSolver;
import utility.Solvers.MicrosoftSolver;
import utility.Solvers.Solver;
import utility.Solvers.WatsonSolver;

/**
 * class representing a captcha challenge specified by a keyword
 * @author Vojta
 */
public class ChallengeKeyword extends AbstractChallenge {

    /**
     *
     */
    public ChallengeKeyword() {
        super();
    }

    /**
     * constructor of challenge of a specific class in task
     * @param specificClass
     */
    public ChallengeKeyword(String specificClass) {
        super(specificClass);
    }

    private void generateKeyword() {
        questionClassIdx = new Random().nextInt(Constants.NUMBER_OF_CLASSES);
        challengeClass = ImageClassContainer.getClassByVal(questionClassIdx);
        keywordStr = challengeClass.printableName();
    }

    
    /**
     * creates a random class for the challenge task
     */
    @Override
    protected void randomClass() {
        fixedClass=false;
        generateKeyword();
    }

   /**
     * creates a specified class for the challenge with keyword in task
     * @param e class of the task
     */
    @Override
    protected void specifyClass(ImageClass e) {
               
        fixedClass = true;
        challengeClass = e;
        questionClassIdx = challengeClass.getValue();
        keywordStr = challengeClass.printableName();
    }
    
    /**
     * creates a challenge according to the fixation of the task class
     */
    @Override
    public void createChallenge() {
        if(! isFixedClass())
            generateKeyword();
    }

    /**
     * returns appropriate JavaFX node containing keyword of this challenge
     * @return Label node with reference image
     */
    @Override
    public Node getNode() {
        return new Label(keywordStr);
    }

    /**
     * keyword getter
     * @return
     */
    @Override
    public String getKeyword() {
        return keywordStr;
    }
    
    /**
     * returns a set of available solvers for this challenge
     * @return
     */
    @Override
    public ArrayList<Solver> getAvailableSolvers() {
        
        ArrayList<Solver> arr = new ArrayList<>();
        arr.add(new GoogleSolver());
        arr.add(new WatsonSolver());
        arr.add(new ClarifaiSolver());
        arr.add(new ImaggaSolver());
        arr.add(new MicrosoftSolver());
        // add more online solvers
        
        return arr;

    }

}
