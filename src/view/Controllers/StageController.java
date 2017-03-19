/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Vojta
 */
public class StageController {
    
    private FXMLLoader fxmlLoader;
    private Pane pane;
    private final Stage currentStage;
    
    private static final StageController instance = new StageController();
    
    public static StageController getInstance(){
        return instance;
        
    }
    
    public Stage getCurrentStage(){
        return currentStage;
    }

    public StageController() {
        this.currentStage = new Stage();
    }

    public void loadNextStage(String window){
       fxmlLoader = new FXMLLoader(getClass().getResource(window));
       
        try {
            pane = fxmlLoader.load();
            Scene scene = new Scene(pane);
            currentStage.setScene(scene);        
            currentStage.setResizable(false);
            
        } catch (IOException ex) {
            Logger.getLogger(StageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeStage(Stage stage){
        
       stage.close();
    }
    
    public void showStage(){
    
       
       
       currentStage.show();
      
    }
    
    public AbstractController getWindowController(){
        
       return fxmlLoader.getController();      
    }
    
    
}
