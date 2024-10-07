package factory;

import models.Block;
import models.MonitorBlock;

public class CreateMonitorBlock implements FactoryBlock {
    public Block create(boolean cross, boolean entry, boolean exit, int direction, int line, int column) {
        return new MonitorBlock(cross, entry, exit, direction, line, column);
    }
}
