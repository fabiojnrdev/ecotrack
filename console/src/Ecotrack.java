import java.util.Scanner;

public class EcoTrack {

    // Fatores de emissão: kg de CO₂ por km percorrido
    // Fonte: IPCC (2023) — médias para Brasil/América do Sul
    static final double FATOR_CARRO  = 0.21;   // carro a gasolina individual
    static final double FATOR_ONIBUS = 0.089;  // ônibus urbano (passageiro/km)
    static final double FATOR_AVIAO  = 0.255;  // voo doméstico (passageiro/km)

    // Fator de compensação: uma árvore absorve ~21,77 kg CO₂ por ano (média FAO)
    static final double KG_CO2_POR_ARVORE = 21.77;

    public static void main(String[] args) {

        // Scanner é o "ouvido" do programa: lê o que o usuário digita
        // System.in representa o teclado (entrada padrão do sistema)
        Scanner scanner = new Scanner(System.in);

        // Variável de controle do loop principal
        // Enquanto for true, o programa continua pedindo cálculos
        boolean continuar = true;

        System.out.println("=== EcoTrack — Calculadora de Emissões ===");

        while (continuar) {
            System.out.println("\nEscolha o transporte:");
            System.out.println("  1. Carro");
            System.out.println("  2. Ônibus");
            System.out.println("  3. Avião");
            System.out.println("  0. Sair");
            System.out.print("Opção: ");

            int opcao = scanner.nextInt();

            // Usuário escolheu sair
            if (opcao == 0) {
                continuar = false;
                continue;
            }

            // Validação: opção fora do intervalo esperado
            if (opcao < 1 || opcao > 3) {
                System.out.println("Opção inválida. Tente novamente.");
                continue;
            }

            System.out.print("Quantos km você percorreu? ");
            double km = scanner.nextDouble();

            double emissao = calcularEmissao(opcao, km);

            System.out.printf("\nResultado: %.2f kg de CO₂ emitidos%n", emissao);
            System.out.printf("Equivale a plantar %.1f árvores para compensar.%n",
                emissao / KG_CO2_POR_ARVORE);
        }

        System.out.println("\nAté logo! Continue monitorando sua pegada.");
        scanner.close();
    }

    /**
     * Calcula a emissão de CO₂ com base no tipo de transporte e distância.
     *
     * Este método é static porque não depende do estado de nenhum objeto —
     * ele só precisa dos parâmetros de entrada para retornar o resultado.
     * Na Etapa 2 (pasta poo/), esta lógica migra para dentro das classes de transporte.
     *
     * @param tipo  código do transporte: 1=Carro, 2=Ônibus, 3=Avião
     * @param km    distância percorrida em quilômetros
     * @return      emissão em kg de CO₂ equivalente; 0.0 se tipo inválido
     */
    static double calcularEmissao(int tipo, double km) {
        switch (tipo) {
            case 1: return km * FATOR_CARRO;
            case 2: return km * FATOR_ONIBUS;
            case 3: return km * FATOR_AVIAO;
            default: return 0.0;
        }
    }
}