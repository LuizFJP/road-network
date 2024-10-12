package br.udesc.t2.dsd.swing.command;

import br.udesc.t2.dsd.swing.models.Block;
import br.udesc.t2.dsd.swing.models.Car;

public class MoveCommand extends Command{
    public MoveCommand(Block block, Car car) {
        super(block, car);
    }
}
