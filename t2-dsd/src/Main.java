import factory.CreateSemaphoreBlock;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        var matrixFile = "/Users/luizportela/Documents/udesc/6a-fase/desenvolvimento-sistemas-distribuidos/T2/t2-dsd/resources/malha-exemplo-3.txt";
        try {
            var matrix = CreateMesh.generateRoads(matrixFile, new CreateSemaphoreBlock());
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    System.out.print(matrix[i][j] + "    ");
                }
                System.out.println();
            }

            System.out.println("number of lines = " + matrix.length);
            System.out.println("number of columns = " + matrix[0].length);
        } catch (IOException e) {
            e.printStackTrace();
        }

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

