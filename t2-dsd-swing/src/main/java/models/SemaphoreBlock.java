package models;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreBlock extends Block {
    private Semaphore mutex = new Semaphore(1);
    public SemaphoreBlock(boolean cross, boolean entry, boolean exit, int direction, int line, int column) {
        super(cross, entry, exit, direction, line, column);
    }

    @Override
    public void occupyCar(Car car) {
        this.car = car;
        meshView.updateCarIcon(this);
    }

    @Override
    public void lockBlock() {
        try {
            mutex.acquire();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean tryLockBlock() {
        var random = new Random();
        try {
            return mutex.tryAcquire(random.nextInt(700), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            System.out.println("Robson estragou tudo de novo " + e);
            return false;
        }
    }

    @Override
    public void releaseBlock() {
        try {
         mutex.release();
        Thread.sleep(new Random().nextInt(500));   
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }
}
