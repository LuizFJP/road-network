package br.udesc.t2.dsd.swing.models;

public abstract class Block {
    private boolean entry;
    private boolean exit;
    private boolean cross;
    private int direction;
    private int line;
    private int column;
    protected Car car;
    public Block(boolean cross, boolean entry, boolean exit, int direction, int line, int column) {
        this.cross = cross;
        this.entry = entry;
        this.exit = exit;
        this.direction = direction;
        this.line = line;
        this.column = column;
    }

    @Override
    public String toString() {
        return "Entry: " + entry + ", Cross: " + cross  + ", Direction: " + direction;
    }

    public void occupyCar(Car car) {
        this.car = car;
    }

    public void releaseCar(){
        this.car = null;
    }

    public abstract void releaseBlock();
    public abstract void lockBlock();
    public abstract boolean tryLockBlock();

    public boolean isEntry() {
        return entry;
    }

    public boolean isCross() {
        return cross;
    }

    public int getDirection() {
        return direction;
    }

    public boolean isExit() {
        return exit;
    }

    public Car getCar() {
        return car;
    }

    public void setEntry(boolean entry) {
        this.entry = entry;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public void setCross(boolean cross) {
        this.cross = cross;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
