/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Controllers;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import resources.Constants;
import utility.Captchas.CAPTCHA;
import utility.ClassifiedImage;
import utility.Result;

/**
 *
 * @author Vojta
 */
public class ResultItem extends VBox {

    private @FXML
    Label solverName;
    private @FXML
    Label solverParam;
    private @FXML
    Label solverAcc;
    private @FXML
    HBox captchaContainer;

    private final CAPTCHA captcha;
    private Result result;
    private CAPTCHAHolder captchaHolder;

    public ResultItem(CAPTCHA c) {
        super();
        loadFxml(getClass().getResource("/view/FXML/ResultItemFXML.fxml"), this);
        this.captcha = c;

        setCaptchaView();
    }

    public void setLabels(String name, String param, String acc) {
                
        solverName.setText(name);
        solverParam.setText(param);        
        
        Double accPercent = Double.valueOf(acc.replace(',', '.'))*100;
        solverAcc.setText(accPercent.toString() + "%");

        styleLabels(accPercent);
        
    }

    private void styleLabels(Double d) {

        if (d.compareTo(30.0) <= 0) {
            solverAcc.setTextFill(Color.RED);
        } else if (d.compareTo(50.0) <= 0) {
            solverAcc.setTextFill(Color.ORANGE);
        } else if (d.compareTo(70.0) <= 0) {
            solverAcc.setTextFill(Color.LIMEGREEN);
        } else {
            solverAcc.setTextFill(Color.GREEN);
            if(d.compareTo(100.0) == 0)
                solverAcc.setStyle("-fx-font-size: 15px; -fx-font-weight: bold");
        }
    }

    private void setCaptchaView() {
        captchaHolder = new CAPTCHAHolder(captcha);
        captchaContainer.getChildren().add(captchaHolder);
    }
      
    
    public void setResult(Result res) {
        result = res;
    }

    protected static void loadFxml(URL fxmlFile, Object rootController) {
        FXMLLoader loader = new FXMLLoader(fxmlFile);
        loader.setController(rootController);
        loader.setRoot(rootController);
        try {
            loader.load();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void setFilter(String filterPath) {
        
        if(filterPath.equals(Constants.FILTER_CHOSEN_PATH)){
            for (ClassifiedImage i : result.getResultArr()) {
                if (i.isEquivalentClass(captcha.getChallenge().getChallengeClass())) {
                    captchaHolder.setFilterOnField(filterPath, i.getCoordinates());
                }
            }
        }
        else if(filterPath.equals(Constants.FILTER_CORRECT_PATH)){
            for (ClassifiedImage i : result.getResultArr()) {
                if (captcha.getChallenge().getChallengeClass().equals(i.getCorrectClass())) {
                    captchaHolder.setFilterOnField(filterPath, i.getCoordinates());
                }
            }
        }        
    }
    
    public void setRefImgFilter(){
        
        if(result.getClassifiedRefImg().guessedRight())
            captchaHolder.setFilterOnRefImg(Constants.FILTER_CHOSEN_PATH);
        else
            captchaHolder.setFilterOnRefImg(Constants.FILTER_REFIMG_WRONG_PATH);
    }

}
