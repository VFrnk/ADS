public class TarefaSimples extends Tarefa {
    TarefaSimples(String descricao, int prioridade) {
        super(descricao, prioridade);
    }

    @Override
    public boolean concluir() throws PrazoExpiradoException {
        return true;
    }
}
