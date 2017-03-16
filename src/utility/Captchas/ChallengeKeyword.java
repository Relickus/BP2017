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
public class ChallengeKeyword extends AbstractChallenge{
    
    private String keyword;

    public ChallengeKeyword() {
        NUMBER_OF_CHALLENGE_IMAGES = 9;
    }
    
    

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


    @Override
    public void getChallenge() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void addChallengeImage(String path) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadChallengeImages(String path) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    
    
}
