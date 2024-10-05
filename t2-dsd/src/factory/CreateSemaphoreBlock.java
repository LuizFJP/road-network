package factory;

import models.Block;
import models.SemaphoreBlock;

public class CreateSemaphoreBlock implements FactoryBlock {
    public Block create(boolean cross, boolean entry, int direction) {
        return new SemaphoreBlock(cross, entry, direction);
    }
}
