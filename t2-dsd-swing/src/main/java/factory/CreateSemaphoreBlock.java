package factory;

import models.Block;
import models.SemaphoreBlock;

public class CreateSemaphoreBlock implements FactoryBlock {
    public Block create(boolean cross, boolean entry, boolean exit, int direction, int line, int column) {
        return new SemaphoreBlock(cross, entry, exit, direction, line, column);
    }
}
