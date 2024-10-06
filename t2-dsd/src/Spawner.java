
import models.Block;
import models.Car;
import models.CreateMesh;

import java.util.Random;

public class Spawner implements Runnable {
    private Block[] blocks;
    private int limit;
    private Car[] cars;

    private CreateMesh mesh;

    public Spawner(Block[] blocks, int limit, CreateMesh mesh) {
        this.blocks = blocks;
        this.limit = limit;
        this.cars = new Car[limit];
        this.mesh = mesh;
    }

    @Override
    public void run() {
        int counter = 0;
        while(true) {
            if (counter > blocks.length - 1) {
                counter = 0;
            }
            if (cars.length <= limit) {
                var block = blocks[counter];
                var speed = Math.random();
                var position = getPosition();
                var car = new Car("", speed, block.getLine(), block.getColumn(), cars, position, mesh);
                cars[position] = car; // ver o problema se ao spawnar vai entrar em um block ocupado
                block.occupyCar(car);
            }
        }
    }



    private int getPosition() {
        for (int i = 0; i < cars.length; i++) {
            if(cars[i] == null) {
                return i;
            }
        }
        return 0;
    }
/*
* cross(block) {
*   List<Block> path;
*   path.add(block);
*   var cb = block;
*   while(cb.cross) {
*       var block = nextBlock();
*       var aux = cb;
*       path.add(aux);
*       cb = block;
*   }
*
*   for(int i = 0; i < path.size(); i++) {
*       if (path.get(i).occupied()) {
*           i = 0;
*           timeout;
*        }
*       path.get(i).occupyBlock(car);
*   }
*
* */

}
