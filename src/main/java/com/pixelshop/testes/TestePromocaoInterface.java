package com.pixelshop.testes;

import com.pixelshop.modelo.JogoFisico;
import com.pixelshop.modelo.Produto;
import com.pixelshop.modelo.Promovivel;

public class TestePromocaoInterface {
    public static void main(String[] args) {
        Produto produto = new JogoFisico(
                "God of War",
                249.90,
                8,
                "PlayStation 5",
                true
        );

        System.out.println("=== TESTE DA INTERFACE DE PROMOÇÃO ===");
        System.out.println("Antes do desconto:");
        System.out.println(produto);

        if (produto instanceof Promovivel) {
            Promovivel promovivel = (Promovivel) produto;
            promovivel.aplicarDesconto(20);
        }

        System.out.println();
        System.out.println("Depois do desconto de 20%:");
        System.out.println(produto);
    }
}
