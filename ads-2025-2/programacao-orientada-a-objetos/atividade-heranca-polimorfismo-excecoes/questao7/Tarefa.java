public abstract class Tarefa {
    private String descricao;
    private int prioridade;

    Tarefa(String descricao, int prioridade) {
        if (descricao == null || descricao.isBlank())
            throw new IllegalArgumentException("descricao nao deve ser nula ou vazia.");

        if (prioridade < 1 || prioridade > 5)
            throw new IllegalArgumentException("prioridade deve ser um inteiro de 1 a 5");

        this.descricao = descricao;
        this.prioridade = prioridade;
    }

    public abstract boolean concluir() throws PrazoExpiradoException;

    public String getDescricao() {
        return this.descricao;
    }
}
