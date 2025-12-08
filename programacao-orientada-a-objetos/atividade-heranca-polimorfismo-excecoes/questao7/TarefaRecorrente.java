public class TarefaRecorrente extends Tarefa {
    private int vezes;

    TarefaRecorrente(String descricao, int prioridade, int vezes) {
        super(descricao, prioridade);

        if (vezes <= 0)
            throw new IllegalArgumentException("quantidade de vezes deve ser maior que 0.");

        this.vezes = vezes;
    }

    @Override
    public boolean concluir() throws PrazoExpiradoException {
        if (this.vezes > 0)
            this.vezes -= 1;

        return this.vezes <= 0;
    }
}
