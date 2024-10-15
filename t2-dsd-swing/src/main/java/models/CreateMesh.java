package models;

import factory.FactoryBlock;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateMesh {
    private static Block[][] mesh;
    private static List<Block> entries;

    public static Block[][] generateRoads(String caminhoArquivo, FactoryBlock factoryBlock) throws IOException {
        BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo));
        String linha;
        int currentLine = 0;

        var lines = Integer.parseInt(leitor.readLine().replaceAll("\\s+", ""));
        var columns = Integer.parseInt(leitor.readLine().replaceAll("\\s+", ""));

        mesh = new Block[lines][columns];
        entries = new ArrayList<>();

        while ((linha = leitor.readLine()) != null && currentLine < lines) {
            String[] valores = linha.trim().split("\\s+");
            for (int currentColumn = 0; currentColumn < columns; currentColumn++) {
                var direction = Integer.parseInt(valores[currentColumn]);
                var entry = checkEntry(currentLine, currentColumn, direction, mesh);
                var exit = checkExit(currentLine, currentColumn, direction, mesh);
                var cross = direction > 4;
                var block = factoryBlock.create(cross, entry, exit, direction, currentLine, currentColumn);
                mesh[currentLine][currentColumn] = block;
                if (block.isEntry()) {
                    entries.add(block);
                }
            }
            currentLine++;
        }

        leitor.close();
        return mesh;
    }

    private static boolean checkEntry(int line, int column, int direction, Block[][] mesh) {
        if (column == 0 && direction == 2) {
            return true;
        } else if (column == mesh[0].length - 1 && direction == 4) {
            return true;
        } else if (line == 0 && direction == 3) {
            return true;
        } else return line == mesh.length - 1 && direction == 1;
    }

    private static boolean checkExit(int line, int column, int direction, Block[][] mesh) {
        if (column == 0 && direction == 4) {
            return true;
        } else if (column == mesh[0].length - 1 && direction == 2) {
            return true;
        } else if (line == 0 && direction == 1) {
            return true;
        } else return line == mesh.length - 1 && direction == 3;
    }

    public static Block getRightBlock(int line, int column){
        return mesh[line][column + 1];
    }

    public static Block getLeftBlock(int line, int column){
        return mesh[line][column - 1];
    }

    public static Block getUpBlock(int line, int column){
        return mesh[line - 1][column];
    }

    public static Block getDownBlock(int line, int column){
        return mesh[line + 1][column];
    }

    public static Block[][] getMesh() {
        return mesh;
    }

    public static List<Block> getEntries() { return entries; }
}
