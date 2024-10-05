import models.Block;
import models.Car;

import java.util.Random;

public class Spawner implements Runnable {
    private Block[] blocks;
    private int limit;
    private Car[] cars;

    public Spawner(Block[] blocks, int limit) {
        this.blocks = blocks;
        this.limit = limit;
        this.cars = new Car[limit];
    }

    @Override
    public void run() {
        int counter = 0;
        while(true) {
            if (counter > blocks.length - 1) {
                counter = 0;
            }
            if (cars.length <= limit) {
                var block = blocks[counter];
                var speed = Math.random();
                var position = getPosition();
                var car = new Car("", speed, block.getLine(), block.getColumn(), cars, position);
                cars[position] = car; // ver o problema se ao spawnar vai entrar em um block ocupado
                block.occupyCar(car);
            }
        }
    }



    private int getPosition() {
        for (int i = 0; i < cars.length; i++) {
            if(cars[i] == null) {
                return i;
            }
        }
        return 0;
    }
}
