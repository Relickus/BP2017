
package resources;

/**
 *
 * @author Vojta
 */
public class Constants {
    
    public static final String APPLICATION_TITLE = "CAPTCHASolver v1.0";
    
    public static final String DATASET_PATH = "D:\\BAP\\DATASET\\";
    
    public static int NUMBER_OF_CLASSES; 
    public static final String CONFIG_IMAGE_CLASSES = DATASET_PATH + "ImageClassConfig.txt";
    
    public static final String LABEL_CHALLENGE_IMAGE = "Select all images related to this one: ";
    public static final String LABEL_CHALLENGE_KEYWORD = "Select all images of a: ";
    
    public static final String CAPTCHA_SELECT_WINDOW = "/view/FXML/CAPTCHASelectFXML.fxml";          
    public static final String INIT_WINDOW = CAPTCHA_SELECT_WINDOW;
    public static final String CAPTCHA_SETTINGS_WINDOW = "/view/FXML/CAPTCHASettingsFXML.fxml";
    public static final String SOLVER_SETTINGS_WINDOW = "/view/FXML/SolverSettingsFXML.fxml";
    public static final String RESULT_WINDOW ="/view/FXML/ResultWindowFXML.fxml"; 
    public static final String TRIAL ="/view/FXML/FXMLDocument.fxml";

    public static final String SCRIPTS_FOLDER_PATH = "src/scripts/";
    public static final String FILTER_CHOSEN_PATH = "file:src/resources/filter_chosen.jpg";
    public static final String FILTER_CORRECT_PATH = "file:src/resources/filter_correct.png";
    public static final String FILTER_REFIMG_WRONG_PATH = "file:src/resources/filter_wrong.png";
            
    public static final double FILTER_CHOSEN_OPACITY = 0.4;
    public static final int MAX_NUMBER_OF_CORRECT_IMAGES = 6;
    public static final int MIN_NUMBER_OF_CORRECT_IMAGES = 4;

    
}
