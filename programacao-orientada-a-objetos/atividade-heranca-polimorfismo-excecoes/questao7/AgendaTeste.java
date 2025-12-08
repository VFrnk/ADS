import java.time.LocalDate;

public class AgendaTeste {

    public static void main(String[] args) {

        // Criar agenda
        Agenda agenda = new Agenda();

        // Criar tarefas
        Tarefa t1 = new TarefaSimples("Ler emails", 2);

        Tarefa t2 = new TarefaComPrazo(
                "Enviar relatorio",
                3,
                LocalDate.now().plusDays(1) // prazo futuro → deve concluir
        );

        Tarefa t3 = new TarefaComPrazo(
                "Pagar boleto",
                4,
                LocalDate.now().minusDays(3) // prazo expirado → deve lançar exceção
        );

        Tarefa t4 = new TarefaRecorrente(
                "Fazer backup",
                1,
                3 // precisa ser chamada 3 vezes para concluir
        );

        // Adicionar tarefas
        agenda.adicionar(t1);
        agenda.adicionar(t2);
        agenda.adicionar(t3);
        agenda.adicionar(t4);

        // Executar todas as tarefas
        System.out.println("\n=== Executando tarefas - 1ª vez ===");
        agenda.executarTodas();

        System.out.println("\n=== Executando tarefas - 2ª vez ===");
        agenda.executarTodas();

        System.out.println("\n=== Executando tarefas - 3ª vez ===");
        agenda.executarTodas();

        System.out.println("\n=== Executando tarefas - 4ª vez ===");
        agenda.executarTodas();

        // Teste: tentar adicionar null
        try {
            agenda.adicionar(null);
        } catch (IllegalArgumentException e) {
            System.out.println("\nExceção esperada ao adicionar null: " + e.getMessage());
        }
    }
}
