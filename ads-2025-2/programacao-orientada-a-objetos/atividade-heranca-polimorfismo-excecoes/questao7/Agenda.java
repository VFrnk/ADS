import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private List<Tarefa> tarefas;

    Agenda() {
        this.tarefas = new ArrayList<Tarefa>();
    }

    public void adicionar(Tarefa t) {
        if (t == null)
            throw new IllegalArgumentException("tarefa nao deve ser nula.");

        this.tarefas.add(t);
    }

    public void executarTodas() {
        for (Tarefa tarefa : this.tarefas) {
            try {

                if(tarefa.concluir())
                    System.out.printf("questao7.Tarefa {%s} concluida.\n", tarefa.getDescricao());

            } catch (PrazoExpiradoException e) {
                System.out.printf("Nao foi possivel concluir {%s}, %s\n", tarefa.getDescricao(), e.getMessage());
            }
        }
    }
}
