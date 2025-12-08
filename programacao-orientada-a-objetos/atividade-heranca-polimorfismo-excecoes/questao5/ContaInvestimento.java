public class ContaInvestimento extends Conta {
    ContaInvestimento(String numero) throws OperacaoInvalidaException {
        super(numero);
    }

    @Override
    public void sacar(double valor) throws OperacaoInvalidaException {
        double saldo = super.getSaldo();

        if (saldo - valor < saldo * 0.1)
            throw new OperacaoInvalidaException("pelo menos 10% do saldo deve ser preservado.");

        super.sacar(valor);
    }
}
