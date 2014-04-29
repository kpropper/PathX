package pathx.ui;

import java.awt.event.KeyEvent;
//import javax.swing.JDialog;
import javax.swing.JOptionPane;
import static pathx.pathXConstants.GAME_SCREEN_STATE;
import static pathx.pathXConstants.MENU_SCREEN_STATE;
import static pathx.pathXConstants.*;
import pathx.PathX;
import pathx.data.pathXDataModel;
//import pathx.file.pathXFileManager;



/**
 *
 * @author Karl Propper
 */
public class pathXEventHandler {
    
    // THE PATHX GAME, IT PROVIDES ACCESS TO EVERYTHING
    private pathXMiniGame game;

    /**
     * Constructor, it just keeps the game for when the events happen.
     */
    public pathXEventHandler(pathXMiniGame initGame)
    {
        game = initGame;
    }

    /**
     * Called when the user clicks the in-game close button
     */
    public void respondToCloseRequest()
    {
        //CLOSE THE GAME
        System.exit(0);
    }
    
    public void respondToExitRequest()
    {
        //CLOSE THE GAME
        System.exit(0);
    }
    
    public void respondToPlayRequest()
    {
        game.switchToLevelSelectScreen();
    }
    
    public void respondToSettingsRequest()
    {
        game.switchToSettingsScreen();
    }
    
    public void respondToHelpRequest()
    {
        //Update to pull from properties later
        JOptionPane.showMessageDialog(null,"Here you are, a getaway driver starting out in your life of crime.\n"
                + "What's the fastest way to drive from Point A to Point B? Well that depends\n"
                + "on the graph of roads connecting them. Different roads have different lengths\n"
                + "and speed limits and obstacles. Some roads are even one-way. In addition, sometimes\n"
                + "there are bandits on the road trying to catch you to steal the money you stole.\n"
                + "Or police looking to lock you up for your crimes. There might even be zombies.\n"
                + "These are all things to consider while one sits behind the wheel of a car and these\n"
                + "are the problems that pathX players will be confronted with while working out how\n"
                + "to get from the start location (site of robbery) to finish (your hideout) on a given\n"
                + "game level. Players will be able to choose the path they take to avoid trouble and as\n"
                + "they progress through the game, will earn new abilities to be used to overcome new\n"
                + "obstacles. \n\n Author: Karl Propper\nStudent @ SBU","Help",JOptionPane.QUESTION_MESSAGE);
    }
    
    public void respondToHomeRequest()
    {
        game.switchToSplashScreen();
    }
    
    public void respondToGameRequest()
    {
        game.switchToGameScreen();
    }
    
    public void respondToBackRequest()
    {
        game.switchToLevelSelectScreen();
    }
    
    public void respondToLevelInfoCloseRequest()
    {
        game.closeLevelInfoDialog();
    }
    
    public void respondToUpRequest()
    {
        game.moveViewport(0,-LEVEL_VIEWPORT_INC);
    }
    
    public void respondToDownRequest()
    {
        game.moveViewport(0,LEVEL_VIEWPORT_INC);
    }
        
    public void respondToLeftRequest()
    {
        game.moveViewport(-LEVEL_VIEWPORT_INC,0);
    }
            
    public void respondToRightRequest()
    {
        game.moveViewport(LEVEL_VIEWPORT_INC,0);
    }
            
    public void respondToSoundRequest()
    {

    }
    
    public void respondToMusicRequest()
    {
        
    }
    
    public void respondToKeyPress(int keyCode)
    {
        if(keyCode == KeyEvent.VK_UP)
        {
            respondToUpRequest();
        }
        
        if(keyCode == KeyEvent.VK_DOWN)
        {
            respondToDownRequest();
        }
        
        if(keyCode == KeyEvent.VK_LEFT)
        {
            respondToLeftRequest();
        }
        
        if(keyCode == KeyEvent.VK_RIGHT)
        {
            respondToRightRequest();
        }
    }
}
