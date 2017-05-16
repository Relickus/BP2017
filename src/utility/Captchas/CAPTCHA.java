/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.Captchas;

/**
 * Wrapper class for a certain type of challenge
 * @author Vojta
 */
public class CAPTCHA {
    
  private AbstractChallenge challenge;

    /**
     * constructor 
     * @param challenge Challenge of any type
     */
    public CAPTCHA(AbstractChallenge challenge) {
        this.challenge = challenge;
        
    }

    /**
     *
     */
    public CAPTCHA() {
    }
    
    /**
     * challenge setter
     * @param challenge challenge to be set
     */
    public void setChallenge(AbstractChallenge challenge){
       this.challenge = challenge;
   }

    /**
     * getter for challenge in this captcha 
     * @return challenge of this captcha
     */
    public AbstractChallenge getChallenge() {
        return challenge;
    }
    
    /**
     * generates a random challenge
     */
    public void generateRandomChallenge(){
       
       challenge.createChallenge();
   }
   
    /**
     * generates a payload for curent challenge
     */
    public void generatePayload(){
       challenge.createPayload();
   }
   
   
}
