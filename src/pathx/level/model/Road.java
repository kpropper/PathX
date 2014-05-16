package pathx.level.model;

/**
 * This class represents a road in level graph, which means it's 
 * basically a graph edge.
 * 
 * @author Richard McKenna
 */

public class Road {
    // THESE ARE THE EDGE'S NODES
    Intersection node1;
    Intersection node2;
    
    // false IF IT'S TWO-WAY, true IF IT'S ONE WAY
    boolean oneWay;
    
    boolean roadOpen = true;
    
    // ROAD SPEED LIMIT
    int speedLimit;
    
    float distance;

    // ACCESSOR METHODS
    public Intersection getNode1()  {   return node1;       }
    public Intersection getNode2()  {   return node2;       }
    public boolean isOneWay()       {   return oneWay;      }
    public int getSpeedLimit()      {   return speedLimit;  }
    public boolean roadOpen()       {   return roadOpen;    }
    
    // MUTATOR METHODS
    public void setNode1(Intersection node1)    {   this.node1 = node1;             }
    public void setNode2(Intersection node2)    {   this.node2 = node2;             }
    public void setOneWay(boolean oneWay)       {   this.oneWay = oneWay;           }
    public void setSpeedLimit(int speedLimit)   {   this.speedLimit = speedLimit;   }
    public void setOpen(boolean isOpen)         {   this.roadOpen = isOpen;         }

    public void calculateDistance()
    {
        // GET THE X-AXIS DISTANCE TO GO
        float diffX = node2.x - node1.x;
        
        // AND THE Y-AXIS DISTANCE TO GO
        float diffY = node2.y - node1.y;
        
        // AND EMPLOY THE PYTHAGOREAN THEOREM TO CALCULATE THE DISTANCE
        distance = (float)Math.sqrt((diffX * diffX) + (diffY * diffY));
    }
    
    /**
     * Builds and returns a textual representation of this road.
     */
    @Override
    public String toString()
    {
        return node1 + " - " + node2 + "(" + speedLimit + ":" + oneWay + ")";
    }
    
}
