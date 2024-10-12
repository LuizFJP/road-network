package br.udesc.t2.dsd.swing.factory;

import br.udesc.t2.dsd.swing.models.Block;
import br.udesc.t2.dsd.swing.models.SemaphoreBlock;

public class CreateSemaphoreBlock implements FactoryBlock {
    public Block create(boolean cross, boolean entry, boolean exit, int direction, int line, int column) {
        return new SemaphoreBlock(cross, entry, exit, direction, line, column);
    }
}
