package questao_1;

import javax.swing.*;
import java.awt.*;

public class TelaCaixaEletronico extends JFrame {

    private Conta conta;
    private CaixaEletronico caixa;

    private JLabel lblSaldo;
    private JTextArea areaCaixa;
    private JTextField txtValor;
    private JButton btnSacar;

    public TelaCaixaEletronico() {

        conta = new Conta(1000);

        caixa = new CaixaEletronico(10, 10, 10, 10);

        setTitle("Caixa Eletrônico");
        setSize(450, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();

        painel.setLayout(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel topo = new JPanel(new GridLayout(2, 1));

        lblSaldo = new JLabel("Saldo: R$ " + conta.getSaldo());

        topo.add(lblSaldo);

        areaCaixa = new JTextArea();
        areaCaixa.setEditable(false);

        atualizarCaixa();

        JPanel centro = new JPanel(new GridLayout(3, 1, 10, 10));

        centro.add(new JLabel("Digite o valor do saque:"));

        txtValor = new JTextField();

        centro.add(txtValor);

        btnSacar = new JButton("Sacar");

        centro.add(btnSacar);

        painel.add(topo, BorderLayout.NORTH);
        painel.add(areaCaixa, BorderLayout.CENTER);
        painel.add(centro, BorderLayout.SOUTH);
        add(painel);

        btnSacar.addActionListener(e -> {

            try {

                int valor = Integer.parseInt(txtValor.getText());

                String resultado = caixa.sacar(conta, valor);

                atualizarCaixa();

                JOptionPane.showMessageDialog(null, resultado);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Digite um número válido.");

            } catch (InsufBalanceException | MissingBallotsException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });

        setVisible(true);
    }

    private void atualizarCaixa() {

        lblSaldo.setText("Saldo: R$ " + conta.getSaldo());
        areaCaixa.setText(caixa.getEstadoCaixa());
    }

    public static void main(String[] args) {

        new TelaCaixaEletronico();
    }
}