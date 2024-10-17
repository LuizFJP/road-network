package myDefault;

import models.RobsonBlock;
import models.RobsonCar;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;

public class RobsonSpawner implements Runnable {

    private List<RobsonBlock> entries;
    private Integer limit;
    private RobsonCar[] cars;
    private Integer quantityOfCars;
    private RobsonBlock[][] mesh;
    private Semaphore mutex = new Semaphore(1);
    private boolean tearDown;
    private boolean pause;
    private int insertionInterval;

    public RobsonSpawner(List<RobsonBlock> entries, int limit, RobsonBlock[][] mesh, int insertionInterval) {
        this.entries = entries;
        this.limit = limit;
        this.cars = new RobsonCar[limit];
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
                    var position = robsonGetPosition();
                    var icon = robsonPickCarImage();
                    quantityOfCars++;
                    var car = new RobsonCar(icon, speed, block.robsonGetLine(), block.robsonGetColumn(), cars, position, mesh, this);
                    car.robsonSetBlock(block);
                    cars[position] = car;
                    block.robsonOccupyCar(car);
                    car.start();
                    counter++;
                }
            }
        } catch (Exception e) {
            System.out.println("Robson estragou tudo!! " + e);
        }
    }
    
    private ImageIcon robsonPickCarImage() {
    String[] images = {"fernandinho.png", "robson2.png", "feio1.png", "feio2.png", "feio3.png"};
    int rnd = new Random().nextInt(images.length);
    return new ImageIcon(getClass().getClassLoader().getResource(images[rnd]).getPath());

    }

    private int robsonGetPosition() {
        for (int i = 0; i < cars.length; i++) {
            if (cars[i] == null) {
                return i;
            }
        }
        return 0;
    }

    public void robsonTryLockQuantityOfCars() {
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

    public void robsonPause() {
        pause = !pause;
    }

    public void robsonTearDown() {
        this.tearDown = true;
        for (int i = 0; i < mesh.length; i++) {
            for (int j = 0; j < mesh[i].length; j++) {
                mesh[i][j].robsonSetExit(true);
                }
            }
        }
}
