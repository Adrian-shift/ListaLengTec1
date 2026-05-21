package questao_4;

public class Livro {

    private int id;

    private String titulo;

    private String autor;

    private boolean estaEmprestado;

    public Livro(int id, String titulo, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.estaEmprestado = false;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isEstaEmprestado() {
        return estaEmprestado;
    }

    public void setEstaEmprestado(boolean estaEmprestado) {
        this.estaEmprestado = estaEmprestado;
    }
}

