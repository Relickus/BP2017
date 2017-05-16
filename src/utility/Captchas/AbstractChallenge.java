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
import resources.ImageClass;
import resources.ImageClassContainer;
import utility.Coordinates;
import utility.Loader;
import utility.PayloadImage;
import utility.Solvers.Solver;

/**
 * an abstract class of captcha challenge types
 * @author Vojta
 */
public abstract class AbstractChallenge {

    /**
     * number of images in payload of a challenge
     */
    protected int NUMBER_OF_CHALLENGE_IMAGES;

    /**
     * number of correct images correct in a payload of challenge 
     */
    protected int NUMBER_OF_CORRECT_IMAGES;

    /**
     *number of noise images correct in a payload of challenge
     */
    protected int NUMBER_OF_NOISE_IMAGES;

    /**
     * array of all peyload images in the challenge
     */
    protected ArrayList<PayloadImage> payloadImgArr;

    /**
     * array of coordinates of all images in the payload
     */
    protected ArrayList<Coordinates> payloadCoordinatesArr;

    /**
     * array of noise images in the payload
     */
    protected ArrayList<ImageClass> noiseImgsClassArr;

    /**
     * ID of an image class of task of the challenge
     */
    protected int questionClassIdx;

    /**
     * task in a form of keyword
     */
    protected String keywordStr;

    /**
     * image class of the task of challenge
     */
    protected ImageClass challengeClass;

    /**
     * boolean value denoting if the challenge has a fixed task class
     */
    protected boolean fixedClass;
    
    /**
     * constructor for captcha challenge with specified class of task
     * @param key specification of the task of challenge
     */
    public AbstractChallenge(String key) {

        setConstants();
        checkInput(key);
    }

    /**
     * constructor
     */
    public AbstractChallenge() {

        setConstants();

        questionClassIdx = (new Random().nextInt(Constants.NUMBER_OF_CLASSES));
        challengeClass = ImageClassContainer.getClassByVal(questionClassIdx);
    }

    private void setConstants() {
        NUMBER_OF_CHALLENGE_IMAGES = 9;
        NUMBER_OF_CORRECT_IMAGES = new Random().nextInt(Constants.MAX_NUMBER_OF_CORRECT_IMAGES-Constants.MIN_NUMBER_OF_CORRECT_IMAGES+1) + Constants.MIN_NUMBER_OF_CORRECT_IMAGES;
        NUMBER_OF_NOISE_IMAGES = NUMBER_OF_CHALLENGE_IMAGES - NUMBER_OF_CORRECT_IMAGES;
    }

    /**
     * changes constants of captcha challenge
     */
    public void changeConstants() {
        NUMBER_OF_CORRECT_IMAGES = new Random().nextInt(Constants.MAX_NUMBER_OF_CORRECT_IMAGES-Constants.MIN_NUMBER_OF_CORRECT_IMAGES+1) + Constants.MIN_NUMBER_OF_CORRECT_IMAGES;
        NUMBER_OF_NOISE_IMAGES = NUMBER_OF_CHALLENGE_IMAGES - NUMBER_OF_CORRECT_IMAGES;
    }

    /**
     * returns number of correct images in payload of challenge
     * @return number of correct images 
     */
    public int getNumberOfCorrectImgs(){
        return NUMBER_OF_CORRECT_IMAGES;
    }
    
    /**
     * returns number of challenge images in payload of challenge
     * @return number of challenge images (it is always 9 in this project)
     */
    public int getNumberOfChallengeImgs(){
        return NUMBER_OF_CHALLENGE_IMAGES;
    }
    
    /**
     * checks if the class of task of this challenge is fixed by user
     * @return boolean value if task class is fixed
     */
    public boolean isFixedClass() {
        return fixedClass;
    }
     
    /**
     * abstract method to create a  challenge
     */
    public abstract void createChallenge();


    /**
     * returns an appropriate javafx node to contain a task of the challenge. More information in implementation in subclasses.
     * @return
     */
    public abstract Node getNode();

    /**
     * returns task class as a keyword
     * @return
     */
    public abstract String getKeyword();
    
    /**
     * Getter for image class of the challenge task 
     * @return Imageclass of the task
     */
    public ImageClass getChallengeClass(){
        return challengeClass;
    }


    /**
     * Getter for payload of the challenge
     * @return array of images in the payload
     */
    public ArrayList<PayloadImage> getPayload() {
        return payloadImgArr;
    }

    /**
     * generates random payload coordinates for loaded images 
     */
    protected void generatePayloadCoordinates() {
        payloadCoordinatesArr = new ArrayList<>(NUMBER_OF_CHALLENGE_IMAGES);

        int i = 0;
        while (payloadCoordinatesArr.size() != NUMBER_OF_CHALLENGE_IMAGES) {    
            payloadCoordinatesArr.add(new Coordinates(i / 3, i % 3));
            ++i;
        }
        
        Collections.shuffle(payloadCoordinatesArr);
    }

    /**
     * generates random image classes for payload
     */
    protected void generatePayloadClasses() {

        noiseImgsClassArr = new ArrayList<>(NUMBER_OF_NOISE_IMAGES);

        int i = 0;
        while (i < NUMBER_OF_NOISE_IMAGES) {
            int nextIdx = new Random().nextInt(Constants.NUMBER_OF_CLASSES);
            if (nextIdx != questionClassIdx) {
                noiseImgsClassArr.add(ImageClassContainer.getClassByVal(nextIdx));
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

    /**
     * creates a payload of challenge
     */
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

    /**
     * loads challenge images 
     */
    protected void loadChallengeImages() {

        Loader loader = Loader.getInstance();

        // loads correct images to arr
        payloadImgArr = loader.loadNImageFiles(challengeClass, NUMBER_OF_CORRECT_IMAGES);

        // appends noise images to arr
        for (ImageClass i : noiseImgsClassArr) {
            payloadImgArr.add(loader.loadImageFile(i));
        }
    }

    /**
     * sets coordinates to images selected for payload
     */
    protected void fillPayloadImgArr() {
        int i = 0;
        for (PayloadImage pi : payloadImgArr) {
            pi.setCoordinates(payloadCoordinatesArr.get(i));
            ++i;
        }
    }

    /**
     * checks if user specified class is valid
     * @param key specification string of image class 
     */
    protected final void checkInput(String key) {

        ImageClass specClass = ImageClassContainer.getClassByName(key);
        if (specClass != null) {
            specifyClass(specClass);
        } else {
            randomClass();

        }
    }

    protected abstract void specifyClass(ImageClass e);

    protected abstract void randomClass();

    /**
     * Getter for array of solvers available for this challenge
     * @return array of available solvers
     */
    public abstract ArrayList<Solver> getAvailableSolvers();
 
    /**
     * Payload size getter
     * @return Number of challenge images
     */
    public int getPayloadSize(){
       return NUMBER_OF_CHALLENGE_IMAGES;
   }
  
}
