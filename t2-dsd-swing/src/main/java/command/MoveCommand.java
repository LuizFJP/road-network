package command;

import models.Block;
import models.Car;

public class MoveCommand extends Command{
    public MoveCommand(Block block, Car car) {
        super(block, car);
    }
}
