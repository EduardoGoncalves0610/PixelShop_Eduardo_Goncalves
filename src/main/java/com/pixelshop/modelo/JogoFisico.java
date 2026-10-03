package com.pixelshop.modelo;

public class JogoFisico extends Produto implements Promovivel {
    private final String plataforma;
    private final boolean manualImpresso;

    public JogoFisico(String nome, double preco, int quantidadeEstoque,
                      String plataforma, boolean manualImpresso) {
        super(nome, preco, quantidadeEstoque);

        if (plataforma == null || plataforma.isBlank()) {
            throw new IllegalArgumentException("A plataforma não pode ser vazia.");
        }

        this.plataforma = plataforma.trim();
        this.manualImpresso = manualImpresso;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public boolean isManualImpresso() {
        return manualImpresso;
    }

    @Override
    public void aplicarDesconto(double percentual) {
        validarPercentual(percentual);
        double novoPreco = getPreco() - (getPreco() * percentual / 100);
        setPreco(novoPreco);
    }

    private void validarPercentual(double percentual) {
        if (percentual < 0 || percentual > 100) {
            throw new IllegalArgumentException("O desconto deve estar entre 0% e 100%.");
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Jogo Físico: %s | Plataforma: %s | Manual: %s | Preço: R$ %.2f | Estoque: %d",
                getNome(),
                plataforma,
                manualImpresso ? "Sim" : "Não",
                getPreco(),
                getQuantidadeEstoque()
        );
    }
}
