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
import utility.Captchas.CAPTCHA;
import utility.ClassifiedImage;
import utility.PayloadImage;

/**
 *
 * @author Vojta
 */
public abstract class OnlineSolver extends Solver {

    public OnlineSolver(String name,int time) {
        super(name,time);
    }

   
    @Override
    protected void classifyImage(PayloadImage img) throws IOException {

        ProcessBuilder processbuilder = new ProcessBuilder();
        processbuilder.command("python", scriptPath, img.getAbsolutePath());
        
        System.err.println("\tSOLVING: " + processbuilder.command().toString());
        Process process = processbuilder.start();
        

        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
        
        String ret;
        ClassifiedImage i = new ClassifiedImage(img);
        
        while( true ){
            ret = in.readLine();
            if(ret == null)
                break;
            if(ret.isEmpty())
                continue;
            System.out.println("value is : " + ret);
            
            String label = ret.substring(0, ret.indexOf(':'));
            double score = Double.parseDouble(ret.substring(ret.indexOf(':')+1));
            i.addClass( label , score);
            
        }
        
        i.setPredictedClass();
        result.addClassifiedImage(i);
    }

    @Override
    public boolean hasParams() {
        return false;
    }
    
    

}
