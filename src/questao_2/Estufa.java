package questao_2;

public class Estufa {

    private SensorTemperatura sensor;
    private Ventilador ventilador;
    private Aquecedor aquecedor;

    public Estufa() {
        sensor = new SensorTemperatura();
        ventilador = new Ventilador();
        aquecedor = new Aquecedor();
    }

    public String monitorar() {

        String status = "";

        try {

            double temperatura = sensor.lerTemperatura();
            status += "Temperatura atual: " + temperatura + "°C\n";

            if (temperatura > 30) {

                ventilador.ligar();
                status += "Ventilador ligado.";

            } else if (temperatura < 18) {

                aquecedor.ligar();
                status += "Aquecedor ligado.";

            } else {

                status += "Temperatura ideal.";
            }

        } catch (
                LeituraInconsistenteException
                | FalhaNoAtuadorException e
        ) {
            status = "ERRO: " + e.getMessage();

        } finally {
            LoggerSistema.registrar(status);
        }

        return status;
    }
}