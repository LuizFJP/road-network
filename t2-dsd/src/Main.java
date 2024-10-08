

import factory.CreateSemaphoreBlock;
import models.Block;
import models.CreateMesh;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        var matrixFile = "/Users/luizportela/Documents/udesc/6a-fase/desenvolvimento-sistemas-distribuidos/T2/t2-dsd/resources/malha-exemplo-3.txt";
        Block[][] mesh = null;
        try {
            mesh = CreateMesh.generateRoads(matrixFile, new CreateSemaphoreBlock());
            for (int i = 0; i < mesh.length; i++) {
                for (int j = 0; j < mesh[i].length; j++) {
                    System.out.print(mesh[i][j] + "    ");
                }
                System.out.println();
            }

            System.out.println("number of lines = " + mesh.length);
            System.out.println("number of columns = " + mesh[0].length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        var entries = CreateMesh.getEntries();
        var spawner = new Spawner(entries, 100, mesh);
        var threadSpawner = new Thread(spawner);
        threadSpawner.start();
    }
}

//- Gerar a matriz do semáforo
//- Elaborar a lógica dos cruzamentos e estradas com o semáforo
//
//- Geração dos carros
//- Implementação da lógica com semáforo
//- Encaixar o front (talvez)
//
//
// - Elaborar a lógica dos cruzamentos e estradas com o monitor
//- Implementar a lógica do monitor
//- Encaixar o front (talvez)
//
//        - Encaixar o front
//- Montar a apresentação

