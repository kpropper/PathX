package pathx.data;

import java.awt.Graphics;
import pathx.ui.pathXTile;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Iterator;
import pathx.PathX.pathXPropertyType;
import mini_game.MiniGame;
import mini_game.MiniGameDataModel;
import mini_game.Viewport;
import pathx.level.model.Intersection;
import mini_game.SpriteType;
import properties_manager.PropertiesManager;
import static pathx.pathXConstants.*;
import pathx.ui.pathXMiniGame;
import pathx.ui.pathXPanel;
import pathx.ui.pathXTileState;
import pathx.level.model.pathXLevelModel;
import pathx.level.model.Player;
import pathx.ui.pathXTileState;
import pathx.level.model.Police;
import pathx.level.model.pathXLevelPlacement;


/**
 *
 * @author Karl Propper
 */
public class pathXDataModel extends MiniGameDataModel {
    // THIS CLASS HAS A REFERERENCE TO THE MINI GAME SO THAT IT
    // CAN NOTIFY IT TO UPDATE THE DISPLAY WHEN THE DATA MODEL CHANGES
    private pathXMiniGame miniGame;
    
    private pathXLevelModel model;
    
    private Player player;
    
    private int money;
    
    private int level;
    
    private int goal = MONEY_GOAL;
    
    private pathXLevelPlacement placement = new pathXLevelPlacement();
    
    private boolean soundEffectsOn = true;
    
    private ArrayList<Police> police = new ArrayList();
    


    /**
     * Constructor for initializing this data model, it will create the data
     * structures for storing tiles, but not the tile grid itself, that is
     * dependent on file loading, and so should be subsequently initialized.
     *
     * @param initMiniGame The Sorting Hat game UI.
     */
    public pathXDataModel(pathXMiniGame initMiniGame)
    {
        // KEEP THE GAME FOR LATER
        miniGame = initMiniGame;
        
        //SETUP THE PLAYER
        SpriteType sT = new SpriteType(PLAYER_TYPE);
        player = new Player(sT,0,0,0,0,pathXTileState.INVISIBLE_STATE.toString());

    }



    public void initLevel(pathXLevelModel initLevel)
    {
        model = initLevel;
    }
    
    public void turnOffSoundEffects()
    {
        soundEffectsOn = false;
    }
    
    public void turnOnSoundEffect()
    {
        soundEffectsOn = true;
    }
    
    public boolean soundEffectsOn() {   return soundEffectsOn;  }
    public Player getPlayer()       {   return player;          }
    public int getMoney()           {   return money;           }
    public int getLevel()           {   return level;           }
    public Iterator getPolice()   {   return police.iterator();   }
    public pathXLevelModel getLevelModel() {   return model;   }
    public String getGoalDisplay()
    {
        String goalMoney = "$" + Integer.toString(goal);
        return goalMoney;
    }

    //  CHANGE THE AMOUNT OF MONEY
    public void changeMoney(int moneyChange)
    {
        money += moneyChange;
    }
    
    // CHANGE THE LEVEL
    public void setLevel(int newLevel)
    {
        level = newLevel;
    }
    
    //CREATE POLICE SPRITES
    public void createPolice(BufferedImage p)
    {
        Iterator policePos = placement.getPolice(model.getLevelName());
        SpriteType sT = new SpriteType(POLICE_TYPE);
//        sT.addState(pathXTileState.VISIBLE_STATE.toString(), p);
//        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), p);
        
        for(int c = 1; c <= model.getNumPolice(); c++)
        {
            
            Police cop = new Police(sT);
            cop.setImage(p);
            int ix = (int)policePos.next();
            int iy = (int)policePos.next();
            Intersection i = model.findIntersection(ix,iy);
            cop.setPosition(i.x + VIEWABLE_GAMEWORLD_OFFSET, i.y);
            cop.setDataModel(this);
            police.add(cop);
        }
    }
    


    /**
     * This method provides a custom game response for handling mouse clicks on
     * the game screen. We'll use this to close game dialogs as well as to
     * listen for mouse clicks on grid cells.
     *
     * @param game The Sorting Hat game.
     *
     * @param x The x-axis pixel location of the mouse click.
     *
     * @param y The y-axis pixel location of the mouse click.
     */
    @Override
    public void checkMousePressOnSprites(MiniGame game, int x, int y)
    {
        Viewport viewport = this.getViewport();
        if(miniGame.isCurrentScreenState(GAME_SCREEN_STATE))
        {
            Intersection guess = model.findIntersection(x - VIEWABLE_GAMEWORLD_OFFSET + viewport.getViewportX(),y + viewport.getViewportY());
            if(guess != null)
            {
               if(!player.isMoving())
               {   player.setPath(model.findShortestPathToIntersection(guess));
                 //  player.setTarget(guess.x + VIEWABLE_GAMEWORLD_OFFSET - viewport.getViewportX(),guess.y - viewport.getViewportY());
                //    player.startMovingToTarget(4);
               }
            }
        }
    }
    

    
    /**
     * Called when the game is won, it will record the ending game time, update
     * the player record, display the win dialog, and play the win animation.
     */
    @Override
    public void endGameAsWin()
    {

    }
    
    /**
     * Updates the player record, adding a game without a win.
     */
    public void endGameAsLoss()
    {
      
    }

    /**
     * Called when a game is started, the game grid is reset.
     *
     * @param game
     */
    @Override
    public void reset(MiniGame game)
    {

    }

    /**
     * Called each frame, this method updates all the game objects.
     *
     * @param game The Sorting Hat game to be updated.
     */
    @Override
    public void updateAll(MiniGame game)
    {
    }

    /**
     * This method is for updating any debug text to present to the screen. In a
     * graphical application like this it's sometimes useful to display data in
     * the GUI.
     *
     * @param game The Sorting Hat game about which to display info.
     */
    @Override
    public void updateDebugText(MiniGame game)
    {
    }
    
}
