package pathx.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.swing.JPanel;
import mini_game.MiniGame;
import mini_game.Sprite;
import mini_game.SpriteType;
import mini_game.Viewport;
import properties_manager.PropertiesManager;
import pathx.data.pathXDataModel;
import static pathx.pathXConstants.*;
import pathx.PathX.pathXPropertyType;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

//import pathx.data.pathXRecord;


/**
 *
 * @author Karl Propper
 */
public class pathXPanel extends JPanel{
     // THIS IS ACTUALLY OUR pathX APP, WE NEED THIS
    // BECAUSE IT HAS THE GUI STUFF THAT WE NEED TO RENDER
    private MiniGame game;
    
    // AND HERE IS ALL THE GAME DATA THAT WE NEED TO RENDER
    private pathXDataModel data;
    
    
    
    /**
     * This constructor stores the game and data references,
     * which we'll need for rendering.
     * 
     * @param initGame pathX game that is using
     * this panel for rendering.
     * 
     * @param initData pathX game data.
     */
    public pathXPanel(MiniGame initGame, pathXDataModel initData)
    {
        game = initGame;
        data = initData;
    }


    /**
     * This is where rendering starts. This method is called each frame, and the
     * entire game application is rendered here with the help of a number of
     * helper methods.
     * 
     * @param g The Graphics context for this panel.
     */
    @Override
    public void paintComponent(Graphics g)
    {
        try
        {
            // MAKE SURE WE HAVE EXCLUSIVE ACCESS TO THE GAME DATA
            game.beginUsingData();
        
            // CLEAR THE PANEL
            super.paintComponent(g);
        
            // RENDER THE BACKGROUND, WHICHEVER SCREEN WE'RE ON
            renderBackground(g);
            
            //RENDER THE MAP IF THE CURRENT STATE IN LEVEL SELECT
            Sprite map = game.getGUIDecor().get(MAP_TYPE);
            if(map.isEnabled())
            {
                renderMap(g);
            }

            //RENDER THE DIALOGS
            renderDialogs(g);
            

            // AND THE BUTTONS AND DECOR
            renderGUIControls(g);
            
        }
        finally
        {
            // RELEASE THE LOCK
            game.endUsingData();    
        }
    }
    
    // RENDERING HELPER METHODS
        // - renderBackground
        // - renderGUIControls
        // - renderTiles
        // - renderDialogs
        // - renderDebuggingText
    
    /**`
     * Renders the background image, which is different depending on the screen. 
     * 
     * @param g the Graphics context of this panel.
     */
    public void renderBackground(Graphics g)
    {
        // THERE IS ONLY ONE CURRENTLY SET
        Sprite bg = game.getGUIDecor().get(BACKGROUND_TYPE);
        renderSprite(g, bg);
    }

    /**
     * Renders all the GUI decor and buttons.
     * 
     * @param g this panel's rendering context.
     */
    public void renderGUIControls(Graphics g)
    {
        // GET EACH DECOR IMAGE ONE AT A TIME
        Collection<Sprite> decorSprites = game.getGUIDecor().values();
        for (Sprite s : decorSprites)
        {
            if (s.getSpriteType().getSpriteTypeID() != BACKGROUND_TYPE)
                renderSprite(g, s);
        }
        
        // AND NOW RENDER THE BUTTONS
        Collection<Sprite> buttonSprites = game.getGUIButtons().values();
        for (Sprite s : buttonSprites)
        {
            renderSprite(g, s);
        }
    }
    
    public void renderMap(Graphics g)
    {
           Viewport viewport = data.getViewport();
           int x  = viewport.getViewportX();
           int y = viewport.getViewportY();
           
            BufferedImage img;
            BufferedImage tmp;
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            String imgPath = props.getProperty(pathXPropertyType.PATH_IMG);
           img = game.loadImage(imgPath + props.getProperty(pathXPropertyType.IMAGE_MAP));
           tmp = img.getSubimage(x, y, MAP_WIDTH, MAP_HEIGHT);
           g.drawImage(tmp, 0, 100, null);
    }
       
    /**
     * Renders all the game tiles, doing so carefully such
     * that they are rendered in the proper order.
     * 
     * @param g the Graphics context of this panel.
     */
    public void renderTiles(Graphics g)
    {

    }

    /**
     * Helper method for rendering the tiles that are currently moving.
     * 
     * @param g Rendering context for this panel.
     * 
     * @param tileToRender Tile to render to this panel.
     */
    public void renderTile(Graphics g, pathXTile tileToRender)
    {
   //     // ONLY RENDER VISIBLE TILES
   //     if (!tileToRender.getState().equals(pathXTileState.INVISIBLE_STATE.toString()))
   //     {
   //         Viewport viewport = data.getViewport();
    //        int correctedTileX = (int)(tileToRender.getX());
      //     int correctedTileY = (int)(tileToRender.getY());

            // THEN THE TILE IMAGE
        //    SpriteType bgST = tileToRender.getSpriteType();
    //        Image img = bgST.getStateImage(tileToRender.getState());
    //        g.drawImage(img,    correctedTileX, 
    //                            correctedTileY, 
    //                            bgST.getWidth(), bgST.getHeight(), null); 
    //    }
    }
    
    /**
     * Renders the game dialog boxes.
     * 
     * @param g This panel's graphics context.
     */
    public void renderDialogs(Graphics g)
    {
        // GET EACH DECOR IMAGE ONE AT A TIME
        Collection<Sprite> dialogSprites = game.getGUIDialogs().values();
        for (Sprite s : dialogSprites)
        {
            // RENDER THE DIALOG, NOTE IT WILL ONLY DO IT IF IT'S VISIBLE
            renderSprite(g, s);
        }
    }
    
    /**
     * Renders the s Sprite into the Graphics context g. Note
     * that each Sprite knows its own x,y coordinate location.
     * 
     * @param g the Graphics context of this panel
     * 
     * @param s the Sprite to be rendered
     */
    public void renderSprite(Graphics g, Sprite s)
    {
        // ONLY RENDER THE VISIBLE ONES
        if (!s.getState().equals(pathXTileState.INVISIBLE_STATE.toString()))
        {
            SpriteType bgST = s.getSpriteType();
            Image img = bgST.getStateImage(s.getState());
            g.drawImage(img, (int)s.getX(), (int)s.getY(), bgST.getWidth(), bgST.getHeight(), null); 
        }
    }
}
