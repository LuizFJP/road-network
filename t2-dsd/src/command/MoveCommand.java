package command;

import models.Block;
import models.Car;
import models.CreateMesh;

public class MoveCommand extends Command{
    public MoveCommand(Block block, Car car) {
        super(block, car);
    }
}
