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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import resources.Constants;
import utility.Captchas.CAPTCHA;
import utility.Captchas.ChallengeImage;
import utility.Captchas.ChallengeKeyword;

/**
 *
 * @author Vojta
 */
public class CAPTCHASelectController extends AbstractController implements Initializable {

    //@FXML
    //private WebView htmlView;
    private @FXML
    RadioButton CAPTCHA_radiobtn1;
    private @FXML
    RadioButton CAPTCHA_radiobtn2;
    private @FXML
    ToggleGroup radioGroup;

    private @FXML
    TextField keywordSpecifyText;
    private @FXML
    TextField imageSpecifyText;
    private @FXML
    Label optionalLabel;
    private @FXML
    VBox keywordSpecifyVbox;
    private @FXML
    VBox imageSpecifyVbox;

    @FXML
    private void generateCAPTCHA(ActionEvent event) {

        CAPTCHA captcha;

        if (CAPTCHA_radiobtn1.isSelected()) {
            if (!keywordSpecifyText.getText().isEmpty()) {
                captcha = new CAPTCHA(new ChallengeKeyword(keywordSpecifyText.getText()));
                captcha.generatePayload();
            } else {
                captcha = new CAPTCHA(new ChallengeKeyword());
                captcha.generateRandomChallenge();
                captcha.generatePayload();
            }
        } else if (CAPTCHA_radiobtn2.isSelected()) {
            if (!imageSpecifyText.getText().isEmpty()) {
                captcha = new CAPTCHA(new ChallengeImage(imageSpecifyText.getText()));
                captcha.generatePayload();
            } else {
                captcha = new CAPTCHA(new ChallengeImage());
                captcha.generateRandomChallenge();
                captcha.generatePayload();

            }
        } else {
            return;
        }

        //  captcha.generateChallenge();
        // generate captcha and hand it to next stage
        //stageController.getWindowController()
        stageController.loadNextStage(NEXT_SCENE);

        CAPTCHASettingsController windowController = (CAPTCHASettingsController) stageController.getWindowController();
        windowController.setCaptcha(captcha);
        windowController.initView();

        stageController.showStage();
    }

    @FXML
    private void RB1Selected(ActionEvent event) {

        System.out.println("You clicked RB1!");
        keywordSpecifyVbox.setVisible(true);
        imageSpecifyVbox.setVisible(false);
    }

    @FXML
    private void RB2Selected(ActionEvent event) {
        keywordSpecifyVbox.setVisible(false);
        imageSpecifyVbox.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        radioGroup = new ToggleGroup();

        CAPTCHA_radiobtn1.setToggleGroup(radioGroup);
        CAPTCHA_radiobtn2.setToggleGroup(radioGroup);
        keywordSpecifyVbox.setVisible(false);
        imageSpecifyVbox.setVisible(false);

        NEXT_SCENE = PREVIOUS_SCENE = Constants.CAPTCHA_SETTINGS_WINDOW;
    }

}
