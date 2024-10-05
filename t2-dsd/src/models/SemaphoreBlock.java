package models;

import java.util.concurrent.Semaphore;

public class SemaphoreBlock extends Block {
    private Semaphore mutex = new Semaphore(1);
    public SemaphoreBlock(boolean cross, boolean entry, int direction) {
        super(cross, entry, direction);
    }

    @Override
    public void occupyCar(Car car) {
        lockBlock();
        super.occupyCar(car);
    }

    @Override
    public void lockBlock() {
        try {
            mutex.acquire();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void releaseBlock() {
        mutex.release();
    }
}
