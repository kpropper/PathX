package pathx.level.model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import mini_game.MiniGame;
import pathx.PathX;
import mini_game.Sprite;
import pathx.data.pathXDataModel;
import mini_game.Viewport;
import pathx.level.model.Player;
import java.util.HashMap;
import static pathx.pathXConstants.*;
import properties_manager.PropertiesManager;
import java.awt.geom.Line2D;


/**
 *
 * @author Karl
 */
public class pathXLevelModel {
    
    // THIS IS THE LEVEL CURRENTLY LOADED
    pathXLevel level;
    
    pathXDataModel data;
    
    Player player;
    
    Viewport viewport;

    // DATA FOR RENDERING
//    pathXLevelViewport viewport;
    
 //   private MiniGame game;
    
    // WE ONLY NEED TO TURN THIS ON ONCE
    boolean levelBeingEdited;
    Image backgroundImage;
    Image startingLocationImage;
    Image destinationImage;
    
    
    // THE SELECTED INTERSECTION OR ROAD MIGHT BE EDITED OR DELETED
    // AND IS RENDERED DIFFERENTLY
    Intersection selectedIntersection;
    Road selectedRoad;
    
    // WE'LL USE THIS WHEN WE'RE ADDING A NEW ROAD
    Intersection startRoadIntersection;

    // IN CASE WE WANT TO TRACK MOVEMENTS
    int lastMouseX;
    int lastMouseY; 
    
    //GETS THE LOCATION OF IMAGES
    PropertiesManager props = PropertiesManager.getPropertiesManager();
    String imgPath = props.getProperty(PathX.pathXPropertyType.PATH_IMG);
    
    public pathXLevelModel(pathXDataModel initData)
    {
        level = new pathXLevel();
        data = initData;
        player = data.getPlayer();
        viewport = data.getViewport();
        startRoadIntersection = null;
    }
    
        // ACCESSOR METHODS
    public pathXLevel       getLevel()                  {   return level;                   }
    public boolean          isLevelBeingEdited()        {   return levelBeingEdited;        }
    public String           getBackgroundImageName()    {   return level.backgroundImageFileName;       }
    public Image            getBackgroundImage()        {   return backgroundImage;         }
    public Image            getStartingLocationImage()  {   return startingLocationImage;   }
    public Image            getDesinationImage()        {   return destinationImage;        }
    public Intersection     getSelectedIntersection()   {   return selectedIntersection;    }
    public Road             getSelectedRoad()           {   return selectedRoad;            }
    public Intersection     getStartRoadIntersection()  {   return startRoadIntersection;   }
    public int              getLastMouseX()             {   return lastMouseX;              }
    public int              getLastMouseY()             {   return lastMouseY;              }
    public int              getNumPolice()              {   return level.numPolice;         }
    public int              getNumZombies()             {   return level.numZombies;        }
    public int              getNumBandits()             {   return level.numBandits;        }
    public Intersection     getStartingLocation()       {   return level.startingLocation;  }
    public Intersection     getDestination()            {   return level.destination;       }
    public String           getLevelName()              {   return level.getLevelName();    }
    public String getLevelMoneyDisplay()
    {
        String moneyDisplay = "$"+ Integer.toString(level.getMoney());
        return moneyDisplay;
    }
//    public boolean          isDataUpdatedSinceLastSave(){   return dataUpdatedSinceLastSave;}    
    public boolean          isStartingLocation(Intersection testInt)  
    {   return testInt == level.startingLocation;           }
    public boolean isDestination(Intersection testInt)
    {   return testInt == level.destination;                }
    public boolean isSelectedIntersection(Intersection testIntersection)
    {   return testIntersection == selectedIntersection;    }
    public boolean isSelectedRoad(Road testRoad)
    {   return testRoad == selectedRoad;                    }
    
        // ITERATOR METHODS FOR GOING THROUGH THE GRAPH

    public Iterator intersectionsIterator()
    {
        ArrayList<Intersection> intersections = level.getIntersections();
        return intersections.iterator();
    }
    public Iterator roadsIterator()
    {
        ArrayList<Road> roads = level.roads;
        return roads.iterator();
    }
    
