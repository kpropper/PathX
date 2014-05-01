package pathx.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import pathx.level.model.pathXLevelModel;

/**
 * This is what allows us to interact with the game screen
 * @author Karl
 */
public class pathXGameController implements MouseListener, MouseMotionListener {
    
    //WE'LL NEED THIS TO INTERACT WITH THE LEVEL
    pathXLevelModel model;
    
    public pathXGameController(pathXLevelModel initModel)
    {
        model = initModel;
    }
    
    /**
     * Responds to when the user presses the mouse button on the canvas,
     * it may respond in a few different ways depending on what the 
     * current edit mode is.
     */
    @Override
    public void mousePressed(MouseEvent me)
    {
        
    }
    
    /**
     * This method is called when the user releases the mouse button
     * over the canvas. 
     */
    @Override
    public void mouseReleased(MouseEvent me)
    {
        
    }

    /**
     * This method will be used to respond to right-button mouse clicks
     * on intersections so that we can toggle them open or closed.
     */
    @Override
    public void mouseClicked(MouseEvent me)
    {

    }

    /**
     * This method is called every time the user moves the mouse. We
     * use this to keep track of where the mouse is at all times on
     * the canvas.
     */
    @Override
    public void mouseMoved(MouseEvent me)
    {

    }
    
    /**
     * This function is called when we drag the mouse across the canvas with
     * the mouse button pressed. We use this to drag items intersections
     * across the canvas.
     */
    @Override
    public void mouseDragged(MouseEvent me)
    {
 
    }
    
    // WE WON'T BE USING THESE METHODS, BUT NEED TO OVERRIDE
    // THEM TO KEEP THE COMPILER HAPPY
    @Override
    public void mouseEntered(MouseEvent me)     {}    
    public void mouseExited(MouseEvent me)      {}
}
