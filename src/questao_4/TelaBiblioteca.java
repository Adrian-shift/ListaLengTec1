package questao_4;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaBiblioteca extends JFrame {

    private Biblioteca biblioteca;
    private JTable tabela;
    private DefaultTableModel modelo;
    private JTextField txtId;
    private JTextArea areaLog;

    public TelaBiblioteca() {

        biblioteca = new Biblioteca();

        inicializarLivros();

        setTitle("Biobliotech - Gestão de Acervo");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new BorderLayout(15, 15));

        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 2));

        // TÍTULO
        JLabel titulo = new JLabel("Sistema Biobliotech", SwingConstants.CENTER);

        titulo.setFont(new Font("Arial", Font.BOLD, 28));

        painel.add(titulo, BorderLayout.NORTH);

        // TABELA
        modelo = new DefaultTableModel();

        modelo.addColumn("ID");

        modelo.addColumn("Título");

        modelo.addColumn("Autor");

        modelo.addColumn("Status");

        tabela = new JTable(modelo);

        atualizarTabela();

        JScrollPane scrollTabela = new JScrollPane(tabela);

        painel.add(scrollTabela, BorderLayout.CENTER);

        // PAINEL DIREITO
        JPanel painelDireito = new JPanel();

        painelDireito.setLayout(new BorderLayout(10, 10));

        JPanel painelControle = new JPanel(new GridLayout(6, 1, 10, 10));

        txtId = new JTextField();

        JButton btnBuscar = new JButton("Buscar Livro");
        JButton btnEmprestar = new JButton("Emprestar");
        JButton btnDevolver = new JButton("Devolver");

        painelControle.add(new JLabel("ID do Livro:"));

        painelControle.add(txtId);

        painelControle.add(btnBuscar);
        painelControle.add(btnEmprestar);
        painelControle.add(btnDevolver);

        areaLog = new JTextArea();

        areaLog.setEditable(false);

        areaLog.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scrollLog = new JScrollPane(areaLog);

        painelDireito.add(painelControle, BorderLayout.NORTH);
        painelDireito.add(scrollLog, BorderLayout.CENTER);
        painelDireito.setPreferredSize(new Dimension(300, 0));
        painel.add(painelDireito, BorderLayout.EAST);

        add(painel);

        // EVENTOS

        btnBuscar.addActionListener(e -> {

            try {

                int id = Integer.parseInt(txtId.getText());

                Livro livro = biblioteca.buscarLivro(id);

                log("Livro encontrado:\n" + livro.getTitulo());

            } catch (LivroNaoEncontradoException ex) {

                log("ERRO: " + ex.getMessage());

            } catch (NumberFormatException ex) {

                log("Digite um ID válido.");
            }
        });

        btnEmprestar.addActionListener(e -> {

            try {

                int id = Integer.parseInt(txtId.getText());

                biblioteca.emprestarLivro(id);

                atualizarTabela();

                log("Livro emprestado com sucesso.");

            } catch (LivroNaoEncontradoException | EmprestimoInvalidoException ex) {

                log("ERRO: " + ex.getMessage());

            } finally {
                log("Sessão de atendimento finalizada.");
            }
        });

        btnDevolver.addActionListener(e -> {

            try {

                int id = Integer.parseInt(txtId.getText());

                biblioteca.devolverLivro(id);

                atualizarTabela();
                log("Livro devolvido.");

            } catch (LivroNaoEncontradoException ex) {

                log("ERRO: " + ex.getMessage());
            }
        });

        setVisible(true);

        // SIMULAÇÃO
        simulacaoAutomatica();
    }

    private void inicializarLivros() {

        try {

            biblioteca.adicionarLivro(
                    new Livro(
                            1,
                            "Clean Code",
                            "Robert Martin"
                    )
            );

            biblioteca.adicionarLivro(
                    new Livro(
                            2,
                            "Java Efetivo",
                            "Joshua Bloch"
                    )
            );

            biblioteca.adicionarLivro(
                    new Livro(
                            3,
                            "Design Patterns",
                            "GoF"
                    )
            );

        } catch (LimiteAcervoException e) {

            log(e.getMessage());
        }
    }

    private void atualizarTabela() {

        modelo.setRowCount(0);

        for (
                Livro livro
                : biblioteca.getLivros()
        ) {

            modelo.addRow(
                    new Object[]{

                            livro.getId(),

                            livro.getTitulo(),

                            livro.getAutor(),

                            livro.isEstaEmprestado()
                                    ? "Emprestado"
                                    : "Disponível"
                    }
            );
        }
    }

    private void log(String mensagem) {

        areaLog.append(
                mensagem + "\n\n"
        );
    }
    private void simulacaoAutomatica() {

        try {

            biblioteca.emprestarLivro(1);

            log("Fluxo 1:\n" + "Livro emprestado.");

        } catch (Exception e) {

            log(e.getMessage());

        } finally {

            log("Sessão de atendimento finalizada.");
        }

        try {

            biblioteca.emprestarLivro(1);

        } catch (
                EmprestimoInvalidoException e
        ) {

            log(
                    "Fluxo 2:\n"
                            + e.getMessage()
            );

        } catch (
                LivroNaoEncontradoException e
        ) {

            log(e.getMessage());

        } finally {

            log("Sessão de atendimento finalizada.");
        }

        try {

            biblioteca.buscarLivro(999);

        } catch (LivroNaoEncontradoException e) {

            log("Fluxo 3:\n" + e.getMessage());
        }
    }

    public static void main(String[] args) {

        new TelaBiblioteca();
    }
}