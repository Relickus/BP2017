/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.application.Application;
import javafx.stage.Stage;
import resources.Constants;
import resources.ImageClassContainer;
import view.Controllers.StageController;

/**
 *
 * @author Vojta
 */
public class BAP_pokus1 extends Application {
        
    @Override
    public void start(Stage stage) throws Exception {
                
        // tady by asi mel probehnout nejakej kod kteerj zjisti kolik obrazku je 
        // v ktery kategorii ve slozce datasetu a naplnit prislusny konstanty
        
        ImageClassContainer.init();
        
        StageController stageController = StageController.getInstance();       
        stageController.loadNextStage(Constants.INIT_WINDOW);            
        stageController.showStage();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
