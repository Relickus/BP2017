/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.Solvers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import utility.ClassifiedImage;
import utility.PayloadImage;

/**
 * A base class for online classifier services
 * @author Vojta
 */
public abstract class OnlineSolver extends Solver {

    /**
     * Instantiates a subclass of an online solver
     * @param name name of the solver
     * @param time computation time estimation in seconds
     */
    public OnlineSolver(String name, int time) {
        super(name, time);
    }

    /**
     * Classifies an image
     * @param img A challenge image to be classified
     * @throws java.io.IOException
     */
    @Override
    protected void classifyImage(PayloadImage img) throws IOException {

        ProcessBuilder processbuilder = new ProcessBuilder();
        processbuilder.command("python", scriptPath, img.getAbsolutePath());

        System.err.println("\tSOLVING: " + processbuilder.command().toString());
        Process process = processbuilder.start();

        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String ret;
        ClassifiedImage classifiedImage = new ClassifiedImage(img);

        while (true) {
            ret = in.readLine();
            if (ret == null) {
                break;
            }
            if (ret.isEmpty()) {
                continue;
            }
            System.out.println("value is : " + ret);

            String label = ret.substring(0, ret.indexOf(':'));
            double score = Double.parseDouble(ret.substring(ret.indexOf(':') + 1));
            classifiedImage.addClass(label, score);

        }

        classifiedImage.setPredictedClass();
        result.addClassifiedImage(classifiedImage);
    }
    
    /**
     * Online solver classes do not support additional paramters
     * @return false value
     */
    @Override
    public boolean hasParams() {
        return false;
    }

}
