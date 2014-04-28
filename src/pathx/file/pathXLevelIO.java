package pathx.file;

import java.io.File;
import pathx.level.model.pathXLevelModel;
import pathx.level.model.pathXLevel;

/**
 *
 * @author Karl
 */
public interface pathXLevelIO {
    public boolean loadLevel(File levelFile, pathXLevelModel model);
    public boolean saveLevel(File levelFile, pathXLevel levelToSave);
}
