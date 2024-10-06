package command;

import models.Block;
import models.CreateMesh;

public class MoveCommand extends Command{
    public MoveCommand(Block block, CreateMesh mesh) {
        super(block, mesh);
    }
}
