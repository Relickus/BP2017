/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.Solvers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import resources.Constants;
import utility.Captchas.CAPTCHA;
import utility.Captchas.ChallengeImage;
import utility.PayloadImage;
import utility.PleaseWaitDialog;
import utility.Result;
import utility.SolutionInterruptedException;
import utility.SolverParameters;

/**
 *
 * Abstract base class for concrete classifier implementations
 */
public abstract class Solver {

    /**
     * name of the solver
     */
    protected final String name;

    /**
     * parameters of the solver
     */
    protected SolverParameters parameters;

    /**
     * path to the file containing script of the solver
     */
    protected String scriptPath;

    /**
     * result obtained from solving CAPTCHA
     */
    protected Result result;

    /**
     * estimated time of compution in seconds
     */
    protected int estimatedTime;

    /**
     * Base solver constructor
     *
     * @param name name of the solver
     * @param time computation time estimation in seconds
     */
    public Solver(String name, int time) {
        this.name = name;
        this.estimatedTime = time;
        this.result = new Result();
        setScriptPath();
    }

    /**
     * Name getter
     *
     * @return name of the solver
     */
    public String getName() {
        return name;
    }

    /**
     * Parameters getter
     *
     * @return parameters of the solver
     */
    public SolverParameters getParameters() {
        return parameters;
    }

    /**
     * Solves a given challenge in a form of CAPTCHA object
     *
     * @param captcha CAPTCHA containing a challenge to be solved
     * @param pleaseWaitDialog reference to dialog containing progressbar
     * showing computation progress
     * @throws SolutionInterruptedException exception thrown if computation is
     * canceled (user clicked on Stop button)
     */
    public void solve(CAPTCHA captcha, PleaseWaitDialog pleaseWaitDialog) throws SolutionInterruptedException {

        if (captcha.getChallenge() instanceof ChallengeImage) {

            ChallengeImage challenge = (ChallengeImage) captcha.getChallenge();
            PayloadImage refimg = challenge.getReferenceImage();

            try {

                classifyImage(refimg);

                // reference image was not classified correctly -> 0% accuracy and skip classification of the payload 
                if (!result.getClassifiedRefImg().guessedRight()) {
                    try {
                        pleaseWaitDialog.updateProgress(pleaseWaitDialog.getProgress() + (challenge.getNumberOfChallengeImgs() * 1.0) / pleaseWaitDialog.getGranularity());   // adds more progress to progressbar 
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    result.setAccuracy(0);
                    return;
                }

            } catch (IOException e) {
                System.err.println("\t !!!Chyba v processbuileru: ");
                System.err.println(e.getMessage());
                return;
            }

        }

        ArrayList<PayloadImage> payloadArr = captcha.getChallenge().getPayload();
        for (PayloadImage pi : payloadArr) {
            try {
                classifyImage(pi);

                //user clicked Stop on Dialog panel
                if (!pleaseWaitDialog.isShowing()) {
                    throw new SolutionInterruptedException();
                }
                pleaseWaitDialog.updateProgress(pleaseWaitDialog.getProgress() + 1.0 / pleaseWaitDialog.getGranularity());

            } catch (IOException e) {
                System.err.println("\t !!!Chyba v processbuileru: ");
                System.err.println(e.getMessage());
                return;
            }
        }

        result.countAccuracy(captcha.getChallenge().getChallengeClass(), captcha.getChallenge().getNumberOfCorrectImgs());
    }

    private void setScriptPath() {
        scriptPath = Constants.SCRIPTS_FOLDER_PATH + name.replace(" ", "") + ".py";
        String tmp = new File(scriptPath).getAbsolutePath();
        scriptPath = tmp;
    }

    /**
     * Classifies an individual image
     * @param img An image to be classified
     * @throws java.io.IOException
     */
    protected abstract void classifyImage(PayloadImage img) throws IOException;

    /**
     * Detects if current instance of a solver has parameters set
     *
     * @return boolean value
     */
    public abstract boolean hasParams();

    /**
     * Result getter
     *
     * @return the result of solved challenge
     */
    public Result getResult() {
        return result;
    }

    /**
     * Result setter
     *
     * @param result Result to be set
     */
    public void setResult(Result result) {
        this.result = result;
    }

    /**
     * Estimated time getter
     *
     * @return Estimated time of solution in seconds
     */
    public int getEstimatedTime() {
        return estimatedTime;
    }

    /**
     * Parameters setter
     *
     * @param parameters parameters to be set
     */
    public void setParameters(SolverParameters parameters) {
        this.parameters = parameters;
    }

}
