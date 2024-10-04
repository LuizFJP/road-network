import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CreateMatrix {
    public static int[][] generateRoads(String caminhoArquivo) throws IOException {
        BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo));
            String linha;
            int linhaAtual = 0;

            var lines = Integer.parseInt(leitor.readLine().replaceAll("\\s+", ""));
            var columns = Integer.parseInt(leitor.readLine().replaceAll("\\s+", ""));

            int[][] matriz = new int[lines][columns];

            while ((linha = leitor.readLine()) != null && linhaAtual < lines) {
                String[] valores = linha.trim().split("\\s+");
                for (int colunaAtual = 0; colunaAtual < columns; colunaAtual++) {
                    matriz[linhaAtual][colunaAtual] = Integer.parseInt(valores[colunaAtual]);
                }
                linhaAtual++;
            }

            leitor.close();
            return matriz;
        }
}
