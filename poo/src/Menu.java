import java.util.InputMismatchException;
import java.util.Scanner;
 
/**
 * Menu interativo do EcoTrack — Etapa 2.
 *
 * Responsabilidade única: toda a interação com o usuário.
 *   Lê entradas, valida, exibe saídas — não faz cálculos nem cria regras.
 *
 * Fluxo principal:
 *   loop da sessão
 *     └── iniciarViagem()
 *           └── loop de trechos
 *                 ├── escolher transporte
 *                 ├── informar km (+ ocupantes se Carro)
 *                 ├── exibir resultado do trecho
 *                 └── "adicionar outro trecho" ou "fechar viagem"
 *           └── exibir relatório da viagem
 *           └── registrar na CalculadoraEmissao
 *
 * Validação de entrada:
 *   Todo lerInteiro() e lerDouble() usa try/catch com InputMismatchException.
 *   O usuário nunca quebra o programa digitando texto onde se espera número.
 */
public class Menu {
    // Catálogo de transportes disponíveis.
    // Para adicionar um novo modal: inserir aqui — o menu se adapta sozinho.
    private static final Transporte[] TRANSPORTES = {
        new Carro(),
        new Onibus(),
        new Aviao()
    };
    private final Scanner             scanner;
    private final CalculadoraEmissao  calculadora;

     /** Injeção de dependências: Menu não cria Scanner nem Calculadora. */
    public Menu(Scanner scanner, CalculadoraEmissao calculadora) {
        this.scanner     = scanner;
        this.calculadora = calculadora;
    }

    // Loop principal da sessão
    
    public void iniciar() {
        cabecalho();
        boolean continuar = true;
 
        while (continuar) {
            System.out.println("\n╔═════════════════════════════════╗");
            System.out.println("║  O que deseja fazer?            ║");
            System.out.println("║  1. Registrar nova viagem       ║");
            System.out.println("║  2. Ver histórico da sessão     ║");
            System.out.println("║  0. Encerrar                    ║");
            System.out.println("╚═════════════════════════════════╝");
 
            int opcao = lerInteiro("Opção: ", 0, 2);
 
            switch (opcao) {
                case 1: iniciarViagem();           break;
                case 2: calculadora.exibirHistorico(); break;
                case 0: continuar = false;         break;
            }
        }
 
        calculadora.exibirResumoSessao();
        System.out.println("\nAté logo! Continue monitorando sua pegada de carbono.");
    }
     // Fluxo de uma viagem 
 
    /**
     * Coleta nome e destino da viagem, depois entra no loop de trechos.
     * Ao final, exibe o relatório e registra a viagem na calculadora.
     */

    private void iniciarViagem(){
        System.out.println("\n == Nova Viagem ==");
        System.out.print("Nome da viagem (ex: Casa -> Trabalho): ");
        scanner.nextLine().trim();
        String nome = scanner.nextLine().trim();
         f (nome.isEmpty()) nome = "Viagem sem nome";
 
        System.out.print("Destino (ex: Centro, São Paulo): ");
        String destino = scanner.nextLine().trim();
        if (destino.isEmpty()) destino = "Destino não informado";
 
        Viagem viagem = new Viagem(nome, destino);

        // Loop de trechos que fica ativo até fechar a viagem
        boolean adicionandoTrechos = true;
        while (adicionandoTrechos){
            Trecho trecho = coletarTrecho();
            viagem.adicionarTrecho(trecho);

            System.out.printf("%n  Trecho registrado: %.2f kg CO₂%n",
                trecho.getEmissaoKgCo2());
            System.out.printf("  Subtotal da viagem até agora: %.2f kg CO₂%n",
                viagem.getSubtotal());

            System.out.println("n Deseja adicionar outro trecho para esta viagem?");
            System.out.println("1. Sim, adicionar trecho");
            System.out.println("  2. Não, fechar viagem");
 
            int resp = lerInteiro("  Opção: ", 1, 2);
            adicionandoTrechos = (resp == 1);
        }
        viagem.exibirRelatorio();
        calculadora.registrarViagem(viagem);
    }
    /**
     * Coleta os dados de um único trecho: modal + distância (+ ocupantes se Carro).
     *
     * @return Trecho calculado e pronto para ser adicionado à Viagem
     */
}
