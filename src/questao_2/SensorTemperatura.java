package questao_2;

import java.util.Random;

public class SensorTemperatura {

    private Random random = new Random();

    public double lerTemperatura() throws LeituraInconsistenteException {

        double temperatura = random.nextInt(1500) - 500;

        if (temperatura < -50 || temperatura > 60) {
            throw new LeituraInconsistenteException("Leitura inconsistente: " + temperatura + "°C");
        }
        return temperatura;
    }
}