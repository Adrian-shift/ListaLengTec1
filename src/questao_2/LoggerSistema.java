package questao_2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LoggerSistema {

    public static void registrar(String mensagem) {

        try {

            FileWriter arquivo = new FileWriter("logs.txt", true);
            PrintWriter escritor = new PrintWriter(arquivo);

            escritor.println(mensagem);
            escritor.close();

        } catch (IOException e) {
            System.out.println("Erro ao escrever log.");
        }
    }
}