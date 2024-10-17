package models;

import javax.swing.ImageIcon;
import view.RobsonMeshView;

  public abstract class RobsonBlock {
    private boolean entry;
    private boolean exit;
    private boolean cross;
    private int direction;
    private int line;
    private int column;
    private ImageIcon icon;
    protected RobsonCar car;
    protected RobsonMeshView meshView;
    public RobsonBlock(boolean cross, boolean entry, boolean exit, int direction, int line, int column) {
        this.cross = cross;
        this.entry = entry;
        this.exit = exit;
        this.direction = direction;
        this.line = line;
        this.column = column;
    }

    @Override
    public String toString() {
        return "line: "+line + " column: " + column + "Entry: " + entry + ", Cross: " + cross  + ", Direction: " + direction;
    }

    public abstract void robsonOccupyCar(RobsonCar car);

    public void robsonReleaseCar(){
        this.car = null;
        meshView.robsonUpdateCarIcon(this);
    }

    public abstract void robsonReleaseBlock();
    public abstract void robsonLockBlock();
    public abstract boolean robsonTryLockBlock();

    public boolean robsonIsEntry() {
        return entry;
    }

    public boolean robsonIsCross() {
        return cross;
    }

    public int robsonGetDirection() {
        return direction;
    }

    public boolean robsonIsExit() {
        return exit;
    }

    public RobsonCar robsonGetCar() {
        return car;
    }

    public void robsonSetEntry(boolean entry) {
        this.entry = entry;
    }

    public void robsonSetExit(boolean exit) {
        this.exit = exit;
    }

    public void robsonSetCross(boolean cross) {
        this.cross = cross;
    }

    public void robsonSetDirection(int direction) {
        this.direction = direction;
    }

    public int robsonGetLine() {
        return line;
    }

    public void robsonSetLine(int line) {
        this.line = line;
    }

    public int robsonGetColumn() {
        return column;
    }

    public void robsonSetColumn(int column) {
        this.column = column;
    }
    
    public boolean robsonHasCar() {
        return !(car == null);
    }
    
    public ImageIcon robsonGetIcon() {
        return this.icon;
    }
    
    public void robsonSetIcon(ImageIcon imageIcon) {
        this.icon = imageIcon;
    }
    
    public void robsonSetMeshView(RobsonMeshView meshView) {
        this.meshView = meshView;
    }
}
