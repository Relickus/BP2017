/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import resources.Constants;

/**
 *
 * @author Vojta
 */
class AbstractController {
    
    //protected Stage currentStage;   // VYRESIT REDUNDANCI TENHLE STAGE A TEN UVNITR STAGECONTROLERU
    
    protected StageController stageController = StageController.getInstance();
    protected String NEXT_SCENE;
    protected String PREVIOUS_SCENE;

        
//    public void setPrevStage(Stage stage){
//        currentStage = stage;
//    }
    
    protected void goToNextStage(){
        
        stageController.closeStage(stageController.getCurrentStage());
        
        stageController.loadNextStage(NEXT_SCENE);
        
        stageController.showStage();
    }
    
    @FXML    
    private void onBackClicked(ActionEvent event){        
        
        stageController.loadNextStage(PREVIOUS_SCENE);         
        stageController.showStage();
    }
    
    @FXML    
    private void onProceedClicked(ActionEvent event){        
        
        stageController.loadNextStage(NEXT_SCENE);         
        stageController.showStage();
    }
}
