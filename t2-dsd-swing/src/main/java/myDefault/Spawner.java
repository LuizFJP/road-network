package myDefault;

import models.Block;
import models.Car;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;

public class Spawner implements Runnable {

    private List<Block> entries;
    private Integer limit;
    private Car[] cars;
    private Integer quantityOfCars;
    private Block[][] mesh;
    private Semaphore mutex = new Semaphore(1);
    private boolean tearDown;
    private boolean pause;
    private int insertionInterval;

    public Spawner(List<Block> entries, int limit, Block[][] mesh, int insertionInterval) {
        this.entries = entries;
        this.limit = limit;
        this.cars = new Car[limit];
        this.mesh = mesh;
        quantityOfCars = 0;
        this.tearDown = false;
        this.pause = false;
        this.insertionInterval = insertionInterval;
    }

    @Override
    public void run() {
        try {
            int counter = 0;
            for (int i = 0; i < entries.size(); i++) {
                System.out.println(entries.get(i).toString());
            }
            System.out.println();
            while (!this.tearDown) {
                if (counter > entries.size() - 1) {
                    counter = 0;
                }
                Thread.sleep(insertionInterval);
                if (quantityOfCars < limit && !pause) {
                    var block = entries.get(counter);
                    var speed = new Random().nextInt(1000);
                    var position = getPosition();
                    var icon = pickCarImage();
                    quantityOfCars++;
                    var car = new Car(icon, speed, block.getLine(), block.getColumn(), cars, position, mesh, this);
                    car.setBlock(block);
                    cars[position] = car;
                    block.occupyCar(car);
                    car.start();
                    counter++;
                }
            }
        } catch (Exception e) {
            System.out.println("Robson estragou tudo!! " + e);
        }
    }
    
    private ImageIcon pickCarImage() {
    String[] images = {"fernandinho.png", "robson2.png", "feio1.png", "feio2.png", "feio3.png"};
    int rnd = new Random().nextInt(images.length);
    return new ImageIcon(getClass().getClassLoader().getResource(images[rnd]).getPath());

    }

    private int getPosition() {
        for (int i = 0; i < cars.length; i++) {
            if (cars[i] == null) {
                return i;
            }
        }
        return 0;
    }

    public void tryLockQuantityOfCars() {
        try {
            mutex.acquire();
            quantityOfCars--;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

        } catch (Exception e) {
            System.out.println("Robson estragou tudo de novo " + e);
        }
    }

    public void releaseQuantityOfCars() {
        try {
            mutex.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        pause = !pause;
    }

    public void tearDown() {
        this.tearDown = true;
        for (int i = 0; i < mesh.length; i++) {
            for (int j = 0; j < mesh[i].length; j++) {
                mesh[i][j].setExit(true);
                }
            }
        }
}
