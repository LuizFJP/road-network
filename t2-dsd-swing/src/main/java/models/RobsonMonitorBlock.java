package models;

import java.util.Random;

public class RobsonMonitorBlock extends RobsonBlock{
    
    private boolean locked = false;
    
    public RobsonMonitorBlock(boolean cross, boolean entry, boolean exit, int direction, int line, int column) {
        super(cross, entry, exit, direction, line, column);
    }

    @Override
    public synchronized void robsonOccupyCar(RobsonCar car) {
        this.car = car;
        meshView.robsonUpdateCarIcon(this);
    }

    @Override
    public synchronized void robsonReleaseBlock() {
        locked = false;  
        notify();
    }

    @Override
    public synchronized void robsonLockBlock() {
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
    public synchronized boolean robsonTryLockBlock() {
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
