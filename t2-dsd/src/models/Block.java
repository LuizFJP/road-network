package models;

public abstract class Block {
    private boolean entry;
    private boolean cross;
    private int direction;
    private int line;
    private int column;
    protected Car car;
    public Block(boolean cross, boolean entry, int direction) {
        this.cross = cross;
        this.entry = entry;
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Entry: " + entry + ", Cross: " + cross  + ", Direction: " + direction;
    }

    public void occupyCar(Car car) {
        this.car = car;
    }

    public abstract void releaseBlock();
    public abstract void lockBlock();

    public boolean isEntry() {
        return entry;
    }

    public boolean isCross() {
        return cross;
    }

    public int getDirection() {
        return direction;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public Car getCar() {
        return car;
    }
}
