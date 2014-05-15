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
import java.util.Random;

/**
 *
 * @author Karl
 */
public class Zombie extends Sprite{
    
    //COORDINATES OF THE TARGET LOCATION
    private float targetX;
    private float targetY;
    
    private Intersection first;
    
    private Intersection now;
    
    private Intersection next;
       
    private pathXDataModel data;
    
    private Viewport viewport;
    
    private pathXLevelModel levelModel;
    
    private ArrayList<Intersection> path = new ArrayList();
    
    private ArrayList<Intersection> nextStops = new ArrayList();
    
    private Iterator pathIt;
    
    private Road road;
    
    private Random caller = new Random();
    
    
    //IS THE PLAYER CURRENTLY MOVING
    boolean movingToTarget;
    
    
    public Zombie(SpriteType sT){
        
        super(sT, 0, 0,0,0, pathXTileState.VISIBLE_STATE.toString());

        movingToTarget = false;
    }
    
    public boolean isMoving()   {   return movingToTarget;   }
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
    
    public void setDataModel(pathXDataModel initModel)
    {
        data = initModel;
        viewport = data.getViewport();
        levelModel = data.getLevelModel();
        getZombiePath();
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
    
    public void getZombiePath()
    {
        first = levelModel.findIntersection((int)x - VIEWABLE_GAMEWORLD_OFFSET + viewport.getViewportX(),(int)y + viewport.getViewportY());
        path.add(first);
        now = first;
        next = data.getStart();
        while((first.x != next.x) && (first.y != next.y))
        {
            nextStops = levelModel.getNeighbors(now);
            next = nextStops.get(caller.nextInt((nextStops.size())));
            path.add(next);
            now = next;
        }
        newIter();
    }
    
    public void newIter()
    {
        pathIt = path.iterator();
    }
    
    @Override
    public void update(MiniGame game)
    {
        if(!movingToTarget)
        {
            if(pathIt.hasNext())
            {
                next = (Intersection)pathIt.next();
                targetX = next.x + VIEWABLE_GAMEWORLD_OFFSET - viewport.getViewportX();
                targetY = next.y - viewport.getViewportY();
                startMovingToTarget(Math.round(data.getGameSpeed()));
            }
            else
            {
                newIter();
            }
   //         now = levelModel.findIntersection((int)x - VIEWABLE_GAMEWORLD_OFFSET + viewport.getViewportX(),(int)y + viewport.getViewportY());
  //          nextStops = levelModel.getNeighbors(now);
  //          next = nextStops.get(caller.nextInt((nextStops.size())));
  //          targetX = next.x + VIEWABLE_GAMEWORLD_OFFSET - viewport.getViewportX();
  //          targetY = next.y + viewport.getViewportY();
  //          path = levelModel.getRoad(now, next);
  //          startMovingToTarget(1);
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
