/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import resources.Constants;
import utility.Captchas.CAPTCHA;
import utility.Result;
import utility.Solvers.Solver;

/**
 *
 * @author Vojta
 */
public class ResultWindowController extends AbstractController implements Initializable {

    private @FXML
    VBox resultContainer;

    private ArrayList<Solver> solversAndResultsArr;
    private CAPTCHA captcha;

    public void setCaptcha(CAPTCHA captcha) {
        this.captcha = captcha;
    }
    
    public void setResults(ArrayList<Solver> arr){
        this.solversAndResultsArr = arr;
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        NEXT_SCENE = null;
        PREVIOUS_SCENE = Constants.SOLVER_SETTINGS_WINDOW;

    }

    @FXML
    private void onBackClicked(ActionEvent event) {

        stageController.loadNextStage(PREVIOUS_SCENE);
        SolverSettingsController windowController = (SolverSettingsController) stageController.getWindowController();
        windowController.setCaptcha(captcha);
        windowController.initView();
        stageController.showStage();
    }

    @FXML
    private void onMainMenuClicked(ActionEvent event) {

        stageController.loadNextStage(Constants.INIT_WINDOW);
        stageController.showStage();
    }

    public void initView() {

        for( Solver s : solversAndResultsArr){
            
            ResultItem r = new ResultItem(captcha); // v parametru captcha.applyResultFilter(Result,Filter.SHOW_CORRECT_IMGS=true/false)  --- metoda ktera je prekryje payload filtrem a vraci tu captchu samotnou
        
            // r.setFilterChosen(solversAndResultsArr);
            
            String paramStr = s.hasParams() ? s.getParameters().toString() : "";
            
            r.setLabels(s.getName(), paramStr, String.valueOf(s.getResult().getAccuracy()) );
    
            resultContainer.getChildren().add(r);
        }

    }

}
