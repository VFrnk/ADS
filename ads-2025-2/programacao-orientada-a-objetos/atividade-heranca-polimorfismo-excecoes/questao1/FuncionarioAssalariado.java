public class FuncionarioAssalariado extends Funcionario {
    private double salarioMensal;

    FuncionarioAssalariado(String nome, String documento, double salarioMensal) throws DadoInvalidoException {
        super(nome, documento);

        if (salarioMensal <= 0)
            throw new DadoInvalidoException("Salario deve ser maior que 0.");

        this.salarioMensal = salarioMensal;
    }

    @Override
    public double calcularPagamento() {
        return this.salarioMensal;
    }
}
