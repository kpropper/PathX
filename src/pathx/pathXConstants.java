package pathx;

import java.awt.Color;
import java.awt.Font;


/**
 *This class stores all the constants used by the pathX application.
 * *
 * @author Karl Propper
 */
public class pathXConstants {
    
    // WE NEED THESE CONSTANTS JUST TO GET STARTED
    // LOADING SETTINGS FROM OUR XML FILES
    public static String PROPERTY_TYPES_LIST = "property_types.txt";
    public static String PROPERTIES_FILE_NAME = "properties.xml";
    public static String PROPERTIES_SCHEMA_FILE_NAME = "properties_schema.xsd";    
    public static String PATH_DATA = "./data/";
    
    // THESE ARE THE TYPES OF CONTROLS, WE USE THESE CONSTANTS BECAUSE WE'LL
    // STORE THEM BY TYPE, SO THESE WILL PROVIDE A MEANS OF IDENTIFYING THEM
    
    // EACH SCREEN HAS ITS OWN BACKGROUND TYPE
    public static final String BACKGROUND_TYPE = "BACKGROUND_TYPE";
    public static final String MAP_TYPE = "MAP_TYPE";
    
    // THIS REPRESENTS THE BUTTONS ON THE MENU SCREEN
    public static final String LEVEL_SELECT_BUTTON_TYPE = "LEVEL_SELECT_BUTTON_TYPE";
    public static final String PLAY_BUTTON_TYPE = "PLAY_BUTTON_TYPE";
    public static final String RESET_BUTTON_TYPE = "RESET_BUTTON_TYPE";
    public static final String SETTINGS_BUTTON_TYPE = "SETTINGS_BUTTON_TYPE";
    public static final String HELP_BUTTON_TYPE = "HELP_BUTTON_TYPE";
    public static final String CLOSE_BUTTON_TYPE = "CLOSE_BUTTON_TYPE";
    public static final String HOME_BUTTON_TYPE = "HOME_BUTTTON_TYPE";
    
    // SETTINGS BUTTON TYPES
    public static final String SOUND_BUTTON_TYPE = "SOUND_BUTTON_TYPE";
    public static final String MUSIC_BUTTON_TYPE = "MUSIC_BUTTON_TYPE";
    public static final String GAME_SPEED_BUTTON_TYPE = "GAME_SPEED_BUTTON_TYPE";

    // IN-GAME UI CONTROL TYPES 
    public static final String GAME_CLOSE_BUTTON_TYPE = "GAME_CLOSE_BUTTON_TYPE";
    public static final String GAME_HOME_BUTTON_TYPE = "GAME_HOME_BUTTTON_TYPE";
    public static final String NEW_GAME_BUTTON_TYPE = "NEW_GAME_BUTTON_TYPE";
    public static final String BACK_BUTTON_TYPE = "BACK_BUTTON_TYPE";
    public static final String LEVEL_INFO_CLOSE_BUTTON_TYPE = "LEVEL_INFO_CLOSE_BUTTON_TYPE";
    
    //MAP NAVIGATION BUTTONS
    public static final String UP_ARROW_BUTTON_TYPE = "UP_ARROW_BUTTON_TYPE";
    public static final String DOWN_ARROW_BUTTON_TYPE = "DOWN_ARROW_BUTTON_TYPE";
    public static final String LEFT_ARROW_BUTTON_TYPE = "LEFT_ARROW_BUTTON_TYPE";
    public static final String RIGHT_ARROW_BUTTON_TYPE = "RIGHT_ARROW_BUTTON_TYPE";
    
    
    
        public static final String DELETEME_BUTTON_TYPE = "DELETEME_BUTTON_TYPE";////////////////////////////////////DELETE ME

    // DIALOG TYPES
    public static final String LEVEL_INFO_DIALOG_TYPE = "LEVEL_INFO_DIALOG_TYPE";
    public static final String WIN_DIALOG_TYPE = "WIN_DIALOG_TYPE";
    public static final String STATS_DIALOG_TYPE = "STATS_DIALOG_TYPE";
    
