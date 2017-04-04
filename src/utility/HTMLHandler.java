/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import resources.Constants;
import utility.Captchas.AbstractChallenge;

/**
 *
 * @author Vojta
 */
public class HTMLHandler {

    
    public void prepareHTML(AbstractChallenge c){
                
        for( PayloadImage pi: c.getPayload() ){
           
            Path src = Paths.get(URI.create(pi.getPath()));
            Path dst = Paths.get(Constants.HTML_SOURCE + "r" + pi.getCoordinates().getRow() + "c" + pi.getCoordinates().getCol() + ".jpg");
                            
            try {
                Files.copy(src, dst, StandardCopyOption.REPLACE_EXISTING);
                
            } catch (IOException ex) {
                Logger.getLogger(HTMLHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

}
