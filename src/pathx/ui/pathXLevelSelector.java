package pathx.ui;

/**
 *
 * @author Karl
 */
public class pathXLevelSelector {
    
    private String levelPath;
    
    public pathXLevelSelector()
    {
        levelPath = null;
    }
    
    public void setLevelPath(String pathName)
    {
        levelPath = pathName;
    }
    
    public String getLevelPath()    {return levelPath;}
    
}
