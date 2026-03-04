import java.time.LocalDate;

public class TarefaComPrazo extends Tarefa {
    private LocalDate prazo;

    TarefaComPrazo(String descricao, int prioridade, LocalDate prazo) {
        super(descricao, prioridade);
        this.prazo = prazo;
    }

    @Override
    public boolean concluir() throws PrazoExpiradoException {
        if (LocalDate.now().isAfter(this.prazo))
            throw new PrazoExpiradoException("prazo excedido.");

        return true;
    }
}
