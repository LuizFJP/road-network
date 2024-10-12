package br.udesc.t2.dsd.swing.factory;

import br.udesc.t2.dsd.swing.models.Block;
import br.udesc.t2.dsd.swing.models.MonitorBlock;

public class CreateMonitorBlock implements FactoryBlock {
    public Block create(boolean cross, boolean entry, boolean exit, int direction, int line, int column) {
        return new MonitorBlock(cross, entry, exit, direction, line, column);
    }
}
