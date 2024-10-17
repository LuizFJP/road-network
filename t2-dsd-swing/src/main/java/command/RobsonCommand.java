package command;

import models.RobsonBlock;
import models.RobsonCar;
import models.RobsonCreateMesh;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class RobsonCommand {

    private RobsonBlock block;
    private RobsonBlock nextBlock;
    private RobsonCar car;
    private RobsonBlock[][] mesh;

    public RobsonCommand(RobsonBlock block, RobsonCar car) {
        this.block = block;
        this.car = car;
        this.mesh = RobsonCreateMesh.robsonGetMesh();
    }

    public void robsonExecute() {
        switch (block.robsonGetDirection()) {
            case 1 ->
                robsonBlockUp();
            case 2 ->
                robsonBlockRight();
            case 3 ->
                robsonBlockDown();
            case 4 ->
                robsonBlockLeft();
            default ->
                robsonCross(); 
        }
    }

    private void robsonBlockUp() {
        try {
            nextBlock = RobsonCreateMesh.robsonGetUpBlock(block.robsonGetLine(), block.robsonGetColumn());
            robsonTakeBlock();
        } catch (Exception e) {
            System.out.println("Robson explodiu tudo!" + e);
        }
    }

    private void robsonBlockDown() { // 🤬
        try {
            nextBlock = RobsonCreateMesh.robsonGetDownBlock(block.robsonGetLine(), block.robsonGetColumn());
            robsonTakeBlock();
        } catch (Exception e) {
            System.out.println("Robson explodiu tudo!");
        }
    }

    private void robsonTakeBlock() {
        if (nextBlock.robsonIsCross()) {
            robsonCross();
        } else {
            try { 
            nextBlock.robsonLockBlock();
            nextBlock.robsonOccupyCar(car);
            car.robsonSetBlock(nextBlock);
            block.robsonReleaseCar();
            block.robsonReleaseBlock();
            Thread.sleep(car.robsonGetSpeed());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }catch (Exception e) {
                System.out.println("Robson explodiu tudo de novo! " + e);
                Thread.currentThread().interrupt();
            }
            
        }
    }

    private void robsonBlockRight() {
        try {
            nextBlock = RobsonCreateMesh.robsonGetRightBlock(block.robsonGetLine(), block.robsonGetColumn());
            robsonTakeBlock();
        } catch (Exception e) {
            System.out.println("Robson explodiu tudo!");
        }

    }

    private void robsonBlockLeft() {
        try {
            nextBlock = RobsonCreateMesh.robsonGetLeftBlock(block.robsonGetLine(), block.robsonGetColumn());
            robsonTakeBlock();
        } catch (Exception e) {
            System.out.println("Robson explodiu tudo!");
        }
    }

    public void setBlock(RobsonBlock block) {
        this.block = block;
    }

    void robsonCross() {
        try {
            List<RobsonBlock> path = new ArrayList<>();
            var cb = block;
            while (true) {
                var block = robsonNextCrossBlock(cb);
                if (block == null || !block.robsonIsCross()) {
                    cb = block;
                    path.add(cb);
                    break;
                }
                cb = block;
                path.add(cb);
            }

            do {

                    for (int i = 0; i < path.size(); i++) {
                        if (!path.get(i).robsonTryLockBlock()) {
                            for (int j = i; j >= 0; j--) {
                                path.get(i).robsonReleaseBlock();
                            }
                            car.sleep(new Random().nextInt(700));
                            break;
                        }
                    }
                for (int i = 0; i < path.size(); i++) {
                    path.get(i).robsonOccupyCar(car);
                    car.robsonSetBlock(path.get(i));
                    block.robsonReleaseCar();
                    block.robsonReleaseBlock();
                    Thread.sleep(car.robsonGetSpeed());
                    block = path.get(i);
                    cb = path.get(i);
                    if(!path.get(i).robsonIsCross()) {
                        break;
                    }
                }

            } while (cb.robsonIsCross());

        } catch (Exception e) {

        }
    }

    private RobsonBlock robsonNextCrossBlock(RobsonBlock block) {
        switch (block.robsonGetDirection()) {
            case 1, 5 -> {
                return robsonCrossUp(block);
            }
            case 2, 6 -> {
                return robsonCrossRight(block);
            }
            case 3, 7 -> {
                return robsonCrossDown(block);
            }
            case 4, 8 -> {
                return robsonCrossLeft(block);
            }
            case 9 -> {
                return robsonCrossUpRight(block);
            }
            case 10 -> {
                return robsonCrossUpLeft(block);
            }
            case 11 -> {
                return robsonCrossRightDown(block);
            }
            case 12 -> {
                return robsonCrossDownLeft(block);
            }
        }
        return null;
    }

    private RobsonBlock robsonCrossUp(RobsonBlock block) {
        return mesh[block.robsonGetLine() - 1][block.robsonGetColumn()];
    }

    private RobsonBlock robsonCrossRight(RobsonBlock block) {
        return mesh[block.robsonGetLine()][block.robsonGetColumn() + 1];
    }

    private RobsonBlock robsonCrossDown(RobsonBlock block) {
        return mesh[block.robsonGetLine() + 1][block.robsonGetColumn()];
    }

    private RobsonBlock robsonCrossLeft(RobsonBlock block) {
        return mesh[block.robsonGetLine()][block.robsonGetColumn() - 1];
    }

    private RobsonBlock robsonCrossUpRight(RobsonBlock block) {
        var value = new Random().nextInt(2);
        return value == 0 ? mesh[block.robsonGetLine()][block.robsonGetColumn() + 1]
                : mesh[block.robsonGetLine() - 1][block.robsonGetColumn()];
    }

    private RobsonBlock robsonCrossDownLeft(RobsonBlock block) {
        var value = new Random().nextInt(2);
        return value == 0 ? mesh[block.robsonGetLine() + 1][block.robsonGetColumn()]
                : mesh[block.robsonGetLine()][block.robsonGetColumn() - 1];
    }

    private RobsonBlock robsonCrossUpLeft(RobsonBlock block) {
        var value = new Random().nextInt(2);
        return value == 0 ? mesh[block.robsonGetLine() - 1][block.robsonGetColumn()]
                : mesh[block.robsonGetLine()][block.robsonGetColumn() - 1];
    }

    private RobsonBlock robsonCrossRightDown(RobsonBlock block) {
        var value = new Random().nextInt(2);
        return value == 0 ? mesh[block.robsonGetLine()][block.robsonGetColumn() + 1]
                : mesh[block.robsonGetLine() + 1][block.robsonGetColumn()];
    }
}
