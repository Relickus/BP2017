/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.Solvers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javafx.scene.image.Image;
import utility.Captchas.CAPTCHA;
import utility.PayloadImage;
import utility.Result;

/**
 *
 * @author Vojta
 */
public class OnlineSolver extends Solver {

    public OnlineSolver(String name) {
        super(name);
    }

    @Override
    public void solve(CAPTCHA captcha) {
        //set Result object

        ArrayList<PayloadImage> payloadArr = captcha.getChallenge().getPayload();
        for (PayloadImage pi : payloadArr) {
            try {
                classifyImage(pi);
            } catch (IOException e) {
                System.err.println("\t !!!Chyba v processbuileru: ");
                System.err.println(e.getMessage());
            }
        }
    }

    @Override
    protected Result classifyImage(PayloadImage img) throws IOException {

        ProcessBuilder processbuilder = new ProcessBuilder();
        processbuilder.command("python", scriptPath, img.getPath());
        Process process = processbuilder.start();

        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
        
        String ret = in.readLine();
        System.out.println("value is : " + ret);
        
        Result result = new Result();
        //result.parseOutputString(ret);
        
        return result;
    }

}
