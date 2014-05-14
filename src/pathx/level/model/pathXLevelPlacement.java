package pathx.level.model;

import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author Karl
 */
public class pathXLevelPlacement {
    
    //LOSANGLES ARRAYS
    private ArrayList<Integer> LA = new ArrayList();
    private ArrayList<Integer> SF = new ArrayList();
    private ArrayList<Integer> SJ = new ArrayList();
    
    public pathXLevelPlacement()
    {
        //ADD LA POSITIONS
        LA.add(LOSANGLES_P_1_X);
        LA.add(LOSANGLES_P_1_Y);
        
        //ADD SF POSITIONS
        SF.add(SANFRANCISCO_P_1_X);
        SF.add(SANFRANCISCO_P_1_Y);
        SF.add(SANFRANCISCO_Z_1_X);
        SF.add(SANFRANCISCO_Z_1_Y);
        
        //ADD SJ POSITIONS
        SJ.add(SANJOSE_P_1_X);
        SJ.add(SANJOSE_P_1_Y);
        SJ.add(SANJOSE_P_2_X);
        SJ.add(SANJOSE_P_2_Y);
        SJ.add(SANJOSE_B_1_X);
        SJ.add(SANJOSE_B_1_Y);
    }
    
    public Iterator getPolice(String name)
    {
     LevelNames levelName = LevelNames.valueOf(name.toUpperCase());
        switch(levelName){
            case LOSANGLES:
                return LA.iterator();
            case SANFRANCISICO:
                return SF.iterator();
            case SANJOSE:
                return SJ.iterator();
            default:
                return null;
        }
    }
    
    //LOSANGLES INFO
    public static final Integer LOSANGLES_P_1_X = 288;
    public static final Integer LOSANGLES_P_1_Y = 87;
    
    //SANFRANCISCO INFO
    public static final Integer SANFRANCISCO_P_1_X = 587;
    public static final Integer SANFRANCISCO_P_1_Y = 54;
    public static final Integer SANFRANCISCO_Z_1_X = 336;
    public static final Integer SANFRANCISCO_Z_1_Y = 45;  
    
    //SANJOSE
    public static final Integer SANJOSE_P_1_X = 695;
    public static final Integer SANJOSE_P_1_Y = 81;
    public static final Integer SANJOSE_P_2_X = 220;
    public static final Integer SANJOSE_P_2_Y = 820;
    public static final Integer SANJOSE_B_1_X = 700;
    public static final Integer SANJOSE_B_1_Y = 820;
    
    
    //SANFRANCISCO INFO
    
    
    public enum LevelNames{
        LOSANGLES,
        SANFRANCISICO,
        SANJOSE
    }
}
