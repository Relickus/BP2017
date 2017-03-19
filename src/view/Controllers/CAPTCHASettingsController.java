/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebView;
import resources.Constants;
import utility.Captchas.CAPTCHA;

/**
 *
 * @author Vojta
 */
public class CAPTCHASettingsController extends AbstractController implements Initializable {
    
    
    private CAPTCHA captcha;
   
    public Button keywordOrImageBtn;
    public HBox keywordOrImageHbox;
    public WebView payloadView;
    public Label title;
    public Node challengeNode;
    
    public void setCaptcha(CAPTCHA cap){
        this.captcha = cap;
    }
    
    private void styleNode(Node node){
        
        if(node instanceof Label){
            
            node.setStyle("-fx-font-weight: bold; -fx-font-size: 36px;");
            
            //HBox.setMargin(node,new Insets(0,0,0,20));
            
            title.setText("The keyword is: ");
        }
        else{   //node instanceof ImageView
            
            ((ImageView)node).setFitWidth(120);
            ((ImageView)node).setFitHeight(120);
                        
            title.setText("The reference image is: ");
        }
    }
    
    
    @FXML
    private void onAnotherChallengeClicked(ActionEvent event){
        
        captcha.getChallenge().createChallenge(); 
        fillChallengeNode();
    }
    
    @FXML    
    private void onProceedClicked(ActionEvent event){        
        
        stageController.loadNextStage(NEXT_SCENE);   
        
         SolverSettingsController windowController = (SolverSettingsController)stageController.getWindowController();
        windowController.setCaptcha(captcha);  
        windowController.initView();
        
        stageController.showStage();
    }
    
    
    public void fillChallengeNode(){
        challengeNode = captcha.getChallenge().getNode();
        styleNode(challengeNode);
                
        keywordOrImageHbox.getChildren().clear();
        keywordOrImageHbox.getChildren().add(challengeNode);
    }
    
    public void initView(){
        
        captcha.getPayloadWebView(payloadView);
        payloadView.setZoom(0.7);
        
        fillChallengeNode();
        
        keywordOrImageBtn.setText("Another " + captcha.getChallenge().getChallengeName().toLowerCase());
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        NEXT_SCENE = Constants.SOLVER_SETTINGS_WINDOW;
        PREVIOUS_SCENE = Constants.CAPTCHA_SELECT_WINDOW;
        
    }    
    
}
