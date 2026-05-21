package questao_1;

import questao_1.InsufBalanceException;

public class Conta {

    private double saldo;

    public Conta(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void sacar(double valor)
            throws InsufBalanceException {

        if (valor > saldo) {
            throw new InsufBalanceException(
                    "Saldo insuficiente."
            );
        }

        saldo -= valor;
    }

    public void devolver(double valor) {
        saldo += valor;
    }
}
