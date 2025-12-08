public class FuncionarioHorista extends Funcionario {
    private double valorHora;
    private int horasTrabalhadas;

    FuncionarioHorista(String nome, String documento, double valorHora, int horasTrabalhadas) throws DadoInvalidoException {
        super(nome, documento);

        if (valorHora <= 0)
            throw new DadoInvalidoException("Valor da hora deve ser maior que 0.");

        if (horasTrabalhadas <= 0)
            throw new DadoInvalidoException("Horas trabalhadas deve ser maior que 0.");

        this.valorHora = valorHora;
        this.horasTrabalhadas = horasTrabalhadas;
    }

    @Override
    public double calcularPagamento() {
        return this.valorHora * this.horasTrabalhadas;
    }
}
