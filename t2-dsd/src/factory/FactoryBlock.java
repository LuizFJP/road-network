package factory;

import models.Block;

public interface FactoryBlock {
    Block create(boolean cross, boolean entry, int direction);
}
