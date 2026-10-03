package com.pixelshop.modelo;

public class JogoDigital extends Produto {
    private final double tamanhoArquivoGb;

    public JogoDigital(String nome, double preco, int quantidadeEstoque,
                       double tamanhoArquivoGb) {
        super(nome, preco, quantidadeEstoque);

        if (tamanhoArquivoGb <= 0) {
            throw new IllegalArgumentException("O tamanho do arquivo deve ser maior que zero.");
        }

        this.tamanhoArquivoGb = tamanhoArquivoGb;
    }

    public double getTamanhoArquivoGb() {
        return tamanhoArquivoGb;
    }

    @Override
    public String toString() {
        return String.format(
                "Jogo Digital: %s | Tamanho: %.2f GB | Preço: R$ %.2f | Estoque: %d",
                getNome(),
                tamanhoArquivoGb,
                getPreco(),
                getQuantidadeEstoque()
        );
    }
}
