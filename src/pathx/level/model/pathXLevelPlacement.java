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
    private ArrayList<Integer> SD = new ArrayList();
    private ArrayList<Integer> PHE = new ArrayList();
    private ArrayList<Integer> EP = new ArrayList();
    private ArrayList<Integer> AU = new ArrayList();
    private ArrayList<Integer> SA = new ArrayList();
    private ArrayList<Integer> DA = new ArrayList();
    private ArrayList<Integer> HU = new ArrayList();
    private ArrayList<Integer> FW = new ArrayList();
    private ArrayList<Integer> DT = new ArrayList();
    private ArrayList<Integer> CO = new ArrayList();
    
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
        
        //ADD SD POSITIONS
        SD.add(SANDIEGO_P_1_X);
        SD.add(SANDIEGO_P_1_Y);
        SD.add(SANDIEGO_B_1_X);
        SD.add(SANDIEGO_B_1_Y);
        
        //ADD PHE POSITIONS
        PHE.add(PHOENIX_P_1_X);
        PHE.add(PHOENIX_P_1_Y);
        PHE.add(PHOENIX_B_1_X);
        PHE.add(PHOENIX_B_1_Y);
        PHE.add(PHOENIX_Z_1_X);
        PHE.add(PHOENIX_Z_1_Y);
        PHE.add(PHOENIX_Z_2_X);
        PHE.add(PHOENIX_Z_2_Y);
        
        //ADD THE ELPASO POSITIONS
        EP.add(ELPASO_Z_1_X);
        EP.add(ELPASO_Z_1_Y);
        EP.add(ELPASO_Z_2_X);
        EP.add(ELPASO_Z_2_Y);
        EP.add(ELPASO_Z_3_X);
        EP.add(ELPASO_Z_3_Y);
        EP.add(ELPASO_Z_4_X);
        EP.add(ELPASO_Z_4_Y);
        EP.add(ELPASO_Z_5_X);
        EP.add(ELPASO_Z_5_Y);
        EP.add(ELPASO_Z_6_X);
        EP.add(ELPASO_Z_6_Y);
        EP.add(ELPASO_Z_7_X);
        EP.add(ELPASO_Z_7_Y);
        
        //ADD AUSTIN POSITIONS
        AU.add(AUSTIN_P_1_X);
        AU.add(AUSTIN_P_1_Y);
        AU.add(AUSTIN_B_1_X);
        AU.add(AUSTIN_B_1_Y);
        
        //ADD SANANTONIO POSITIONS
        SA.add(SANANTONIO_P_1_X);
        SA.add(SANANTONIO_P_1_Y);
        SA.add(SANANTONIO_Z_1_X);
        SA.add(SANANTONIO_Z_1_Y);
        
        // ADD DALLAS POSITIONS
        DA.add(DALLAS_P_1_X);
        DA.add(DALLAS_P_1_Y);
        DA.add(DALLAS_P_2_X);
        DA.add(DALLAS_P_2_Y);
        DA.add(DALLAS_B_1_X);
        DA.add(DALLAS_B_1_Y);
        
        // ADD HOUSTON POSITIONS
        HU.add(HOUSTON_P_1_X);
        HU.add(HOUSTON_P_1_Y);
        HU.add(HOUSTON_Z_2_X);
        HU.add(HOUSTON_Z_2_Y);
        HU.add(HOUSTON_Z_1_X);
        HU.add(HOUSTON_Z_1_Y);
        
        //ADD FORTWORH POSITIONS
        FW.add(FORTWORTH_Z_1_X);
        FW.add(FORTWORTH_Z_1_Y);
        FW.add(FORTWORTH_Z_2_X);
        FW.add(FORTWORTH_Z_2_Y);
        FW.add(FORTWORTH_Z_3_X);
        FW.add(FORTWORTH_Z_3_Y);
        FW.add(FORTWORTH_Z_4_X);
        FW.add(FORTWORTH_Z_4_Y);
        FW.add(FORTWORTH_Z_5_X);
        FW.add(FORTWORTH_Z_5_Y);
        FW.add(FORTWORTH_Z_6_X);
        FW.add(FORTWORTH_Z_6_Y);
        FW.add(FORTWORTH_Z_7_X);
        FW.add(FORTWORTH_Z_7_Y);
        FW.add(FORTWORTH_Z_8_X);
        FW.add(FORTWORTH_Z_8_Y);
        FW.add(FORTWORTH_Z_9_X);
        FW.add(FORTWORTH_Z_9_Y);
        FW.add(FORTWORTH_Z_10_X);
        FW.add(FORTWORTH_Z_10_Y);
        
        //ADD DETROIT POSITIONS
        DT.add(DETROIT_B_1_X);
        DT.add(DETROIT_B_1_Y);
        DT.add(DETROIT_B_2_X);
        DT.add(DETROIT_B_2_Y);
        DT.add(DETROIT_Z_1_X);
        DT.add(DETROIT_Z_1_Y);
        DT.add(DETROIT_Z_2_X);
        DT.add(DETROIT_Z_2_Y);
        
        //ADD COULUMBUS POSITIONS
        CO.add(COLUMBUS_P_1_X);
        CO.add(COLUMBUS_P_1_Y);
        CO.add(COLUMBUS_P_2_X);
        CO.add(COLUMBUS_P_2_Y);
        CO.add(COLUMBUS_B_1_X);
        CO.add(COLUMBUS_B_1_Y);
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
            case SANDIEGO:
                return SD.iterator();
            case PHOENIX:
                return PHE.iterator();
            case ELPASO:
                return EP.iterator();
            case AUSTIN:
                return AU.iterator();
            case SANANTONIO:
                return SA.iterator();
            case DALLAS:
                return DA.iterator();
            case HOUSTON:
                return HU.iterator();
            case FORTWORTH:
                return FW.iterator();
            case DETROIT:
                return DT.iterator();
            case COLUMBUS:
                return CO.iterator();
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
    public static final Integer SANJOSE_P_1_X = 188;
    public static final Integer SANJOSE_P_1_Y = 193;
    public static final Integer SANJOSE_P_2_X = 596;
    public static final Integer SANJOSE_P_2_Y = 505;
    public static final Integer SANJOSE_B_1_X = 598;
    public static final Integer SANJOSE_B_1_Y = 197;
    
    
    //SANFRANCISCO INFO
    public static final Integer SANDIEGO_P_1_X = 495;
    public static final Integer SANDIEGO_P_1_Y = 70;
    public static final Integer SANDIEGO_B_1_X = 345;
    public static final Integer SANDIEGO_B_1_Y = 207;
    
    //PHOENIX
    public static final Integer PHOENIX_P_1_X = 632;
    public static final Integer PHOENIX_P_1_Y = 237;
    public static final Integer PHOENIX_B_1_X = 395;
    public static final Integer PHOENIX_B_1_Y = 294;
    public static final Integer PHOENIX_Z_1_X = 245;
    public static final Integer PHOENIX_Z_1_Y = 50; 
    public static final Integer PHOENIX_Z_2_X = 520;
    public static final Integer PHOENIX_Z_2_Y = 420;
    
    //EL PASO
    public static final Integer ELPASO_Z_1_X = 230;
    public static final Integer ELPASO_Z_1_Y = 525;
    public static final Integer ELPASO_Z_2_X = 247;
    public static final Integer ELPASO_Z_2_Y = 333;
    public static final Integer ELPASO_Z_3_X = 400;
    public static final Integer ELPASO_Z_3_Y = 457;
    public static final Integer ELPASO_Z_4_X = 450;
    public static final Integer ELPASO_Z_4_Y = 300;
    public static final Integer ELPASO_Z_5_X = 290;
    public static final Integer ELPASO_Z_5_Y = 105;
    public static final Integer ELPASO_Z_6_X = 230;
    public static final Integer ELPASO_Z_6_Y = 30;
    public static final Integer ELPASO_Z_7_X = 400;
    public static final Integer ELPASO_Z_7_Y = 25;
    
    //AUSTIN
    public static final Integer AUSTIN_P_1_X = 500;
    public static final Integer AUSTIN_P_1_Y = 293;
    public static final Integer AUSTIN_B_1_X = 352;
    public static final Integer AUSTIN_B_1_Y = 45;
    
    //SANANTONIO
    public static final Integer SANANTONIO_P_1_X = 560;
    public static final Integer SANANTONIO_P_1_Y = 170;
    public static final Integer SANANTONIO_Z_1_X = 600;
    public static final Integer SANANTONIO_Z_1_Y = 400;
    
    //DALLAS
    public static final Integer DALLAS_P_1_X = 657;
    public static final Integer DALLAS_P_1_Y = 462;
    public static final Integer DALLAS_P_2_X = 488;
    public static final Integer DALLAS_P_2_Y = 54;
    public static final Integer DALLAS_B_1_X = 416;
    public static final Integer DALLAS_B_1_Y = 200;
    
    //HOUSTON
    public static final Integer HOUSTON_P_1_X = 540;
    public static final Integer HOUSTON_P_1_Y = 300;
    public static final Integer HOUSTON_Z_2_X = 325;
    public static final Integer HOUSTON_Z_2_Y = 175;
    public static final Integer HOUSTON_Z_1_X = 640;
    public static final Integer HOUSTON_Z_1_Y = 47;
    
    //FORT WORTH
    public static final Integer FORTWORTH_Z_1_X = 300;
    public static final Integer FORTWORTH_Z_1_Y = 174;
    public static final Integer FORTWORTH_Z_2_X = 411;
    public static final Integer FORTWORTH_Z_2_Y = 305;
    public static final Integer FORTWORTH_Z_3_X = 850;
    public static final Integer FORTWORTH_Z_3_Y = 200;
    public static final Integer FORTWORTH_Z_4_X = 740;
    public static final Integer FORTWORTH_Z_4_Y = 420;
    public static final Integer FORTWORTH_Z_5_X = 310;
    public static final Integer FORTWORTH_Z_5_Y = 305;
    public static final Integer FORTWORTH_Z_6_X = 645;
    public static final Integer FORTWORTH_Z_6_Y = 425;
    public static final Integer FORTWORTH_Z_7_X = 745;
    public static final Integer FORTWORTH_Z_7_Y = 195;
    public static final Integer FORTWORTH_Z_8_X = 165;
    public static final Integer FORTWORTH_Z_8_Y = 77;
    public static final Integer FORTWORTH_Z_9_X = 650;
    public static final Integer FORTWORTH_Z_9_Y = 320;
    public static final Integer FORTWORTH_Z_10_X = 545;
    public static final Integer FORTWORTH_Z_10_Y = 180;
    
    //DETROIT
    public static final Integer DETROIT_B_1_X = 184;
    public static final Integer DETROIT_B_1_Y = 31;
    public static final Integer DETROIT_B_2_X = 391;
    public static final Integer DETROIT_B_2_Y = 451;
    public static final Integer DETROIT_Z_1_X = 542;
    public static final Integer DETROIT_Z_1_Y = 31;
    public static final Integer DETROIT_Z_2_X = 190;
    public static final Integer DETROIT_Z_2_Y = 310;
    
    //COLUMBUS
    public static final Integer COLUMBUS_P_1_X = 362;
    public static final Integer COLUMBUS_P_1_Y = 500;
    public static final Integer COLUMBUS_P_2_X = 595;
    public static final Integer COLUMBUS_P_2_Y = 60;
    public static final Integer COLUMBUS_B_1_X = 140;
    public static final Integer COLUMBUS_B_1_Y = 65;
    
    public enum LevelNames{
        LOSANGLES,
        SANFRANCISICO,
        SANJOSE,
        SANDIEGO,
        PHOENIX,
        ELPASO,
        AUSTIN,
        SANANTONIO,
        DALLAS,
        HOUSTON,
        FORTWORTH,
        DETROIT,
        COLUMBUS
    }
}
