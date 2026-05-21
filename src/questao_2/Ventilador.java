package questao_2;

import java.util.Random;

public class Ventilador {

    private Random random = new Random();

    public void ligar() throws FalhaNoAtuadorException {

        int falha = random.nextInt(10);

        if (falha < 3) {
            throw new FalhaNoAtuadorException("Falha ao ligar ventilador.");
        }
    }
}
