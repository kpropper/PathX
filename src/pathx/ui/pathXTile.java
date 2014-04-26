package pathx.ui;

import java.util.ArrayList;
import mini_game.MiniGame;
import mini_game.Sprite;
import mini_game.SpriteType;
import static pathx.pathXConstants.*;

/**
 * This class represents a single tile in the game world.
 * 
 * @author Richard McKenna
 */
public class pathXTile extends Sprite
{  
    /**
     * This constructor initializes this tile for use, including all the
     * sprite-related data from its ancestor class, Sprite.
     */
   public pathXTile(SpriteType initSpriteType,float initX,float initY,float initVx,float initVy,String initState,int initTileId)
    {
        // SEND ALL THE Sprite DATA TO A Sprite CONSTRUCTOR
        super(initSpriteType, initX, initY, initVx, initVy, initState);
    }
}