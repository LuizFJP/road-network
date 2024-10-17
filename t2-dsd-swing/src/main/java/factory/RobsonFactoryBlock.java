package factory;

import models.RobsonBlock;

public interface RobsonFactoryBlock {
    RobsonBlock create(boolean cross, boolean entry, boolean exit, int direction, int line, int column);
}
