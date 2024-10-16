package models;

import command.Command;
import command.MoveCommand;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import models.Block;
import myDefault.Spawner;

public class Car extends Thread {

    ImageIcon image;
    int speed;
    Block block;
    int line;
    int column;
    int position;
    Block[][] mesh;
    boolean stop;
    Car[] cars;
    Spawner spawner;

    public Car(ImageIcon image, int speed, int line, int column, Car[] cars, int position, Block[][] mesh, Spawner spawner) {
        this.image = image;
        this.speed = speed;
        this.line = line;
        this.column = column;
        this.cars = cars;
        this.position = position;
        this.mesh = mesh;
        this.spawner = spawner;
    }

    public void autoPurge() {
        autoKill();
        this.interrupt();
    }

    public synchronized void autoKill() {
        this.block.releaseCar();
        this.block.releaseBlock();
        cars[position] = null;
        spawner.tryLockQuantityOfCars();
        spawner.releaseQuantityOfCars();
    }

    @Override
    public void run() {
        stop = false;
        while (!stop) {
            go();
            if (block.isExit()) {
                stop = true;
            }
        }
        autoKill();
    }

    public void go() {
        Command command = new MoveCommand(this.block, this);
        command.execute();
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public int getPosition() {
        return position;
    }

    public ImageIcon getImage() {
        return image;
    }

    public int getSpeed() {
        return speed;
    }
}
