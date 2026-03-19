import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Orquestra os cálculos de emissão e mantém o histórico de viagens da sessão.
 *
 * Responsabilidades:
 *   1. Calcular a emissão de um Trecho (Transporte + km → Trecho)
 *   2. Registrar Viagens concluídas no histórico
 *   3. Acumular o total geral da sessão
 *   4. Expor dados para o Menu exibir (somente leitura)
 *   5. Gerar o resumo final da sessão
 *
 * O que ela NÃO faz:
 *   - Não coleta dados do usuário (isso é o Menu)
 *   - Não cria Viagens (isso é o Menu, que conhece nome e destino)
 *   - Não salva em arquivo (Etapa 3)
 */
public class CalculadoraEmissao{
    private static final double KG_CO2_POR_ARVORE = 21.77;

    private final List<Viagem> historicoViagens = new ArrayList<>();
    private double totalSessaoKgCo2 = 0.0;

    /**
     * Calcula a emissão de um trecho e retorna o objeto Trecho imutável.
     * O chamador (Menu) é responsável por adicionar o Trecho à Viagem ativa.
     *
     * @param transporte modal utilizado
     * @param km         distância em quilômetros
     * @return           Trecho com emissão calculada
     */
    public Trecho calcularTrecho(Transporte transporte, double km){
        double emissao = transporte.calcularEmissao(km);
        return new Trecho(transporte, km, emissao);
    }
    /**
     * Registra uma viagem concluída no histórico da sessão e
     * acumula seu subtotal no total geral.
     *
     * Deve ser chamado apenas após a viagem ser "fechada" pelo usuário.
     *
     * @param viagem viagem com pelo menos um trecho
     */

    public void registrarViagem(Viagem viagem){
        if (viagem.getNumTrechos() == 0 ){
            throw new IllegalArgumentException("Não é possível registrar viagem sem trechos");
        }
        historicoViagens.add(viagem);
        totalSessaoKgCo2 +=viagem.getSubtotal();
        }
        /** Lista imutável de viagens registradas nesta sessão. */
    public List<Viagem> getHistoricoViagens(){
        return Collections.unmodifiableList(historicoViagens);
    }
    public double getTotalSessaoKgCo2()   { return totalSessaoKgCo2; }
    public int getQuantidadeViagens()  { return historicoViagens.size(); }
 
    public double getArvoresNecessarias() {
        return totalSessaoKgCo2 / KG_CO2_POR_ARVORE;
    }
    
    /**
     * Exibe o histórico completo de viagens da sessão.
     * Chamado quando o usuário escolhe a opção "Ver histórico".
     */

    public void exibirHistorico() {
        if (historicoViagens.isEmpty()) {
            System.out.println("\nNenhuma viagem registrada nesta sessão.");
            return;
        }
        System.out.println("\n─── Histórico da sessão ──────────────────────────────────────────");
        System.out.println("Viagem                         Trechos   CO₂ total");
        System.out.println("──────────────────────────────────────────────────────────────────");
        for (int i = 0; i < historicoViagens.size(); i++) {
            System.out.printf("  %2d. %s%n", i + 1, historicoViagens.get(i));
        }
        System.out.println("──────────────────────────────────────────────────────────────────");
        System.out.printf("  Total da sessão: %.2f kg CO₂ em %d viagem(ns)%n",
            totalSessaoKgCo2, historicoViagens.size());
    }
 
    /**
     * Resumo executivo exibido ao encerrar o programa.
     */
    public void exibirResumoSessao() {
        if (historicoViagens.isEmpty()) {
            System.out.println("\nNenhuma viagem registrada nesta sessão.");
            return;
        }
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║           RESUMO DA SESSÃO                  ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.printf ("║  Viagens realizadas  : %-21d║%n", historicoViagens.size());
 
        int totalTrechos = historicoViagens.stream()
            .mapToInt(Viagem::getNumTrechos).sum();
 
        System.out.printf ("║  Trechos registrados : %-21d║%n", totalTrechos);
        System.out.printf ("║  Total emitido       : %-15.2f kg CO₂ ║%n", totalSessaoKgCo2);
        System.out.printf ("║  Árvores p/ compensar: %-21.1f║%n", getArvoresNecessarias());
        System.out.println("╚══════════════════════════════════════════════╝");
    }
}
