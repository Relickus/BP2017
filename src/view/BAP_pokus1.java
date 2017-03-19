/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.application.Application;
import javafx.stage.Stage;
import resources.Constants;
import view.Controllers.StageController;

/**
 *
 * @author Vojta
 */
public class BAP_pokus1 extends Application {
        
    @Override
    public void start(Stage stage) throws Exception {
        
        stage.setTitle("CAPTCHAsolver v1.0");
        
        // tady by asi mel probehnout nejakej kod kteerj zjisti kolik obrazku je 
        // v ktery kategorii ve slozce datasetu a naplnit prislusny konstanty
        
        
       
        //Parent root = FXMLLoader.load(getClass().getResource("/view/FXML/CAPTCHAWindowFXML.fxml"));
        
        StageController stageController = StageController.getInstance();
       
        stageController.loadNextStage(Constants.CAPTCHA_SELECT_WINDOW);     

        //CAPTCHASelectController windowController = (CAPTCHASelectController)stageController.getWindowController();
        //windowController.setPrevStage(stage);
       
        stageController.showStage();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
