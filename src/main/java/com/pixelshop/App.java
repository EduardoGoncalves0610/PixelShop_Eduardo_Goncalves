package com.pixelshop;

import com.pixelshop.modelo.JogoDigital;
import com.pixelshop.modelo.JogoFisico;
import com.pixelshop.modelo.Produto;
import com.pixelshop.modelo.Promovivel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Produto> produtos = new ArrayList<>();

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");

            try {
                switch (opcao) {
                    case 1 -> cadastrarProduto();
                    case 2 -> consultarProdutos();
                    case 3 -> entradaEstoque();
                    case 4 -> saidaEstoque();
                    case 5 -> aplicarDescontos();
                    case 6 -> exibirTotalProdutosCriados();
                    case 0 -> System.out.println("Sistema encerrado.");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }

            System.out.println();
        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("======================================");
        System.out.println("             PIXELSHOP");
        System.out.println("      Gestão de Jogos e Acessórios");
        System.out.println("======================================");
        System.out.println("1 - Cadastrar produto");
        System.out.println("2 - Consultar produtos e estoque");
        System.out.println("3 - Entrada de estoque");
        System.out.println("4 - Saída de estoque");
        System.out.println("5 - Aplicar desconto promocional");
        System.out.println("6 - Total de produtos criados");
        System.out.println("0 - Sair");
        System.out.println("======================================");
    }

    private static void cadastrarProduto() {
        System.out.println();
        System.out.println("=== CADASTRO DE PRODUTO ===");
        System.out.println("1 - Jogo físico");
        System.out.println("2 - Jogo digital");

        int tipo = lerInteiro("Tipo: ");
        String nome = lerTexto("Nome: ");
        double preco = lerDouble("Preço: ");
        int estoque = lerInteiro("Quantidade em estoque: ");

        Produto novoProduto;

        if (tipo == 1) {
            String plataforma = lerTexto("Plataforma: ");
            boolean manual = lerBoolean("Possui manual impresso? (s/n): ");
            novoProduto = new JogoFisico(nome, preco, estoque, plataforma, manual);
        } else if (tipo == 2) {
            double tamanho = lerDouble("Tamanho do arquivo em GB: ");
            novoProduto = new JogoDigital(nome, preco, estoque, tamanho);
        } else {
            System.out.println("Tipo de produto inválido.");
            return;
        }

        for (Produto produto : produtos) {
            if (produto.equals(novoProduto)) {
                System.out.println("Já existe um produto cadastrado com esse nome.");
                return;
            }
        }

        produtos.add(novoProduto);
        System.out.println("Produto cadastrado com sucesso.");
    }

    private static void consultarProdutos() {
        System.out.println();
        System.out.println("=== PRODUTOS CADASTRADOS ===");

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        double valorTotal = 0;

        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);
            System.out.println((i + 1) + " - " + produto);
            valorTotal += produto.getPreco() * produto.getQuantidadeEstoque();
        }

        System.out.printf("%nValor total em estoque: R$ %.2f%n", valorTotal);
    }

    private static void entradaEstoque() {
        Produto produto = selecionarProduto();
        if (produto == null) {
            return;
        }

        int quantidade = lerInteiro("Quantidade para entrada: ");
        produto.adicionarEstoque(quantidade);

        System.out.println("Entrada registrada com sucesso.");
        System.out.println(produto);
    }

    private static void saidaEstoque() {
        Produto produto = selecionarProduto();
        if (produto == null) {
            return;
        }

        int quantidade = lerInteiro("Quantidade para saída: ");
        produto.removerEstoque(quantidade);

        System.out.println("Saída registrada com sucesso.");
        System.out.println(produto);
    }

    private static void aplicarDescontos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        double percentual = lerDouble("Percentual de desconto: ");
        int quantidadePromovida = 0;

        for (Produto produto : produtos) {
            if (produto instanceof Promovivel) {
                Promovivel promovivel = (Promovivel) produto;
                promovivel.aplicarDesconto(percentual);
                quantidadePromovida++;
            }
        }

        System.out.println("Desconto aplicado em " + quantidadePromovida + " produto(s).");
    }

    private static void exibirTotalProdutosCriados() {
        System.out.println(
                "Total global de produtos criados: "
                        + Produto.getTotalProdutosCadastrados()
        );
    }

    private static Produto selecionarProduto() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return null;
        }

        consultarProdutos();
        int numero = lerInteiro("Informe o número do produto: ");

        if (numero < 1 || numero > produtos.size()) {
            System.out.println("Produto inválido.");
            return null;
        }

        return produtos.get(numero - 1);
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }

    private static double lerDouble(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                String valor = scanner.nextLine().trim().replace(",", ".");
                return Double.parseDouble(valor);
            } catch (NumberFormatException e) {
                System.out.println("Digite um valor numérico válido.");
            }
        }
    }

    private static boolean lerBoolean(String mensagem) {
        while (true) {
            String resposta = lerTexto(mensagem).trim().toLowerCase();

            if (resposta.equals("s")) {
                return true;
            }

            if (resposta.equals("n")) {
                return false;
            }

            System.out.println("Digite s para sim ou n para não.");
        }
    }
}
