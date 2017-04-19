/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.Solvers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.concurrent.Task;
import resources.Constants;
import utility.Captchas.CAPTCHA;
import utility.PayloadImage;
import utility.PleaseWaitDialog;
import utility.Result;
import utility.SolutionInterruptedException;
import utility.SolverParameters;

/**
 *
 * @author Vojta
 */
public abstract class Solver {

    protected final String name;
    protected SolverParameters parameters;
    protected String scriptPath;
    protected Result result;
    protected int estimatedTime;

    public Solver(String name, int time) {
        this.name = name;
        this.estimatedTime = time;
        this.result = new Result();
        setScriptPath();
    }

    public String getName() {
        return name;
    }

    public SolverParameters getParameters() {
        return parameters;
    }

    public void solve(CAPTCHA captcha, Task<Boolean> task, PleaseWaitDialog pleaseWaitDialog) throws SolutionInterruptedException {

        ArrayList<PayloadImage> payloadArr = captcha.getChallenge().getPayload();
        for (PayloadImage pi : payloadArr) {
            try {
                classifyImage(pi);

                //user clicked Stop on Dialog panel
                if (!pleaseWaitDialog.isShowing()) {
                    throw new SolutionInterruptedException();
                }
                pleaseWaitDialog.updateProgress(pleaseWaitDialog.getProgress() + 1.0/pleaseWaitDialog.getGranularity());

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

    protected abstract void classifyImage(PayloadImage img) throws IOException;

    public abstract boolean hasParams();

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    /**
     *
     * @return Estimated time of solution in SECONDS
     */
    public int getEstimatedTime() {
        return estimatedTime;
    }

}
