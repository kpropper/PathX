package pathx.level.model;

import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author Karl
 */
public class pathXLevelPlacement {
    
    //LOSANGLES ARRAYS
    private ArrayList<Integer> LAPD = new ArrayList();
    
    public pathXLevelPlacement()
    {
        LAPD.add(LOSANGLES_P_1_X_1);
        LAPD.add(LOSANGLES_P_1_Y_1);
    }
    
    public Iterator getPolice(String name)
    {
     LevelNames levelName = LevelNames.valueOf(name.toUpperCase());
        switch(levelName){
            case LOSANGLES:
                return LAPD.iterator();
            default:
                return null;
        }
    }
    
    //LOSANGLES INFO
    public static final Integer LOSANGLES_P_1_X_1 = 288;
    public static final Integer LOSANGLES_P_1_Y_1 = 87;
    
    
    public enum LevelNames{
        LOSANGLES
    }
}
