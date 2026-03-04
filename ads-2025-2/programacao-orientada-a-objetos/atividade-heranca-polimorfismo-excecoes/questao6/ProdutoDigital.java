public class ProdutoDigital implements Entregavel {
    private String nome;
    private double tamanhoMB;

    ProdutoDigital(String nome, double tamanhoMB) throws ProdutoInvalidoException {
        if (nome == null || nome.isBlank())
            throw new ProdutoInvalidoException("nome nao deve ser nulo ou vazio.");

        if (tamanhoMB <= 0)
            throw new ProdutoInvalidoException("tamanho deve ser maior que 0.");

        this.nome = nome;
        this.tamanhoMB = tamanhoMB;
    }

    @Override
    public double calcularFrete() {
        return 0;
    }

    @Override
    public String getDescricao() {
        return String.format("Digital: %s (%.2fMB)",
                this.nome,
                this.tamanhoMB
        );
    }
}
