public class FuncionarioComissionado extends Funcionario {
    private double vendasBrutas;
    private double percentualComissao;

    FuncionarioComissionado(String nome, String documento, double vendasBrutas, double percentualComissao) throws DadoInvalidoException {
        super(nome, documento);

        if (vendasBrutas < 0)
            throw new DadoInvalidoException("total de vendas deve ser maior ou igual a 0.");

        if (percentualComissao <= 0 || percentualComissao > 1)
            throw new DadoInvalidoException("percentual deve ser um valor entre 0 e 1");

        this.vendasBrutas = vendasBrutas;
        this.percentualComissao = percentualComissao;
    }

    @Override
    public double calcularPagamento(){
        return this.vendasBrutas * this.percentualComissao;
    }
}
