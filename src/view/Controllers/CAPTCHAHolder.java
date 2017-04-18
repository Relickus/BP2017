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
import javafx.scene.web.WebView;
import resources.Constants;
import utility.Captchas.CAPTCHA;
import utility.Captchas.ChallengeImage;
import utility.Captchas.ChallengeKeyword;
import utility.Coordinates;
import utility.PayloadImage;

/**
 *
 * @author Vojta
 */
public class CAPTCHAHolder extends VBox{
    
    private @FXML
    Label promptLabel;
    private @FXML
    HBox keywordOrImageHbox;
    private @FXML
    GridPane payloadGrid;

    public CAPTCHAHolder(CAPTCHA captcha) {
        super();
        loadFxml(getClass().getResource("/view/FXML/CAPTCHAHolderFXML.fxml"), this);
        
        if(captcha.getChallenge() instanceof ChallengeKeyword)
            setChallengeHeader((ChallengeKeyword)captcha.getChallenge());
        else if(captcha.getChallenge() instanceof ChallengeImage)            
            setChallengeHeader((ChallengeImage)captcha.getChallenge());
        
        setPayload(captcha.getChallenge().getPayload());
    }

    private void setChallengeHeader(ChallengeKeyword c){
        
        promptLabel.setText("Select all images of a ");
        Label keywordLabel = (Label)c.getNode();
        keywordLabel.setTextFill(Color.WHITE);
        keywordLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 18px; -fx-font-weight: bold;");
        
        keywordOrImageHbox.setPadding(new Insets(20,20,20,10));
        keywordOrImageHbox.getChildren().add(keywordLabel);
        
    }
    private void setChallengeHeader(ChallengeImage c){
        
        promptLabel.setText("Select all images that match this one: ");
        ImageView iv = (ImageView)c.getNode();
        iv.setFitWidth(100);
        iv.setFitHeight(100);
        
        keywordOrImageHbox.getChildren().add(iv);
        
    }
    
    private void setPayload(ArrayList<PayloadImage> payloadarr) {
        
        for(PayloadImage pi : payloadarr){           
            ImageView iv = new ImageView(pi);
            iv.setFitWidth(96);
            iv.setFitHeight(96);
            GridPane.setConstraints(iv, pi.getCoordinates().getRow(), pi.getCoordinates().getCol());
            
            payloadGrid.setVgap(6);
            payloadGrid.setHgap(6);
            payloadGrid.getChildren().add(iv);
        }
        
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
    
    void setFilterOnField(String filterPath, Coordinates coord){
        
        ImageView iv = new ImageView(new Image(filterPath));
        iv.setFitWidth(96);
        iv.setFitHeight(96);
        if(filterPath.equals(Constants.FILTER_CHOSEN_PATH))
            iv.setOpacity(Constants.FILTER_CHOSEN_OPACITY);           
        GridPane.setConstraints(iv, coord.getRow(), coord.getCol());
        payloadGrid.getChildren().add(iv);
    }
    
}
