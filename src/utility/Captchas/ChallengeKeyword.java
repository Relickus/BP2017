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
import utility.Solvers.CNNSolver;
import utility.Solvers.ClarifaiSolver;
import utility.Solvers.GoogleSolver;
import utility.Solvers.ImaggaSolver;
import utility.Solvers.KNNSolver;
import utility.Solvers.MicrosoftSolver;
import utility.Solvers.Solver;
import utility.Solvers.WatsonSolver;

/**
 *
 * @author Vojta
 */
public class ChallengeKeyword extends AbstractChallenge {

    public ChallengeKeyword() {
        super();
    }

    public ChallengeKeyword(String key) {
        super(key);
    }

    private void generateKeyword() {
        questionClassIdx = new Random().nextInt(Constants.NUMBER_OF_CLASSES);
        challengeClass = ImageClassContainer.getClassByVal(questionClassIdx);
        keywordStr = challengeClass.printableName();
    }

    
    // zmenit v hierarchii tyhle dve metody na createRandomChallenge a createSpecifiedChallenge:
    
    @Override
    protected void randomClass() {
        fixedClass=false;
        generateKeyword();
    }

    @Override
    protected void specifyClass(ImageClass e) {
               
        fixedClass = true;
        challengeClass = e;
        questionClassIdx = challengeClass.getValue();
        keywordStr = challengeClass.printableName();
    }
    
     

    @Override
    public void createChallenge() {
        if(! isFixedClass())
            generateKeyword();
    }

    @Override
    public Node getNode() {
        return new Label(keywordStr);
    }

    @Override
    public String getChallengeName() {
        return "Keyword";
    }

    @Override
    public String getKeyword() {
        return keywordStr;
    }

    @Override
    protected void generatePayloadWebView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void generateCaptchaWebView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Solver> getAvailableSolvers() {
        
        ArrayList<Solver> arr = new ArrayList<>();
        arr.add(new CNNSolver());
        arr.add(new GoogleSolver());
        arr.add(new WatsonSolver());
        arr.add(new ClarifaiSolver());
        arr.add(new ImaggaSolver());
        arr.add(new MicrosoftSolver());
        // add more online solvers
        
        return arr;

    }

}
