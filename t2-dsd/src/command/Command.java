package command;
import models.Block;
import models.Car;
import models.CreateMesh;

public abstract class Command {

    private Block block;
    private Block nextBlock;
    private Car car;
    private CreateMesh mesh;

    public Command(Block block,CreateMesh mesh){
        this.block = block;
        this.mesh = mesh;
    }
    /*
    Pegar a direçao do block atual
    adicionar na posicao do carro e bla bla
    tentar da aquire no proximo block


    {
        1: blockUp
        2:
        3:
        9:
    }

    * */
    public void execute(){
        switch (block.getDirection()) {
            case 1 -> blockUp();
            case 2 -> blockRight();
            case 3 -> blockDown();
            case 4 -> blockLeft();
            case 5 -> blockUp();
            case 6 -> blockUp();
            case 7 -> blockUp();
            case 8 -> blockUp();
            case 9 -> blockUp();
            case 10 -> blockUp();
            case 11 -> blockUp();
            case 12 -> blockUp();
        }
    }

    /**
     * Busca o proximo block, da um acquire para ver se está liberado
     * setta o carro no proximo block e o block no carro
     * remove do anterior e release
     */

    private void blockUp(){
        try {
            nextBlock = mesh.getUpBlock(block.getLine(), block.getColumn());
            nextBlock.occupyCar(car);
            car.setBlock(nextBlock);
            block.releaseCar();
            block.releaseBlock();
        } catch (Exception e){
            System.out.println("Robson explodiu tudo!" + e);
        }
    }

    private void blockDown(){
        try {
            nextBlock = mesh.getDownBlock(block.getLine(), block.getColumn());
            nextBlock.occupyCar(car);
            car.setBlock(nextBlock);
            block.releaseCar();
            block.releaseBlock();
        } catch (Exception e){
            System.out.println("Robson explodiu tudo!");
        }
    }

    private void blockRight(){
        try {
            nextBlock = mesh.getRightBlock(block.getLine(), block.getColumn());
            nextBlock.occupyCar(car);
            car.setBlock(nextBlock);
            block.releaseCar();
            block.releaseBlock();
        } catch (Exception e){
            System.out.println("Robson explodiu tudo!");
        }

    }

    private void blockLeft(){
        try {
            nextBlock = mesh.getLeftBlock(block.getLine(), block.getColumn());
            nextBlock.occupyCar(car);
            car.setBlock(nextBlock);
            block.releaseCar();
            block.releaseBlock();
        } catch (Exception e){
            System.out.println("Robson explodiu tudo!");
        }
    }

    public void setBlock(Block block) {
        this.block = block;
    }

}