    public void updateBackgroundImage(String newBgImage)
    {

        // UPDATE THE LEVEL TO FIT THE BACKGROUDN IMAGE SIZE
        level.backgroundImageFileName = newBgImage;
        
        try
        {
            backgroundImage = ImageIO.read(new File(PATH_LEVELS + level.backgroundImageFileName));
        } catch (IOException e) {
        }

        int levelWidth = backgroundImage.getWidth(null);
        int levelHeight = backgroundImage.getHeight(null);
    }

    /**
     * Updates the image used for the starting location and forces rendering.
     */
    public void updateStartingLocationImage(String newStartImage)
    {
        level.startingLocationImageFileName = newStartImage;
        try
        {
            startingLocationImage = ImageIO.read(new File(PATH_LEVELS + level.startingLocationImageFileName));
        } catch (IOException e) {
        }
    }

    /**
     * Updates the image used for the destination and forces rendering.
     */
    public void updateDestinationImage(String newDestImage)
    {
        level.destinationImageFileName = newDestImage;
        try
        {
            destinationImage = ImageIO.read(new File(PATH_LEVELS + level.destinationImageFileName));
        } catch (IOException e) {
        }
    }

    
    public Intersection findIntersection(int x, int y)
    {
        for(Intersection i : level.intersections)
        {
            double distance = calculateDistanceBetweenPoints(i.x, i.y, x, y);
            if (distance < INTERSECTION_RADIUS)
            {
                return i;
            }
        }
        return null;
    }
    
    public double calculateDistanceBetweenPoints(int x1, int y1, int x2, int y2)
    {
        double diffXSquared = Math.pow(x1 - x2, 2);
        double diffYSquared = Math.pow(y1 - y2, 2);
        return Math.sqrt(diffXSquared + diffYSquared);
    }
    
    //PATH FINDING ALGORITHMS
    public ArrayList<Intersection> findShortestPathToIntersection(Intersection selectedIntersection)
    {
        boolean found = false;
        int selectedX = selectedIntersection.x;
        int selectedY = selectedIntersection.y;
        
        Road startRoad = findRoadAtSpriteLocation(player);
        Intersection currentIntersection = getClosestIntersection(startRoad, player);
        
        ArrayList<Road> roadsVisited = new ArrayList<Road>();
        ArrayList<Intersection> intersectionsVisited = new ArrayList<Intersection>();
        intersectionsVisited.add(currentIntersection);
        
        // LIST TO STORE THE SHORTEST PATH TO THE SELECTED INTERSECTION
        ArrayList<Intersection> path = new ArrayList();
        
        // SAVES ALL NODE1 AS KEY AND NODE2 AS VALUE IN A HASHMAP
        HashMap<Intersection, Intersection> intersections = new HashMap();
        for (Road r : level.roads)
        {
            Intersection i1 = r.node1;
            Intersection i2 = r.node2;
            intersections.put(i1, i2);
        }
        
        while (!found)
        {
            if (path.size() == 20) {return path;}
            ArrayList<Intersection> neighbors = getNeighbors(currentIntersection);
            if (neighbors.size() == 1)
            {
                currentIntersection = neighbors.get(0);
                path.add(neighbors.get(0));
                if (neighbors.get(0).x == selectedX && neighbors.get(0).y == selectedY)
                    return path;
            }
            else
            {
                for (Intersection i : neighbors)
                {
                    if (i.x == selectedX && i.y == selectedY)
                    {
                        path.add(i);
                        return path;
                    }
                }
                currentIntersection = getClosestNeighbor(neighbors, currentIntersection);
                path.add(currentIntersection);
                intersectionsVisited.add(currentIntersection);
            }
        }
        return path;
    }
    
    public Intersection getClosestIntersection(Road currentRoad, Player player)
    {
        int x1 = currentRoad.node1.x;
        int y1 = currentRoad.node1.y;
        int x2 = currentRoad.node2.x;
        int y2 = currentRoad.node2.y;
        int px = (int) player.getX() - VIEWABLE_GAMEWORLD_OFFSET - viewport.getViewportX();
        int py = (int) player.getY() - viewport.getViewportY();
        double distanceNode1 = calculateDistanceBetweenPoints(px, py, x1, y1);
        double distanceNode2 = calculateDistanceBetweenPoints(px, py, x2, y2);
        
        if (distanceNode1 < distanceNode2)
            return currentRoad.node1;
        else
            return currentRoad.node2;
    }
    
