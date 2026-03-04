public abstract class Conta implements Operavel {
    private String numero;
    private double saldo;

    Conta(String numero) throws OperacaoInvalidaException {
        if (numero == null || numero.isBlank())
            throw new OperacaoInvalidaException("nome nao deve ser nulo ou vazio.");

        this.numero = numero;
        this.saldo = 0;
    }

    @Override
    public void depositar(double valor) throws OperacaoInvalidaException {
        if (valor <= 0)
            throw new OperacaoInvalidaException("valor de deposito deve ser maior que 0.");

        this.saldo += valor;
    }

    @Override
    public void sacar(double valor) throws OperacaoInvalidaException {
        if (valor <= 0)
            throw new OperacaoInvalidaException("valor de saque deve ser maior que 0.");

        if (valor > this.saldo)
            throw new OperacaoInvalidaException("saldo insuficiente.");

        this.saldo -= valor;
    }

    @Override
    public double getSaldo() {
        return this.saldo;
    }

    public String getNumero() {
        return this.numero;
    }
}
