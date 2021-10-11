package br.com.livraria.entity;

public class Venda {
    private Livro livro;
    private Integer qtd;

    public Venda() {
    }

    public Venda(Livro livro, Integer qtd) {
        this.livro = livro;
        this.qtd = qtd;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }
}
