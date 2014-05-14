package pathx;

import pathx.ui.pathXMiniGame;
import pathx.ui.pathXErrorHandler;
import xml_utilities.InvalidXMLFileFormatException;
import properties_manager.PropertiesManager;
import static pathx.pathXConstants.*;

/**
 *
 * @author Karl Propper
 */
public class PathX {
    
    // THIS HAS THE FULL USER INTERFACE AND ONCE IN EVENT
    // HANDLING MODE, BASICALLY IT BECOMES THE FOCAL
    // POINT, RUNNING THE UI AND EVERYTHING ELSE
    static pathXMiniGame miniGame = new pathXMiniGame();
    

    /**
     * This is where the pathX game application starts execution. We'll
     * load the application properties and then use them to build our
     * user interface and start the window in real-time mode.
     */
    public static void main(String[] args)
    {
        try
        {
            // LOAD THE SETTINGS FOR STARTING THE APP
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            props.addProperty(PropertiesManager.DATA_PATH_PROPERTY, PATH_DATA);
            props.loadProperties(PROPERTIES_FILE_NAME, PROPERTIES_SCHEMA_FILE_NAME);
            
            // THEN WE'LL LOAD THE GAME FLAVOR AS SPECIFIED BY THE PROPERTIES FILE
            String gameFlavorFile = props.getProperty(pathXPropertyType.FILE_GAME_PROPERTIES);
            props.loadProperties(gameFlavorFile, PROPERTIES_SCHEMA_FILE_NAME);

            // NOW WE CAN LOAD THE UI, WHICH WILL USE ALL THE FLAVORED CONTENT
            String appTitle = props.getProperty(pathXPropertyType.TEXT_TITLE_BAR_GAME);
            miniGame.initMiniGame(appTitle, FPS, WINDOW_WIDTH, WINDOW_HEIGHT);
            
            // GET THE PROPER WINDOW DIMENSIONS
            miniGame.startGame();
        }
        // THERE WAS A PROBLEM LOADING THE PROPERTIES FILE
        catch(InvalidXMLFileFormatException ixmlffe)
        {
            // LET THE ERROR HANDLER PROVIDE THE RESPONSE
            pathXErrorHandler errorHandler = miniGame.getErrorHandler();
            errorHandler.processError(pathXPropertyType.TEXT_ERROR_LOADING_XML_FILE);
        }
    }
    
    /**
     * pathXPropertyType represents the types of data that will need
     * to be extracted from XML files.
     */
    public enum pathXPropertyType
    {
        // LOADED FROM properties.xml
        
        /* SETUP FILE NAMES */
        FILE_GAME_PROPERTIES,
        FILE_PLAYER_RECORD,

        /* DIRECTORY PATHS FOR FILE LOADING */
        PATH_AUDIO,
        PATH_IMG,
        
        // LOADED FROM THE GAME FLAVOR PROPERTIES XML FILE
        // pathXproperties.xml
                
