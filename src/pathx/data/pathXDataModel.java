package pathx.data;

import java.awt.Graphics;
import pathx.ui.pathXTile;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Iterator;
import pathx.PathX.pathXPropertyType;
import mini_game.MiniGame;
import mini_game.MiniGameDataModel;
import mini_game.Viewport;
import pathx.level.model.Intersection;
import mini_game.Sprite;
import mini_game.SpriteType;
import properties_manager.PropertiesManager;
import static pathx.pathXConstants.*;
import pathx.ui.pathXMiniGame;
import pathx.ui.pathXPanel;
import pathx.ui.pathXTileState;
import pathx.level.model.pathXLevelModel;
import pathx.level.model.Intersection;
import pathx.level.model.Player;
import pathx.ui.pathXTileState;
import pathx.level.model.Police;
import pathx.level.model.Zombie;
import pathx.level.model.Bandit;
import pathx.level.model.pathXLevelPlacement;
import pathx.data.pathXSpecialsType;


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
    
    private String currentSpecial = NONE;
    
    private int money = 0;
    
    private int levelMoney;
    
    private int level = 1;
    
    private int goal = MONEY_GOAL;
    
    private float gameSpeed = 1;
    
    private long timeStopped;
    
    //private Intersection destination;
    
    private pathXLevelPlacement placement = new pathXLevelPlacement();
    
    private boolean soundEffectsOn = true;
    
    private ArrayList<Police> police = new ArrayList();
    
    private ArrayList<Zombie> zombie = new ArrayList();
    
    private ArrayList<Bandit> bandits = new ArrayList();
    
    private ArrayList<Sprite> NPCs = new ArrayList();
    
    private Iterator NPC;
    
    private boolean gameWon = false;
    
    private int mousePressedX;
    private int mousePressedY;
    
    private GregorianCalendar startTime;


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
        createPlayer();
        
        startTime = new GregorianCalendar();

    }



    public void initLevel(pathXLevelModel initLevel)
    {
        model = initLevel;
        levelMoney = model.getMoney();
    }
    
    public void turnOffSoundEffects()
    {
        soundEffectsOn = false;
    }
    
    public void turnOnSoundEffect()
    {
        soundEffectsOn = true;
    }
    
    public void setMousePressedX(int x)
    {
        mousePressedX = x;
    }
    
    public void setMousePressedY(int y)
    {
        mousePressedY = y;
    }
    
    public void setGameSpeed(float gS)
    {
        gameSpeed = gS;
    }
            
    public boolean soundEffectsOn() {   return soundEffectsOn;  }
    public Player getPlayer()       {   return player;          }
    public int getMoney()           {   return money;           }
    public int getLevel()           {   return level;           }
    public int getLevelMoney()      {   return levelMoney;    }
    public int getMousePressedX()   {   return mousePressedX;   }
    public int getMousePressedY()   {   return mousePressedY;   }
    public float getGameSpeed()    {   return gameSpeed;       }
    public boolean isGameWon()     {  return gameWon;          }
    public String getCurrentSpecial() {   return currentSpecial; }
    public Intersection getStart()  {   return model.getStartingLocation(); }
    public Intersection getDestination() {  return model.getDestination();  }
    public Iterator getPolice()   {   return police.iterator();   }
    public Iterator getZombie()   {   return zombie.iterator();   }
    public Iterator getBandits()  {   return bandits.iterator();  }
    public Iterator getNPCs()       {   return NPCs.iterator(); }
    public pathXLevelModel getLevelModel() {   return model;   }
    public String getGoalDisplay()
    {
        String goalMoney = "$" + Integer.toString(goal);
        return goalMoney;
    }
    
    public String getMoneyDisplay()
    {
        String moneyDisplay = "$"+ Integer.toString(money);
        return moneyDisplay;
    }
    
    public String getLevelMoneyDisplay()
    {
        String levelMoneyDisplay = "$" + Integer.toString(levelMoney);
        return levelMoneyDisplay;
    }

    //  CHANGE THE AMOUNT OF MONEY
    public void changeMoney(int moneyChange)
    {
        money += moneyChange;
    }
    
    //CHANGE THE AMOUNT OF MONEY THAT IT IS POSSIBLE TO COME AWAY WITH
    public void changeLevelMoney(int moneyChange)
    {
        levelMoney += moneyChange;
    }
    //HAS THE GAME BEEN WON
    public void setGameWon()
    {
        gameWon = true;
        money += levelMoney;
    }        
            
    // CHANGE THE LEVEL
    public void setLevel(int newLevel)
    {
        if(level < newLevel)
        {
            level = newLevel;
        }
    }
    
    // SET THE SPECIAL
    public void setSpecial(String special)
    {
        currentSpecial = special;
    }
    
    public void setTimeStopped(long time)
    {
        timeStopped = time;
    }
    
    //GET A TIMER
    public long getTime()
    {
        startTime = new GregorianCalendar();
        return startTime.getTimeInMillis();
    }
    
    //CHEATS
    public void increaseLevel()
    {
        if(level <= 19)
        {
            level++;  
        }
    }
    
    // CREATE THE PLAYER
    public void createPlayer()
    {
        SpriteType sT = new SpriteType(PLAYER_TYPE);
        sT.setDimensions(50, 50);
        player = new Player(sT,0,0,0,0,pathXTileState.INVISIBLE_STATE.toString());
    }
    
    
    //CREATE POLICE SPRITES
    public void createPolice(BufferedImage p)
    {
        NPC = placement.getPolice(model.getLevelName());
        SpriteType sT = new SpriteType(POLICE_TYPE);
        sT.setDimensions(POLICE_SPRITE_HEIGHT, POLICE_SPRITE_WIDTH);
        for(int c = 1; c <= model.getNumPolice(); c++)
        {
            
            Police cop = new Police(sT);
            cop.setImage(p);
            int ix = (int)NPC.next();
            int iy = (int)NPC.next();
            Intersection i = model.findIntersection(ix,iy);
            cop.setPosition(i.x + VIEWABLE_GAMEWORLD_OFFSET, i.y);
            cop.setDataModel(this);
            police.add(cop);
            NPCs.add(cop);
        }
    }
    
    //CREATE ZOMBIE SPRITES
    public void createZombies(BufferedImage p)
    {
        SpriteType sT = new SpriteType(ZOMBIE_TYPE);
        sT.setDimensions(ZOMBIE_SPRITE_HEIGHT, ZOMBIE_SPRITE_WIDTH);
        for(int z = 1; z <= model.getNumZombies(); z++)
        {
            
            Zombie zom = new Zombie(sT);
            zom.setImage(p);
            int ix = (int)NPC.next();
            int iy = (int)NPC.next();
            Intersection i = model.findIntersection(ix,iy);
            zom.setPosition(i.x + VIEWABLE_GAMEWORLD_OFFSET, i.y);
            zom.setDataModel(this);
            zombie.add(zom);
            NPCs.add(zom);
        }     
    }
    
    //CREATE BANDIT SPRITES
    public void createBandits(BufferedImage p)
    {
        SpriteType sT = new SpriteType(BANDIT_TYPE);
        sT.setDimensions(BANDIT_SPRITE_HEIGHT, BANDIT_SPRITE_WIDTH);
        for(int b = 1; b <= model.getNumBandits(); b++)
        {
            
            Bandit bandit = new Bandit(sT);
            bandit.setImage(p);
            int ix = (int)NPC.next();
            int iy = (int)NPC.next();
            Intersection i = model.findIntersection(ix,iy);
            bandit.setPosition(i.x + VIEWABLE_GAMEWORLD_OFFSET, i.y);
            bandit.setDataModel(this);
            bandits.add(bandit);
            NPCs.add(bandit);
        }     
    }
    

    public void clearGame()
    {
        police.clear();
        zombie.clear();
        bandits.clear();
        gameWon = false;
 //       createPlayer();
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
               if(currentSpecial.compareTo(pathXSpecialsType.GREEN_LIGHT.toString()) == 0)
               {
                   if(!guess.isOpen())
                   {
                       guess.toggleOpen();
                       currentSpecial = NONE;
                       guess.toggleTime = getTime() + GREEN_LIGHT_TIME;
                   }
               }
               else if(currentSpecial.compareTo(pathXSpecialsType.RED_LIGHT.toString()) == 0)
               {
                   if(guess.isOpen())
                   {
                       guess.toggleOpen();
                       currentSpecial = NONE;
                       guess.toggleTime = getTime() + GREEN_LIGHT_TIME;
                   }
               }
               else if(!player.isMoving())
               {   player.setPath(model.findShortestPathToIntersection(guess));
                 //  player.setTarget(guess.x + VIEWABLE_GAMEWORLD_OFFSET - viewport.getViewportX(),guess.y - viewport.getViewportY());
                //    player.startMovingToTarget(4);
               }
            }
            if(currentSpecial.compareTo(pathXSpecialsType.FLAT_TIRE.toString()) == 0  || currentSpecial.compareTo(pathXSpecialsType.EMPTY_TANK.toString()) == 0)
            {
                Iterator NPC = NPCs.iterator();
                Sprite s;
                Sprite selectedSprite = null;
                int xNow = x;
                int yNow = y;
                while(NPC.hasNext())
                {
                    s = (Sprite)NPC.next();
                    if(s.getX() < xNow + 35 && s.getX() > xNow - 35 && s.getY() < yNow + 35 && s.getY() > yNow - 35)
                    {
                        selectedSprite = s;
                    }
                }
                if(selectedSprite != null)
                {
                    Iterator p = police.iterator();
                    while(p.hasNext())
                    {
                        s = (Sprite)p.next();
                        if(selectedSprite.getID() == s.getID())
                        {
                           Police temp = (Police)s;
                           temp.setStopped(getTime(), timeStopped);                          
                        }          
                    }
                    
                    Iterator z = zombie.iterator();
                    while(z.hasNext())
                    {
                        s = (Sprite)z.next();
                        if(selectedSprite.getID() == s.getID())
                        {
                           Zombie temp = (Zombie)s;
                           temp.setStopped(getTime(), timeStopped);                          
                        }          
                    }
                    
                    Iterator b = bandits.iterator();
                    while(b.hasNext())
                    {
                        s = (Sprite)b.next();
                        if(selectedSprite.getID() == s.getID())
                        {
                           Bandit temp = (Bandit)s;
                           temp.setStopped(getTime(), timeStopped);                          
                        }          
                    }
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
