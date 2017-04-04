/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.Captchas;

import java.util.Random;
import javafx.scene.Node;
import javafx.scene.control.Label;
import resources.Constants;
import resources.ImageClass;
import resources.ImageClassEnum;

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
        challengeClass = ImageClassEnum.getEnum(questionClassIdx);
        keywordStr = challengeClass.printableName();
    }

    
    // zmenit v hierarchii tyhle dve metody na createRandomChallenge a createSpecifiedChallenge:
    
    @Override
    protected void randomClass() {
        generateKeyword();
    }

    @Override
    protected void specifyClass(ImageClassEnum e) {
               
        challengeClass = e;
        questionClassIdx = challengeClass.getValue();
        keywordStr = challengeClass.printableName();
    }
    
     

    @Override
    public void createChallenge() {
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

}