    // WE'LL USE THESE STATES TO CONTROL SWITCHING BETWEEN THE FOUR SCREEN STATES
    public static final String MENU_SCREEN_STATE = "MENU_SCREEN_STATE";
    public static final String LEVEL_SELECT_SCREEN_STATE = "LEVEL_SELECT_SCREEN_STATE";
    public static final String SETTINGS_SCREEN_STATE = "SETTINGS_SCREEN_STATE";
    public static final String GAME_SCREEN_STATE = "GAME_SCREEN_STATE";    

    // ANIMATION SPEED
    public static final int FPS = 30;
    
    
    // UI CONTROL SIZE AND POSITION SETTINGS
    public static final int WINDOW_WIDTH = 640;
    public static final int WINDOW_HEIGHT = 480;
    
    //FOR MAP CONTROL
    public static final int MAP_WIDTH = 640;
    public static final int MAP_HEIGHT = 380;
//    public static final int VIEWPORT_MARGIN_LEFT = 20;
//    public static final int VIEWPORT_MARGIN_RIGHT = 20;
//    public static final int VIEWPORT_MARGIN_TOP = 50;
//    public static final int VIEWPORT_MARGIN_BOTTOM = 20;
//    public static final int VIEWPORT_INC = 5;
    
    // FOR TILE RENDERING
    public static final int NUM_TILES = 30;
    public static final int TILE_WIDTH = 135;
    public static final int TILE_HEIGHT = 126;
    public static final int TILE_IMAGE_OFFSET_X = 45;
    public static final int TILE_IMAGE_OFFSET_Y = 30;
    public static final String TILE_SPRITE_TYPE_PREFIX = "TILE_";
    public static final int COLOR_INC = 10;
    
    // FOR MOVING TILES AROUND
    public static final int MAX_TILE_VELOCITY = 20;
    
    // UI CONTROLS POSITIONS FOR THE MENU SCREEN
    public static final int CLOSE_BUTTON_X = 580;
    public static final int CLOSE_BUTTON_Y =0;
    public static final int HOME_BUTTON_X = CLOSE_BUTTON_X -50;
    public static final int HOME_BUTTON_Y = CLOSE_BUTTON_Y;
    public static final int PLAY_BUTTON_X = 70;
    public static final int PLAY_BUTTON_Y = 380;
    public static final int RESET_BUTTON_X = PLAY_BUTTON_X + 120;
    public static final int RESET_BUTTON_Y = PLAY_BUTTON_Y;
    public static final int SETTINGS_BUTTON_X = RESET_BUTTON_X +120;
    public static final int SETTINGS_BUTTON_Y = PLAY_BUTTON_Y;
    public static final int HELP_BUTTON_X = SETTINGS_BUTTON_X + 120;
    public static final int HELP_BUTTON_Y = PLAY_BUTTON_Y;
    
    // UI CONTROLS POSITIONS FOR THE SETTINGS SCREEN
    public static final int SOUND_BUTTON_X = 200;
    public static final int SOUND_BUTTON_Y = 210;
    public static final int MUSIC_BUTTON_X = SOUND_BUTTON_X;
    public static final int MUSIC_BUTTON_Y = SOUND_BUTTON_Y -50;
    public static final int GAME_SPEED_BUTTON_X = 300;
    public static final int GAME__SPEED_BUTTON_Y = 310;
    
    // UI CONTROLS FOR THE LEVEL SELECT SCREEN
    public static final int UP_BUTTON_X = 100;
    public static final int UP_BUTTON_Y = 300;
    public static final int DOWN_BUTTON_X = UP_BUTTON_X;
    public static final int DOWN_BUTTON_Y = UP_BUTTON_Y + 100;
    public static final int LEFT_BUTTON_X = UP_BUTTON_X - 50;
    public static final int LEFT_BUTTON_Y = UP_BUTTON_Y + 50;
    public static final int RIGHT_BUTTON_Y = LEFT_BUTTON_Y;
    public static final int RIGHT_BUTTON_X = LEFT_BUTTON_X + 100;
    
