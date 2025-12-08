public class ContaCorrente extends Conta {
    public static final double TAXA_POR_OPERACAO = 1.50;

    ContaCorrente(String numero) throws OperacaoInvalidaException {
        super(numero);
    }

    @Override
    public void sacar(double valor) throws OperacaoInvalidaException {
        super.sacar(valor);

        try {
            super.sacar(ContaCorrente.TAXA_POR_OPERACAO);
        } catch(OperacaoInvalidaException e) {
            super.depositar(valor);
            throw new OperacaoInvalidaException("sem saldo para taxa de operacao.");
        }
    }

    @Override
    public void depositar(double valor) throws OperacaoInvalidaException {
        super.depositar(valor);

        try {
            super.sacar(ContaCorrente.TAXA_POR_OPERACAO);
        } catch (OperacaoInvalidaException e) {
            super.sacar(valor);
            throw new OperacaoInvalidaException("sem saldo para taxa de operacao.");
        }
    }
}
