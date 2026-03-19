import java.util.Scanner;

/**
 * Ponto de entrada da aplicação EcoTrack.
 *
 * Padrão Composition Root:
 *   Todas as dependências são instanciadas aqui, em um único lugar.
 *   Nenhuma outra classe cria suas próprias dependências.
 *   Isso permite trocar implementações facilmente — por exemplo,
 *   substituir o Menu de console por uma interface JavaFX (Etapa 5)
 *   sem alterar CalculadoraEmissao nem as classes de Transporte.
 *
 * try-with-resources:
 *   O Scanner é fechado automaticamente ao sair do bloco,
 *   mesmo que uma exceção não tratada interrompa a execução.
 */
public class App {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            CalculadoraEmissao calculadora = new CalculadoraEmissao();
            Menu menu = new Menu(scanner, calculadora);
            menu.iniciar();
        }
    }
}