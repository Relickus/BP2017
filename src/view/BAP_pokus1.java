/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.File;
import javafx.application.Application;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import resources.Constants;
import resources.ImageClassContainer;
import view.Controllers.StageController;

/**
 * This is an initiation class of the application
 * @author Vojta
 */
public class BAP_pokus1 extends Application {
        
    @Override
    public void start(Stage stage) throws Exception {
        
        showDatasetChooser(stage); 
        showCofigFileChooser(stage);
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
    
    /**
     * This method displays a window for dataset directory selection. Selection of empty folder is not allowed.
     * 
     * @param stage Stage to show the file selector in
     */
    private void showDatasetChooser(Stage stage){
        stage.setTitle("Please select directory of a dataset.");
        DirectoryChooser dc = new DirectoryChooser();
        File datasetFolder = dc.showDialog(stage);
        
        if(datasetFolder == null || datasetFolder.getAbsoluteFile().toString().isEmpty() || datasetFolder.listFiles().length == 0){
            System.exit(1);
        }
            
        Constants.DATASET_PATH = datasetFolder.getAbsolutePath() + "\\";
    }
    
     /**
     * This method displays a window for image class config file selection. Ensuring validity of the selected file is user's responsibility and is not checked.
     * 
     * @param stage Stage to show the file selector in
     */
    private void showCofigFileChooser(Stage stage){
        
        stage.setTitle("Please select the image classes configuration file.");
        FileChooser fc = new FileChooser();
        File configFile = fc.showOpenDialog(stage);
        
        if(configFile == null || configFile.length() == 0){
            System.exit(1);
        }
            
        Constants.CONFIG_IMAGE_CLASSES = configFile.getAbsolutePath();
    }
    
}
