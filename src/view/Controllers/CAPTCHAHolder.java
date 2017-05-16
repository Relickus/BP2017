/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import resources.Constants;
import utility.Captchas.CAPTCHA;
import utility.Captchas.ChallengeImage;
import utility.Captchas.ChallengeKeyword;
import utility.Coordinates;
import utility.PayloadImage;

/**
 * custom container class providing displaying of a CAPTCHA challege in GUI
 *
 * @author Vojta
 */
public class CAPTCHAHolder extends VBox {

    private @FXML
    Label promptLabel;
    private @FXML
    HBox keywordOrImageHbox;
    private @FXML
    GridPane payloadGrid;

    /**
     * constructs the container from passed CAPTCHA object
     *
     * @param captcha Captcha wrapper object
     */
    public CAPTCHAHolder(CAPTCHA captcha) {
        super();
        loadFxml(getClass().getResource("/view/FXML/CAPTCHAHolderFXML.fxml"), this);

        if (captcha.getChallenge() instanceof ChallengeKeyword) {
            setChallengeHeader((ChallengeKeyword) captcha.getChallenge());
        } else if (captcha.getChallenge() instanceof ChallengeImage) {
            setChallengeHeader((ChallengeImage) captcha.getChallenge());
        }

        setPayload(captcha.getChallenge().getPayload());
    }

    private void setChallengeHeader(ChallengeKeyword c) {

        promptLabel.setText(Constants.LABEL_CHALLENGE_KEYWORD);
        Label keywordLabel = (Label) c.getNode();
        keywordLabel.setTextFill(Color.WHITE);
        keywordLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20px; -fx-font-weight: bold;");

        keywordOrImageHbox.setMinHeight(100);
        keywordOrImageHbox.setPadding(new Insets(20, 20, 20, 10));
        keywordOrImageHbox.getChildren().add(keywordLabel);

    }

    private void setChallengeHeader(ChallengeImage c) {

        promptLabel.setText(Constants.LABEL_CHALLENGE_IMAGE);
        promptLabel.setWrapText(true);
        ImageView iv = (ImageView) c.getNode();
        iv.setFitWidth(100);
        iv.setFitHeight(100);

        GridPane ivholder = new GridPane();
        ivholder.setHgap(0);
        ivholder.setVgap(0);
        ivholder.add(iv, 0, 0);

        keywordOrImageHbox.getChildren().add(ivholder);

    }

    private void setPayload(ArrayList<PayloadImage> payloadarr) {

        for (PayloadImage pi : payloadarr) {
            ImageView iv = new ImageView(pi);
            iv.setFitWidth(96);
            iv.setFitHeight(96);
            GridPane.setConstraints(iv, pi.getCoordinates().getRow(), pi.getCoordinates().getCol());

            payloadGrid.setVgap(6);
            payloadGrid.setHgap(6);
            payloadGrid.getChildren().add(iv);
        }

    }

    /**
     * loads apporpriate FXML template
     *
     * @param fxmlFile fxml template
     * @param rootController sets a controller of the template
     */
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

    /**
     * sets a graphical filter on a field of specified coordinates in payload
     * grid of CAPTCHA challenge
     *
     * @param filterPath absolute path to an image file with the filter
     * @param coord coordinates in the payload grid
     */
    public void setFilterOnField(String filterPath, Coordinates coord) {

        ImageView iv = new ImageView(new Image(filterPath));
        iv.setFitWidth(96);
        iv.setFitHeight(96);
        if (filterPath.equals(Constants.FILTER_CHOSEN_PATH)) {
            iv.setOpacity(Constants.FILTER_CHOSEN_OPACITY);
        }
        GridPane.setConstraints(iv, coord.getRow(), coord.getCol());
        payloadGrid.getChildren().add(iv);
    }

    /**
     * sets a graphical filter on reference image of CAPTCHA challenge
     *
     * @param filterPath absolute path to an image file with the filter
     */
    public void setFilterOnRefImg(String filterPath) {

        ImageView filteriv = new ImageView(new Image(filterPath));
        filteriv.setFitHeight(100);
        filteriv.setFitWidth(100);
        filteriv.setOpacity(Constants.FILTER_CHOSEN_OPACITY);

        ((GridPane) (keywordOrImageHbox.getChildren().get(0))).add(filteriv, 0, 0);

    }
}
