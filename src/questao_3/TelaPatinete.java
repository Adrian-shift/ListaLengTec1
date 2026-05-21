package questao_3;

import javax.swing.*;
import java.awt.*;

public class TelaPatinete extends JFrame {

    private SistemaAluguel sistema;

    private JLabel lblBateria;
    private JLabel lblStatus;
    private JLabel lblCronometro;
    private JTextArea areaInfo;
    private JButton btnIniciar;
    private JButton btnFinalizar;

    private Timer timer;

    public TelaPatinete() {

        sistema = new SistemaAluguel();

        setTitle("Aluguel de Patinetes");

        setSize(550, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Sistema de Patinetes Elétricos", SwingConstants.CENTER);

        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        painel.add(titulo, BorderLayout.NORTH);

        areaInfo = new JTextArea();

        areaInfo.setEditable(false);

        areaInfo.setFont(new Font("Monospaced", Font.PLAIN, 16));

        JScrollPane scroll = new JScrollPane(areaInfo);

        painel.add(scroll, BorderLayout.CENTER);

        JPanel rodape = new JPanel(new GridLayout(5, 1, 10, 10));

        lblBateria = new JLabel();
        lblStatus = new JLabel();
        lblCronometro = new JLabel("Tempo: 0 s");

        btnIniciar = new JButton("Iniciar Aluguel");
        btnFinalizar = new JButton("Finalizar Aluguel");

        rodape.add(lblBateria);

        rodape.add(lblStatus);

        rodape.add(lblCronometro);

        rodape.add(btnIniciar);

        rodape.add(btnFinalizar);

        painel.add(
                rodape,
                BorderLayout.SOUTH
        );

        atualizarTela();

        add(painel);

        // Timer visual
        timer = new Timer(1000, e -> {

                    if (sistema.getRelogio().isLigado()) {
                        lblCronometro.setText("Tempo: " + sistema.getRelogio().getTempoSegundos() + " s");
                    }
                }
        );

        timer.start();

        btnIniciar.addActionListener(e -> {

            String resultado = sistema.iniciarAluguel();

            areaInfo.setText(resultado);

            atualizarTela();
        });

        btnFinalizar.addActionListener(e -> {

            String resultado = sistema.finalizarAluguel();

            areaInfo.setText(resultado);

            atualizarTela();
        });

        setVisible(true);
    }

    private void atualizarTela() {

        lblBateria.setText("Bateria: " + sistema.getPatinete().getBateria() + "%");
        lblStatus.setText(sistema.getPatinete().isOcupado() ? "Status: EM USO" : "Status: LIVRE");
    }

    public static void main(String[] args) {

        new TelaPatinete();
    }
}