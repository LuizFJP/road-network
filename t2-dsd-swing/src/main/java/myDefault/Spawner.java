
package myDefault;

import models.Block;
import models.Car;

import java.util.List;
import javax.swing.ImageIcon;

public class Spawner implements Runnable {
    private List<Block> entries;
    private int limit;
    private Car[] cars;
    private static int quantityOfCars;
    private Block[][] mesh;

    public Spawner(List<Block> entries, int limit, Block[][] mesh) {
        this.entries = entries;
        this.limit = limit;
        this.cars = new Car[limit];
        this.mesh = mesh;
        quantityOfCars = 0;
    }

    @Override
    public void run() {
        int counter = 0;
        for (int i = 0; i < entries.size(); i++) {
            System.out.println(entries.get(i).toString());
        }
        System.out.println();
        while(true) {
            if (counter > entries.size() - 1) {
                counter = 0;
            }
            if (quantityOfCars < limit) {
                var block = entries.get(counter);
                var speed = Math.random();
                var position = getPosition();
                var icon = new ImageIcon(getClass().getClassLoader().getResource("robson2.png").getPath());
                var car = new Car(icon, speed, block.getLine(), block.getColumn(), cars, position, mesh, quantityOfCars);
                car.setBlock(block);
                cars[position] = car;
                quantityOfCars++;
                block.occupyCar(car);
                car.start();
                counter++;
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
