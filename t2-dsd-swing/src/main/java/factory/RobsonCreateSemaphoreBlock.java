package factory;

import models.RobsonBlock;
import models.RobsonSemaphoreBlock;

public class RobsonCreateSemaphoreBlock implements RobsonFactoryBlock {
    @Override
    public RobsonBlock create(boolean cross, boolean entry, boolean exit, int direction, int line, int column) {
        return new RobsonSemaphoreBlock(cross, entry, exit, direction, line, column);
    }
}
