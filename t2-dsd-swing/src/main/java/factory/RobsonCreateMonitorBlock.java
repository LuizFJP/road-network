package factory;

import models.RobsonBlock;
import models.RobsonMonitorBlock;

public class RobsonCreateMonitorBlock implements RobsonFactoryBlock {
    public RobsonBlock create(boolean cross, boolean entry, boolean exit, int direction, int line, int column) {
        return new RobsonMonitorBlock(cross, entry, exit, direction, line, column);
    }
}
