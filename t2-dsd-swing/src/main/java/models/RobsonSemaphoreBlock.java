
package models;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class RobsonSemaphoreBlock extends RobsonBlock {

    private Semaphore mutex = new Semaphore(1);

    public RobsonSemaphoreBlock(boolean cross, boolean entry, boolean exit, int direction, int line, int column) {
        super(cross, entry, exit, direction, line, column);
    }

    @Override
    public void robsonOccupyCar(RobsonCar car) {
        this.car = car;
        meshView.robsonUpdateCarIcon(this);
    }

    @Override
    public void robsonLockBlock() {
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean robsonTryLockBlock() {
        var random = new Random();
        try {
            return mutex.tryAcquire(random.nextInt(700), TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        } catch (Exception e) {
            System.out.println("Robson estragou tudo de novo " + e);
            return false;
        }
    }

    @Override
    public void robsonReleaseBlock() {
        try {
            mutex.release();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