    // UI CONTROLS POSITIONS IN THE GAME SCREEN
    public static final int GAME_CLOSE_BUTTON_X = 70;
    public static final int GAME_CLOSE_BUTTON_Y = 90;
    public static final int GAME_HOME_BUTTON_X = GAME_CLOSE_BUTTON_X -50;
    public static final int GAME_HOME_BUTTON_Y = GAME_CLOSE_BUTTON_Y;
    public static final int BACK_BUTTON_X = GAME_HOME_BUTTON_X + 26;
    public static final int BACK_BUTTON_Y = GAME_HOME_BUTTON_Y + 50;
    
    
    //LEVEL INFO DIALOG COORDINATES
    public static final int LEVEL_INFO_DIALOG_X = 120;
    public static final int LEVEL_INFO_DIALOG_Y = 10;
    public static final int LEVEL_INFO_CLOSE_BUTTON_X = 252;
    public static final int LEVEL_INFO_CLOSE_BUTTON_Y = 300;
    
    
    // THESE ARE USED FOR FORMATTING THE TIME OF GAME
    public static final long MILLIS_IN_A_SECOND = 1000;
    public static final long MILLIS_IN_A_MINUTE = 1000 * 60;
    public static final long MILLIS_IN_AN_HOUR  = 1000 * 60 * 60;


    // COLORS USED FOR RENDERING VARIOUS THINGS, INCLUDING THE
    // COLOR KEY, WHICH REFERS TO THE COLOR TO IGNORE WHEN
    // LOADING ART.
    public static final Color COLOR_KEY = new Color(255, 174, 201);
    public static final Color COLOR_DEBUG_TEXT = Color.BLACK;
    public static final Color COLOR_TEXT_DISPLAY = new Color (10, 160, 10);
    public static final Color COLOR_STATS = new Color(0, 60, 0);
    public static final Color COLOR_ALGORITHM_HEADER = Color.WHITE;

    // FONTS USED DURING FOR TEXTUAL GAME DISPLAYS
    public static final Font FONT_TEXT_DISPLAY = new Font(Font.SANS_SERIF, Font.BOLD, 48);
    public static final Font FONT_DEBUG_TEXT = new Font(Font.MONOSPACED, Font.BOLD, 14);
    public static final Font FONT_STATS = new Font(Font.MONOSPACED, Font.BOLD, 20);
    public static final Font FONT_ALGORITHM_HEADER = new Font(Font.SERIF, Font.PLAIN, 25);
    
    
    //USED FOR LEVEL RENDERING
    public static final int INTERSECTION_RADIUS = 20;
    public static final int INT_STROKE = 3;
    public static final int ONE_WAY_TRIANGLE_HEIGHT = 40;
    public static final int ONE_WAY_TRIANGLE_WIDTH = 60;

    // INITIAL START/DEST LOCATIONS
    public static final int DEFAULT_START_X = 32;
    public static final int DEFAULT_START_Y = 100;
    public static final int DEFAULT_DEST_X = 650;
    public static final int DEFAULT_DEST_Y = 100;
    
    // FOR INITIALIZING THE SPINNERS
    public static final int MIN_BOTS_PER_LEVEL = 0;
    public static final int MAX_BOTS_PER_LEVEL = 50;
    public static final int BOTS_STEP = 1;
    public static final int MIN_MONEY = 100;
    public static final int MAX_MONEY = 10000;
    public static final int STEP_MONEY = 100;
    public static final int DEFAULT_MONEY = 100;
    
    // AND FOR THE ROAD SPEED LIMITS
    public static final int DEFAULT_SPEED_LIMIT = 30;
    public static final int MIN_SPEED_LIMIT = 10;
    public static final int MAX_SPEED_LIMIT = 100;
    public static final int SPEED_LIMIT_STEP = 10;
    
    // DEFAULT COLORS
    public static final Color   INT_OUTLINE_COLOR   = Color.BLACK;
    public static final Color   HIGHLIGHTED_COLOR = Color.YELLOW;
    public static final Color   OPEN_INT_COLOR      = Color.GREEN;
    public static final Color   CLOSED_INT_COLOR    = Color.RED;
    
    // FOR RENDERING STATS
    public static final Color STATS_TEXT_COLOR = Color.ORANGE;
    public static final Font STATS_TEXT_FONT = new Font("Monospace", Font.BOLD, 16);
    public static final String MOUSE_SCREEN_POSITION_TITLE = "Screen Mouse Position: ";
    public static final String MOUSE_LEVEL_POSITION_TITLE = "Level Mouse Position: ";
    public static final String VIEWPORT_POSITION_TITLE = "Viewport Position: ";
    
}