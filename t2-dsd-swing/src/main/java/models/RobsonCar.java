package models;

import command.RobsonCommand;
import command.RobsonMoveCommand;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import models.RobsonBlock;
import myDefault.RobsonSpawner;

public class RobsonCar extends Thread {

    ImageIcon image;
    int speed;
    RobsonBlock block;
    int line;
    int column;
    int position;
    RobsonBlock[][] mesh;
    RobsonCar[] cars;
    RobsonSpawner spawner;

    public RobsonCar(ImageIcon image, int speed, int line, int column, RobsonCar[] cars, int position, RobsonBlock[][] mesh, RobsonSpawner spawner) {
        this.image = image;
        this.speed = speed;
        this.line = line;
        this.column = column;
        this.cars = cars;
        this.position = position;
        this.mesh = mesh;
        this.spawner = spawner;
    }

    public void robsonAutoPurge() {
        robsonAutoKill();
        this.interrupt();
    }

    public synchronized void robsonAutoKill() {
        this.block.robsonReleaseCar();
        this.block.robsonReleaseBlock();
        cars[position] = null;
        spawner.robsonTryLockQuantityOfCars();
        spawner.releaseQuantityOfCars();
    }

    @Override
    public void run() {
        do {
            if (block.robsonIsExit()) {
                break;
            }
            robsonGo();
        } while (!Thread.currentThread().isInterrupted());
            
        robsonAutoKill();
    }

    public void robsonGo() {
        RobsonCommand command = new RobsonMoveCommand(this.block, this);
        command.robsonExecute();
    }

    public void robsonSetBlock(RobsonBlock block) {
        this.block = block;
    }

    public int robsonGetPosition() {
        return position;
    }

    public ImageIcon robsonGetImage() {
        return image;
    }

    public int robsonGetSpeed() {
        return speed;
    }
}
