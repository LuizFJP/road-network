package command;

import models.RobsonBlock;
import models.RobsonCar;

public class RobsonMoveCommand extends RobsonCommand{
    public RobsonMoveCommand(RobsonBlock block, RobsonCar car) {
        super(block, car);
    }
}
