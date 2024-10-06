package models;

import command.Command;
import command.MoveCommand;

public class Car {
    String image;
    double speed;
    Block block;
    int line;
    int column;
    int position;
    CreateMesh mesh;
    boolean stop;
    Car[] cars;

    public Car(String image, double speed, int line, int column, Car[] cars, int position, CreateMesh mesh) {
        this.image = image;
        this.speed = speed;
        this.line = line;
        this.column = column;
        this.cars = cars;
        this.position = position;
        this.mesh = mesh;
    }

    public synchronized void autoKill() {
        cars[position] = null;
    }

    public void go(){
        Command command = new MoveCommand(this.block, this.mesh);
        stop = false;
        while(!stop){
            command.execute();
            command.setBlock(this.block);
        }
        autoKill();
    }

    public void setBlock(Block block) {
        this.block = block;
    }
}
