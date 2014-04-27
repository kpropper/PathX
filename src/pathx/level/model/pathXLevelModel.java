package pathx.level.model;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;

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
    
    public pathXLevelModel()
    {
        level = new pathXLevel();
//        editMode = PXLE_EditMode.NOTHING_SELECTED;
        viewport = new pathXLevelViewport();
//        levelBeingEdited = false;
//        startRoadIntersection = null;
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
}
