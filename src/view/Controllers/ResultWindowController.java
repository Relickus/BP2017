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
import javafx.scene.web.WebView;

/**
 *
 * @author Vojta
 */
public class ResultWindowController extends AbstractController implements Initializable {
    
    public WebView htmlView;
    public Label solverName;
    public Label solverParam;
    public Label solverAcc;

    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO      
       
        URL file = getClass().getResource("/htmlCAPTCHAs/index.html");
        htmlView.getEngine().load(file.toExternalForm());
        htmlView.setZoom(0.7);
        
        solverName.setText("MyKNN");
        solverParam.setText("K=5");
        solverAcc.setText("75%");
        
        

        
        // SET NEXT_STAGE
    }    
    
}
