/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.Captchas;

import java.net.URL;
import javafx.scene.web.WebView;
import utility.HTMLHandler;

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
    
   public void generateRandomChallenge(){
       
       challenge.createChallenge();
   }
   
   public void generatePayload(){
       challenge.createPayload();
   }
   
   
   public WebView getWebView(WebView view){
       
       // generate WebView from saved captcha and save it somewhere
             
       
        new HTMLHandler().prepareHTML(getChallenge());
       URL file = getClass().getResource("/htmlCAPTCHAs/index.html");
       view.getEngine().load(file.toExternalForm());
       
       return view;
   }
   
   
}
