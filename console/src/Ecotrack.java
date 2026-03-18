//package console.src;

import java.util.Scanner;

public class Ecotrack{

    // Fatores de emissão: kg de CO₂ por km percorrido
    static final double FATOR_CARRO  = 0.21;  // carro a gasolina
    static final double FATOR_ONIBUS = 0.089; // ônibus urbano
    static final double FATOR_AVIAO  = 0.255; // voo doméstico

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean continuar = true;

        System.out.println("=== EcoTrack — Calculadora de Emissões ===");

        while (continuar) {
            System.out.println("\nEscolha o transporte:");
            System.out.println("  1. Carro");
            System.out.println("  2. Ônibus");
            System.out.println("  3. Avião");
            System.out.println("  0. Sair");
            System.out.print("Opção: ");

            int opcao = sc.nextInt();

            if (opcao == 0) {
                continuar = false;
                continue;
            }

            System.out.print("Quantos km você percorreu? ");
            double km = sc.nextDouble();

            double emissao = calcularEmissao(opcao, km);

            System.out.printf("\nResultado: %.2f kg de CO₂ emitidos%n", emissao);
            System.out.printf("Equivale a plantar %.1f árvores para compensar.%n",
                emissao / 21.77);
        }

        System.out.println("\nAté logo! Continue monitorando sua pegada.");
        sc.close();
    }

    static double calcularEmissao(int tipo, double km) {
        switch (tipo) {
            case 1: return km * FATOR_CARRO;
            case 2: return km * FATOR_ONIBUS;
            case 3: return km * FATOR_AVIAO;
            default: return 0;
        }
    }
}