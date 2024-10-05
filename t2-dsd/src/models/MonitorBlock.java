package models;

public class MonitorBlock extends Block{
    public MonitorBlock(boolean cross, boolean entry, int direction) {
        super(cross, entry, direction);
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
}
