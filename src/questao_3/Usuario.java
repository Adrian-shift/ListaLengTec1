package questao_3;

public class Usuario {

    private String nome;

    private boolean corridaAtiva;

    public Usuario(String nome) {
        this.nome = nome;
        this.corridaAtiva = false;
    }

    public String getNome() {
        return nome;
    }

    public boolean isCorridaAtiva() {
        return corridaAtiva;
    }

    public void iniciarCorrida() throws UsuarioComCorridaAbertaException {

        if (corridaAtiva) {
            throw new UsuarioComCorridaAbertaException("Usuário já possui corrida em aberto.");
        }

        corridaAtiva = true;
    }

    public void finalizarCorrida() {

        corridaAtiva = false;
    }
}