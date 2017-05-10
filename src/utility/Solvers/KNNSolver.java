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
import resources.Constants;
import utility.ClassifiedImage;
import utility.KNNParameters;
import utility.PayloadImage;

/**
 *
 * @author Vojta
 */
public class KNNSolver extends Solver {

    private final String datasetPath = Constants.DATASET_PATH;
    
    public KNNSolver() {
        super("KNN",40);
        this.parameters = new KNNParameters();
    }
        
    public KNNSolver(KNNParameters par) {
        super("KNN",60);
        this.parameters = par;
    }
   
    private ArrayList<String> prepareParametersArr(){
        
        ArrayList<String> res = new ArrayList<>();
        
        String kStr = String.valueOf(((KNNParameters)parameters).getK());
        String distStr = ((KNNParameters)parameters).getMeasureDistance().getParameterName();
        String weightStr = String.valueOf(((KNNParameters)parameters).isWeightedVotes());
        
        res.add( kStr);
        res.add( distStr);
        res.add( weightStr);
        
        return res;        
    }

    @Override
    protected void classifyImage(PayloadImage img) throws IOException{
        
        ProcessBuilder processbuilder = new ProcessBuilder();
        
        ArrayList<String> paramsArr = prepareParametersArr();
        
        processbuilder.command("python", scriptPath, img.getAbsolutePath(), paramsArr.get(0), paramsArr.get(1), paramsArr.get(2) );
        
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

    @Override
    public boolean hasParams() {
        return true;
    }
        
}
