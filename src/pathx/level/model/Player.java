package pathx.level.model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import mini_game.MiniGame;
import mini_game.Sprite;
import mini_game.SpriteType;
import pathx.PathX;
import static pathx.pathXConstants.*;
import properties_manager.PropertiesManager;
import pathx.PathX.pathXPropertyType;
import pathx.ui.pathXTileState;
/**
 *
 * @author Karl
 */
public class Player extends Sprite{

    //PATH FOR THE PLAYER TO TRAVEL
    private ArrayList<Intersection> path;
    
    //COORDINATES OF THE TARGET LOCATION
    private float targetX;
    private float targetY;
    
    //IS THE PLAYER CURRENTLY MOVING
    boolean moving;
    
    
    public Player(SpriteType initSpriteType, float initX, float initY, float initVx, float initVy, String initState){
        
        super(initSpriteType, initX, initY,initVx,initVy, initState);
        
        path = new ArrayList();
        moving = false;
    }
    
    public boolean isMoving()   {   return moving;   }
    public float getTargetX()   {   return targetX;  }
    public float getTargetY()   {   return targetY;  }
    
    public void setImage(BufferedImage img)
    {
        SpriteType sT = this.getSpriteType();
        sT.addState(pathXTileState.VISIBLE_STATE.toString(), img);
        sT.addState(pathXTileState.MOUSE_OVER_STATE.toString(), img);
        this.setState(pathXTileState.VISIBLE_STATE.toString());
        this.setEnabled(true);
    }
    
    public void setTarget(float x, float y)
    {
        targetX = x;
        targetY = y;
    }
    
    public void move (int velocity)
    {
        moving = true;
        
        //Calculate the path to the target
        float diffX = targetX - this.x + (VIEWABLE_GAMEWORLD_OFFSET);
        float diffY = targetY - this.y;
        float tanResult = diffX/diffY;
        float angleInRadians = (float)Math.atan(tanResult);

        
        //COMPUTE THE X VELOCITY COMPONENT
        vX = (float)(velocity * Math.cos(angleInRadians));
        
        //CLAMP THE VELOCITY IN CASE OF NEGATIVE ANGLES
        if ((diffX < 0) && (vX > 0)) vX *= -1;
        if ((diffX > 0) && (vX < 0)) vX *= -1;
        
        //COMPUTE THE Y VELOCITY COMPONENT
        vY = (float)(velocity * Math.cos(angleInRadians));
        
        //CLAMP THE VELOCITY IN CASE OF NEGATIVE ANGLES
        if ((diffY < 0) && (vY > 0)) vY *= -1;
        if ((diffY > 0) && (vY < 0)) vY *= -1;
    }
    
    public void setPosition(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public void checkPosition()
    {
        if((targetX <= x + 30 && targetX >= x - 30) && (targetY >= y - 30 && targetY <= y + 30))
        {
            moving = false;
        }
    }
}
