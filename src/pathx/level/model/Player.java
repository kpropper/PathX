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
import pathx.level.model.Intersection;
import java.util.Iterator;
import mini_game.Viewport;
import pathx.data.pathXDataModel;
import java.math.RoundingMode;
import pathx.data.pathXSpecialsType;

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
    
    private Intersection next;
    
    private Intersection now;
    
    private Intersection last;
    
    private Iterator pathIt;
    
    private pathXDataModel data;
    
    private pathXLevelModel model;
    
    private int speed = 100;
    
    private Viewport viewport;
    
    
    //IS THE PLAYER CURRENTLY MOVING
    boolean movingToTarget;
    
    
    public Player(SpriteType initSpriteType, float initX, float initY, float initVx, float initVy, String initState){
        
        super(initSpriteType, initX, initY,initVx,initVy, initState);
        
        path = new ArrayList();
        movingToTarget = false;
    }
    
    public boolean isMoving()   {   return movingToTarget;   }
    public float getTargetX()   {   return targetX;  }
    public float getTargetY()   {   return targetY;  }
    public Iterator getPathIterator() {return path.iterator(); }
    public int GetSpeed()       {   return speed;   }
    
    public void setImage(BufferedImage img)
    {
        SpriteType sT = this.getSpriteType();
        sT.setDimensions(PLAYER_SPRITE_HEIGHT, PLAYER_SPRITE_WIDTH);
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
    
    public void setPath(ArrayList<Intersection> newPath)
    {
        path = newPath;
        pathIt = path.iterator();
    }
    
    public void setDataModel(pathXDataModel initModel)
    {
        data = initModel;
        viewport = data.getViewport();
        model = data.getLevelModel();
        path.clear();
        targetX = this.x;
        targetY = this.y;
    }
    
    public void startMovingToTarget (int velocity)
    {
        movingToTarget = true;
        
        //Calculate the path to the target
        float diffX = targetX - x /*+ (VIEWABLE_GAMEWORLD_OFFSET)*/;
        float diffY = targetY - y;
        float tanResult = diffY/diffX;
        float angleInRadians = (float)Math.atan(tanResult);

        
        //COMPUTE THE X VELOCITY COMPONENT
        vX = (float)(velocity * Math.cos(angleInRadians));
        
        //CLAMP THE VELOCITY IN CASE OF NEGATIVE ANGLES
        if ((diffX < 0) && (vX > 0)) vX *= -1;
        if ((diffX > 0) && (vX < 0)) vX *= -1;
        
        //COMPUTE THE Y VELOCITY COMPONENT
        vY = (float)(velocity * Math.sin(angleInRadians));
        
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
            movingToTarget = false;
        }
    }
    
    public float calculateDistanceToTarget()
    {
        // GET THE X-AXIS DISTANCE TO GO
        float diffX = targetX - x;
        
        // AND THE Y-AXIS DISTANCE TO GO
        float diffY = targetY - y;
        
        // AND EMPLOY THE PYTHAGOREAN THEOREM TO CALCULATE THE DISTANCE
        float distance = (float)Math.sqrt((diffX * diffX) + (diffY * diffY));
        
        // AND RETURN THE DISTANCE
        return distance;
    }
    
    public void decreaseSpeed()
    {
        speed -= 10;
    }
    
    public void increaseSpeed()
    {
        speed += 20;
    }
    
    @Override
    public void update(MiniGame game)
    {
        now = model.findIntersection((int)x - VIEWABLE_GAMEWORLD_OFFSET + viewport.getViewportX(),(int)y + viewport.getViewportY());
        if(now == model.getDestination())
        {
            if(!data.isGameWon())
            {
                data.setGameWon();
            }
        }
        
        if(data.getCurrentSpecial().compareTo(pathXSpecialsType.FLYING.toString())==0)
        {
            now = model.findIntersection(data.getMousePressedX() - VIEWABLE_GAMEWORLD_OFFSET + viewport.getViewportX(),data.getMousePressedY() + viewport.getViewportY());
            if(now != null)
            {
                targetX = now.x + VIEWABLE_GAMEWORLD_OFFSET - viewport.getViewportX();
                targetY = now.y - viewport.getViewportY();
                path.clear();
                this.x = now.x + VIEWABLE_GAMEWORLD_OFFSET - viewport.getViewportX();
                this.y = now.y - viewport.getViewportY();
                data.setSpecial(NONE);
            }
        }     
        else if(!movingToTarget && !path.isEmpty() && pathIt.hasNext())
        {
            next = (Intersection)pathIt.next();
            targetX = next.x + VIEWABLE_GAMEWORLD_OFFSET - viewport.getViewportX();
            targetY = next.y - viewport.getViewportY();
            startMovingToTarget(Math.round(speed/10 * data.getGameSpeed()));
            if(!pathIt.hasNext())
            {
                path.clear();
            }
        }
        if (calculateDistanceToTarget() < MAX_TILE_VELOCITY)
        {
            vX = 0;
            vY = 0;
            x = targetX;
            y = targetY;
            movingToTarget = false;
        }
        // OTHERWISE, JUST DO A NORMAL UPDATE, WHICH WILL CHANGE ITS POSITION
        // USING ITS CURRENT VELOCITY.
        else
        {
            super.update(game);
        }
    }  
}
