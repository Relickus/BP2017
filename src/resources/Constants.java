package resources;

/**
 * A resource file containing all used constants
 *
 */
public class Constants {

    /**
     * title of the application window
     */
    public static final String APPLICATION_TITLE = "CAPTCHASolver v1.0";

    /**
     * absolute path to the dataset
     */
    public static String DATASET_PATH = "";

    /**
     * number of defined image classes in resource file
     */
    public static int NUMBER_OF_CLASSES;

    /**
     * path to the image class resource file
     */
    public static String CONFIG_IMAGE_CLASSES = "";

    // -------------------------------------------------------------------------------------
    public static final String LABEL_CHALLENGE_IMAGE = "Select all images related to this one: ";

    public static final String LABEL_CHALLENGE_KEYWORD = "Select all images of a: ";
    /**
     * FXML file of captcha select window
     */
    public static final String CAPTCHA_SELECT_WINDOW = "/view/FXML/CAPTCHASelectFXML.fxml";

    /**
     * initial window of the application
     */
    public static final String INIT_WINDOW = CAPTCHA_SELECT_WINDOW;
    /**
     * FXML file of captcha setting window
     */
    public static final String CAPTCHA_SETTINGS_WINDOW = "/view/FXML/CAPTCHASettingsFXML.fxml";
    /**
     * FXML file of solver select window
     */
    public static final String SOLVER_SETTINGS_WINDOW = "/view/FXML/SolverSettingsFXML.fxml";
    /**
     * FXML file of result window
     */
    public static final String RESULT_WINDOW = "/view/FXML/ResultWindowFXML.fxml";

    /**
     * folder with algorithm scripts
     */
    public static final String SCRIPTS_FOLDER_PATH = "src/scripts/";

    /**
     * path to a filter highlighting correctly classified images in the captcha
     * challenge
     */
    public static final String FILTER_CHOSEN_PATH = "file:src/resources/filter_chosen.jpg";

    /**
     * path to a filter highlighting correct images in the captcha challenge
     */
    public static final String FILTER_CORRECT_PATH = "file:src/resources/filter_correct.png";

    /**
     * path to a filter highlighting incorrectly classified images in the
     * captcha challenge
     */
    public static final String FILTER_REFIMG_WRONG_PATH = "file:src/resources/filter_wrong.png";

    /**
     * opacity of the highlight filter of correctly classified images
     */
    public static final double FILTER_CHOSEN_OPACITY = 0.4;

    /**
     * an upper bound value for number of correct images in captcha challenge
     * payload
     */
    public static final int MAX_NUMBER_OF_CORRECT_IMAGES = 6;

    /**
     * a lower bound value for number of correct images in captcha challenge
     * payload
     */
    public static final int MIN_NUMBER_OF_CORRECT_IMAGES = 4;

}
