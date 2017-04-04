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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javax.imageio.ImageIO;
import resources.Constants;
import utility.Captchas.CAPTCHA;
import utility.Captchas.ChallengeImage;
import utility.PayloadImage;

/**
 *
 * @author Vojta
 */
public class CAPTCHASettingsController extends AbstractController implements Initializable {

    private CAPTCHA captcha;

    private @FXML
    Button keywordOrImageBtn;
    private @FXML
    HBox keywordOrImageHbox;
    private @FXML
    GridPane payloadGrid;
    private @FXML
    Label title;
    private @FXML
    Label classSpecifyLabel;
    private @FXML
    HBox fixedClassHbox;

    private Node challengeNode;

    public void setCaptcha(CAPTCHA cap) {
        this.captcha = cap;
    }

    private void styleNode(Node node) {

        if (node instanceof Label) {

            node.setStyle("-fx-font-weight: bold; -fx-font-size: 36px;");

            //HBox.setMargin(node,new Insets(0,0,0,20));
            title.setText("The keyword is: ");
        } else {   //node instanceof ImageView

            ((ImageView) node).setFitWidth(120);
            ((ImageView) node).setFitHeight(120);

            title.setText("The reference image is: ");
        }
    }

    @FXML
    private void onAnotherChallengeClicked(ActionEvent event) {

        captcha.getChallenge().createChallenge();
        fillChallengeNode();
        
        captcha.getChallenge().changeConstants();
        captcha.generatePayload();
        fillPayloadGrid();
    }
       
//    @FXML
//    private void onAnotherPayloadClicked(ActionEvent event) {
//
//        // feature canceled 
//    }


    @FXML
    private void onProceedClicked(ActionEvent event) {

        stageController.loadNextStage(NEXT_SCENE);

        SolverSettingsController windowController = (SolverSettingsController) stageController.getWindowController();
        windowController.setCaptcha(captcha);
        windowController.initView();

        stageController.showStage();
    }

    public void fillChallengeNode() {
        challengeNode = captcha.getChallenge().getNode();
        styleNode(challengeNode);

        keywordOrImageHbox.getChildren().clear();
        keywordOrImageHbox.getChildren().add(challengeNode);
    }

    private void fillPayloadGrid(){
        ArrayList<PayloadImage> payloadArr = captcha.getChallenge().getPayload();
        int idx = 0;
        for(PayloadImage pi : payloadArr){
            ImageView imgview = new ImageView(pi);
            imgview.setFitWidth(100);
            imgview.setFitHeight(100);
            imgview.setSmooth(true);
            GridPane.setConstraints(imgview, pi.getCoordinates().getRow(), pi.getCoordinates().getCol());            
            payloadGrid.getChildren().add(imgview);
            idx++;
        }
        
        
        // !!!!!!!!!!!!!!!!!!! working filtering !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!§
        
//        ImageView fltr = new ImageView(new Image("file:src/htmlCAPTCHAs/filterCorrect.jpg"));
//        fltr.setFitWidth(100);
//        fltr.setFitHeight(100);
//        fltr.setOpacity(0.5);
//        GridPane.setConstraints(fltr, 0, 0);
//        payloadGrid.getChildren().add(fltr);
        
    }
    
    public void initView() {

        fillPayloadGrid();

        fillChallengeNode();

        keywordOrImageBtn.setText("Another " + captcha.getChallenge().getChallengeName().toLowerCase());

        if (captcha.getChallenge() instanceof ChallengeImage) {
            if (((ChallengeImage) captcha.getChallenge()).isFixedClass()) {
                fixedClassHbox.setVisible(true);
                classSpecifyLabel.setText(captcha.getChallenge().getKeyword().toUpperCase());
                classSpecifyLabel.setStyle("-fx-font-weight: bold;");
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        NEXT_SCENE = Constants.SOLVER_SETTINGS_WINDOW;
        PREVIOUS_SCENE = Constants.CAPTCHA_SELECT_WINDOW;

    }
    

}
