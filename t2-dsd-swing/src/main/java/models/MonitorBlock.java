package models;

public class MonitorBlock extends Block{
    public MonitorBlock(boolean cross, boolean entry, boolean exit, int direction, int line, int column) {
        super(cross, entry, exit, direction, line, column);
    }

    @Override
    public synchronized void occupyCar(Car car) {
        super.occupyCar(car);
    }

    @Override
    public void releaseBlock() {

    }

    @Override
    public void lockBlock() {

    }

    @Override
    public boolean tryLockBlock() {
        return false;
    }
}
