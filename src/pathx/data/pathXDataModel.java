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
import mini_game.SpriteType;
import properties_manager.PropertiesManager;
import static pathx.pathXConstants.*;
import pathx.ui.pathXMiniGame;
import pathx.ui.pathXPanel;
import pathx.ui.pathXTileState;
import pathx.level.model.pathXLevelModel;


/**
 *
 * @author Karl Propper
 */
public class pathXDataModel extends MiniGameDataModel {
    // THIS CLASS HAS A REFERERENCE TO THE MINI GAME SO THAT IT
    // CAN NOTIFY IT TO UPDATE THE DISPLAY WHEN THE DATA MODEL CHANGES
    private MiniGame miniGame;
    
    private pathXLevelModel model;
    


    /**
     * Constructor for initializing this data model, it will create the data
     * structures for storing tiles, but not the tile grid itself, that is
     * dependent on file loading, and so should be subsequently initialized.
     *
     * @param initMiniGame The Sorting Hat game UI.
     */
    public pathXDataModel(MiniGame initMiniGame)
    {
        // KEEP THE GAME FOR LATER
        miniGame = initMiniGame;

    }



    public void initLevel(pathXLevelModel initLevel)
    {
        model = initLevel;
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
