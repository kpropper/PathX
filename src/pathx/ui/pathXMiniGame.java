package pathx.ui;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JViewport;
import pathx.data.pathXDataModel;
import mini_game.MiniGame;
import mini_game.MiniGameState;
import static pathx.pathXConstants.*;
import mini_game.Sprite;
import mini_game.SpriteType;
import mini_game.Viewport;
import properties_manager.PropertiesManager;
import pathx.pathXConstants;
import pathx.PathX.pathXPropertyType;
//import pathx.file.pathXFileManager;
//import pathx.data.pathXRecord;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.io.File;
import pathx.file.pathXFileIO;
import pathx.level.model.pathXLevelModel;

/**
 *
 * @author Karl Propper
 */
public class pathXMiniGame extends MiniGame{
    
    // THE PLAYER RECORD FOR EACH LEVEL, WHICH LIVES BEYOND ONE SESSION
    //    private pathXRecord record;

    // HANDLES GAME UI EVENTS
    private pathXEventHandler eventHandler;

    
    // HANDLES ERROR CONDITIONS
    private pathXErrorHandler errorHandler;
    
    // MANAGES LOADING OF LEVELS AND THE PLAYER RECORDS FILES
//    private pathXFileManager fileManager;
    
    // THE SCREEN CURRENTLY BEING PLAYED
    private String currentScreenState;

    // ACCESSOR METHODS
        // - getPlayerRecord
        // - getErrorHandler
        // - getFileManager
        // - isCurrentScreenState
    
    /**
     * Accessor method for getting the player record object, which
     * summarizes the player's record on all levels.
     * 
     * @return The player's complete record.
     *
    public pathXRecord getPlayerRecord() 
    { 
        return record; 
    }*/

    /**
     * Accessor method for getting the application's error handler.
     * 
     * @return The error handler.
     */
    public pathXErrorHandler getErrorHandler()
    {
        return errorHandler;
    }

    /**
     * Accessor method for getting the app's file manager.
     * 
     * @return The file manager.
     
    public SortingHatFileManager getFileManager()
    {
        return fileManager;
    }*/

    /**
     * Used for testing to see if the current screen state matches
     * the testScreenState argument. If it mates, true is returned,
     * else false.
     * 
     * @param testScreenState Screen state to test against the 
     * current state.
     * 
     * @return true if the current state is testScreenState, false otherwise.
     */
    public boolean isCurrentScreenState(String testScreenState)
    {
        return testScreenState.equals(currentScreenState);
    }
    
    
    // VIEWPORT UPDATE METHODS
        // - initViewport
        // - scroll
        // - moveViewport
    
    public void initMapViewport()
    {
        data.getViewport().setViewportSize(640, 380);
        data.getViewport().setGameWorldSize(1812, 1000);
    }
    
    public void moveViewport(int x, int y)
    {
        Viewport viewport = data.getViewport();
        if((viewport.getViewportX() + x >= 0 && viewport.getViewportX() + x <= (viewport.getGameWorldWidth()- WINDOW_WIDTH))
                && (viewport.getViewportY() + y >= 0 && viewport.getViewportY() + y <= (viewport.getGameWorldHeight()-380)))
        {
            viewport.scroll(x, y);
            
            Sprite temp = guiButtons.get(DELETEME_BUTTON_TYPE);
            temp.setX(temp.getX()- x);
            temp.setY(temp.getY() - y);
            if(temp.getY() < 100)
            {
                temp.setState("INVISIBLE_STATE");
                temp.setEnabled(false);
            }
            
            if(temp.getY() > 100)
            {
                temp.setState("VISIBLE_STATE");
                temp.setEnabled(true);
            }
        }
    }
    


     // SERVICE METHODS
        // - displayStats
        // - savePlayerRecord
        // - switchToGameScreen
        // - switchToSplashScreen
        // - updateBoundaries
   
