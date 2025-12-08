public class ProdutoFisico implements Entregavel {
    private String nome;
    private double pesoKg;
    private double valor;

    ProdutoFisico(String nome, double pesoKg, double valor) throws ProdutoInvalidoException {
        if (nome == null || nome.isBlank())
            throw new ProdutoInvalidoException("nome nao deve ser nulo ou vazio.");

        if (pesoKg <= 0)
            throw new ProdutoInvalidoException("peso deve ser maior que 0.");

        if (valor <= 0)
            throw new ProdutoInvalidoException("valor deve ser maior que 0.");

        this.nome = nome;
        this.pesoKg = pesoKg;
        this.valor = valor;
    }

    @Override
    public double calcularFrete() {
        return this.pesoKg * 12.5;
    }

    @Override
    public String getDescricao() {
        return String.format("%s (R$%.2f, %.2fkg)",
                this.nome,
                this.valor,
                this.pesoKg
        );
    }
}
