package models;

import command.Command;
import command.MoveCommand;

public class Car extends Thread {
    String image;
    double speed;
    Block block;
    int line;
    int column;
    int position;
    Block[][] mesh;
    boolean stop;
    Car[] cars;
    int quantityOfCars;

    public Car(String image, double speed, int line, int column, Car[] cars, int position, Block[][] mesh, int quantityOfCars) {
        this.image = image;
        this.speed = speed;
        this.line = line;
        this.column = column;
        this.cars = cars;
        this.position = position;
        this.mesh = mesh;
        this.quantityOfCars = quantityOfCars;
    }

    public synchronized void autoKill() {
        this.quantityOfCars--;
        this.block.releaseCar();
        this.block.releaseBlock();
        cars[position] = null;
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
}
