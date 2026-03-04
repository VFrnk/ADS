public abstract class NodeSistema {
    private String nome;

    NodeSistema(String nome) throws ArquivoInvalidoException {
        if(nome == null || nome.isBlank())
            throw new ArquivoInvalidoException("nome nao deve ser nulo ou vazio.");

        this.nome = nome;
    }

    public abstract long getTamanho();

    public String getNome() {
        return this.nome;
    }
}
