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
    public static String PATH_LEVELS = "./data/levels/";
    
    // GAME CONSTANTS (USED EDIT TO MAKE GAMEPLAY MORE "FAIR"
    public static final int MONEY_GOAL = 250000;
    
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
    public static final String GAME_UP_ARROW_BUTTON_TYPE = "GAME_UP_ARROW_BUTTON_TYPE";
    public static final String GAME_DOWN_ARROW_BUTTON_TYPE = "GAME_DOWN_ARROW_BUTTON_TYPE";
    public static final String GAME_LEFT_ARROW_BUTTON_TYPE = "GAME_LEFT_ARROW_BUTTON_TYPE";
    public static final String GAME_RIGHT_ARROW_BUTTON_TYPE = "GAME_RIGHT_ARROW_BUTTON_TYPE";
    public static final String PAUSE_BUTTON_TYPE = "PAUSE_BUTTON_TYPE";
    public static final String START_BUTTON_TYPE = "START_BUTTON_TYPE";
    
    //MAP HEADER
    public static final int PLAYER_MONEY_X = 328;
    public static final int PLAYER_MONEY_Y = 40;
    public static final int GOAL_X = 278;
    public static final int GOAL_Y = PLAYER_MONEY_Y + 35;
    public static final Color LEVEL_SELECT_COLOR = Color.BLACK;
    public static final Font LEVEL_SELECT_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 24);
    
    //MAP NAVIGATION BUTTONS
    public static final String UP_ARROW_BUTTON_TYPE = "UP_ARROW_BUTTON_TYPE";
    public static final String DOWN_ARROW_BUTTON_TYPE = "DOWN_ARROW_BUTTON_TYPE";
    public static final String LEFT_ARROW_BUTTON_TYPE = "LEFT_ARROW_BUTTON_TYPE";
    public static final String RIGHT_ARROW_BUTTON_TYPE = "RIGHT_ARROW_BUTTON_TYPE";
    
    
    // LEVEL BUTTONS
    public static final String LA_BUTTON_TYPE = "LA_BUTTON_TYPE";
    public static final String NY_BUTTON_TYPE = "NY_BUTTON_TYPE";
    public static final String CH_BUTTON_TYPE = "CH_BUTTON_TYPE";
    public static final String HOU_BUTTON_TYPE = "HOU_BUTTON_TYPE";
    public static final String SA_BUTTON_TYPE = "SA_BUTTON_TYPE";
    public static final String PHIL_BUTTON_TYPE = "PHIL_BUTTON_TYPE";
    public static final String PHE_BUTTON_TYPE = "PHE_BUTTON_TYPE";
    public static final String SD_BUTTON_TYPE = "SD_BUTTON_TYPE";
    public static final String DALL_BUTTON_TYPE = "DALL_BUTTON_TYPE";
    public static final String SJ_BUTTON_TYPE = "SJ_BUTTON_TYPE";
    public static final String AUS_BUTTON_TYPE = "AUS_BUTTON_TYPE";
    public static final String JAC_BUTTON_TYPE = "JAC_BUTTON_TYPE";
    public static final String IND_BUTTON_TYPE = "IND_BUTTON_TYPE";
    public static final String SF_BUTTON_TYPE = "SF_BUTTON_TYPE";
    public static final String CO_BUTTON_TYPE = "CO_BUTTON_TYPE";
    public static final String FW_BUTTON_TYPE = "FW_BUTTON_TYPE";
    public static final String CHAR_BUTTON_TYPE = "CHAR_BUTTON_TYPE";
    public static final String DET_BUTTON_TYPE = "DET_BUTTON_TYPE";
    public static final String EP_BUTTON_TYPE = "EP_BUTTON_TYPE";
    public static final String MEM_BUTTON_TYPE = "MEM_BUTTON_TYPE";
    

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
//    public static final int NUM_TILES = 30;
//    public static final int TILE_WIDTH = 135;
//    public static final int TILE_HEIGHT = 126;
//    public static final int TILE_IMAGE_OFFSET_X = 45;
//    public static final int TILE_IMAGE_OFFSET_Y = 30;
//    public static final String TILE_SPRITE_TYPE_PREFIX = "TILE_";
 //   public static final int COLOR_INC = 10;
    
    // FOR MOVING TILES AROUND
    public static final int MAX_TILE_VELOCITY = 40;
    
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
    public static final int MUSIC_BUTTON_X = 200;
    public static final int MUSIC_BUTTON_Y = 210;
    public static final int SOUND_BUTTON_X = MUSIC_BUTTON_X;
    public static final int SOUND_BUTTON_Y = MUSIC_BUTTON_Y -50;
    public static final int GAME_SPEED_BUTTON_X = 300;
    public static final int GAME__SPEED_BUTTON_Y = 310;
    public static final int GAME_SPEED_MIN_X = 134;
    public static final int GAME_SPEED_MAX_X = 484;
    public static final int GAME_SPEED_MIN_Y = 311;
    public static final int GAME_SPEED_MAX_Y = 347;
    public static final int GAME_SPEED_MIDDLE = 306;
    
    
    // UI CONTROLS FOR THE LEVEL SELECT SCREEN
    public static final int UP_BUTTON_X = 100;
    public static final int UP_BUTTON_Y = 300;
    public static final int DOWN_BUTTON_X = UP_BUTTON_X;
    public static final int DOWN_BUTTON_Y = UP_BUTTON_Y + 100;
    public static final int LEFT_BUTTON_X = UP_BUTTON_X - 50;
    public static final int LEFT_BUTTON_Y = UP_BUTTON_Y + 50;
    public static final int RIGHT_BUTTON_Y = LEFT_BUTTON_Y;
    public static final int RIGHT_BUTTON_X = LEFT_BUTTON_X + 100;
    public static final int LEVEL_VIEWPORT_INC = 10;
    public static final int LA_X = 190;
    public static final int LA_Y = 675;
    public static final int NY_X = 1560;
    public static final int NY_Y = 385;
    public static final int CH_X = 1120;
    public static final int CH_Y = 375;
    public static final int HOU_X = 885;
    public static final int HOU_Y = 830;
    public static final int SA_X = 795;
    public static final int SA_Y = 845;
    public static final int PHIL_X = 1525;
    public static final int PHIL_Y = 410;
    public static final int PHE_X = 405;
    public static final int PHE_Y = 730;
    public static final int SD_X = 245;
    public static final int SD_Y = 730;
    public static final int DALL_X = 850;
    public static final int DALL_Y = 725;
    public static final int SJ_X = 80;
    public static final int SJ_Y = 555;
    public static final int AUS_X = 820;
    public static final int AUS_Y = 810;
    public static final int JAC_X = 1318;
    public static final int JAC_Y = 800;
    public static final int IND_X = 1180;
    public static final int IND_Y = 450;
    public static final int SF_X = 72;
    public static final int SF_Y = 530;
    public static final int CO_X = 1290;
    public static final int CO_Y = 430;
    public static final int FW_X = 820;
    public static final int FW_Y = 726;
    public static final int CHAR_X = 1351;
    public static final int CHAR_Y = 616;
    public static final int DET_X = 1280;
    public static final int DET_Y = 310;
    public static final int EP_X = 560;
    public static final int EP_Y = 740;
    public static final int MEM_X = 1000;
    public static final int MEM_Y = 6000;
    
    // UI CONTROLS POSITIONS IN THE GAME SCREEN
    public static final String GAME_IMG_PATH = "./img/pathX/";
    public static final String PLAYER_TYPE = "PLAYER_TYPE";
    public static final String POLICE_TYPE = "POLICE_TYPE";
    public static final String ZOMBIE_TYPE = "ZOMBIE_TYPE";
    public static final String BANDIT_TYPE = "BANDIT_TYPE";
    public static final int    PLAYER_SPRITE_HEIGHT = 50;
    public static final int    PLAYER_SPRITE_WIDTH = 50;
    public static final int    POLICE_SPRITE_HEIGHT = PLAYER_SPRITE_HEIGHT;
    public static final int    POLICE_SPRITE_WIDTH = PLAYER_SPRITE_WIDTH;
    public static final int    BANDIT_SPRITE_HEIGHT = PLAYER_SPRITE_HEIGHT;
    public static final int    BANDIT_SPRITE_WIDTH = PLAYER_SPRITE_WIDTH;
    public static final int    ZOMBIE_SPRITE_HEIGHT = 50;
    public static final int    ZOMBIE_SPRITE_WIDTH = 29; 
    public static final String GREEN_LIGHT_BUTTON_TYPE = "GREEN_LIGHT_BUTTON_TYPE";
    public static final String RED_LIGHT_BUTTON_TYPE = "RED_LIGHT_BUTTON_TYPE";
    public static final String FLAT_TIRE_BUTTON_TYPE = "FLAT_TIRE_BUTTON_TYPE";
    public static final String GAS_BUTTON_TYPE = "GAS_BUTTON_TYPE";
    public static final String INCREASE_SPEED_BUTTON_TYPE = "INCREASE_SPEED_BUTTON_TYPE";
    public static final String DECREASE_SPEED_BUTTON_TYPE = "DECREASE_SPEED_BUTTON_TYPE";
    public static final String INCREASE_PLAYER_SPEED_BUTTON_TYPE = "INCREASE_PLAYER_SPEED_BUTTON_TYPE";
    public static final String CLOSE_ROAD_BUTTON_TYPE = "CLOSE_ROAD_BUTTON_TYPE";
    public static final String CLOSE_INTERSECTION_BUTTON_TYPE = "CLOSE_INTERSECTION_BUTTON_TYPE";
    public static final String OPEN_INTERSECTION_BUTTON_TYPE = "OPEN_INTERSECTION_BUTTON_TYPE";
    public static final String MIND_CONTROL_BUTTON_TYPE = "MIND_CONTROL_BUTTON_TYPE";
    public static final String FREEZE_TIME_BUTTON_TYPE = "FREEZE_TIME_BUTTON_TYPE";
    public static final String STEAL_BUTTON_TYPE = "STEAL_BUTTON_TYPE";
    public static final String INTANGABILITY_BUTTON_TYPE = "INTANGABILITY_BUTTON_TYPE";
    public static final String FLY_BUTTON_TYPE = "FLY_BUTTON_TYPE";
    public static final String INVINCIBLE_BUTTON_TYPE = "INVINCIBLE_BUTTON_TYPE";
    public static final int GAME_CLOSE_BUTTON_X = 70;
    public static final int GAME_CLOSE_BUTTON_Y = 80;
    public static final int GAME_HOME_BUTTON_X = GAME_CLOSE_BUTTON_X -50;
    public static final int GAME_HOME_BUTTON_Y = GAME_CLOSE_BUTTON_Y;
    public static final int BACK_BUTTON_X = GAME_HOME_BUTTON_X;
    public static final int BACK_BUTTON_Y = GAME_HOME_BUTTON_Y + 50;
    public static final int START_BUTTON_X = GAME_CLOSE_BUTTON_X;
    public static final int START_BUTTON_Y = BACK_BUTTON_Y;
    public static final int GAME_UP_BUTTON_X = 70;
    public static final int GAME_UP_BUTTON_Y = 330;
    public static final int GAME_DOWN_BUTTON_X = GAME_UP_BUTTON_X;
    public static final int GAME_DOWN_BUTTON_Y = GAME_UP_BUTTON_Y + 80;
    public static final int GAME_LEFT_BUTTON_X = GAME_UP_BUTTON_X - 40;
    public static final int GAME_LEFT_BUTTON_Y = GAME_UP_BUTTON_Y + 40;
    public static final int GAME_RIGHT_BUTTON_X = GAME_UP_BUTTON_X + 40;
    public static final int GAME_RIGHT_BUTTON_Y = GAME_LEFT_BUTTON_Y;
    public static final int GAME_PAUSE_BUTTON_X = GAME_UP_BUTTON_X;
    public static final int GAME_PAUSE_BUTTON_Y = GAME_UP_BUTTON_Y + 40;
    public static final int GREEN_LIGHT_BUTTON_X = 20;
    public static final int GREEN_LIGHT_BUTTON_Y = 187;
    public static final int RED_LIGHT_BUTTON_X = GREEN_LIGHT_BUTTON_X + 32;
    public static final int RED_LIGHT_BUTTON_Y = GREEN_LIGHT_BUTTON_Y;
    public static final int FLAT_TIRE_BUTTON_X = RED_LIGHT_BUTTON_X + 32;
    public static final int FLAT_TIRE_BUTTON_Y = GREEN_LIGHT_BUTTON_Y;
    public static final int GAS_BUTTON_X = FLAT_TIRE_BUTTON_X + 32;
    public static final int GAS_BUTTON_Y = GREEN_LIGHT_BUTTON_Y;
    public static final int INCREASE_SPEED_BUTTON_X = GREEN_LIGHT_BUTTON_X;
    public static final int INCREASE_SPEED_BUTTON_Y = GREEN_LIGHT_BUTTON_Y + 35;
    public static final int DECREASE_SPEED_BUTTON_X = RED_LIGHT_BUTTON_X;
    public static final int DECREASE_SPEED_BUTTON_Y = INCREASE_SPEED_BUTTON_Y;
    public static final int INCREASE_PLAYER_SPEED_BUTTON_X = FLAT_TIRE_BUTTON_X;
    public static final int INCREASE_PLAYER_SPEED_BUTTON_Y = INCREASE_SPEED_BUTTON_Y;
    public static final int CLOSE_ROAD_BUTTON_X = GAS_BUTTON_X;
    public static final int CLOSE_ROAD_BUTTON_Y = INCREASE_SPEED_BUTTON_Y;
    public static final int CLOSE_INTERSECTION_BUTTON_X = GREEN_LIGHT_BUTTON_X;
    public static final int CLOSE_INTERSECTION_BUTTON_Y = INCREASE_SPEED_BUTTON_Y + 35;
    public static final int OPEN_INTERSECTION_BUTTON_X = RED_LIGHT_BUTTON_X;
    public static final int OPEN_INTERSECTION_BUTTON_Y = CLOSE_INTERSECTION_BUTTON_Y;
    public static final int MIND_CONTROL_BUTTON_X = FLAT_TIRE_BUTTON_X;
    public static final int MIND_CONTROL_BUTTON_Y = CLOSE_INTERSECTION_BUTTON_Y;
    public static final int FREEZE_TIME_BUTTON_X = GAS_BUTTON_X;
    public static final int FREEZE_TIME_BUTTON_Y = CLOSE_INTERSECTION_BUTTON_Y;
    public static final int STEAL_BUTTON_X = GREEN_LIGHT_BUTTON_X;
    public static final int STEAL_BUTTON_Y = CLOSE_INTERSECTION_BUTTON_Y + 35;
    public static final int INTANGABILITY_BUTTON_X = RED_LIGHT_BUTTON_X;
    public static final int INTANGABILITY_BUTTON_Y = STEAL_BUTTON_Y;
    public static final int FLY_BUTTON_X = FLAT_TIRE_BUTTON_X;
    public static final int FLY_BUTTON_Y = STEAL_BUTTON_Y;
    public static final int INVINCIBLE_BUTTON_X = GAS_BUTTON_X;
    public static final int INVINCIBLE_BUTTON_Y = STEAL_BUTTON_Y;

    
    public static final int VIEWABLE_GAMEWORLD_OFFSET = 220;
    
    
    //LEVEL INFO DIALOG COORDINATES
    public static final int LEVEL_INFO_DIALOG_X = 120;
    public static final int LEVEL_INFO_DIALOG_Y = 10;
    public static final int LEVEL_INFO_CLOSE_BUTTON_X = 252;
    public static final int LEVEL_INFO_CLOSE_BUTTON_Y = 300;
    public static final String LEVEL_INFO_TEXT_1 = "Rob the Bank of ";
    public static final String LEVEL_INFO_TEXT_2 = " and make your getaway to earn ";
    public static final String PERIOD = ".";
    public static final Color LEVEL_INFO_COLOR = Color.BLACK;
    public static final Font LEVEL_INFO_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 24);
    public static final int LEVEL_INFO_TITLE_X = 175;
    public static final int LEVEL_INFO_TITLE_Y = 60;
    public static final int LEVEL_INFO_LINE1_X = LEVEL_INFO_TITLE_X;
    public static final int LEVEL_INFO_LINE1_Y = LEVEL_INFO_TITLE_Y + 75;
    public static final int LEVEL_INFO_LINE2_X = LEVEL_INFO_LINE1_X;
    public static final int LEVEL_INFO_LINE2_Y = LEVEL_INFO_LINE1_Y + 35;
    public static final int LEVEL_INFO_LINE3_X = LEVEL_INFO_LINE2_X;
    public static final int LEVEL_INFO_LINE3_Y = LEVEL_INFO_LINE2_Y + 35;
       
    
    
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