    /**
     * This method displays makes the stats dialog display visible,
     * which includes the text inside.
     */
//    public void displayStats()
 //   {
        // MAKE SURE ONLY THE PROPER DIALOG IS VISIBLE
//        guiDialogs.get(WIN_DIALOG_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
//        guiDialogs.get(STATS_DIALOG_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
 //   }
    
    
    public void closeLevelInfoDialog()
    {
        guiDialogs.get(LEVEL_INFO_DIALOG_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(LEVEL_INFO_CLOSE_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(LEVEL_INFO_CLOSE_BUTTON_TYPE).setEnabled(false);
    }
    
    /**
     * This method forces the file manager to save the current player record.
     
    public void savePlayerRecord()
    {
        fileManager.saveRecord(record);
    }*/
    
    
    /**
     * This method switches the application to the menu screen, making
     * all the appropriate UI controls visible & invisible.
     */    
    public void switchToSplashScreen()
    {
        // CHANGE THE BACKGROUND
        guiDecor.get(BACKGROUND_TYPE).setState(MENU_SCREEN_STATE);
        
        // DEACTIVATE BUTTONS        
        guiButtons.get(HOME_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(HOME_BUTTON_TYPE).setEnabled(false);        
        guiButtons.get(SOUND_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(SOUND_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(MUSIC_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(MUSIC_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(GAME_HOME_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(GAME_HOME_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(GAME_CLOSE_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(GAME_CLOSE_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(BACK_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(BACK_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(LEVEL_INFO_CLOSE_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(LEVEL_INFO_CLOSE_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(UP_ARROW_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(UP_ARROW_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(DOWN_ARROW_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(DOWN_ARROW_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(LEFT_ARROW_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(LEFT_ARROW_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(RIGHT_ARROW_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(RIGHT_ARROW_BUTTON_TYPE).setEnabled(false);
        guiDialogs.get(LEVEL_INFO_DIALOG_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(GAME_SPEED_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(GAME_SPEED_BUTTON_TYPE).setEnabled(false);
        guiDecor.get(MAP_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiDecor.get(MAP_TYPE).setEnabled(false);
        
        guiButtons.get(DELETEME_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(DELETEME_BUTTON_TYPE).setEnabled(false);
        
        
        //ACTIVATE THE SPLASH SCREEN CONTROLS
        guiButtons.get(PLAY_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(PLAY_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(RESET_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(RESET_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(SETTINGS_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(SETTINGS_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(HELP_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(HELP_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(CLOSE_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(CLOSE_BUTTON_TYPE).setEnabled(true);
        
        
        // AND CHANGE THE SCREEN STATE
        currentScreenState = MENU_SCREEN_STATE;
        
        //AND SET THE CURRENT SCREEN STATE
       
        
        // DEACTIVATE THE LEVEL SELECT BUTTONS
        PropertiesManager props = PropertiesManager.getPropertiesManager();
/*        

        // DEACTIVATE ALL DIALOGS
        guiDialogs.get(WIN_DIALOG_TYPE).setState(SortingHatTileState.INVISIBLE_STATE.toString());
        guiDialogs.get(STATS_DIALOG_TYPE).setState(SortingHatTileState.INVISIBLE_STATE.toString());

        // HIDE THE TILES
        ((SortingHatDataModel)data).enableTiles(false);

        // MAKE THE CURRENT SCREEN THE MENU SCREEN
        currentScreenState = MENU_SCREEN_STATE;
        
        // AND UPDATE THE DATA GAME STATE
        data.setGameState(MiniGameState.NOT_STARTED);
        
        // PLAY THE WELCOME SCREEN SONG
        audio.play(SortingHatPropertyType.SONG_CUE_MENU_SCREEN.toString(), true); 
        audio.stop(SortingHatPropertyType.SONG_CUE_GAME_SCREEN.toString());    */
    }
    
    public void switchToSettingsScreen()
    {
        // CHANGE THE BACKGROUND
        guiDecor.get(BACKGROUND_TYPE).setState(SETTINGS_SCREEN_STATE);
        
        //DEACTIVATE THE SPLASH SCREEN CONTROLS
        guiButtons.get(PLAY_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(PLAY_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(RESET_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(RESET_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(SETTINGS_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(SETTINGS_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(HELP_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(HELP_BUTTON_TYPE).setEnabled(false);
        
        //ACTIVATE THE HOME BUTTON
        guiButtons.get(HOME_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(HOME_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(SOUND_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(SOUND_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(MUSIC_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(MUSIC_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(GAME_SPEED_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(GAME_SPEED_BUTTON_TYPE).setEnabled(true);
        
        // AND CHANGE THE SCREEN STATE
        currentScreenState = SETTINGS_SCREEN_STATE;
        
    }
    
    public void switchToLevelSelectScreen()
    {
        // CHANGE THE BACKGROUND
        guiDecor.get(BACKGROUND_TYPE).setState(LEVEL_SELECT_SCREEN_STATE);
        
        //DEACTIVATE THE SPLASH SCREEN CONTROLS
        guiButtons.get(PLAY_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(PLAY_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(RESET_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(RESET_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(SETTINGS_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(SETTINGS_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(HELP_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(HELP_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(GAME_HOME_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(GAME_HOME_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(GAME_CLOSE_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(GAME_CLOSE_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(BACK_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(BACK_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(LEVEL_INFO_CLOSE_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(LEVEL_INFO_CLOSE_BUTTON_TYPE).setEnabled(false);
        guiDialogs.get(LEVEL_INFO_DIALOG_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        
        //ACTIVATE THE HOME BUTTON
        guiButtons.get(HOME_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(HOME_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(CLOSE_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(CLOSE_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(DELETEME_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(DELETEME_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(UP_ARROW_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(UP_ARROW_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(DOWN_ARROW_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(DOWN_ARROW_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(LEFT_ARROW_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(LEFT_ARROW_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(RIGHT_ARROW_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(RIGHT_ARROW_BUTTON_TYPE).setEnabled(true);
        guiDecor.get(MAP_TYPE).setEnabled(true);
        
        // AND CHANGE THE SCREEN STATE
        currentScreenState = LEVEL_SELECT_SCREEN_STATE;
        
        initMapViewport();

    }
    
        /**
     * This method switches the application to the game screen, making
     * all the appropriate UI controls visible & invisible.
     */
    public void switchToGameScreen()
    {
        
        File testFile = new File("C:\\Users\\karl.PROPPERENT\\Documents\\NetBeansProjects\\pathX\\pathXLevelEditor\\data\\levels\\Cali.bin");
       // levelIO.loadLevel(testFile, model);
        
        PropertiesManager props = PropertiesManager.getPropertiesManager();
     
        //DEACTIVATE LEVEL SELECT CONTROLS
        guiButtons.get(HOME_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(HOME_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(CLOSE_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(CLOSE_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(DELETEME_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(DELETEME_BUTTON_TYPE).setEnabled(false);
        guiDecor.get(MAP_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(UP_ARROW_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(UP_ARROW_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(DOWN_ARROW_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(DOWN_ARROW_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(LEFT_ARROW_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(LEFT_ARROW_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(RIGHT_ARROW_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(RIGHT_ARROW_BUTTON_TYPE).setEnabled(false);
        guiDecor.get(MAP_TYPE).setEnabled(false);
        
        //ACTIVATE GAME SCREEN CONTROLS
        guiButtons.get(GAME_HOME_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(GAME_HOME_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(GAME_CLOSE_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(GAME_CLOSE_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(BACK_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(BACK_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(LEVEL_INFO_CLOSE_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(LEVEL_INFO_CLOSE_BUTTON_TYPE).setEnabled(true);
        guiDialogs.get(LEVEL_INFO_DIALOG_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        
        
        // CHANGE THE BACKGROUND
        guiDecor.get(BACKGROUND_TYPE).setState(GAME_SCREEN_STATE);

        // AND CHANGE THE SCREEN STATE
        currentScreenState = GAME_SCREEN_STATE;
        
        
        // PLAY THE GAMEPLAY SCREEN SONG
  //      audio.stop(path.SONG_CUE_MENU_SCREEN.toString()); 
//        audio.play(SortingHatPropertyType.SONG_CUE_GAME_SCREEN.toString(), true);        
    }
   
    // METHODS OVERRIDDEN FROM MiniGame
        // - initAudioContent
        // - initData
        // - initGUIControls
        // - initGUIHandlers
        // - reset
        // - updateGUI

    @Override
    /**
     * Initializes the sound and music to be used by the application.
     */
    public void initAudioContent()
    {
       /* try
        {
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            String audioPath = props.getProperty(SortingHatPropertyType.PATH_AUDIO);

            // LOAD ALL THE AUDIO
            loadAudioCue(SortingHatPropertyType.AUDIO_CUE_SELECT_TILE);
            loadAudioCue(SortingHatPropertyType.AUDIO_CUE_DESELECT_TILE);
            loadAudioCue(SortingHatPropertyType.AUDIO_CUE_GOOD_MOVE);
            loadAudioCue(SortingHatPropertyType.AUDIO_CUE_BAD_MOVE);
            loadAudioCue(SortingHatPropertyType.AUDIO_CUE_CHEAT);
            loadAudioCue(SortingHatPropertyType.AUDIO_CUE_UNDO);
            loadAudioCue(SortingHatPropertyType.AUDIO_CUE_WIN);
            loadAudioCue(SortingHatPropertyType.SONG_CUE_MENU_SCREEN);
            loadAudioCue(SortingHatPropertyType.SONG_CUE_GAME_SCREEN);

            // PLAY THE WELCOME SCREEN SONG
            audio.play(SortingHatPropertyType.SONG_CUE_MENU_SCREEN.toString(), true);
        }
        catch(UnsupportedAudioFileException | IOException | LineUnavailableException | InvalidMidiDataException | MidiUnavailableException e)
        {
            errorHandler.processError(SortingHatPropertyType.TEXT_ERROR_LOADING_AUDIO);
        }*/        
    }

    /**
     * This helper method loads the audio file associated with audioCueType,
     * which should have been specified via an XML properties file.
     
    private void loadAudioCue(SortingHatPropertyType audioCueType) 
            throws  UnsupportedAudioFileException, IOException, LineUnavailableException, 
                    InvalidMidiDataException, MidiUnavailableException
    {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String audioPath = props.getProperty(SortingHatPropertyType.PATH_AUDIO);
        String cue = props.getProperty(audioCueType.toString());
        audio.loadAudio(audioCueType.toString(), audioPath + cue);        
    }*/
    
    /**
     * Initializes the game data used by the application. Note
     * that it is this method's obligation to construct and set
     * this Game's custom GameDataModel object as well as any
     * other needed game objects.
     */
    @Override
    public void initData()
    {        
        // INIT OUR ERROR HANDLER
        errorHandler = new pathXErrorHandler(window);
        
        // INIT OUR FILE MANAGER
//        fileManager = new SortingHatFileManager(this);

        // LOAD THE PLAYER'S RECORD FROM A FILE
//        record = fileManager.loadRecord();
        
        // INIT OUR DATA MANAGER
        data = new pathXDataModel(this);
    }
    
    /**
     * Initializes the game controls, like buttons, used by
     * the game application. Note that this includes the tiles,
     * which serve as buttons of sorts.
     */
    @Override
    public void initGUIControls()
    {
        // WE'LL USE AND REUSE THESE FOR LOADING STUFF
        BufferedImage img;
        float x, y;
        SpriteType sT;
        Sprite s;
 
        // FIRST PUT THE ICON IN THE WINDOW
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String imgPath = props.getProperty(pathXPropertyType.PATH_IMG);        
        String windowIconFile = props.getProperty(pathXPropertyType.IMAGE_WINDOW_ICON);
        img = loadImage(imgPath + windowIconFile);
        window.setIconImage(img);

        // CONSTRUCT THE PANEL WHERE WE'LL DRAW EVERYTHING
        canvas = new pathXPanel(this, (pathXDataModel)data);
        
        // LOAD THE BACKGROUNDS, WHICH ARE GUI DECOR
        currentScreenState = MENU_SCREEN_STATE;
        img = loadImage(imgPath + props.getProperty(pathXPropertyType.IMAGE_BACKGROUND_MENU));
        sT = new SpriteType(BACKGROUND_TYPE);
        sT.addState(MENU_SCREEN_STATE, img);
        img = loadImage(imgPath + props.getProperty(pathXPropertyType.IMAGE_BACKGROUND_SETTINGS));
        sT.addState(SETTINGS_SCREEN_STATE,img);
        img = loadImage(imgPath + props.getProperty(pathXPropertyType.IMAGE_BACKGROUND_LEVEL_SELECT));
        sT.addState(LEVEL_SELECT_SCREEN_STATE, img);
        img = loadImage(imgPath + props.getProperty(pathXPropertyType.IMAGE_BACKGROUND_GAME));
        sT.addState(GAME_SCREEN_STATE, img);
        s = new Sprite(sT, 0, 0, 0, 0, MENU_SCREEN_STATE);
        guiDecor.put(BACKGROUND_TYPE, s);
        
        //LOAD THE MAPS, WHICH ARE GUI DECOR
        img = loadImage(imgPath + props.getProperty(pathXPropertyType.IMAGE_MAP));
        sT = new SpriteType(MAP_TYPE);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        s = new Sprite(sT, 0, 100, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiDecor.put(MAP_TYPE, s);
        guiDecor.get(MAP_TYPE).setEnabled(false);
        

        //ADD THE BUTTONS TO THE MENU SCREEN
        
        //THE PLAY BUTTON
        String playButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_PLAY);
        sT = new SpriteType(PLAY_BUTTON_TYPE);
	img = loadImage(imgPath + playButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        String playMouseOverButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_PLAY_MOUSE_OVER);
        img = loadImage(imgPath + playMouseOverButton);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, PLAY_BUTTON_X, PLAY_BUTTON_Y, 0, 0, pathXTileState.VISIBLE_STATE.toString());
        guiButtons.put(PLAY_BUTTON_TYPE, s);
        
        //AND THE RESET BUTTON
        String resetButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_RESET);
        sT = new SpriteType(RESET_BUTTON_TYPE);
	img = loadImage(imgPath + resetButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        String newMouseOverButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_RESET_MOUSE_OVER);
        img = loadImage(imgPath + newMouseOverButton);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, RESET_BUTTON_X, RESET_BUTTON_Y, 0, 0, pathXTileState.VISIBLE_STATE.toString());
        guiButtons.put(RESET_BUTTON_TYPE, s);
        
        //AND THE SETTINGS BUTTON
        String settingsButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_SETTINGS);
        sT = new SpriteType(SETTINGS_BUTTON_TYPE);
	img = loadImage(imgPath + settingsButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        String settingsMouseOverButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_SETTINGS_MOUSE_OVER);
        img = loadImage(imgPath + settingsMouseOverButton);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, SETTINGS_BUTTON_X, SETTINGS_BUTTON_Y, 0, 0, pathXTileState.VISIBLE_STATE.toString());
        guiButtons.put(SETTINGS_BUTTON_TYPE, s);
        
        //AND THE HELP BUTTON
        String helpButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_HELP);
        sT = new SpriteType(HELP_BUTTON_TYPE);
	img = loadImage(imgPath + helpButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        String helpMouseOverButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_HELP_MOUSE_OVER);
        img = loadImage(imgPath + helpMouseOverButton);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, HELP_BUTTON_X, HELP_BUTTON_Y, 0, 0, pathXTileState.VISIBLE_STATE.toString());
        guiButtons.put(HELP_BUTTON_TYPE, s);
        
        //AND THE CLOSE BUTTON
        String closeButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_CLOSE);
        sT = new SpriteType(CLOSE_BUTTON_TYPE);
	img = loadImage(imgPath + closeButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        String closeMouseOverButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_CLOSE_MOUSE_OVER);
        img = loadImage(imgPath + closeMouseOverButton);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, CLOSE_BUTTON_X, CLOSE_BUTTON_Y, 0, 0, pathXTileState.VISIBLE_STATE.toString());
        guiButtons.put(CLOSE_BUTTON_TYPE, s);
        
        //HOME BUTTON FOR MULTIPULE SCREENS
        String homeButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_HOME);
        sT = new SpriteType(HOME_BUTTON_TYPE);
	img = loadImage(imgPath + homeButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        String homeMouseOverButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_HOME_MOUSE_OVER);
        img = loadImage(imgPath + homeMouseOverButton);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, HOME_BUTTON_X, HOME_BUTTON_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(HOME_BUTTON_TYPE, s);
        guiButtons.get(HOME_BUTTON_TYPE).setEnabled(false);
        
        //SETTINGS BUTTONS
        
        //ADD THE SOUND BUTTON
        String soundButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_SOUND);
        sT = new SpriteType(SOUND_BUTTON_TYPE);
	img = loadImage(imgPath + soundButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        String soundMouseOverButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_SOUND_MOUSE_OVER);
        img = loadImage(imgPath + soundMouseOverButton);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        String soundSelectedButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_SOUND_SELECTED);
        img = loadImage(imgPath + soundSelectedButton);
        sT.addState(pathXTileState.SELECTED_STATE.toString(), img);
        s = new Sprite(sT, SOUND_BUTTON_X, SOUND_BUTTON_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(SOUND_BUTTON_TYPE, s);
        guiButtons.get(SOUND_BUTTON_TYPE).setEnabled(false);
        
        //AND THE MUSIC BUTTON
        String musicButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_MUSIC);
        sT = new SpriteType(MUSIC_BUTTON_TYPE);
	img = loadImage(imgPath + musicButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        String musicMouseOverButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_MUSIC_MOUSE_OVER);
        img = loadImage(imgPath + musicMouseOverButton);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        String musicSelectedButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_MUSIC_SELECTED);
        img = loadImage(imgPath + musicSelectedButton);
        sT.addState(pathXTileState.SELECTED_STATE.toString(), img);
        s = new Sprite(sT, MUSIC_BUTTON_X, MUSIC_BUTTON_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(MUSIC_BUTTON_TYPE, s);
        guiButtons.get(MUSIC_BUTTON_TYPE).setEnabled(false);
        
        String gameSpeedButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_GAME_SPEED);
        sT = new SpriteType(GAME_SPEED_BUTTON_TYPE);
	img = loadImage(imgPath + gameSpeedButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        String gameSpeedMouseOverButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_GAME_SPEED_MOUSE_OVER);
        img = loadImage(imgPath + gameSpeedMouseOverButton);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        sT.addState(pathXTileState.SELECTED_STATE.toString(), img);
        s = new Sprite(sT, GAME_SPEED_BUTTON_X,GAME__SPEED_BUTTON_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(GAME_SPEED_BUTTON_TYPE, s);
        guiButtons.get(GAME_SPEED_BUTTON_TYPE).setEnabled(false);
        
        
        // GAME SCREEN BUTTONS
        
        //CLOSE BUTTON FOR GAMESCREEN
        sT = new SpriteType(GAME_CLOSE_BUTTON_TYPE);
	img = loadImage(imgPath + closeButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        img = loadImage(imgPath + closeMouseOverButton);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, GAME_CLOSE_BUTTON_X, GAME_CLOSE_BUTTON_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(GAME_CLOSE_BUTTON_TYPE, s);
        guiButtons.get(GAME_CLOSE_BUTTON_TYPE).setEnabled(false);
        
        //HOME BUTTON FOR GAMESCREEN
        sT = new SpriteType(GAME_CLOSE_BUTTON_TYPE);
	img = loadImage(imgPath + homeButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        img = loadImage(imgPath + homeMouseOverButton);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, GAME_HOME_BUTTON_X, GAME_HOME_BUTTON_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(GAME_HOME_BUTTON_TYPE, s);
        guiButtons.get(GAME_HOME_BUTTON_TYPE).setEnabled(false);
        
        //BACK BUTTON FOR GAMESCREEN
        String backButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_BACK);
        sT = new SpriteType(BACK_BUTTON_TYPE);
	img = loadImage(imgPath + backButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        String backMouseOverButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_BACK_MOUSE_OVER);
        img = loadImage(imgPath + backMouseOverButton);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, BACK_BUTTON_X, BACK_BUTTON_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(BACK_BUTTON_TYPE, s);
        guiButtons.get(BACK_BUTTON_TYPE).setEnabled(false);
        
                //BACK BUTTON FOR GAMESCREEN
        String levelInfoCloseButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_LEVEL_INFO_CLOSE);
        sT = new SpriteType(BACK_BUTTON_TYPE);
	img = loadImage(imgPath + levelInfoCloseButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        String levelInfoCloseMouseOverButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_LEVEL_INFO_CLOSE_MOUSE_OVER);
        img = loadImage(imgPath + levelInfoCloseMouseOverButton);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, LEVEL_INFO_CLOSE_BUTTON_X, LEVEL_INFO_CLOSE_BUTTON_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(LEVEL_INFO_CLOSE_BUTTON_TYPE, s);
        guiButtons.get(LEVEL_INFO_CLOSE_BUTTON_TYPE).setEnabled(false);
        
        //MAP NAVIGATION BUTTONS
        //UP BUTTON
        String upButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_ARROW_UP);
        sT = new SpriteType(UP_ARROW_BUTTON_TYPE);
	img = loadImage(imgPath + upButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        String upMouseOverButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_ARROW_UP_MOUSE_OVER);
        img = loadImage(imgPath + upMouseOverButton);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, UP_BUTTON_X, UP_BUTTON_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(UP_ARROW_BUTTON_TYPE, s);
        guiButtons.get(UP_ARROW_BUTTON_TYPE).setEnabled(false);
        
        //DOWN BUTTON
        String downButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_ARROW_DOWN);
        sT = new SpriteType(DOWN_ARROW_BUTTON_TYPE);
	img = loadImage(imgPath + downButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        String downMouseOverButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_ARROW_DOWN_MOUSE_OVER);
        img = loadImage(imgPath + downMouseOverButton);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, DOWN_BUTTON_X, DOWN_BUTTON_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(DOWN_ARROW_BUTTON_TYPE, s);
        guiButtons.get(DOWN_ARROW_BUTTON_TYPE).setEnabled(false);
        
        //LEFT BUTTON
        String leftButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_ARROW_LEFT);
        sT = new SpriteType(LEFT_ARROW_BUTTON_TYPE);
	img = loadImage(imgPath + leftButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        String leftMouseOverButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_ARROW_LEFT_MOUSE_OVER);
        img = loadImage(imgPath + leftMouseOverButton);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, LEFT_BUTTON_X, LEFT_BUTTON_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(LEFT_ARROW_BUTTON_TYPE, s);
        guiButtons.get(LEFT_ARROW_BUTTON_TYPE).setEnabled(false);
        
        //RIGHT BUTTON
        String rightButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_ARROW_RIGHT);
        sT = new SpriteType(RIGHT_ARROW_BUTTON_TYPE);
	img = loadImage(imgPath + rightButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        String rightMouseOverButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_ARROW_RIGHT_MOUSE_OVER);
        img = loadImage(imgPath + rightMouseOverButton);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, RIGHT_BUTTON_X, RIGHT_BUTTON_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(RIGHT_ARROW_BUTTON_TYPE, s);
        guiButtons.get(RIGHT_ARROW_BUTTON_TYPE).setEnabled(false);
        
//////////////////////////////DELETE ME BUTTON/////////////////////////////////////////////////// 
        String deleteMeButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_DELETE_ME);
        sT = new SpriteType(DELETEME_BUTTON_TYPE);
	img = loadImage(imgPath + deleteMeButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, 200, 675, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(DELETEME_BUTTON_TYPE, s);
        guiButtons.get(DELETEME_BUTTON_TYPE).setEnabled(false);
        

        // NOW ADD THE DIALOGS
        
        // THE LEVEL INFORMATION DISPLAY
        String levelInfoDialog = props.getProperty(pathXPropertyType.IMAGE_DIALOG_LEVEL_INFO);
        sT = new SpriteType(LEVEL_INFO_DIALOG_TYPE);
        img = loadImageWithColorKey(imgPath + levelInfoDialog, COLOR_KEY);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
//        x = (viewport.getScreenWidth()/2) - (img.getWidth(null)/2);
//        y = (viewport.getScreenHeight()/2) - (img.getHeight(null)/2);
        s = new Sprite(sT, LEVEL_INFO_DIALOG_X, LEVEL_INFO_DIALOG_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiDialogs.put(LEVEL_INFO_DIALOG_TYPE, s);
/*        
        // AND THE WIN CONDITION DISPLAY
        String winDisplay = props.getProperty(SortingHatPropertyType.IMAGE_DIALOG_WIN);
        sT = new SpriteType(WIN_DIALOG_TYPE);
        img = loadImageWithColorKey(imgPath + winDisplay, COLOR_KEY);
        sT.addState(SortingHatTileState.VISIBLE_STATE.toString(), img);
        x = (viewport.getScreenWidth()/2) - (img.getWidth(null)/2);
        y = (viewport.getScreenHeight()/2) - (img.getHeight(null)/2);
        s = new Sprite(sT, x, y, 0, 0, SortingHatTileState.INVISIBLE_STATE.toString());
        guiDialogs.put(WIN_DIALOG_TYPE, s);
		    */
    }		
        
    /**
     * Initializes the game event handlers for things like
     * game gui buttons.
     */
    @Override
    public void initGUIHandlers()
    {
        // WE'LL RELAY UI EVENTS TO THIS OBJECT FOR HANDLING
        eventHandler = new pathXEventHandler(this);
                
        // WE'LL HAVE A CUSTOM RESPONSE FOR WHEN THE USER CLOSES THE WINDOW
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we) 
            { eventHandler.respondToExitRequest(); }
        });
/*
        // SEND ALL LEVEL SELECTION HANDLING OFF TO THE EVENT HANDLER
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        ArrayList<String> levels = props.getPropertyOptionsList(pathXPropertyType.LEVEL_OPTIONS);
        for (String levelFile : levels)
        {
            Sprite levelButton = guiButtons.get(levelFile);
            levelButton.setActionCommand(PATH_DATA + levelFile);
            levelButton.setActionListener(new ActionListener(){
                Sprite s;
                public ActionListener init(Sprite initS) 
                {   s = initS; 
                    return this;    }
                public void actionPerformed(ActionEvent ae)
                {   eventHandler.respondToSelectLevelRequest(s.getActionCommand());    }
            }.init(levelButton));
        }  
*/
        //CLOSE EVENT HANDLER
        guiButtons.get(CLOSE_BUTTON_TYPE).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae)
                {   eventHandler.respondToCloseRequest();  }
        });
        
        // PLAY EVENT HANDLER
        guiButtons.get(PLAY_BUTTON_TYPE).setActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {   eventHandler.respondToPlayRequest();     }
        });
        
        // SETTINGS EVENT HANDLER
        guiButtons.get(SETTINGS_BUTTON_TYPE).setActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {   eventHandler.respondToSettingsRequest();     }
        });
        
        // HELP EVENT HANDLER
        guiButtons.get(HELP_BUTTON_TYPE).setActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {   eventHandler.respondToHelpRequest();     }
        });
        
        // HOME EVENT HANDLER
        guiButtons.get(HOME_BUTTON_TYPE).setActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {   eventHandler.respondToHomeRequest();     }
        });
        
        // SOUND EVENT HANDLER
         guiButtons.get(SOUND_BUTTON_TYPE).setActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {   eventHandler.respondToSoundRequest();     }
        });       
        
        // MUSIC EVENT HANDLER
                 guiButtons.get(MUSIC_BUTTON_TYPE).setActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {   eventHandler.respondToMusicRequest();     }
        });
                 
        // GAME HOME EVENT HANDLER
        guiButtons.get(GAME_HOME_BUTTON_TYPE).setActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {   eventHandler.respondToHomeRequest();     }
        });
        
        //CLOSE EVENT HANDLER
        guiButtons.get(GAME_CLOSE_BUTTON_TYPE).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae)
                {   eventHandler.respondToCloseRequest();  }
        });
        
        //CLOSE EVENT HANDLER
        guiButtons.get(BACK_BUTTON_TYPE).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae)
                {   eventHandler.respondToBackRequest();  }
        });
        
        //LEVEL INFO CLOSE EVENT HANDLER
        guiButtons.get(LEVEL_INFO_CLOSE_BUTTON_TYPE).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae)
                {   eventHandler.respondToLevelInfoCloseRequest();  }
        });
        
        //UP ARROW PRESS EVENT HANDLER
        guiButtons.get(UP_ARROW_BUTTON_TYPE).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae)
                {   eventHandler.respondToUpRequest();  }
        });
        
        //DOWN ARROW PRESS EVENT HANDLER
        guiButtons.get(DOWN_ARROW_BUTTON_TYPE).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae)
                {   eventHandler.respondToDownRequest();  }
        });
        
        //LEFT ARROW PRESS EVENT HANDLER
        guiButtons.get(LEFT_ARROW_BUTTON_TYPE).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae)
                {   eventHandler.respondToLeftRequest();  }
        });
        
        //RIGHT ARROW PRESS EVENT HANDLER
        guiButtons.get(RIGHT_ARROW_BUTTON_TYPE).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae)
                {   eventHandler.respondToRightRequest();  }
        });
        
        
        ////////////////////////////////////////////////////DELETE ME???????????????//////////////////////////////////
            guiButtons.get(DELETEME_BUTTON_TYPE).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae)
                {   eventHandler.respondToGameRequest();  }
        });

        // KEY LISTENER - LET'S US PROVIDE CUSTOM RESPONSES
        this.setKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent ke)
            {   
                eventHandler.respondToKeyPress(ke.getKeyCode());    
            }
        });
    }
    
    /**
     * Invoked when a new game is started, it resets all relevant
     * game data and gui control states. 
     */
    @Override
    public void reset()
    {
        data.reset(this);
    }
    
    
    /**
     * Updates the state of all gui controls according to the 
     * current game conditions.
     */
    @Override
    public void updateGUI()
    {
        // GO THROUGH THE VISIBLE BUTTONS TO TRIGGER MOUSE OVERS
        Iterator<Sprite> buttonsIt = guiButtons.values().iterator();
        Iterator<Sprite> tiles = guiDecor.values().iterator();
        while (buttonsIt.hasNext())
        {
            Sprite button = buttonsIt.next();
            
            // ARE WE ENTERING A BUTTON?
            if (button.getState().equals(pathXTileState.VISIBLE_STATE.toString()))
            {
                if (button.containsPoint(data.getLastMouseX(), data.getLastMouseY()))
                {
                    button.setState(pathXTileState.MOUSE_OVER_STATE.toString());
                }
            }
            // ARE WE EXITING A BUTTON?
            else if (button.getState().equals(pathXTileState.MOUSE_OVER_STATE.toString()))
            {
                 if (!button.containsPoint(data.getLastMouseX(), data.getLastMouseY()))
                {
                    button.setState(pathXTileState.VISIBLE_STATE.toString());
                }
            }
        }
    }    
}
