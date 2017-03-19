/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import resources.Constants;
import utility.Captchas.CAPTCHA;

/**
 *
 * @author Vojta
 */
public class CAPTCHASettingsController extends AbstractController implements Initializable {
    
    
    private CAPTCHA captcha;
   
    public Button round_btn;
    //public WebView imgMatrixHtml;
    public GridPane grid;

    
    public void setCaptcha(CAPTCHA cap){
        this.captcha = cap;
    }
    
    private void loadChallengeImages(){
        
        Image im1 = new Image("file:src/htmlCAPTCHAs/a.jpg");    
        Image im2 = new Image("file:src/htmlCAPTCHAs/b.JPG");
        Image im3 = new Image("file:src/htmlCAPTCHAs/cat.jpg");
        Image im4 = new Image("file:src/htmlCAPTCHAs/a.jpg");    
        Image im5 = new Image("file:src/htmlCAPTCHAs/b.JPG");
        Image im6 = new Image("file:src/htmlCAPTCHAs/cat.jpg");

         
         ImageView iv1 = new ImageView(im1);
         ImageView iv2 = new ImageView(im2);
         ImageView iv3 = new ImageView(im3);
         ImageView iv4 = new ImageView(im4);
         ImageView iv5 = new ImageView(im5);
         ImageView iv6 = new ImageView(im6);
         
         iv1.setFitHeight(100);
         iv1.setFitWidth(100);
         iv2.setFitHeight(100);
         iv2.setFitWidth(100);
         iv3.setFitHeight(100);
         iv3.setFitWidth(100);
         iv4.setFitHeight(100);
         iv4.setFitWidth(100);
         iv5.setFitHeight(100);
         iv5.setFitWidth(100);
         iv6.setFitHeight(100);
         iv6.setFitWidth(100);
         
         GridPane.setConstraints(iv1, 0, 0);
         GridPane.setConstraints(iv2, 0, 1);
         GridPane.setConstraints(iv3, 0, 2);
         GridPane.setConstraints(iv4, 1, 0);
         GridPane.setConstraints(iv5, 1, 1);
         GridPane.setConstraints(iv6, 2, 2);

         grid.getChildren().addAll(iv1,iv2,iv3,iv4,iv5,iv6);        
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //imgMatrixHtml.getEngine().load(file.toExternalForm());
        //imgMatrixHtml.setZoom(0.7);
      
        loadChallengeImages();
       
        NEXT_SCENE = Constants.SOLVER_SETTINGS_WINDOW;
        
    }    
    
}
