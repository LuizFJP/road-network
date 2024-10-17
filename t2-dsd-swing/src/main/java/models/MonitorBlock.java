package models;

import java.util.Random;

public class MonitorBlock extends Block{
    
    private boolean locked = false;
    
    public MonitorBlock(boolean cross, boolean entry, boolean exit, int direction, int line, int column) {
        super(cross, entry, exit, direction, line, column);
    }

    @Override
    public synchronized void occupyCar(Car car) {
        this.car = car;
        meshView.updateCarIcon(this);
    }

    @Override
    public synchronized void releaseBlock() {
        locked = false;  
        notify();
    }

    @Override
    public synchronized void lockBlock() {
        while (locked) {
            try {
                wait(); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        locked = true;
    }

    @Override
    public synchronized boolean tryLockBlock() {
        var random = new Random();
        try {
            
            if (!locked) {
                locked = true;
                return true;
            } else {
                wait(random.nextInt(700));  
                return !locked; 
            }
        } catch (InterruptedException e) {
            System.out.println("Robson estragou tudo de novo " + e);
            return false;
        }
    }
}
