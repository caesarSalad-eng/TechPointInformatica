package model;

public class Produto {
    private int codigo;
    private String nome;
    private String categoria;
    private double preco;
    private int estoque;

    //Atributos
    public Produto(int codigo, String nome, String categoria, double preco, int estoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.estoque = estoque;;
    }

    //Getters
    public int getCodigo() {
        return codigo;
    }
    public String getNome() {
        return nome;
    }
    public String getCategoria() {
        return categoria;
    }
    public double getPreco() {
        return preco;
    }
    public int getEstoque() {
        return estoque;
    }

    //Setter
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void atualizarEstoque(int quantidadeVendida) {
        if (quantidadeVendida <= estoque) {
            estoque -= quantidadeVendida;
        } else {
            System.out.println("Estoque insuficiente para o produto: " + nome);
        }
    }

    @Override
    public String toString() {
        return "Código: " + codigo + " | " + nome + " (" + categoria + " ) - R$ " + preco + " | Estoque: " + estoque;
    }
}

//terminado
