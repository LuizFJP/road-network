package factory;

import models.Block;

public interface FactoryBlock {
    Block create(boolean cross, boolean entry, boolean exit, int direction, int line, int column);
}
