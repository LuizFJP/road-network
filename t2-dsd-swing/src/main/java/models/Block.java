package models;

import javax.swing.ImageIcon;
import view.MeshView;

public abstract class Block {
    private boolean entry;
    private boolean exit;
    private boolean cross;
    private int direction;
    private int line;
    private int column;
    private ImageIcon icon;
    protected Car car;
    protected MeshView meshView;
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

    public abstract void occupyCar(Car car);

    public void releaseCar(){
        this.car = null;
        meshView.updateCarIcon(this);
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
    
    public boolean hasCar() {
        return !(car == null);
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon imageIcon) {
        this.icon = imageIcon;
    }
    
    public void setMeshView(MeshView meshView) {
        this.meshView = meshView;
    }
}
