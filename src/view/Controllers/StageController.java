/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Controllers;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Vojta
 */
public class StageController {
    
    private AbstractController windowController;
    private FXMLLoader fxmlLoader;
    private Pane pane;
    private final Stage stage;
    
    private static final StageController instance = new StageController();
    
    public static StageController getInstance(){
        return instance;
        
    }

    public StageController() {
        this.stage = new Stage();
    }

    public void setNextStage(String window) throws IOException{
       fxmlLoader = new FXMLLoader(getClass().getResource(window));
       pane = fxmlLoader.load();
    }
    
    public void closeStage(Stage stage){
        
       stage.close();
    }
    
    public void showStage(){
    
       Scene scene = new Scene(pane);
       stage.setScene(scene);        
       stage.setResizable(false);
       
       stage.show();
      
    }
    
    public AbstractController getWindowController(){
        
       return windowController = fxmlLoader.getController();      
    }
    
    
}
