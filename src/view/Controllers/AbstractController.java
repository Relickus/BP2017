/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 *
 * Abstract base class for all FXML controllers
 */
class AbstractController {

    protected StageController stageController = StageController.getInstance();
    protected String NEXT_SCENE;
    protected String PREVIOUS_SCENE;

    @FXML
    /**
     * back button base functionality
     */
    private void onBackClicked(ActionEvent event) {

        stageController.loadNextStage(PREVIOUS_SCENE);
        stageController.showStage();
    }

    /**
     * proceed button base functionality
     */
    @FXML
    private void onProceedClicked(ActionEvent event) {

        stageController.loadNextStage(NEXT_SCENE);
        stageController.showStage();
    }
}
