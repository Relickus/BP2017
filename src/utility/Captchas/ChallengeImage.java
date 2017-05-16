/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.Captchas;

import java.util.ArrayList;
import utility.Solvers.Solver;
import javafx.scene.Node;
import java.util.Random;
import javafx.scene.image.ImageView;
import resources.Constants;
import resources.ImageClass;
import resources.ImageClassContainer;
import utility.Coordinates;
import utility.Loader;
import utility.PayloadImage;
import utility.Solvers.ClarifaiSolver;
import utility.Solvers.GoogleSolver;
import utility.Solvers.ImaggaSolver;
import utility.Solvers.KNNSolver;
import utility.Solvers.MicrosoftSolver;
import utility.Solvers.WatsonSolver;

/**
 * class representing captcha challenge specified by a reference image
 *
 * @author Vojta
 */


public class ChallengeImage extends AbstractChallenge {

    private PayloadImage refImg;

    /**
     *
     */
    public ChallengeImage() {
        refImg = null;
    }

    /**
     * constructor of a challenge with specific class of reference image
     * @param specificClass
     */
    public ChallengeImage(String specificClass) {
        super(specificClass);

    }

    /**
     * creates a random class for the challenge task
     */
    @Override
    protected void randomClass() {
        fixedClass = false;
        questionClassIdx = new Random().nextInt(Constants.NUMBER_OF_CLASSES);
        challengeClass = ImageClassContainer.getClassByVal(questionClassIdx);
        keywordStr = challengeClass.printableName();
        loadRefImage();
    }

    /**
     * loads a reference image of class specified by parameter
     *
     * @param e class of the task
     */
    @Override
    protected void specifyClass(ImageClass e) {
        fixedClass = true;
        challengeClass = e;
        questionClassIdx = challengeClass.getValue();
        keywordStr = challengeClass.printableName();
        loadRefImage();
    }

    /**
     * creates a challenge according to the fixation of the task class
     */
    @Override
    public void createChallenge() {
        if (fixedClass) {
            loadRefImage();
        } else {
            randomClass();
        }
    }

    public void setRefImgIndex(int idx) {
        questionClassIdx = idx;
    }

    private void loadRefImage() {
        Loader loader = Loader.getInstance();
        refImg = loader.loadImageFile(challengeClass);
        refImg.setCoordinates(new Coordinates(-1, -1));

    }

    /**
     * returns appropriate JavaFX node containing reference image of this
     * challenge
     *
     * @return ImageView node with reference image
     */
    @Override
    public Node getNode() {
        return new ImageView(refImg);
    }

    /**
     * reference image getter
     *
     * @return
     */
    public PayloadImage getReferenceImage() {
        return refImg;
    }

    /**
     * keyword getter
     *
     * @return
     */
    @Override
    public String getKeyword() {
        if (refImg == null) {
            return "null refimg";
        }

        return keywordStr;
    }

    /**
     * returns a set of available solvers for this challenge
     *
     * @return
     */
    @Override
    public ArrayList<Solver> getAvailableSolvers() {

        ArrayList<Solver> arr = new ArrayList<>();
        arr.add(new KNNSolver());
        arr.add(new GoogleSolver());
        arr.add(new WatsonSolver());
        arr.add(new ClarifaiSolver());
        arr.add(new ImaggaSolver());
        arr.add(new MicrosoftSolver());
        // add more online solvers

        return arr;
    }

}