    public ArrayList<Intersection> getNeighbors(Intersection intersection)
    {
        ArrayList<Intersection> neighbors = new ArrayList();
        ArrayList<Road> roads = level.roads;
        int x = intersection.x;
        int y = intersection.y;
        
        for (int i = 0; i < roads.size(); i++)
        {
            if (roads.get(i).node1.x == x && roads.get(i).node1.y == y)
            {
                neighbors.add(roads.get(i).node2);
            } else if (roads.get(i).node2.x == x && roads.get(i).node2.y == y)
            {
                neighbors.add(roads.get(i).node1);
            }
        }
        return neighbors;
    }
    
    public Intersection getClosestNeighbor(ArrayList<Intersection> intersections, Intersection ci)
    {
        Intersection closestIntersection = intersections.get(0);
        if (intersections.size() > 0)
        {
            for (int i = 1; i < intersections.size(); i++)
            {
                Intersection intersection = intersections.get(i);
                if (calculateDistanceBetweenPoints(ci.x, ci.y, closestIntersection.x, closestIntersection.y) >
                    calculateDistanceBetweenPoints(ci.x, ci.y, intersection.x, intersection.y))
                {
                    closestIntersection = intersection;
                }
            }
        }
        return closestIntersection;
    }
    
    public ArrayList<Road> getNeighborRoads(Intersection intersection)
    {
        ArrayList<Road> neighborRoads = new ArrayList();
        ArrayList<Road> roads = level.roads;
        int x = intersection.x;
        int y = intersection.y;
        
        for (int i = 0; i < roads.size(); i++)
        {
            // IF EITHER NODE 1 OR NODE 2 OF ROAD IS A NEIGHBOR
            if ((roads.get(i).node1.x == x &&
                roads.get(i).node1.y == y) ||
                (roads.get(i).node2.x == x &&
                roads.get(i).node2.y == y))
            {
                neighborRoads.add(roads.get(i));
            }
        }
        return neighborRoads;
    }
    
    public Road getShortestRoad(ArrayList<Road> roads)
    {
        Road shortestRoad = roads.get(0);
        for (Road r : roads)
        {
            if (shortestRoad.distance > r.distance)
            {
                shortestRoad = r;
            }
        }
        return shortestRoad;
    }
    
    public Road getRoad(Intersection i1, Intersection i2)
    {
        for (Road r : level.roads)
        {
            if (((r.node1.x == i1.x && r.node1.y == i1.y) &&
                (r.node2.x == i2.x && r.node2.y == i2.y)) ||
                ((r.node1.x == i2.x && r.node1.y == i2.y) &&
                (r.node2.x == i1.x && r.node2.y == i1.y)))
            {
                return r;
            }
        }
        return null;
    }
    
    public Road findRoadAtSpriteLocation(Sprite s)
    {
        Iterator<Road> it = level.roads.iterator();
        Line2D.Double tempLine = new Line2D.Double();
        double smallestDistance = 100;
        Road closestRoad = new Road();
        double centerX = s.getX() + 17.5 - VIEWABLE_GAMEWORLD_OFFSET;
        double centerY = s.getY() + 17.5;
        while (it.hasNext())
        {
            Road r = it.next();
            tempLine.x1 = r.node1.x;
            tempLine.y1 = r.node1.y;
            tempLine.x2 = r.node2.x;
            tempLine.y2 = r.node2.y;
            double distance = tempLine.ptSegDist(centerX + viewport.getViewportX(), centerY + viewport.getViewportY());
            
            // IS IT CLOSE ENOUGH?
            if (distance <= smallestDistance/*23 INT_STROKE*/)
            {
                // SELECT IT
                //this.selectedRoad = r;
                //return r;
                smallestDistance = distance;
                closestRoad = r;
            }
        }
        // IS IT CLOSE ENOUGH?
     //   if (smallestDistance <= 30/*INT_STROKE*/)
    //    {
           // SELECT IT
           this.selectedRoad = closestRoad;
           return closestRoad;
     //   }
    //    return null;
    }
}
