package br.udesc.t2.dsd.swing.factory;

import br.udesc.t2.dsd.swing.models.Block;

public interface FactoryBlock {
    Block create(boolean cross, boolean entry, boolean exit, int direction, int line, int column);
}
