package pathx.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.swing.JPanel;
import mini_game.MiniGame;
import mini_game.Sprite;
import mini_game.SpriteType;
import mini_game.Viewport;
import pathx.ui.pathXMiniGame;
import properties_manager.PropertiesManager;
import pathx.data.pathXDataModel;
import static pathx.pathXConstants.*;
import pathx.PathX.pathXPropertyType;
import pathx.level.view.pathXLevelCanvas;
import pathx.level.model.pathXLevelModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import pathx.file.pathXFileIO;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.util.HashMap;
import java.util.Iterator;
import pathx.level.model.Road;
import pathx.level.model.Intersection;




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
    
    pathXFileIO levelIO = new pathXFileIO();
    pathXLevelModel model = new pathXLevelModel();

    
    private int renderGameField = 0;
 //   pathXLevelCanvas canvas;
    
    private Viewport viewport;
    
    private pathXLevelSelector levelPath;
    
    private String imgPath;
    
    
    
    /**
     * This constructor stores the game and data references,
     * which we'll need for rendering.
     * 
     * @param initGame pathX game that is using
     * this panel for rendering.
     * 
     * @param initData pathX game data.
     */
    public pathXPanel(MiniGame initGame, pathXDataModel initData, pathXLevelSelector initLevelSelector)
    {
        game = initGame;
        data = initData;
        levelPath = initLevelSelector;
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
                renderGameField = 0;
                renderMap(g);

            }
            else if(data.inProgress())
            {
                    renderGame(g);
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
           viewport = data.getViewport();
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
    
    public void renderGame(Graphics g)
    {
        viewport = data.getViewport();
        BufferedImage img;
        if (renderGameField == 0)
        {
            renderGameField++;
            File testFile = new File(levelPath.getLevelPath());
            if(testFile.canRead())
            {
             levelIO.loadLevel(testFile, model);
            }
            data.initLevel(model);
            img = game.loadImage(PATH_LEVELS + model.getBackgroundImageName());
            initGameRender();
            viewport.setGameWorldSize(img.getWidth(), img.getHeight());
            viewport.setViewportSize(440, 480);
            while(viewport.getViewportX() != 0) viewport.scroll(-1, 0);
            while(viewport.getViewportY() != 0) viewport.scroll(0, -1);
            
            pathXGameController editLevelHandler = new pathXGameController(model);
            this.addMouseListener(editLevelHandler);
            this.addMouseMotionListener(editLevelHandler);
        }
        
        int x  = viewport.getViewportX();
        int y = viewport.getViewportY();
        
        img = game.loadImage(PATH_LEVELS + model.getBackgroundImageName());
        BufferedImage tmp;
        tmp = img.getSubimage(x, y, 440, 480);
        g.drawImage(tmp, 200, 0, null);
        
        // WE'LL USE THE Graphics2D FEATURES, WHICH IS 
        // THE ACTUAL TYPE OF THE g OBJECT
        Graphics2D g2 = (Graphics2D) g;
        
        //BUTTONS AND DIOLOGS ARE RERENDERED DUE TO CLIPPPING OF THE ROADS
        renderDialogs(g);
        renderGUIControls(g);
        
        //RENDER THE ROADS TODO:CLEAN UP DISPLAY
        renderRoads(g2);
        
        //RENDERS THE INTERSECTIONS
        renderIntersections(g2);

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
    
    
    
    
    
    
    
    //USED FOR RENDERING ROADS AND INTERSECTIONS
    // WE'LL RECYCLE THESE DURING RENDERING
    Ellipse2D.Double recyclableCircle;
    Line2D.Double recyclableLine;
    HashMap<Integer, BasicStroke> recyclableStrokes;
    int triangleXPoints[] = {-ONE_WAY_TRIANGLE_WIDTH/2,  -ONE_WAY_TRIANGLE_WIDTH/2,  ONE_WAY_TRIANGLE_WIDTH/2};
    int triangleYPoints[] = {ONE_WAY_TRIANGLE_WIDTH/2, -ONE_WAY_TRIANGLE_WIDTH/2, 0};
    GeneralPath recyclableTriangle;
    
    /**
     * Constructor prepares for rendering.
     */
    public void initGameRender()
    {

        // MAKE THE RENDER OBJECTS TO BE RECYCLED
        recyclableCircle = new Ellipse2D.Double(0, 0, INTERSECTION_RADIUS * 2, INTERSECTION_RADIUS * 2);
        recyclableLine = new Line2D.Double(0,0,0,0);
        recyclableStrokes = new HashMap();
        for (int i = 1; i <= 10; i++)
        {
            recyclableStrokes.put(i, new BasicStroke(i*2));
        }
        
        // MAKING THE TRIANGLE FOR ONE WAY STREETS IS A LITTLE MORE INVOLVED
        recyclableTriangle =  new GeneralPath(   GeneralPath.WIND_EVEN_ODD,
                                                triangleXPoints.length);
        recyclableTriangle.moveTo(triangleXPoints[0], triangleYPoints[0]);
        for (int index = 1; index < triangleXPoints.length; index++) 
        {
            recyclableTriangle.lineTo(triangleXPoints[index], triangleYPoints[index]);
        };
        recyclableTriangle.closePath();
    }

    // HELPER METHOD FOR RENDERING THE LEVEL ROADS
    private void renderRoads(Graphics2D g2)
    {
        // GO THROUGH THE ROADS AND RENDER ALL OF THEM
  

        Iterator<Road> it = model.roadsIterator();
        g2.setStroke(recyclableStrokes.get(INT_STROKE));
        while (it.hasNext())
        {
            Road road = it.next();
        //    if (model.isSelectedRoad(road))
                renderRoad(g2, road, INT_OUTLINE_COLOR);
        }
        

        // AND RENDER THE SELECTED ONE, IF THERE IS ONE
        Road selectedRoad = model.getSelectedRoad();
        if (selectedRoad != null)
        {
            renderRoad(g2, selectedRoad, HIGHLIGHTED_COLOR);
        }
    }
    
    // HELPER METHOD FOR RENDERING A SINGLE ROAD
    private void renderRoad(Graphics2D g2, Road road, Color c)
    {
        g2.setColor(c);
        int strokeId = road.getSpeedLimit()/10;

        // CLAMP THE SPEED LIMIT STROKE
        if (strokeId < 1) strokeId = 1;
        if (strokeId > 10) strokeId = 10;
        g2.setStroke(recyclableStrokes.get(strokeId));

        // LOAD ALL THE DATA INTO THE RECYCLABLE LINE
        recyclableLine.x1 = road.getNode1().x-viewport.getViewportX() + VIEWABLE_GAMEWORLD_OFFSET;
        recyclableLine.y1 = road.getNode1().y-viewport.getViewportY();
        recyclableLine.x2 = road.getNode2().x-viewport.getViewportX()+ VIEWABLE_GAMEWORLD_OFFSET;
        recyclableLine.y2 = road.getNode2().y-viewport.getViewportY();

        // AND DRAW IT
        g2.setClip(200, 0, 440, 480);
        g2.draw(recyclableLine);
        
        // AND IF IT'S A ONE WAY ROAD DRAW THE MARKER
        if (road.isOneWay())
        {
            this.renderOneWaySignalsOnRecyclableLine(g2);
        }
    }

    // HELPER METHOD FOR RENDERING AN INTERSECTION
    private void renderIntersections(Graphics2D g2)
    {
        Iterator<Intersection> it = model.intersectionsIterator();
        while (it.hasNext())
        {
            Intersection intersection = it.next();

            // ONLY RENDER IT THIS WAY IF IT'S NOT THE START OR DESTINATION
            // AND IT IS IN THE VIEWPORT
            if ((!model.isStartingLocation(intersection))
                    && (!model.isDestination(intersection))
                    && isCircleBoundingBoxInsideViewport(intersection.x, intersection.y, INTERSECTION_RADIUS))
            {
                // FIRST FILL
                if (intersection.isOpen())
                {
                    g2.setColor(OPEN_INT_COLOR);
                } else
                {
                    g2.setColor(CLOSED_INT_COLOR);
                }
                recyclableCircle.x = intersection.x - viewport.getViewportX() - INTERSECTION_RADIUS +  VIEWABLE_GAMEWORLD_OFFSET;
                recyclableCircle.y = intersection.y - viewport.getViewportY() - INTERSECTION_RADIUS;
                g2.fill(recyclableCircle);

                // AND NOW THE OUTLINE
                if (model.isSelectedIntersection(intersection))
                {
                    g2.setColor(HIGHLIGHTED_COLOR);
                } else
                {
                    g2.setColor(INT_OUTLINE_COLOR);
                }
                Stroke s = recyclableStrokes.get(INT_STROKE);
                g2.setStroke(s);
                g2.draw(recyclableCircle);
            }
        }

        // AND NOW RENDER THE START AND DESTINATION LOCATIONS
        Image startImage = model.getStartingLocationImage();
        Intersection startInt = model.getStartingLocation();
        renderIntersectionImage(g2, startImage, startInt);

        Image destImage = model.getDesinationImage();
        Intersection destInt = model.getDestination();
        renderIntersectionImage(g2, destImage, destInt);
    }

    // HELPER METHOD FOR RENDERING AN IMAGE AT AN INTERSECTION, WHICH IS
    // NEEDED BY THE STARTING LOCATION AND THE DESTINATION
    private void renderIntersectionImage(Graphics2D g2, Image img, Intersection i)
    {
        // CALCULATE WHERE TO RENDER IT
        int w = img.getWidth(null);
        int h = img.getHeight(null);
        int x1 = i.x-(w/2);
        int y1 = i.y-(h/2);
        int x2 = x1 + img.getWidth(null);
        int y2 = y1 + img.getHeight(null);
        
        // ONLY RENDER IF INSIDE THE VIEWPORT
        if (isRectInsideViewport(x1, y1, x2, y2))
        {
            g2.drawImage(img, x1 - viewport.getViewportX() + VIEWABLE_GAMEWORLD_OFFSET, y1 - viewport.getViewportY(), null);
        }        
    }
    
    // YOU'LL LIKELY AT THE VERY LEAST WANT THIS ONE. IT RENDERS A NICE
    // LITTLE POINTING TRIANGLE ON ONE-WAY ROADS
    private void renderOneWaySignalsOnRecyclableLine(Graphics2D g2)
    {
        // CALCULATE THE ROAD LINE SLOPE
        double diffX = recyclableLine.x2 - recyclableLine.x1;
        double diffY = recyclableLine.y2 - recyclableLine.y1;
        double slope = diffY/diffX;
        
        // AND THEN FIND THE LINE MIDPOINT
        double midX = (recyclableLine.x1 + recyclableLine.x2)/2.0;
        double midY = (recyclableLine.y1 + recyclableLine.y2)/2.0;
        
        // GET THE RENDERING TRANSFORM, WE'LL RETORE IT BACK
        // AT THE END
        AffineTransform oldAt = g2.getTransform();
        
        // CALCULATE THE ROTATION ANGLE
        double theta = Math.atan(slope);
        if (recyclableLine.x2 < recyclableLine.x1)
            theta = (theta + Math.PI);
        
        // MAKE A NEW TRANSFORM FOR THIS TRIANGLE AND SET IT
        // UP WITH WHERE WE WANT TO PLACE IT AND HOW MUCH WE
        // WANT TO ROTATE IT
        AffineTransform at = new AffineTransform();        
        at.setToIdentity();
        at.translate(midX, midY);
        at.rotate(theta);
        g2.setTransform(at);
        
        // AND RENDER AS A SOLID TRIANGLE
        g2.fill(recyclableTriangle);
        
        // RESTORE THE OLD TRANSFORM SO EVERYTHING DOESN'T END UP ROTATED 0
        g2.setTransform(oldAt);
    }
    
    public boolean isRectInsideViewport(int x1, int y1, int x2, int y2)
    {
        if (x2 < viewport.getViewportX()) return false;
        if (x1 > (viewport.getViewportX() + viewport.getViewportWidth())) return false;
        if (y2 < viewport.getViewportY()) return false;
        if (y1 > (viewport.getViewportY() + viewport.getViewportHeight())) return false;
        return true;
    }

    /**
     * Returns true if the circle argument's bounding box is inside the
     * viewport, false otherwise.
     */
    public boolean isCircleBoundingBoxInsideViewport(int centerX, int centerY, int radius)
    {
        return isRectInsideViewport(centerX-radius, centerY-radius, centerX+radius, centerY+radius);
    }
}
