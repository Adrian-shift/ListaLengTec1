package questao_3;

public class Patinete {

    private boolean ocupado;
    private int bateria;

    public Patinete(int bateria) {
        this.ocupado = false;
        this.bateria = bateria;
    }

    public int getBateria() {
        return bateria;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void destravar() throws BateriaFracaException, VeiculoOcupadoException {

        if (bateria < 20) {
            throw new BateriaFracaException("Patinete com bateria insuficiente.");
        }

        if (ocupado) {
            throw new VeiculoOcupadoException("Patinete já está em uso.");
        }

        ocupado = true;
    }

    public void devolver() {

        bateria -= 10;
        ocupado = false;

        if (bateria < 0) {
            bateria = 0;
        }
    }
}