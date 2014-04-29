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
import static pathx.pathXConstants.*;
import properties_manager.PropertiesManager;

/**
 *
 * @author Karl
 */
public class pathXLevelModel {
    
    // THIS IS THE LEVEL CURRENTLY LOADED
    pathXLevel level;
    
    // USED TO MANAGE WHAT THE USER IS CURRENTLY EDITING
//    PXLE_EditMode editMode;

    // DATA FOR RENDERING
    pathXLevelViewport viewport;
    
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
    String imgPath = "./img/";//props.getProperty(PathX.pathXPropertyType.PATH_IMG);
    
    public pathXLevelModel()
    {
        level = new pathXLevel();
//        editMode = PXLE_EditMode.NOTHING_SELECTED;
        viewport = new pathXLevelViewport();
//        levelBeingEdited = false;
        startRoadIntersection = null;
    }
    
        // ACCESSOR METHODS
    public pathXLevel       getLevel()                  {   return level;                   }
//    public PXLE_View        getView()                   {   return view;                    }
    public pathXLevelViewport         getViewport()               {   return viewport;                }
//    public PXLE_EditMode    getEditMode()               {   return editMode;                }
    public boolean          isLevelBeingEdited()        {   return levelBeingEdited;        }
    public Image            getBackgroundImage()        {   return backgroundImage;         }
    public Image            getStartingLocationImage()  {   return startingLocationImage;   }
    public Image            getDesinationImage()        {   return destinationImage;        }
    public Intersection     getSelectedIntersection()   {   return selectedIntersection;    }
    public Road             getSelectedRoad()           {   return selectedRoad;            }
    public Intersection     getStartRoadIntersection()  {   return startRoadIntersection;   }
    public int              getLastMouseX()             {   return lastMouseX;              }
    public int              getLastMouseY()             {   return lastMouseY;              }
    public Intersection     getStartingLocation()       {   return level.startingLocation;  }
    public Intersection     getDestination()            {   return level.destination;       }
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
        level.backgroundImageFileName = "D:\\Development\\NetBeansProjects\\PathX\\img\\DeathValleyBackground.png";//newBgImage;
   //     BufferedImage img = null;
        try
        {
            backgroundImage = ImageIO.read(new File(level.backgroundImageFileName));
        } catch (IOException e) {
        }
  //      backgroundImage = game.loadImage(/*imgPath +*/ level.backgroundImageFileName);//view.loadImage(LEVELS_PATH + level.backgroundImageFileName);
        int levelWidth = backgroundImage.getWidth(null);
        int levelHeight = backgroundImage.getHeight(null);
        viewport.setLevelDimensions(levelWidth, levelHeight);
   //     view.getCanvas().repaint();
    }

    /**
     * Updates the image used for the starting location and forces rendering.
     */
    public void updateStartingLocationImage(String newStartImage)
    {
        level.startingLocationImageFileName = newStartImage;
        try
        {
            startingLocationImage = ImageIO.read(new File(imgPath + level.startingLocationImageFileName));
        } catch (IOException e) {
        }
  //      startingLocationImage = game.loadImage(imgPath + level.startingLocationImageFileName);
  //      view.getCanvas().repaint();
    }

    /**
     * Updates the image used for the destination and forces rendering.
     */
    public void updateDestinationImage(String newDestImage)
    {
        level.destinationImageFileName = newDestImage;
        try
        {
            startingLocationImage = ImageIO.read(new File(imgPath + level.destinationImageFileName));
        } catch (IOException e) {
        }
 //       destinationImage = game.loadImage(imgPath + level.destinationImageFileName);
 //       view.getCanvas().repaint();
    }

    /**
     * Used for scrolling the viewport by (incX, incY). Note that it won't
     * let the viewport scroll off the level.
     */
    public void moveViewport(int incX, int incY)
    {
        // MOVE THE VIEWPORT
        viewport.move(incX, incY);

        // AND NOW FORCE A REDRAW
 //       view.getCanvas().repaint();
    }
}
