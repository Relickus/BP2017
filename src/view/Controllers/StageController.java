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
import resources.Constants;

/**
 * A controller class handling stage displaying and switching. Singleton design
 * pattern applied.
 *
 * @author Vojta
 */
public class StageController {

    private FXMLLoader fxmlLoader;
    private Pane pane;
    private final Stage currentStage;

    private static final StageController instance = new StageController();

    /**
     * returns an instance of this class
     *
     * @return StageController instance
     */
    public static StageController getInstance() {
        return instance;

    }

    /**
     * returns currently displaying stage
     *
     * @return currently shown stage
     */
    public Stage getCurrentStage() {
        return currentStage;
    }

    /**
     * a constructor of the class
     */
    public StageController() {
        this.currentStage = new Stage();
        currentStage.setWidth(500);
        currentStage.setHeight(720);

        currentStage.setTitle(Constants.APPLICATION_TITLE);
    }

    /**
     * loads next stage
     *
     * @param window A path to fxml definition of the next window
     */
    public void loadNextStage(String window) {
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

    /**
     * displays the loaded stage
     */
    public void showStage() {

        currentStage.show();

    }

    /**
     * Getter for controller of current class
     *
     * @return a controller of current class
     */
    public AbstractController getWindowController() {

        return fxmlLoader.getController();
    }

}
