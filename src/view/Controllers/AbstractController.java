/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Stage;
import resources.Constants;

/**
 *
 * @author Vojta
 */
class AbstractController {
    
    protected Stage prevStage;
    protected StageController stageController = StageController.getInstance();
    protected String NEXT_SCENE;
    
    public void setPrevStage(Stage stage){
        prevStage = stage;
    }
    
    protected void goToNextStage(){
        
        stageController.closeStage(prevStage);
        
        try {
            stageController.setNextStage(NEXT_SCENE);
        } catch (IOException ex) {
            Logger.getLogger(AbstractController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        stageController.showStage();
    }
    
}
