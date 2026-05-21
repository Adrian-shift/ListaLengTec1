package questao_2;

import javax.swing.*;
import java.awt.*;

public class TelaEstufa extends JFrame {

    private Estufa estufa;
    private JTextArea areaStatus;
    private JButton btnMonitorar;

    public TelaEstufa() {

        estufa = new Estufa();

        setTitle("Monitoramento de Estufa");
        setSize(500, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();

        painel.setLayout(new BorderLayout(10, 10));

        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Sistema Inteligente de Estufa", SwingConstants.CENTER);

        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        areaStatus = new JTextArea();
        areaStatus.setEditable(false);
        areaStatus.setFont(new Font("Monospaced", Font.PLAIN, 16));

        JScrollPane scroll = new JScrollPane(areaStatus);

        btnMonitorar = new JButton("Monitorar Temperatura");

        painel.add(titulo, BorderLayout.NORTH);
        painel.add(scroll, BorderLayout.CENTER);
        painel.add(btnMonitorar, BorderLayout.SOUTH);

        add(painel);

        btnMonitorar.addActionListener(e -> {
            String resultado = estufa.monitorar();
            areaStatus.setText(resultado);
        });

        setVisible(true);
    }

    public static void main(String[] args) {

        new TelaEstufa();
    }
}