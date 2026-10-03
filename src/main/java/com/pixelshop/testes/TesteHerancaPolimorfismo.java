package com.pixelshop.testes;

import com.pixelshop.modelo.JogoDigital;
import com.pixelshop.modelo.JogoFisico;
import com.pixelshop.modelo.Produto;

public class TesteHerancaPolimorfismo {
    public static void main(String[] args) {
        Produto produto1 = new JogoFisico(
                "FIFA 26",
                299.90,
                10,
                "PlayStation 5",
                true
        );

        Produto produto2 = new JogoDigital(
                "Minecraft",
                129.90,
                20,
                4.5
        );

        Produto produto3 = new JogoFisico(
                "FIFA 26",
                299.90,
                5,
                "Xbox Series",
                false
        );

        System.out.println("=== HERANÇA E POLIMORFISMO ===");
        System.out.println(produto1);
        System.out.println(produto2);

        System.out.println();
        System.out.println("Total de produtos criados: "
                + Produto.getTotalProdutosCadastrados());

        System.out.println();
        System.out.println("Comparação com equals():");
        System.out.println("produto1.equals(produto3): " + produto1.equals(produto3));
        System.out.println("produto1.equals(produto2): " + produto1.equals(produto2));
    }
}
