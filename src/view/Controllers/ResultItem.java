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
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import utility.Captchas.CAPTCHA;
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

    public ResultItem(CAPTCHA c) {
        super();
        loadFxml(getClass().getResource("/view/FXML/ResultItemFXML.fxml"), this);
        this.captcha = c;
        
        setCaptchaView();
    }

    public void setLabels(String name, String param, String acc) {
        solverName.setText(name);
        solverParam.setText(param);
        solverAcc.setText(acc);
    }

    private void setCaptchaView(){
        
        captchaContainer.getChildren().add(new CAPTCHAHolder(captcha));
        
    }
    
    public void setResult(Result res){
        
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
}
