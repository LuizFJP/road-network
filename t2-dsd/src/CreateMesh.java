import factory.CreateSemaphoreBlock;
import factory.FactoryBlock;
import models.Block;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CreateMesh {
    public static Block[][] generateRoads(String caminhoArquivo, FactoryBlock factoryBlock) throws IOException {
        BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo));
        String linha;
        int currentLine = 0;

        var lines = Integer.parseInt(leitor.readLine().replaceAll("\\s+", ""));
        var columns = Integer.parseInt(leitor.readLine().replaceAll("\\s+", ""));

        Block[][] mesh = new Block[lines][columns];

        while ((linha = leitor.readLine()) != null && currentLine < lines) {
            String[] valores = linha.trim().split("\\s+");
            for (int currentColumn = 0; currentColumn < columns; currentColumn++) {
                var direction = Integer.parseInt(valores[currentColumn]);
                var entry = checkEntry(currentLine, currentColumn, direction, mesh);
                var cross = direction > 4;
                var block = factoryBlock.create(cross, entry, direction);
                mesh[currentLine][currentColumn] = block;
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
}
