package questao_3;

public class SistemaAluguel {

    private Patinete patinete;
    private Usuario usuario;
    private CronometroCobranca relogio;

    public SistemaAluguel() {
        patinete = new Patinete(100);
        usuario = new Usuario("Adrian");
        relogio = new CronometroCobranca();
    }

    public String iniciarAluguel() {

        try {

            // Verifica usuário
            usuario.iniciarCorrida();

            // Tenta destravar
            patinete.destravar();

            relogio.iniciar();

            return "Patinete destravado com sucesso!\n" + "Cobrança iniciada.";

        } catch (
                BateriaFracaException
                | VeiculoOcupadoException
                | UsuarioComCorridaAbertaException e
        ) {
            relogio.parar();
            usuario.finalizarCorrida();

            return "ERRO: " + e.getMessage();
        }
    }

    public String finalizarAluguel() {

        if (!relogio.isLigado()) {

            return "Nenhuma corrida em andamento.";
        }

        relogio.parar();

        usuario.finalizarCorrida();

        patinete.devolver();

        return "Corrida encerrada.\n" + "Tempo total: " + relogio.getTempoSegundos() + " segundos.";
    }

    public Patinete getPatinete() {
        return patinete;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public CronometroCobranca getRelogio() {
        return relogio;
    }
}