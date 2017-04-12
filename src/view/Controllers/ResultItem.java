/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Controllers;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
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
        
        Double accPercent = Double.valueOf(acc)*100;
        solverAcc.setText(accPercent.toString() + "%");

        styleLabels();
    }

    private void styleLabels() {
        solverName.setStyle("-fx-font-weight: bold;");

        Double d = Double.valueOf(solverAcc.getText().replace(',', '.'));

        if (d.compareTo(0.3) <= 0) {
            solverAcc.setTextFill(Color.RED);
        } else if (d.compareTo(0.5) <= 0) {
            solverAcc.setTextFill(Color.ORANGE);
        } else if (d.compareTo(0.7) <= 0) {
            solverAcc.setTextFill(Color.LIGHTGREEN);
        } else {
            solverAcc.setTextFill(Color.GREEN);
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

        for (ClassifiedImage i : result.getResultArr()) {
            if (i.matchesKeyword(captcha.getChallenge().getKeyword().toLowerCase())) {
                captchaHolder.setFilterOnField(filterPath, i.getCoordinates());
            }
        }
    }

}
