/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.Captchas;

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
    
   public void generateChallenge(){
       
       challenge.getChallenge();
   }
   
   
}
