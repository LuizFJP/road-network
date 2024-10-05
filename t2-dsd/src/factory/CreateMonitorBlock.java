package factory;

import models.Block;
import models.MonitorBlock;

public class CreateMonitorBlock implements FactoryBlock {
    public Block create(boolean cross, boolean entry, int direction) {
        return new MonitorBlock(cross, entry, direction);
    }
}
