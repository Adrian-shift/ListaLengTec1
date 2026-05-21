package questao_1;

public class CaixaEletronico {

    private int notasde10;
    private int notasde20;
    private int notasde50;
    private int notasde100;

    public CaixaEletronico(int notas10, int notas20, int notas50, int notas100) {
        this.notasde10 = notas10;
        this.notasde20 = notas20;
        this.notasde50 = notas50;
        this.notasde100 = notas100;
    }

    public String sacar(Conta conta, int valor) throws InsufBalanceException, MissingBallotsException {

        conta.sacar(valor);

        int backup10 = notasde10;
        int backup20 = notasde20;
        int backup50 = notasde50;
        int backup100 = notasde100;

        try {

            int restante = valor;

            int usar100 = Math.min(restante / 100, notasde100);
            restante -= usar100 * 100;

            int usar50 = Math.min(restante / 50, notasde50);
            restante -= usar50 * 50;

            int usar20 = Math.min(restante / 20, notasde20);
            restante -= usar20 * 20;

            int usar10 = Math.min(restante / 10, notasde10);
            restante -= usar10 * 10;

            // Não conseguiu montar o valor
            if (restante != 0) {

                throw new MissingBallotsException("O caixa não possui cédulas suficientes para esse valor.");
            }
            notasde100 -= usar100;
            notasde50 -= usar50;
            notasde20 -= usar20;
            notasde10 -= usar10;

            return
                    "Saque realizado com sucesso!\n\n"
                            + "Notas entregues:\n"
                            + "R$100: " + usar100 + "\n"
                            + "R$50: " + usar50 + "\n"
                            + "R$20: " + usar20 + "\n"
                            + "R$10: " + usar10;

        } catch (MissingBallotsException e) {

            notasde10 = backup10;
            notasde20 = backup20;
            notasde50 = backup50;
            notasde100 = backup100;

            conta.devolver(valor);

            throw e;
        }
    }

    public String getEstadoCaixa() {

        return
                "Notas disponíveis:\n"
                        + "R$100: " + notasde100 + "\n"
                        + "R$50: " + notasde50 + "\n"
                        + "R$20: " + notasde20 + "\n"
                        + "R$10: " + notasde10;
    }
}