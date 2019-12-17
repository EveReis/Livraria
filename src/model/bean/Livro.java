package model.bean;

public class Livro {
    
    private String titulo;
    private String autor;
    private String categoria;
    private boolean status;
    private double preco;
    private int paginas;
    private int isbn;
    

    public void setISBN(int isbn) {
        this.isbn = isbn;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getISBN() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getPaginas() {
        return paginas;
    }

    public double getPreco() {
        return preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public boolean isStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Livro{" + "titulo=" + titulo + ", autor=" + autor + ", categoria=" + categoria + ", status=" + status + ", preco=" + preco + ", paginas=" + paginas + ", isbn=" + isbn + '}';
    }
    
    
    
}
