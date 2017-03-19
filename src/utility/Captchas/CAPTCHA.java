/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.Captchas;

import java.net.URL;
import javafx.scene.web.WebView;

/**
 *
 * @author Vojta
 */
public class CAPTCHA {
    
  private AbstractChallenge challenge;

    public CAPTCHA(AbstractChallenge challenge) {
        this.challenge = challenge;
        
    }

    public CAPTCHA() {
    }
    
     
   public void setChallenge(AbstractChallenge challenge){
       this.challenge = challenge;
   }

    public AbstractChallenge getChallenge() {
        return challenge;
    }
    
   public void generateChallenge(){
       
       challenge.createChallenge();
   }
   
   public WebView getPayloadWebView(WebView view){
       
       //generate payload view from saved captcha and save it somewhere
       
       URL file = getClass().getResource("/htmlCAPTCHAs/payloadImages.html");
       view.getEngine().load(file.toExternalForm());
       
       return view;
   }
   
   public WebView getWebView(WebView view){
       
       // generate WebView from saved captcha and save it somewhere
              
       URL file = getClass().getResource("/htmlCAPTCHAs/index.html");
       view.getEngine().load(file.toExternalForm());
       
       return view;
   }
   
   
}
