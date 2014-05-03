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
        IMAGE_BUTTON_DELETE_ME,//////////////////////////////////////DELETE ME/////////////////////////////////////////////
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
        AUDIO_CUE_BAD_MOVE,
        AUDIO_CUE_CHEAT,
        AUDIO_CUE_DESELECT_TILE,
        AUDIO_CUE_GOOD_MOVE,
        AUDIO_CUE_SELECT_TILE,
        AUDIO_CUE_UNDO,
        AUDIO_CUE_WIN,
        SONG_CUE_GAME_SCREEN,
        SONG_CUE_MENU_SCREEN,    
    }  
}
