package models;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import factory.RobsonFactoryBlock;

public class RobsonCreateMesh {
    private static RobsonBlock[][] mesh;
    private static List<RobsonBlock> entries;

    public static RobsonBlock[][] robsonGenerateRoads(String caminhoArquivo, RobsonFactoryBlock factoryBlock) throws IOException {
        BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo));
        String linha;
        int currentLine = 0;

        var lines = Integer.parseInt(leitor.readLine().replaceAll("\\s+", ""));
        var columns = Integer.parseInt(leitor.readLine().replaceAll("\\s+", ""));

        mesh = new RobsonBlock[lines][columns];
        entries = new ArrayList<>();

        while ((linha = leitor.readLine()) != null && currentLine < lines) {
            String[] valores = linha.trim().split("\\s+");
            for (int currentColumn = 0; currentColumn < columns; currentColumn++) {
                var direction = Integer.parseInt(valores[currentColumn]);
                var entry = robsonCheckEntry(currentLine, currentColumn, direction, mesh);
                var exit = robsonCheckExit(currentLine, currentColumn, direction, mesh);
                var cross = direction > 4;
                var block = factoryBlock.create(cross, entry, exit, direction, currentLine, currentColumn);
                mesh[currentLine][currentColumn] = block;
                if (block.robsonIsEntry()) {
                    entries.add(block);
                }
            }
            currentLine++;
        }

        leitor.close();
        return mesh;
    }

    private static boolean robsonCheckEntry(int line, int column, int direction, RobsonBlock[][] mesh) {
        if (column == 0 && direction == 2) {
            return true;
        } else if (column == mesh[0].length - 1 && direction == 4) {
            return true;
        } else if (line == 0 && direction == 3) {
            return true;
        } else return line == mesh.length - 1 && direction == 1;
    }

    private static boolean robsonCheckExit(int line, int column, int direction, RobsonBlock[][] mesh) {
        if (column == 0 && direction == 4) {
            return true;
        } else if (column == mesh[0].length - 1 && direction == 2) {
            return true;
        } else if (line == 0 && direction == 1) {
            return true;
        } else return line == mesh.length - 1 && direction == 3;
    }

    public static RobsonBlock robsonGetRightBlock(int line, int column){
        return mesh[line][column + 1];
    }

    public static RobsonBlock robsonGetLeftBlock(int line, int column){
        return mesh[line][column - 1];
    }

    public static RobsonBlock robsonGetUpBlock(int line, int column){
        return mesh[line - 1][column];
    }

    public static RobsonBlock robsonGetDownBlock(int line, int column){
        return mesh[line + 1][column];
    }

    public static RobsonBlock[][] robsonGetMesh() {
        return mesh;
    }

    public static List<RobsonBlock> robsonGetEntries() { return entries; }
}
