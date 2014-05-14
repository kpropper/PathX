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
import pathx.file.pathXLevelIO;
import pathx.file.pathXFileIO;
import pathx.level.model.pathXLevelViewport;
import pathx.level.model.pathXLevelModel;
import pathx.level.model.Player;
import pathx.level.model.Intersection;
import pathx.level.model.Police;
import pathx.level.model.Zombie;
import pathx.level.model.Bandit;

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
    
    // THIS IS THE VIEWPORT
    private Viewport viewport;
    
    //THIS IS THE LEVEL SELECTOR
    private pathXLevelSelector path = new pathXLevelSelector();
    
    // THIS IS TO HAVE MUSIC PLAY OR NOT
    private boolean musicPlaying = true;

    

//    pathXFileIO levelIO = new pathXFileIO();
//    pathXLevelModel model = new pathXLevelModel();
    
    
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
        viewport = data.getViewport();
        
        while(viewport.getViewportX() != 0) viewport.scroll(-1, 0);
        while(viewport.getViewportY() != 0) viewport.scroll(0, -1);
        viewport.setViewportSize(640, 380);
        viewport.setGameWorldSize(1812, 1000);
    }
    
    public void moveViewport(int x, int y)
    {
        viewport = data.getViewport();
        if(currentScreenState == LEVEL_SELECT_SCREEN_STATE)
        {
            if((viewport.getViewportX() + x >= 0 && viewport.getViewportX() + x <= (viewport.getGameWorldWidth()- WINDOW_WIDTH))
                && (viewport.getViewportY() + y >= 0 && viewport.getViewportY() + y <= (viewport.getGameWorldHeight()-380)))
            {
                viewport.scroll(x, y);    
                Sprite temp = guiButtons.get(LA_BUTTON_TYPE);
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
                
                temp = guiButtons.get(NY_BUTTON_TYPE);
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
                
                temp = guiButtons.get(CH_BUTTON_TYPE);
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
                
                temp = guiButtons.get(HOU_BUTTON_TYPE);
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
                
                temp = guiButtons.get(SA_BUTTON_TYPE);
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
                
                temp = guiButtons.get(PHIL_BUTTON_TYPE);
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
                
                temp = guiButtons.get(PHE_BUTTON_TYPE);
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
                
                temp = guiButtons.get(SD_BUTTON_TYPE);
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
 
                temp = guiButtons.get(DALL_BUTTON_TYPE);
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
                
                temp = guiButtons.get(SJ_BUTTON_TYPE);
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
                
                temp = guiButtons.get(AUS_BUTTON_TYPE);
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
                
                temp = guiButtons.get(JAC_BUTTON_TYPE);
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
                
                temp = guiButtons.get(IND_BUTTON_TYPE);
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
                
                temp = guiButtons.get(SF_BUTTON_TYPE);
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
                
                temp = guiButtons.get(CO_BUTTON_TYPE);
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
                
                temp = guiButtons.get(FW_BUTTON_TYPE);
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
                
                if(temp.getY() > 100)
                {
                    temp.setState("VISIBLE_STATE");
                    temp.setEnabled(true);
                }
                
                temp = guiButtons.get(CHAR_BUTTON_TYPE);
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
                
                temp = guiButtons.get(DET_BUTTON_TYPE);
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
                
                temp = guiButtons.get(EP_BUTTON_TYPE);
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
                
                temp = guiButtons.get(MEM_BUTTON_TYPE);
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
        else if(currentScreenState == GAME_SCREEN_STATE)
        {
            if((viewport.getViewportX() + x >= 0 && viewport.getViewportX() + x <= (viewport.getGameWorldWidth()- 440))
                && (viewport.getViewportY() + y >= 0 && viewport.getViewportY() + y <= (viewport.getGameWorldHeight()-480)))
            {
                viewport.scroll(x, y); 
                Player temp;
                    pathXDataModel dataModel = (pathXDataModel)data;
                    temp = dataModel.getPlayer();
                    temp.setX(temp.getX()- x);
                    temp.setY(temp.getY() - y);
                    temp.setTarget(temp.getTargetX() - x,temp.getTargetY()- y);
                //    Iterator playerPath = temp.getPathIterator();
               //     while(playerPath.hasNext())
                 //   {
                   //     Intersection pathNode = (Intersection)playerPath.next();
                     //   pathNode.setX(pathNode.getX() + x);
                       // pathNode.setX(pathNode.getY() + y);
                    //}
                    if(temp.getX() < 200)
                    {
                        temp.setState("INVISIBLE_STATE");
                        temp.setEnabled(false);
                    }   
            
                    if(temp.getX() > 200 && !guiButtons.get(LEVEL_INFO_CLOSE_BUTTON_TYPE).isEnabled())
                    {
                        temp.setState("VISIBLE_STATE");
                        temp.setEnabled(true);
                    }
                    
                    //UPDATE NPCS
                    //POLICE
                    Iterator<Police> police = ((pathXDataModel)data).getPolice();
                    Police cop;
                    while(police.hasNext())
                    {
                        cop = police.next();
                        cop.setPosition((int)cop.getX()-x,(int)cop.getY()-y);
                        cop.setTarget(cop.getTargetX() - x,cop.getTargetY()- y);
                        if(cop.getX() < 200)
                        {
                            cop.setState("INVISIBLE_STATE");
                            cop.setEnabled(false);
                        }   
            
                        if(temp.getX() > 200 && !guiButtons.get(LEVEL_INFO_CLOSE_BUTTON_TYPE).isEnabled())
                        {
                            cop.setState("VISIBLE_STATE");
                            cop.setEnabled(true);
                        }
                    }
                    //ZOMBIES
                    Iterator<Zombie> zombie = ((pathXDataModel)data).getZombie();
                    Zombie zom;
                    while(zombie.hasNext())
                    {
                        zom = zombie.next();
                        zom.setPosition((int)zom.getX()-x,(int)zom.getY()-y);
                        zom.setTarget(zom.getTargetX() - x,zom.getTargetY()- y);
                        if(zom.getX() < 200)
                        {
                            zom.setState("INVISIBLE_STATE");
                            zom.setEnabled(false);
                        }   
            
                        if(temp.getX() > 200 && !guiButtons.get(LEVEL_INFO_CLOSE_BUTTON_TYPE).isEnabled())
                        {
                            zom.setState("VISIBLE_STATE");
                            zom.setEnabled(true);
                        }
                    }
                    //BANDITS
                    Iterator<Bandit> bandits = ((pathXDataModel)data).getBandits();
                    Bandit bandit;
                    while(bandits.hasNext())
                    {
                        bandit = bandits.next();
                        bandit.setPosition((int)bandit.getX()-x,(int)bandit.getY()-y);
                        bandit.setTarget(bandit.getTargetX() - x,bandit.getTargetY()- y);
                        if(bandit.getX() < 200)
                        {
                            bandit.setState("INVISIBLE_STATE");
                            bandit.setEnabled(false);
                        }   
            
                        if(bandit.getX() > 200 && !guiButtons.get(LEVEL_INFO_CLOSE_BUTTON_TYPE).isEnabled())
                        {
                            bandit.setState("VISIBLE_STATE");
                            bandit.setEnabled(true);
                        }
                    }
           }
        }
    }
    
    public void startGameLevel()
    {
        if(!data.inProgress())
        {
            data.setGameState(MiniGameState.IN_PROGRESS);
            data.unpause();
            if(((pathXDataModel)data).soundEffectsOn())
            {
                audio.play(pathXPropertyType.AUDIO_CUE_CAR_START.toString(), true);
            }
        }
    }
    public void respondToPauseRequest()
    {
        if(!data.isPaused())
        {
            guiButtons.get(PAUSE_BUTTON_TYPE).setState(pathXTileState.SELECTED_STATE.toString());
            data.pause();
        }
        else
        {
            guiButtons.get(PAUSE_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
            data.unpause();
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
//        guiButtons.get(PLAYER_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
//        guiButtons.get(PLAYER_TYPE).setEnabled(true);
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
        data.setGameState(MiniGameState.NOT_STARTED);
        
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
        guiButtons.get(GAME_UP_ARROW_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(GAME_UP_ARROW_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(GAME_DOWN_ARROW_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(GAME_DOWN_ARROW_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(GAME_LEFT_ARROW_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(GAME_LEFT_ARROW_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(GAME_RIGHT_ARROW_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(GAME_RIGHT_ARROW_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(PAUSE_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(PAUSE_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(START_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(START_BUTTON_TYPE).setEnabled(false);
        
        // AND THE LEVEL BUTTONS
        guiButtons.get(LA_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(LA_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(NY_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(NY_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(HOU_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(HOU_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(SA_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(SA_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(PHIL_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(PHIL_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(PHE_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(PHE_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(SD_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(SD_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(DALL_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(DALL_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(SJ_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(SJ_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(AUS_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(AUS_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(JAC_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(JAC_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(IND_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(IND_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(SF_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(SF_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(CO_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(CO_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(FW_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(FW_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(CHAR_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(CHAR_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(DET_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(DET_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(EP_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(EP_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(MEM_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(MEM_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(CH_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(CH_BUTTON_TYPE).setEnabled(false);
        
        // DEACTIVATE SPECIALS BUTTONS
        guiButtons.get(GREEN_LIGHT_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(GREEN_LIGHT_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(RED_LIGHT_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(RED_LIGHT_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(FLAT_TIRE_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(FLAT_TIRE_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(GAS_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(GAS_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(INCREASE_SPEED_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(INCREASE_SPEED_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(DECREASE_SPEED_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(DECREASE_SPEED_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(INCREASE_PLAYER_SPEED_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(INCREASE_PLAYER_SPEED_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(CLOSE_ROAD_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(CLOSE_ROAD_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(CLOSE_INTERSECTION_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(CLOSE_INTERSECTION_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(OPEN_INTERSECTION_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(OPEN_INTERSECTION_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(MIND_CONTROL_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(MIND_CONTROL_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(FREEZE_TIME_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(FREEZE_TIME_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(STEAL_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(STEAL_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(INTANGABILITY_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(INTANGABILITY_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(FLY_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(FLY_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(INVINCIBLE_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(INVINCIBLE_BUTTON_TYPE).setEnabled(false);
        
        
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
        
        // CLEAR LEFTOVER GAME DATA
        ((pathXDataModel)data).clearGame();
        
        // PLAY THE TITLE SONG
        if(musicPlaying)
        {
            audio.stop(pathXPropertyType.GAME_SONG.toString());
            if(!audio.isPlaying(pathXPropertyType.TITLE_SONG.toString()))
            {
                audio.play(pathXPropertyType.TITLE_SONG.toString(), true);
            }
        }
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
        guiButtons.get(GAME_SPEED_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(GAME_SPEED_BUTTON_TYPE).setEnabled(true);
        
        //  WHAT IS THE CURRENT MUSIC STATE
        if(musicPlaying)
        {
            guiButtons.get(MUSIC_BUTTON_TYPE).setState(pathXTileState.SELECTED_STATE.toString());
            guiButtons.get(MUSIC_BUTTON_TYPE).setEnabled(true);
        }
        else
        {
            guiButtons.get(MUSIC_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
            guiButtons.get(MUSIC_BUTTON_TYPE).setEnabled(true);
        }

        // WHAT IS THE CURRENT SOUND EFFECT STATE
        if(((pathXDataModel)data).soundEffectsOn())
        {
            guiButtons.get(SOUND_BUTTON_TYPE).setState(pathXTileState.SELECTED_STATE.toString());
            guiButtons.get(SOUND_BUTTON_TYPE).setEnabled(true);
        }
        else
        {
            guiButtons.get(SOUND_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
            guiButtons.get(SOUND_BUTTON_TYPE).setEnabled(true);
        }
        
        // AND CHANGE THE SCREEN STATE
        currentScreenState = SETTINGS_SCREEN_STATE;
        
        
    }
    
    public void switchToLevelSelectScreen()
    {
        data.setGameState(MiniGameState.NOT_STARTED);
        
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
        guiButtons.get(GAME_UP_ARROW_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(GAME_UP_ARROW_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(GAME_DOWN_ARROW_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(GAME_DOWN_ARROW_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(GAME_LEFT_ARROW_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(GAME_LEFT_ARROW_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(GAME_RIGHT_ARROW_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(GAME_RIGHT_ARROW_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(PAUSE_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(PAUSE_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(START_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(START_BUTTON_TYPE).setEnabled(false);
        
        // DEACTIVATE SPECIALS BUTTONS
        guiButtons.get(GREEN_LIGHT_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(GREEN_LIGHT_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(RED_LIGHT_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(RED_LIGHT_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(FLAT_TIRE_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(FLAT_TIRE_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(GAS_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(GAS_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(INCREASE_SPEED_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(INCREASE_SPEED_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(DECREASE_SPEED_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(DECREASE_SPEED_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(INCREASE_PLAYER_SPEED_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(INCREASE_PLAYER_SPEED_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(CLOSE_ROAD_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(CLOSE_ROAD_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(CLOSE_INTERSECTION_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(CLOSE_INTERSECTION_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(OPEN_INTERSECTION_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(OPEN_INTERSECTION_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(MIND_CONTROL_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(MIND_CONTROL_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(FREEZE_TIME_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(FREEZE_TIME_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(STEAL_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(STEAL_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(INTANGABILITY_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(INTANGABILITY_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(FLY_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(FLY_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(INVINCIBLE_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(INVINCIBLE_BUTTON_TYPE).setEnabled(false);
        
        //RESET THE VIEWPORT FOR THE MAP
        initMapViewport();
        
        //RESET LEVEL POSITIONS
        guiButtons.get(LA_BUTTON_TYPE).setX(LA_X);
        guiButtons.get(LA_BUTTON_TYPE).setY(LA_Y);
        guiButtons.get(NY_BUTTON_TYPE).setX(NY_X);
        guiButtons.get(NY_BUTTON_TYPE).setY(NY_Y);
        guiButtons.get(CH_BUTTON_TYPE).setX(CH_X);
        guiButtons.get(CH_BUTTON_TYPE).setY(CH_Y);
        guiButtons.get(HOU_BUTTON_TYPE).setX(HOU_X);
        guiButtons.get(HOU_BUTTON_TYPE).setY(HOU_Y);
        guiButtons.get(SA_BUTTON_TYPE).setX(SA_X);
        guiButtons.get(SA_BUTTON_TYPE).setY(SA_Y);
        guiButtons.get(PHIL_BUTTON_TYPE).setX(PHIL_X);
        guiButtons.get(PHIL_BUTTON_TYPE).setY(PHIL_Y);
        guiButtons.get(PHE_BUTTON_TYPE).setX(PHE_X);
        guiButtons.get(PHE_BUTTON_TYPE).setY(PHE_Y);
        guiButtons.get(SD_BUTTON_TYPE).setX(SD_X);
        guiButtons.get(SD_BUTTON_TYPE).setY(SD_Y);
        guiButtons.get(DALL_BUTTON_TYPE).setX(DALL_X);
        guiButtons.get(DALL_BUTTON_TYPE).setY(DALL_Y);
        guiButtons.get(SJ_BUTTON_TYPE).setX(SJ_X);
        guiButtons.get(SJ_BUTTON_TYPE).setY(SJ_Y);
        guiButtons.get(AUS_BUTTON_TYPE).setX(AUS_X);
        guiButtons.get(AUS_BUTTON_TYPE).setY(AUS_Y);
        guiButtons.get(JAC_BUTTON_TYPE).setX(JAC_X);
        guiButtons.get(JAC_BUTTON_TYPE).setY(JAC_Y);
        guiButtons.get(IND_BUTTON_TYPE).setX(IND_X);
        guiButtons.get(IND_BUTTON_TYPE).setY(IND_Y);
        guiButtons.get(SF_BUTTON_TYPE).setX(SF_X);
        guiButtons.get(SF_BUTTON_TYPE).setY(SF_Y);
        guiButtons.get(CO_BUTTON_TYPE).setX(CO_X);
        guiButtons.get(CO_BUTTON_TYPE).setY(CO_Y);
        guiButtons.get(FW_BUTTON_TYPE).setX(FW_X);
        guiButtons.get(FW_BUTTON_TYPE).setY(FW_Y);
        guiButtons.get(CHAR_BUTTON_TYPE).setX(CHAR_X);
        guiButtons.get(CHAR_BUTTON_TYPE).setY(CHAR_Y);
        guiButtons.get(DET_BUTTON_TYPE).setX(DET_X);
        guiButtons.get(DET_BUTTON_TYPE).setY(DET_Y);
        guiButtons.get(EP_BUTTON_TYPE).setX(EP_X);
        guiButtons.get(EP_BUTTON_TYPE).setY(EP_Y);
        guiButtons.get(MEM_BUTTON_TYPE).setX(MEM_X);
        guiButtons.get(MEM_BUTTON_TYPE).setY(MEM_Y);
        
        //ACTIVATE BUTTONS
        guiButtons.get(HOME_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(HOME_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(CLOSE_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(CLOSE_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(UP_ARROW_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(UP_ARROW_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(DOWN_ARROW_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(DOWN_ARROW_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(LEFT_ARROW_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(LEFT_ARROW_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(RIGHT_ARROW_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(RIGHT_ARROW_BUTTON_TYPE).setEnabled(true);
        guiDecor.get(MAP_TYPE).setEnabled(true);
        
        // AND THE LEVEL BUTTONS
        guiButtons.get(LA_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(LA_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(NY_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(NY_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(HOU_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(HOU_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(SA_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(SA_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(PHIL_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(PHIL_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(PHE_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(PHE_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(SD_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(SD_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(DALL_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(DALL_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(SJ_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(SJ_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(AUS_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(AUS_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(JAC_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(JAC_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(IND_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(IND_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(SF_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(SF_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(CO_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(CO_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(FW_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(FW_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(CHAR_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(CHAR_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(DET_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(DET_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(EP_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(EP_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(MEM_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(MEM_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(CH_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(CH_BUTTON_TYPE).setEnabled(true);
        
        
        // AND CHANGE THE SCREEN STATE
        currentScreenState = LEVEL_SELECT_SCREEN_STATE;
        
        while(viewport.getViewportX() != 0) moveViewport(-1, 0);
        while(viewport.getViewportY() != LA_Y - 300) moveViewport(0, +1);
        
        // CLEAR LEFTOVER GAME DATA
        ((pathXDataModel)data).clearGame();
        
        if(musicPlaying)
        {
            audio.stop(pathXPropertyType.GAME_SONG.toString());
            if(!audio.isPlaying(pathXPropertyType.TITLE_SONG.toString()))
            {
                audio.play(pathXPropertyType.TITLE_SONG.toString(), true);
            }
        }
        
   //     playerReset();

    }
    
        /**
     * This method switches the application to the game screen, making
     * all the appropriate UI controls visible & invisible.
     */
    public void switchToGameScreen()
    {   
        PropertiesManager props = PropertiesManager.getPropertiesManager();
     
        //DEACTIVATE LEVEL SELECT CONTROLS
        guiButtons.get(HOME_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(HOME_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(CLOSE_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(CLOSE_BUTTON_TYPE).setEnabled(false);
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
        
        // AND THE LEVEL BUTTONS
        guiButtons.get(LA_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(LA_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(NY_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(NY_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(HOU_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(HOU_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(SA_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(SA_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(PHIL_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(PHIL_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(PHE_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(PHE_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(SD_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(SD_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(DALL_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(DALL_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(SJ_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(SJ_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(AUS_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(AUS_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(JAC_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(JAC_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(IND_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(IND_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(SF_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(SF_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(CO_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(CO_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(FW_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(FW_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(CHAR_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(CHAR_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(DET_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(DET_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(EP_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(EP_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(MEM_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(MEM_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(CH_BUTTON_TYPE).setState(pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.get(CH_BUTTON_TYPE).setEnabled(false);
        
        //ACTIVATE GAME SCREEN CONTROLS
        guiButtons.get(GAME_HOME_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(GAME_HOME_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(GAME_CLOSE_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(GAME_CLOSE_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(BACK_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(BACK_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(START_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(START_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(GAME_UP_ARROW_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(GAME_UP_ARROW_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(GAME_DOWN_ARROW_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(GAME_DOWN_ARROW_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(GAME_LEFT_ARROW_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(GAME_LEFT_ARROW_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(GAME_RIGHT_ARROW_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(GAME_RIGHT_ARROW_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(PAUSE_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(PAUSE_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(LEVEL_INFO_CLOSE_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        guiButtons.get(LEVEL_INFO_CLOSE_BUTTON_TYPE).setEnabled(true);
        guiDialogs.get(LEVEL_INFO_DIALOG_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
        
        //SPECIALS BUTTONS
        guiButtons.get(GREEN_LIGHT_BUTTON_TYPE).setState(pathXTileState.NOT_AVAILABLE_STATE.toString());
        guiButtons.get(RED_LIGHT_BUTTON_TYPE).setState(pathXTileState.NOT_AVAILABLE_STATE.toString());
        guiButtons.get(FLAT_TIRE_BUTTON_TYPE).setState(pathXTileState.NOT_AVAILABLE_STATE.toString());
        guiButtons.get(GAS_BUTTON_TYPE).setState(pathXTileState.NOT_AVAILABLE_STATE.toString());
        guiButtons.get(INCREASE_SPEED_BUTTON_TYPE).setState(pathXTileState.NOT_AVAILABLE_STATE.toString());
        guiButtons.get(DECREASE_SPEED_BUTTON_TYPE).setState(pathXTileState.NOT_AVAILABLE_STATE.toString());
        guiButtons.get(INCREASE_PLAYER_SPEED_BUTTON_TYPE).setState(pathXTileState.NOT_AVAILABLE_STATE.toString());
        guiButtons.get(CLOSE_ROAD_BUTTON_TYPE).setState(pathXTileState.NOT_AVAILABLE_STATE.toString());
        guiButtons.get(CLOSE_INTERSECTION_BUTTON_TYPE).setState(pathXTileState.NOT_AVAILABLE_STATE.toString());
        guiButtons.get(OPEN_INTERSECTION_BUTTON_TYPE).setState(pathXTileState.NOT_AVAILABLE_STATE.toString());
        guiButtons.get(MIND_CONTROL_BUTTON_TYPE).setState(pathXTileState.NOT_AVAILABLE_STATE.toString());
        guiButtons.get(FREEZE_TIME_BUTTON_TYPE).setState(pathXTileState.NOT_AVAILABLE_STATE.toString());
        guiButtons.get(STEAL_BUTTON_TYPE).setState(pathXTileState.NOT_AVAILABLE_STATE.toString());
        guiButtons.get(INTANGABILITY_BUTTON_TYPE).setState(pathXTileState.NOT_AVAILABLE_STATE.toString());
        guiButtons.get(FLY_BUTTON_TYPE).setState(pathXTileState.NOT_AVAILABLE_STATE.toString());
        guiButtons.get(INVINCIBLE_BUTTON_TYPE).setState(pathXTileState.NOT_AVAILABLE_STATE.toString());

        
        
        // CHANGE THE BACKGROUND
        guiDecor.get(BACKGROUND_TYPE).setState(GAME_SCREEN_STATE);

        // AND CHANGE THE SCREEN STATE
        currentScreenState = GAME_SCREEN_STATE;
        
        
 //       data.setGameState(MiniGameState.IN_PROGRESS);
        
        
        if(musicPlaying)
        {
            audio.stop(pathXPropertyType.TITLE_SONG.toString());
            audio.play(pathXPropertyType.GAME_SONG.toString(), true);
        }
        
        data.pause();       
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
        try
        {
            PropertiesManager props = PropertiesManager.getPropertiesManager();
//            String audioPath = props.getProperty(pathXPropertyType.PATH_AUDIO);

            // LOAD ALL THE AUDIO
            loadAudioCue(pathXPropertyType.AUDIO_CUE_CAR_START);
            loadAudioCue(pathXPropertyType.AUDIO_CUE_BULLET_RICOCHET);
            loadAudioCue(pathXPropertyType.AUDIO_CUE_POLICE_SIREN);
            loadAudioCue(pathXPropertyType.AUDIO_CUE_CAR_WONT_START);
            loadAudioCue(pathXPropertyType.AUDIO_CUE_CAR_CRASH);
            loadAudioCue(pathXPropertyType.TITLE_SONG);
            loadAudioCue(pathXPropertyType.GAME_SONG);

            // PLAY THE WELCOME SCREEN SONG
            audio.play(pathXPropertyType.TITLE_SONG.toString(), true);
        }
        catch(UnsupportedAudioFileException | IOException | LineUnavailableException | InvalidMidiDataException | MidiUnavailableException e)
        {
            errorHandler.processError(pathXPropertyType.TEXT_ERROR_LOADING_AUDIO);
        }        
    }

    /**
     * This helper method loads the audio file associated with audioCueType,
     * which should have been specified via an XML properties file.
     */
    private void loadAudioCue(pathXPropertyType audioCueType) 
            throws  UnsupportedAudioFileException, IOException, LineUnavailableException, 
                    InvalidMidiDataException, MidiUnavailableException
    {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String audioPath = props.getProperty(pathXPropertyType.PATH_AUDIO);
        String cue = props.getProperty(audioCueType.toString());
        audio.loadAudio(audioCueType.toString(), audioPath + cue);        
    }
    
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
        canvas = new pathXPanel(this, (pathXDataModel)data, path);
        
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
        
        String startButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_START);
        sT = new SpriteType(START_BUTTON_TYPE);
	img = loadImage(imgPath + startButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        img = loadImage(imgPath + startButton);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, START_BUTTON_X, START_BUTTON_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(START_BUTTON_TYPE, s);
        guiButtons.get(START_BUTTON_TYPE).setEnabled(false);
        
        String GameUpButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_GAME_ARROW_UP);
        sT = new SpriteType(GAME_UP_ARROW_BUTTON_TYPE);
	img = loadImage(imgPath + GameUpButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
//        String upMouseOverButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_ARROW_UP_MOUSE_OVER);
//        img = loadImage(imgPath + GameUpButton);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, GAME_UP_BUTTON_X, GAME_UP_BUTTON_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(GAME_UP_ARROW_BUTTON_TYPE, s);
        guiButtons.get(GAME_UP_ARROW_BUTTON_TYPE).setEnabled(false);
        
        String GameRightButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_GAME_ARROW_RIGHT);
        sT = new SpriteType(GAME_RIGHT_ARROW_BUTTON_TYPE);
	img = loadImage(imgPath + GameRightButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
//        String upMouseOverButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_ARROW_UP_MOUSE_OVER);
//        img = loadImage(imgPath + GameRightButton);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, GAME_RIGHT_BUTTON_X, GAME_RIGHT_BUTTON_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(GAME_RIGHT_ARROW_BUTTON_TYPE, s);
        guiButtons.get(GAME_RIGHT_ARROW_BUTTON_TYPE).setEnabled(false);
        
        String GameDownButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_GAME_ARROW_DOWN);
        sT = new SpriteType(GAME_DOWN_ARROW_BUTTON_TYPE);
	img = loadImage(imgPath + GameDownButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, GAME_DOWN_BUTTON_X, GAME_DOWN_BUTTON_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(GAME_DOWN_ARROW_BUTTON_TYPE, s);
        guiButtons.get(GAME_DOWN_ARROW_BUTTON_TYPE).setEnabled(false);
        
        String GameLeftButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_GAME_ARROW_LEFT);
        sT = new SpriteType(GAME_LEFT_ARROW_BUTTON_TYPE);
	img = loadImage(imgPath + GameLeftButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, GAME_LEFT_BUTTON_X, GAME_LEFT_BUTTON_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(GAME_LEFT_ARROW_BUTTON_TYPE, s);
        guiButtons.get(GAME_LEFT_ARROW_BUTTON_TYPE).setEnabled(false);
        
        String GamePauseButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_PAUSE);
        sT = new SpriteType(PAUSE_BUTTON_TYPE);
	img = loadImage(imgPath + GamePauseButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        String GamePlayButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_GAME_PLAY);
        img = loadImage(imgPath + GamePlayButton);
        sT.addState(pathXTileState.SELECTED_STATE.toString(), img);
        s = new Sprite(sT, GAME_PAUSE_BUTTON_X, GAME_PAUSE_BUTTON_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(PAUSE_BUTTON_TYPE, s);
        guiButtons.get(PAUSE_BUTTON_TYPE).setEnabled(false);
        
        // SPECIALS BUTTONS
        String GreenLightButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_GREEN_LIGHT);
        sT = new SpriteType(GREEN_LIGHT_BUTTON_TYPE);
	img = loadImage(imgPath + GreenLightButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        String GreenLightNAButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_GREEN_LIGHT_NA);
        img = loadImage(imgPath + GreenLightNAButton);
        sT.addState(pathXTileState.NOT_AVAILABLE_STATE.toString(), img);
        s = new Sprite(sT, GREEN_LIGHT_BUTTON_X, GREEN_LIGHT_BUTTON_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(GREEN_LIGHT_BUTTON_TYPE, s);
        guiButtons.get(GREEN_LIGHT_BUTTON_TYPE).setEnabled(false);
        
        String RedLightButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_RED_LIGHT);
        sT = new SpriteType(RED_LIGHT_BUTTON_TYPE);
	img = loadImage(imgPath + RedLightButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        String RedLightNAButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_RED_LIGHT_NA);
        img = loadImage(imgPath + RedLightNAButton);
        sT.addState(pathXTileState.NOT_AVAILABLE_STATE.toString(), img);
        s = new Sprite(sT, RED_LIGHT_BUTTON_X, RED_LIGHT_BUTTON_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(RED_LIGHT_BUTTON_TYPE, s);
        guiButtons.get(RED_LIGHT_BUTTON_TYPE).setEnabled(false);
        
        String FlatTireButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_FLAT_TIRE);
        sT = new SpriteType(FLAT_TIRE_BUTTON_TYPE);
	img = loadImage(imgPath + FlatTireButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        String FlatTireNAButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_FLAT_TIRE_NA);
        img = loadImage(imgPath + FlatTireNAButton);
        sT.addState(pathXTileState.NOT_AVAILABLE_STATE.toString(), img);
        s = new Sprite(sT, FLAT_TIRE_BUTTON_X, FLAT_TIRE_BUTTON_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(FLAT_TIRE_BUTTON_TYPE, s);
        guiButtons.get(FLAT_TIRE_BUTTON_TYPE).setEnabled(false);
        
        String GasButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_GAS);
        sT = new SpriteType(GAS_BUTTON_TYPE);
	img = loadImage(imgPath + GasButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        String GasNAButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_GAS_NA);
        img = loadImage(imgPath + GasNAButton);
        sT.addState(pathXTileState.NOT_AVAILABLE_STATE.toString(), img);
        s = new Sprite(sT, GAS_BUTTON_X, GAS_BUTTON_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(GAS_BUTTON_TYPE, s);
        guiButtons.get(GAS_BUTTON_TYPE).setEnabled(false);
        
        String IncreaseSpeedButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_INCREASE_SPEED);
        sT = new SpriteType(INCREASE_SPEED_BUTTON_TYPE);
	img = loadImage(imgPath + IncreaseSpeedButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        String IncreaseSpeedNAButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_INCREASE_SPEED_NA);
        img = loadImage(imgPath + IncreaseSpeedNAButton);
        sT.addState(pathXTileState.NOT_AVAILABLE_STATE.toString(), img);
        s = new Sprite(sT, INCREASE_SPEED_BUTTON_X, INCREASE_SPEED_BUTTON_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(INCREASE_SPEED_BUTTON_TYPE, s);
        guiButtons.get(INCREASE_SPEED_BUTTON_TYPE).setEnabled(false);
        
        String DecreaseSpeedButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_DECREASE_SPEED);
        sT = new SpriteType(DECREASE_SPEED_BUTTON_TYPE);
	img = loadImage(imgPath + DecreaseSpeedButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        String DecreaseSpeedNAButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_DECREASE_SPEED_NA);
        img = loadImage(imgPath + DecreaseSpeedNAButton);
        sT.addState(pathXTileState.NOT_AVAILABLE_STATE.toString(), img);
        s = new Sprite(sT, DECREASE_SPEED_BUTTON_X, DECREASE_SPEED_BUTTON_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(DECREASE_SPEED_BUTTON_TYPE, s);
        guiButtons.get(DECREASE_SPEED_BUTTON_TYPE).setEnabled(false);
        
        String IncreasePlayerSpeedButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_INCREASE_PLAYER_SPEED);
        sT = new SpriteType(INCREASE_PLAYER_SPEED_BUTTON_TYPE);
	img = loadImage(imgPath + IncreasePlayerSpeedButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        String IncreasePlayerSpeedNAButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_INCREASE_PLAYER_SPEED_NA);
        img = loadImage(imgPath + IncreasePlayerSpeedNAButton);
        sT.addState(pathXTileState.NOT_AVAILABLE_STATE.toString(), img);
        s = new Sprite(sT, INCREASE_PLAYER_SPEED_BUTTON_X, INCREASE_PLAYER_SPEED_BUTTON_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(INCREASE_PLAYER_SPEED_BUTTON_TYPE, s);
        guiButtons.get(INCREASE_PLAYER_SPEED_BUTTON_TYPE).setEnabled(false);
        
        String CloseRoadButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_CLOSE_ROAD);
        sT = new SpriteType(CLOSE_ROAD_BUTTON_TYPE);
	img = loadImage(imgPath + CloseRoadButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        String CloseRoadNAButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_CLOSE_ROAD_NA);
        img = loadImage(imgPath + CloseRoadNAButton);
        sT.addState(pathXTileState.NOT_AVAILABLE_STATE.toString(), img);
        s = new Sprite(sT, CLOSE_ROAD_BUTTON_X, CLOSE_ROAD_BUTTON_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(CLOSE_ROAD_BUTTON_TYPE, s);
        guiButtons.get(CLOSE_ROAD_BUTTON_TYPE).setEnabled(false);
        
        String CloseIntersectionButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_CLOSE_INTERSECTION);
        sT = new SpriteType(CLOSE_INTERSECTION_BUTTON_TYPE);
	img = loadImage(imgPath + CloseIntersectionButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        String CloseIntersectionNAButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_CLOSE_INTERSECTION_NA);
        img = loadImage(imgPath + CloseIntersectionNAButton);
        sT.addState(pathXTileState.NOT_AVAILABLE_STATE.toString(), img);
        s = new Sprite(sT, CLOSE_INTERSECTION_BUTTON_X, CLOSE_INTERSECTION_BUTTON_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(CLOSE_INTERSECTION_BUTTON_TYPE, s);
        guiButtons.get(CLOSE_INTERSECTION_BUTTON_TYPE).setEnabled(false);
        
        String OpenIntersectionButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_OPEN_INTERSECTION);
        sT = new SpriteType(OPEN_INTERSECTION_BUTTON_TYPE);
	img = loadImage(imgPath + OpenIntersectionButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        String OpenIntersectionNAButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_OPEN_INTERSECTION_NA);
        img = loadImage(imgPath + OpenIntersectionNAButton);
        sT.addState(pathXTileState.NOT_AVAILABLE_STATE.toString(), img);
        s = new Sprite(sT, OPEN_INTERSECTION_BUTTON_X, OPEN_INTERSECTION_BUTTON_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(OPEN_INTERSECTION_BUTTON_TYPE, s);
        guiButtons.get(OPEN_INTERSECTION_BUTTON_TYPE).setEnabled(false);
        
        String MindControlButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_MIND_CONTROL);
        sT = new SpriteType(MIND_CONTROL_BUTTON_TYPE);
	img = loadImage(imgPath + MindControlButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        String MindControlNAButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_MIND_CONTROL_NA);
        img = loadImage(imgPath + MindControlNAButton);
        sT.addState(pathXTileState.NOT_AVAILABLE_STATE.toString(), img);
        s = new Sprite(sT, MIND_CONTROL_BUTTON_X, MIND_CONTROL_BUTTON_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(MIND_CONTROL_BUTTON_TYPE, s);
        guiButtons.get(MIND_CONTROL_BUTTON_TYPE).setEnabled(false);
        
        String FreezeTimeButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_FREEZE_TIME);
        sT = new SpriteType(FREEZE_TIME_BUTTON_TYPE);
	img = loadImage(imgPath + FreezeTimeButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        String FreezeTimeNAButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_FREEZE_TIME_NA);
        img = loadImage(imgPath + FreezeTimeNAButton);
        sT.addState(pathXTileState.NOT_AVAILABLE_STATE.toString(), img);
        s = new Sprite(sT, FREEZE_TIME_BUTTON_X, FREEZE_TIME_BUTTON_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(FREEZE_TIME_BUTTON_TYPE, s);
        guiButtons.get(FREEZE_TIME_BUTTON_TYPE).setEnabled(false);
        
        String StealButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_STEAL);
        sT = new SpriteType(STEAL_BUTTON_TYPE);
	img = loadImage(imgPath + StealButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        String StealNAButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_STEAL_NA);
        img = loadImage(imgPath + StealNAButton);
        sT.addState(pathXTileState.NOT_AVAILABLE_STATE.toString(), img);
        s = new Sprite(sT, STEAL_BUTTON_X, STEAL_BUTTON_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(STEAL_BUTTON_TYPE, s);
        guiButtons.get(STEAL_BUTTON_TYPE).setEnabled(false);
        
        String IntangibleButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_INTANGABILITY);
        sT = new SpriteType(STEAL_BUTTON_TYPE);
	img = loadImage(imgPath + IntangibleButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        String IntangibleNAButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_INTANGABILITY_NA);
        img = loadImage(imgPath + IntangibleNAButton);
        sT.addState(pathXTileState.NOT_AVAILABLE_STATE.toString(), img);
        s = new Sprite(sT, INTANGABILITY_BUTTON_X, INTANGABILITY_BUTTON_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(INTANGABILITY_BUTTON_TYPE, s);
        guiButtons.get(INTANGABILITY_BUTTON_TYPE).setEnabled(false);
        
        String FlyButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_FLY);
        sT = new SpriteType(FLY_BUTTON_TYPE);
	img = loadImage(imgPath + FlyButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        String FlyNAButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_FLY_NA);
        img = loadImage(imgPath + FlyNAButton);
        sT.addState(pathXTileState.NOT_AVAILABLE_STATE.toString(), img);
        s = new Sprite(sT, FLY_BUTTON_X, FLY_BUTTON_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(FLY_BUTTON_TYPE, s);
        guiButtons.get(FLY_BUTTON_TYPE).setEnabled(false);
        
        String InvincableButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_INVINCIBLE);
        sT = new SpriteType(INVINCIBLE_BUTTON_TYPE);
	img = loadImage(imgPath + InvincableButton);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        String InvincibleNAButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_INVINCIBLE_NA);
        img = loadImage(imgPath + InvincibleNAButton);
        sT.addState(pathXTileState.NOT_AVAILABLE_STATE.toString(), img);
        s = new Sprite(sT, INVINCIBLE_BUTTON_X, INVINCIBLE_BUTTON_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(INVINCIBLE_BUTTON_TYPE, s);
        guiButtons.get(INVINCIBLE_BUTTON_TYPE).setEnabled(false);
        
        
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
        
        // LEVEL BUTTONS
        String cityButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_LOCATION_MARKER);
        img = loadImage(imgPath + cityButton);
        sT = new SpriteType(LA_BUTTON_TYPE);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, LA_X, LA_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(LA_BUTTON_TYPE, s);
        guiButtons.get(LA_BUTTON_TYPE).setEnabled(false);
        
        sT = new SpriteType(NY_BUTTON_TYPE);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, NY_X, NY_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(NY_BUTTON_TYPE, s);
        guiButtons.get(NY_BUTTON_TYPE).setEnabled(false);
        
        sT = new SpriteType(CH_BUTTON_TYPE);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, CH_X, CH_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(CH_BUTTON_TYPE, s);
        guiButtons.get(CH_BUTTON_TYPE).setEnabled(false);
        
        sT = new SpriteType(HOU_BUTTON_TYPE);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, HOU_X, HOU_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(HOU_BUTTON_TYPE, s);
        guiButtons.get(HOU_BUTTON_TYPE).setEnabled(false);
        
        sT = new SpriteType(SA_BUTTON_TYPE);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, SA_X, SA_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(SA_BUTTON_TYPE, s);
        guiButtons.get(SA_BUTTON_TYPE).setEnabled(false);
        
        sT = new SpriteType(PHIL_BUTTON_TYPE);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, PHIL_X, PHIL_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(PHIL_BUTTON_TYPE, s);
        guiButtons.get(PHIL_BUTTON_TYPE).setEnabled(false);
        
        sT = new SpriteType(PHE_BUTTON_TYPE);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, PHE_X, PHE_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(PHE_BUTTON_TYPE, s);
        guiButtons.get(PHE_BUTTON_TYPE).setEnabled(false);
        
        sT = new SpriteType(SD_BUTTON_TYPE);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, SD_X, SD_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(SD_BUTTON_TYPE, s);
        guiButtons.get(SD_BUTTON_TYPE).setEnabled(false);
        
        sT = new SpriteType(DALL_BUTTON_TYPE);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, DALL_X, DALL_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(DALL_BUTTON_TYPE, s);
        guiButtons.get(DALL_BUTTON_TYPE).setEnabled(false);
        
        sT = new SpriteType(SJ_BUTTON_TYPE);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, SJ_X, SJ_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(SJ_BUTTON_TYPE, s);
        guiButtons.get(SJ_BUTTON_TYPE).setEnabled(false);
        
        sT = new SpriteType(AUS_BUTTON_TYPE);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, AUS_X, AUS_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(AUS_BUTTON_TYPE, s);
        guiButtons.get(AUS_BUTTON_TYPE).setEnabled(false);
        
        sT = new SpriteType(JAC_BUTTON_TYPE);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, JAC_X, JAC_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(JAC_BUTTON_TYPE, s);
        guiButtons.get(JAC_BUTTON_TYPE).setEnabled(false);
        
        sT = new SpriteType(IND_BUTTON_TYPE);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, IND_X, IND_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(IND_BUTTON_TYPE, s);
        guiButtons.get(IND_BUTTON_TYPE).setEnabled(false);
        
        sT = new SpriteType(SF_BUTTON_TYPE);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, SF_X, SF_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(SF_BUTTON_TYPE, s);
        guiButtons.get(SF_BUTTON_TYPE).setEnabled(false);
        
        sT = new SpriteType(CO_BUTTON_TYPE);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, CO_X, CO_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(CO_BUTTON_TYPE, s);
        guiButtons.get(CO_BUTTON_TYPE).setEnabled(false);
        
        sT = new SpriteType(FW_BUTTON_TYPE);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, FW_X, FW_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(FW_BUTTON_TYPE, s);
        guiButtons.get(FW_BUTTON_TYPE).setEnabled(false);
        
        sT = new SpriteType(CHAR_BUTTON_TYPE);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, CHAR_X, CHAR_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(CHAR_BUTTON_TYPE, s);
        guiButtons.get(CHAR_BUTTON_TYPE).setEnabled(false);
        
        sT = new SpriteType(DET_BUTTON_TYPE);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, DET_X, DET_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(DET_BUTTON_TYPE, s);
        guiButtons.get(DET_BUTTON_TYPE).setEnabled(false);
        
        sT = new SpriteType(EP_BUTTON_TYPE);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, EP_X, EP_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(EP_BUTTON_TYPE, s);
        guiButtons.get(EP_BUTTON_TYPE).setEnabled(false);
        
        sT = new SpriteType(MEM_BUTTON_TYPE);
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, MEM_X, MEM_Y, 0, 0, pathXTileState.INVISIBLE_STATE.toString());
        guiButtons.put(MEM_BUTTON_TYPE, s);
        guiButtons.get(MEM_BUTTON_TYPE).setEnabled(false);
        
      
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
    }		
        
    
    // MUSIC AND SOUND EFFECTS
    public void toggleMusic()
    {
        if(musicPlaying)
        {
            audio.stop(pathXPropertyType.TITLE_SONG.toString());
            guiButtons.get(MUSIC_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
            musicPlaying = false;
        }
        else
        {
            audio.play(pathXPropertyType.TITLE_SONG.toString(), true);
            guiButtons.get(MUSIC_BUTTON_TYPE).setState(pathXTileState.SELECTED_STATE.toString());
            musicPlaying = true;
        }
    }
    
    public void toggleSoundEffects()
    {
        if(((pathXDataModel)data).soundEffectsOn())
        {
            guiButtons.get(SOUND_BUTTON_TYPE).setState(pathXTileState.VISIBLE_STATE.toString());
            ((pathXDataModel)data).turnOffSoundEffects();
        }
        else
        {
            guiButtons.get(SOUND_BUTTON_TYPE).setState(pathXTileState.SELECTED_STATE.toString());
            ((pathXDataModel)data).turnOnSoundEffect();
        }
    }
    
    public void playGameMusic()
    {
        if(musicPlaying)
        {
            audio.play(pathXPropertyType.GAME_SONG.toString(), true);
        }
    }
    
    public void playCarStart()
    {
        if(((pathXDataModel)data).soundEffectsOn())
        {
            audio.play(pathXPropertyType.AUDIO_CUE_CAR_START.toString(), true);
        }
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
        
        //GAME START PRESS EVENT HANDLER
        guiButtons.get(START_BUTTON_TYPE).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae)
                {   eventHandler.respondToGameStartRequest();  }
        });
        
         //GAME UP ARROW PRESS EVENT HANDLER
        guiButtons.get(GAME_UP_ARROW_BUTTON_TYPE).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae)
                {   eventHandler.respondToUpRequest();  }
        });
        
        //GAME DOWN ARROW PRESS EVENT HANDLER
        guiButtons.get(GAME_DOWN_ARROW_BUTTON_TYPE).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae)
                {   eventHandler.respondToDownRequest();  }
        });
        
        //GAME LEFT ARROW PRESS EVENT HANDLER
        guiButtons.get(GAME_LEFT_ARROW_BUTTON_TYPE).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae)
                {   eventHandler.respondToLeftRequest();  }
        });
        
        //GAME RIGHT ARROW PRESS EVENT HANDLER
        guiButtons.get(GAME_RIGHT_ARROW_BUTTON_TYPE).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae)
                {   eventHandler.respondToRightRequest();  }
        });
        
        //PAUSE PRESS EVENT HANDLER
        guiButtons.get(PAUSE_BUTTON_TYPE).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae)
                {   eventHandler.respondToPauseRequest();  }
        });
        
        
        //LEVEL BUTTON PRESS EVENT HANDLERS
        //LA (LOS ANGELES)
        guiButtons.get(LA_BUTTON_TYPE).setActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae)
                {   path.setLevelPath(PATH_LEVELS + "LosAngles.bin");
                    eventHandler.respondToGameRequest();  }
        });
        
        //NY (NEW YORK)
        guiButtons.get(NY_BUTTON_TYPE).setActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae)
                {   path.setLevelPath(PATH_LEVELS + "NewYork.bin");
                    eventHandler.respondToGameRequest();  }
        });
        
        //CH (CHICAGO)
        guiButtons.get(CH_BUTTON_TYPE).setActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae)
                {   path.setLevelPath(PATH_LEVELS + "Cali.bin");
                    eventHandler.respondToGameRequest();  }
        });
        
        //HOU (HOUSTON)
        guiButtons.get(HOU_BUTTON_TYPE).setActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae)
                {   path.setLevelPath(PATH_LEVELS + "Houston.bin");
                    eventHandler.respondToGameRequest();  }
        });
        
        //SA (SAN ANTONIO)
        guiButtons.get(SA_BUTTON_TYPE).setActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae)
                {   path.setLevelPath(PATH_LEVELS + "SanAntonio.bin");
                    eventHandler.respondToGameRequest();  }
        });
        
        //PHIL (PHILADELPHIA)
        guiButtons.get(PHIL_BUTTON_TYPE).setActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae)
                {   path.setLevelPath(PATH_LEVELS + "Cali.bin");
                    eventHandler.respondToGameRequest();  }
        });
        
        //PHE (PHOENIX)
        guiButtons.get(PHE_BUTTON_TYPE).setActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae)
                {   path.setLevelPath(PATH_LEVELS + "Phoenix.bin");
                    eventHandler.respondToGameRequest();  }
        });
        
        //SD (SAN DIEGO)
        guiButtons.get(SD_BUTTON_TYPE).setActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae)
                {   path.setLevelPath(PATH_LEVELS + "SanDiego.bin");
                    eventHandler.respondToGameRequest();  }
        });
        
        //DALL (DALLAS)
        guiButtons.get(DALL_BUTTON_TYPE).setActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae)
                {   path.setLevelPath(PATH_LEVELS + "Dallas.bin");
                    eventHandler.respondToGameRequest();  }
        });
        
        //SJ (SAN JOSE)
        guiButtons.get(SJ_BUTTON_TYPE).setActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae)
                {   path.setLevelPath(PATH_LEVELS + "SANJOSE.bin");
                    eventHandler.respondToGameRequest();  }
        });
        
        //AUS (AUSTIN)
        guiButtons.get(AUS_BUTTON_TYPE).setActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae)
                {   path.setLevelPath(PATH_LEVELS + "Austin.bin");
                    eventHandler.respondToGameRequest();  }
        });
        
        //JAC (JACKSONVILLE)
        guiButtons.get(JAC_BUTTON_TYPE).setActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae)
                {   path.setLevelPath(PATH_LEVELS + "Cali.bin");
                    eventHandler.respondToGameRequest();  }
        });
        
        //IND (INDIANAPOLIS)
        guiButtons.get(IND_BUTTON_TYPE).setActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae)
                {   path.setLevelPath(PATH_LEVELS + "Cali.bin");
                    eventHandler.respondToGameRequest();  }
        });
        
        //SF (SAN FRANCISCO)
        guiButtons.get(SF_BUTTON_TYPE).setActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae)
                {   path.setLevelPath(PATH_LEVELS + "SANFRANCISICO.bin");
                    eventHandler.respondToGameRequest();  }
        });
        
        //CO (COLUMBUS)
        guiButtons.get(CO_BUTTON_TYPE).setActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae)
                {   path.setLevelPath(PATH_LEVELS + "Columbus.bin");
                    eventHandler.respondToGameRequest();  }
        });
        
        //FW (FORT WORTH)
        guiButtons.get(FW_BUTTON_TYPE).setActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae)
                {   path.setLevelPath(PATH_LEVELS + "FortWorth.bin");
                    eventHandler.respondToGameRequest();  }
        });
        
        //CHAR (CHARLOTTE)
        guiButtons.get(CHAR_BUTTON_TYPE).setActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae)
                {   path.setLevelPath(PATH_LEVELS + "Cali.bin");
                    eventHandler.respondToGameRequest();  }
        });
        
        //DET (DETROIT)
        guiButtons.get(DET_BUTTON_TYPE).setActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae)
                {   path.setLevelPath(PATH_LEVELS + "Detroit.bin");
                    eventHandler.respondToGameRequest();  }
        });
        
        //EP (EL PASO)
        guiButtons.get(EP_BUTTON_TYPE).setActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae)
                {   path.setLevelPath(PATH_LEVELS + "ElPaso.bin");
                    eventHandler.respondToGameRequest();  }
        });
        
        //MEM (MEMPHIS)
        guiButtons.get(MEM_BUTTON_TYPE).setActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae)
                {   path.setLevelPath(PATH_LEVELS + "Cali.bin");
                    eventHandler.respondToGameRequest();  }
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
