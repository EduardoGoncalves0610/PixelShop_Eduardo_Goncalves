package com.pixelshop.modelo;

import java.util.Objects;

public abstract class Produto {
    private final String nome;
    private double preco;
    private int quantidadeEstoque;
    private static int totalProdutosCadastrados = 0;

    protected Produto(String nome, double preco, int quantidadeEstoque) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do produto não pode ser vazio.");
        }
        if (preco < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo.");
        }
        if (quantidadeEstoque < 0) {
            throw new IllegalArgumentException("A quantidade em estoque não pode ser negativa.");
        }

        this.nome = nome.trim();
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        totalProdutosCadastrados++;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    protected void setPreco(double preco) {
        if (preco < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo.");
        }
        this.preco = preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void adicionarEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        quantidadeEstoque += quantidade;
    }

    public void removerEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        if (quantidade > quantidadeEstoque) {
            throw new IllegalArgumentException("Estoque insuficiente.");
        }
        quantidadeEstoque -= quantidade;
    }

    public static int getTotalProdutosCadastrados() {
        return totalProdutosCadastrados;
    }

    @Override
    public String toString() {
        return String.format(
                "Produto: %s | Preço: R$ %.2f | Estoque: %d",
                nome, preco, quantidadeEstoque
        );
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }
        if (!(objeto instanceof Produto)) {
            return false;
        }

        Produto outro = (Produto) objeto;
        return nome.equalsIgnoreCase(outro.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome.toLowerCase());
    }
}
