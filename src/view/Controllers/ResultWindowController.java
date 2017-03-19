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

/**
 *
 * @author Vojta
 */
public class ResultWindowController extends AbstractController implements Initializable {
    
    public WebView captchaView;
    public Label solverName;
    public Label solverParam;
    public Label solverAcc;
    public VBox resultContainer;
    public VBox resultItem;
    
    private ArrayList<Result> resultsArr;
    private CAPTCHA captcha;

    
    public void setCaptcha(CAPTCHA captcha) {
        this.captcha = captcha;
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
    private void onBackClicked(ActionEvent event){        
        
        stageController.loadNextStage(PREVIOUS_SCENE); 
        SolverSettingsController windowController = (SolverSettingsController)stageController.getWindowController();
        windowController.setCaptcha(captcha);  
        windowController.initView();        
        stageController.showStage();
    }
    
     @FXML    
    private void onMainMenuClicked(ActionEvent event){        
        
        stageController.loadNextStage(Constants.INIT_WINDOW);        
        stageController.showStage();
    }
    

    public void initView() {
          
        URL file = getClass().getResource("/htmlCAPTCHAs/index.html");
        captchaView.getEngine().load(file.toExternalForm());
        captchaView.setZoom(0.7);
        
        solverName.setText("MyKNN");
        solverParam.setText("K=5");
        solverAcc.setText("75%");
        
       
        // v cyklu vytvaret ResultItemy, naplnit je vysledkama a pak je dat do containeru:
        
        ResultItem ri = new ResultItem();
        ResultItem rii = new ResultItem();
        
        ri.setCaptchaView("/htmlCAPTCHAs/index.html");
        ri.setLabels("jinej", "zadny", "0%");
        
        resultContainer.getChildren().addAll(ri,rii);
    }
    
}
