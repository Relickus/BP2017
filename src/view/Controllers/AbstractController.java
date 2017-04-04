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
    protected void goToNextStage() {

        stageController.closeStage(stageController.getCurrentStage());

        stageController.loadNextStage(NEXT_SCENE);

        stageController.showStage();
    }

    @FXML
    private void onBackClicked(ActionEvent event) {

        stageController.loadNextStage(PREVIOUS_SCENE);
        stageController.showStage();
    }

    @FXML
    private void onProceedClicked(ActionEvent event) {

        stageController.loadNextStage(NEXT_SCENE);
        stageController.showStage();
    }
}
