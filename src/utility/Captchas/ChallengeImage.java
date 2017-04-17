/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.Captchas;

;
import java.util.ArrayList;
import utility.Solvers.Solver;import javafx.scene.Node;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import resources.Constants;
import resources.ImageClassEnum;
import utility.Loader;
import utility.Solvers.CNNSolver;
import utility.Solvers.ClarifaiSolver;
import utility.Solvers.GoogleSolver;
import utility.Solvers.ImaggaSolver;
import utility.Solvers.KNNSolver;
import utility.Solvers.WatsonSolver;

/**
 *
 * @author Vojta
 */


public class ChallengeImage extends AbstractChallenge {

    private Image refImg;
    private boolean fixedClass;

    public ChallengeImage() {
        refImg = null;
    }

    public ChallengeImage(String key) {
        super(key);

    }

    @Override
    protected void randomClass() {
        fixedClass = false;
        questionClassIdx = new Random().nextInt(Constants.NUMBER_OF_CLASSES);
        challengeClass = ImageClassEnum.getEnum(questionClassIdx);
        keywordStr = challengeClass.printableName();
        loadRefImage();
    }

    @Override
    protected void specifyClass(ImageClassEnum e) {                    
        fixedClass = true;
        challengeClass = e;
        questionClassIdx = challengeClass.getValue();
        keywordStr = challengeClass.printableName();  
        loadRefImage();
    }

    
    
    @Override
    public void createChallenge() {
        if(fixedClass)
            loadRefImage();
        else{
            randomClass();
        }
    }

    public boolean isFixedClass() {
        return fixedClass;
    }

    public void setRefImgIndex(int idx) {
        questionClassIdx = idx;
    }

    private void loadRefImage() {
        Loader loader = Loader.getInstance();
        refImg = loader.loadImageFile(challengeClass);

    }

    @Override
    public Node getNode() {
        return new ImageView(refImg);
    }

    @Override
    public String getChallengeName() {
        return "Reference image";
    }

    @Override
    public String getKeyword() {
        if (refImg == null) {
            return "null refimg";
        }

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
        arr.add(new KNNSolver());
        arr.add(new CNNSolver());
        arr.add(new GoogleSolver());        
        arr.add(new WatsonSolver());
        arr.add(new ClarifaiSolver());
        arr.add(new ImaggaSolver());
        // add more online solvers
        
        return arr;
    }

}