        /* IMAGE FILE NAMES */
        IMAGE_BACKGROUND_GAME,
        IMAGE_BACKGROUND_MENU,
        IMAGE_BACKGROUND_SETTINGS,
        IMAGE_BACKGROUND_LEVEL_SELECT,
        IMAGE_BUTTON_PLAY,
        IMAGE_BUTTON_PLAY_MOUSE_OVER,
        IMAGE_BUTTON_RESET,
        IMAGE_BUTTON_RESET_MOUSE_OVER,
        IMAGE_BUTTON_SETTINGS,
        IMAGE_BUTTON_SETTINGS_MOUSE_OVER,
        IMAGE_BUTTON_HELP,
        IMAGE_BUTTON_HELP_MOUSE_OVER,
        IMAGE_BUTTON_CLOSE,
        IMAGE_BUTTON_CLOSE_MOUSE_OVER,
        IMAGE_BUTTON_HOME,
        IMAGE_BUTTON_HOME_MOUSE_OVER,
        IMAGE_BUTTON_SOUND,
        IMAGE_BUTTON_SOUND_MOUSE_OVER,
        IMAGE_BUTTON_SOUND_SELECTED,
        IMAGE_BUTTON_MUSIC,
        IMAGE_BUTTON_MUSIC_MOUSE_OVER,
        IMAGE_BUTTON_MUSIC_SELECTED,
        IMAGE_BUTTON_GAME_SPEED,
        IMAGE_BUTTON_GAME_SPEED_MOUSE_OVER,
        IMAGE_BUTTON_BACK,
        IMAGE_BUTTON_BACK_MOUSE_OVER,
        IMAGE_DIALOG_LEVEL_INFO,
        IMAGE_BUTTON_LEVEL_INFO_CLOSE,
        IMAGE_BUTTON_LEVEL_INFO_CLOSE_MOUSE_OVER,
        IMAGE_BUTTON_ARROW_UP,
        IMAGE_BUTTON_ARROW_UP_MOUSE_OVER,
        IMAGE_BUTTON_ARROW_DOWN,
        IMAGE_BUTTON_ARROW_DOWN_MOUSE_OVER,
        IMAGE_BUTTON_ARROW_LEFT,
        IMAGE_BUTTON_ARROW_LEFT_MOUSE_OVER,
        IMAGE_BUTTON_ARROW_RIGHT,
        IMAGE_BUTTON_ARROW_RIGHT_MOUSE_OVER,
        IMAGE_BUTTON_START,
        IMAGE_WINDOW_ICON,
        IMAGE_MAP,
        IMAGE_BUTTON_LOCATION_MARKER,
        IMAGE_BUTTON_GAME_ARROW_LEFT,
        IMAGE_BUTTON_GAME_ARROW_RIGHT,
        IMAGE_BUTTON_GAME_ARROW_UP,
        IMAGE_BUTTON_GAME_ARROW_DOWN,
        IMAGE_BUTTON_GAME_PLAY,
        IMAGE_BUTTON_PAUSE,
        IMAGE_PLAYER,
        IMAGE_ZOMBIE,
        IMAGE_BANDIT,
        IMAGE_POLICE,
        IMAGE_BUTTON_FLAT_TIRE,
        IMAGE_BUTTON_GAS,
        IMAGE_BUTTON_GREEN_LIGHT,
        IMAGE_BUTTON_RED_LIGHT,
        IMAGE_BUTTON_CLOSE_ROAD,
        IMAGE_BUTTON_OPEN_INTERSECTION,
        IMAGE_BUTTON_CLOSE_INTERSECTION,
        IMAGE_BUTTON_INCREASE_PLAYER_SPEED,
        IMAGE_BUTTON_INCREASE_SPEED,
        IMAGE_BUTTON_DECREASE_SPEED,
        IMAGE_BUTTON_MIND_CONTROL,
        IMAGE_BUTTON_INVINCIBLE,
        IMAGE_BUTTON_STEAL,
        IMAGE_BUTTON_FLY,
        IMAGE_BUTTON_INTANGABILITY,
        IMAGE_BUTTON_FREEZE_TIME,
        IMAGE_BUTTON_FLAT_TIRE_NA,
        IMAGE_BUTTON_GAS_NA,
        IMAGE_BUTTON_GREEN_LIGHT_NA,
        IMAGE_BUTTON_RED_LIGHT_NA,
        IMAGE_BUTTON_CLOSE_ROAD_NA,
        IMAGE_BUTTON_OPEN_INTERSECTION_NA,
        IMAGE_BUTTON_CLOSE_INTERSECTION_NA,
        IMAGE_BUTTON_INCREASE_PLAYER_SPEED_NA,
        IMAGE_BUTTON_INCREASE_SPEED_NA,
        IMAGE_BUTTON_DECREASE_SPEED_NA,
        IMAGE_BUTTON_MIND_CONTROL_NA,
        IMAGE_BUTTON_INVINCIBLE_NA,
        IMAGE_BUTTON_STEAL_NA,
        IMAGE_BUTTON_FLY_NA,
        IMAGE_BUTTON_INTANGABILITY_NA,
        IMAGE_BUTTON_FREEZE_TIME_NA,
        
        /* GAME TEXT */
        TEXT_ERROR_LOADING_AUDIO,
        TEXT_ERROR_LOADING_LEVEL,
        TEXT_ERROR_LOADING_RECORD,
        TEXT_ERROR_LOADING_XML_FILE,
        TEXT_ERROR_SAVING_RECORD,
        TEXT_PROMPT_EXIT,
        TEXT_TITLE_BAR_GAME,
        TEXT_TITLE_BAR_ERROR,
        TEXT_HELP_TITLE_BAR,
        TEXT_HELP_DIALOG,
        
        /* AUDIO CUES */
        TITLE_SONG,
        GAME_SONG,
        AUDIO_CUE_POLICE_SIREN,
        AUDIO_CUE_CAR_START,
        AUDIO_CUE_CAR_WONT_START,
        AUDIO_CUE_BULLET_RICOCHET,
        AUDIO_CUE_CAR_CRASH,  
    }  
}
