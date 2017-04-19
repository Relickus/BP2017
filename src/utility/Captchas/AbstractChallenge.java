/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.Captchas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javafx.scene.Node;
import javafx.scene.web.WebView;
import resources.Constants;
import resources.ImageClassEnum;
import utility.Coordinates;
import utility.Loader;
import utility.PayloadImage;
import utility.Solvers.Solver;

/**
 *
 * @author Vojta
 */
public abstract class AbstractChallenge {

    protected int NUMBER_OF_CHALLENGE_IMAGES;
    protected int NUMBER_OF_CORRECT_IMAGES;
    protected int NUMBER_OF_NOISE_IMAGES;

    protected ArrayList<PayloadImage> payloadImgArr;
    protected ArrayList<Coordinates> payloadCoordinatesArr;
    protected ArrayList<ImageClassEnum> noiseImgsClassArr;

    protected int questionClassIdx;
    protected String keywordStr;
    protected ImageClassEnum challengeClass;

    protected WebView captchaWebView;
    protected WebView payloadWebView;

    protected boolean fixedClass;
    
    public AbstractChallenge(String key) {

        setConstants();
        checkInput(key);
    }

    public AbstractChallenge() {

        setConstants();

        questionClassIdx = (new Random().nextInt(Constants.NUMBER_OF_CLASSES)) + 1;
        challengeClass = ImageClassEnum.getEnum(questionClassIdx);
    }

    private void setConstants() {
        NUMBER_OF_CHALLENGE_IMAGES = 9;
        NUMBER_OF_CORRECT_IMAGES = new Random().nextInt(Constants.MAX_NUMBER_OF_CORRECT_IMAGES-Constants.MIN_NUMBER_OF_CORRECT_IMAGES+1) + Constants.MIN_NUMBER_OF_CORRECT_IMAGES;
        NUMBER_OF_NOISE_IMAGES = NUMBER_OF_CHALLENGE_IMAGES - NUMBER_OF_CORRECT_IMAGES;
    }

    public void changeConstants() {
        NUMBER_OF_CORRECT_IMAGES = new Random().nextInt(Constants.MAX_NUMBER_OF_CORRECT_IMAGES-Constants.MIN_NUMBER_OF_CORRECT_IMAGES+1) + Constants.MIN_NUMBER_OF_CORRECT_IMAGES;
        NUMBER_OF_NOISE_IMAGES = NUMBER_OF_CHALLENGE_IMAGES - NUMBER_OF_CORRECT_IMAGES;
    }

    public int getNumberOfCorrectImgs(){
        return NUMBER_OF_CORRECT_IMAGES;
    }
    
     public boolean isFixedClass() {
        return fixedClass;
    }
     
    public abstract void createChallenge();

    protected abstract void generatePayloadWebView();

    protected abstract void generateCaptchaWebView();

    //protected loadNumberOfImagesInClasses(){
    public abstract Node getNode();

    public abstract String getKeyword();
    
    public ImageClassEnum getChallengeClass(){
        return challengeClass;
    }

    public abstract String getChallengeName();

    public ArrayList<PayloadImage> getPayload() {
        return payloadImgArr;
    }

    protected void generatePayloadCoordinates() {
        payloadCoordinatesArr = new ArrayList<>(NUMBER_OF_CHALLENGE_IMAGES);

        int i = 0;
        while (payloadCoordinatesArr.size() != NUMBER_OF_CHALLENGE_IMAGES) {    
            payloadCoordinatesArr.add(new Coordinates(i / 3, i % 3));
            ++i;
        }
        
        Collections.shuffle(payloadCoordinatesArr);
    }

    protected void generatePayloadClasses() {

        noiseImgsClassArr = new ArrayList<>(NUMBER_OF_NOISE_IMAGES);

        int i = 0;
        while (i < NUMBER_OF_NOISE_IMAGES) {
            int nextIdx = new Random().nextInt(Constants.NUMBER_OF_CLASSES);
            if (nextIdx != questionClassIdx) {
                noiseImgsClassArr.add(ImageClassEnum.getEnum(nextIdx));
                ++i;
            }
            //if generated same class as keywordStr try again...
        }
    }

    private void clearAll() {
        if (payloadImgArr != null) {
            payloadImgArr.clear();
        }
        if (payloadCoordinatesArr != null) {
            payloadCoordinatesArr.clear();
        }
        if (noiseImgsClassArr != null) {
            noiseImgsClassArr.clear();
        }
    }

    public void createPayload() {

        // challenge keywordStr class is already known at this moment
        // 0. clear all arrays
        // 1. generate payload noise classes
        // 2. generate coordinates for whole payload grid
        // 3. load random imgs from those classes
        // 4. fill payloadArr and assign Coordinates to images in it
        clearAll();
        generatePayloadClasses();
        generatePayloadCoordinates();
        loadChallengeImages();
        fillPayloadImgArr();    // join Coordinates and loaded images

    }

    protected void loadChallengeImages() {

        Loader loader = Loader.getInstance();

        // loads correct images to arr
        payloadImgArr = loader.loadNImageFiles(challengeClass, NUMBER_OF_CORRECT_IMAGES);

        // appends noise images to arr
        for (ImageClassEnum i : noiseImgsClassArr) {
            payloadImgArr.add(loader.loadImageFile(i));
        }
    }

    protected void fillPayloadImgArr() {
        int i = 0;
        for (PayloadImage pi : payloadImgArr) {
            pi.setCoordinates(payloadCoordinatesArr.get(i));
            ++i;
        }
    }

    protected final void checkInput(String key) {

        ImageClassEnum specClass = ImageClassEnum.getEnum(key);
        if (specClass != null) {
            specifyClass(specClass);
        } else {
            randomClass();

        }
    }

    protected abstract void specifyClass(ImageClassEnum e);

    protected abstract void randomClass();

    public abstract ArrayList<Solver> getAvailableSolvers();
 
   
   public int getPayloadSize(){
       return NUMBER_OF_CHALLENGE_IMAGES;
   }
  
}
