package models;

public class Car {
    String image;
    double speed;
    int line;
    int column;
    int position;
    Car[] cars;

    public Car(String image, double speed, int line, int column, Car[] cars, int position) {
        this.image = image;
        this.speed = speed;
        this.line = line;
        this.column = column;
        this.cars = cars;
        this.position = position;
    }

    public synchronized void autoKill() {
        cars[position] = null;
    }
}
