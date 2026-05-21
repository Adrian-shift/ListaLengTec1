package questao_4;

import java.util.ArrayList;

public class Biblioteca {

    private ArrayList<Livro> livros;

    private final int LIMITE = 100;

    public Biblioteca() {
        livros = new ArrayList<>();
    }

    // Adicionar livro
    public void adicionarLivro(Livro livro) throws LimiteAcervoException {

        if (livros.size() >= LIMITE) {

            throw new LimiteAcervoException(
                    "Limite do acervo atingido."
            );
        }

        livros.add(livro);
    }

    // Buscar livro
    public Livro buscarLivro(int id) throws LivroNaoEncontradoException {

        for (Livro livro : livros) {

            if (livro.getId() == id) {

                return livro;
            }
        }

        throw new LivroNaoEncontradoException(
                "Livro com ID "
                        + id
                        + " não encontrado."
        );
    }

    // Empréstimo
    public void emprestarLivro(int id) throws LivroNaoEncontradoException, EmprestimoInvalidoException {

        Livro livro = buscarLivro(id);

        if (livro.isEstaEmprestado()) {

            throw new EmprestimoInvalidoException(
                    "Livro já está emprestado."
            );
        }

        livro.setEstaEmprestado(true);
    }

    // Devolução
    public void devolverLivro(int id) throws LivroNaoEncontradoException {

        Livro livro = buscarLivro(id);

        livro.setEstaEmprestado(false);
    }

    public ArrayList<Livro> getLivros() {

        return livros;
    }
}