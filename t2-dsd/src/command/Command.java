package command;

import models.Block;
import models.Car;
import models.CreateMesh;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Command {
    private Block block;
    private Block nextBlock;
    private Car car;
    private Block[][] mesh;

    public Command(Block block, Car car) {
        this.block = block;
        this.car = car;
        this.mesh = CreateMesh.getMesh();
    }

    public void execute() {
        switch (block.getDirection()) {
            case 1 -> blockUp();
            case 2 -> blockRight();
            case 3 -> blockDown();
            case 4 -> blockLeft();
            default -> cross();
        }
    }

    private void blockUp() {
        try {
            nextBlock = CreateMesh.getUpBlock(block.getLine(), block.getColumn());
            takeBlock();
        } catch (Exception e) {
            System.out.println("Robson explodiu tudo!" + e);
        }
    }

    private void blockDown() {
        try {
            nextBlock = CreateMesh.getDownBlock(block.getLine(), block.getColumn());
            takeBlock();
        } catch (Exception e) {
            System.out.println("Robson explodiu tudo!");
        }
    }

    private void takeBlock() {
        if (nextBlock.isCross()) {
            cross();
        } else {
            nextBlock.occupyCar(car);
            car.setBlock(nextBlock);
            block.releaseCar();
            block.releaseBlock();
        }
    }

    private void blockRight() {
        try {
            nextBlock = CreateMesh.getRightBlock(block.getLine(), block.getColumn());
            takeBlock();
        } catch (Exception e) {
            System.out.println("Robson explodiu tudo!");
        }

    }

    private void blockLeft() {
        try {
            nextBlock = CreateMesh.getLeftBlock(block.getLine(), block.getColumn());
            takeBlock();
        } catch (Exception e) {
            System.out.println("Robson explodiu tudo!");
        }
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    void cross() {
        try {
            List<Block> path = new ArrayList<>();
            path.add(block);
            var cb = block;
            while (true) {
                var block = nextCrossBlock(cb);
                if (block == null) {
                    break;
                }
                var aux = cb;
                path.add(aux);
                cb = block;
            }

            while (true) {
                do {
                    for (int i = 0; i < path.size(); i++) {
                        if (!path.get(i).tryLockBlock()) {
                            for (int j = i; j >= 0; j--) {
                                path.get(i).releaseBlock();
                            }
                            car.sleep(new Random().nextInt(700));
                            break;
                        }
                    }
                } while (cb.isCross());
            }


        } catch (Exception e) {

        }
    }

    private Block nextCrossBlock(Block block) {
        switch (block.getDirection()) {
            case 5 -> {
                return crossUp(block);
            }
            case 6 -> {
                return crossRight(block);
            }
            case 7 -> {
                return crossDown(block);
            }
            case 8 -> {
                return crossLeft(block);
            }
            case 9 -> {
                return crossUpRight(block);
            }
            case 10 -> {
                return crossUpLeft(block);
            }
            case 11 -> {
                return crossRightDown(block);
            }
            case 12 -> {
                return crossDownLeft(block);
            }
        }
        return null;
    }

    private Block crossUp(Block block) {
        return mesh[block.getLine() - 1][block.getColumn()];
    }
    private Block crossRight(Block block) {
        return mesh[block.getLine()][block.getColumn() + 1];
    }
    private Block crossDown(Block block) {
        return mesh[block.getLine() + 1][block.getColumn()];
    }
    private Block crossLeft(Block block) {
        return mesh[block.getLine()][block.getColumn() - 1];
    }
    private Block crossUpRight(Block block) {
        var value = new Random().nextInt(2);
        return value == 0 ? mesh[block.getLine()][block.getColumn() - 1] :
                mesh[block.getLine() + 1][block.getColumn()];
    }
    private Block crossDownLeft(Block block) {
        var value = new Random().nextInt(2);
        return value == 0 ? mesh[block.getLine() + 1][block.getColumn()] :
                mesh[block.getLine()][block.getColumn() - 1];
    }
    private Block crossUpLeft(Block block) {
        var value = new Random().nextInt(2);
        return value == 0 ? mesh[block.getLine() - 1][block.getColumn()] :
                mesh[block.getLine()][block.getColumn() - 1];
    }
    private Block crossRightDown(Block block) {
        var value = new Random().nextInt(2);
        return value == 0 ? mesh[block.getLine()][block.getColumn() + 1] :
                mesh[block.getLine() + 1][block.getColumn()];
    }
}
