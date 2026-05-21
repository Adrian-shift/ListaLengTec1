package questao_3;

public class CronometroCobranca {

    private boolean ligado;

    private long iniciu;

    public CronometroCobranca() {

        ligado = false;
    }

    public void iniciar() {

        ligado = true;

        iniciu = System.currentTimeMillis();
    }

    public void parar() {

        ligado = false;
    }

    public boolean isLigado() {
        return ligado;
    }

    public long getTempoSegundos() {

        if (!ligado) {
            return 0;
        }

        return
                (System.currentTimeMillis()
                        - iniciu) / 1000;
    }
}