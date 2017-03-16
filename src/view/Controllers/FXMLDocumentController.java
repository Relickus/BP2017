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
import javafx.scene.transform.Scale;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author Vojta
 */
public class FXMLDocumentController extends AbstractController implements Initializable {
    
    public WebView htmlView;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO      
       
        //WebEngine webEngine = htmlView.getEngine();
        //webEngine.load("http://www.seznam.cz");
        
        URL file = getClass().getResource("/htmlCAPTCHAs/index.html");
        htmlView.getEngine().load(file.toExternalForm());
        htmlView.setZoom(0.7);
        
        
    }    
    
}
