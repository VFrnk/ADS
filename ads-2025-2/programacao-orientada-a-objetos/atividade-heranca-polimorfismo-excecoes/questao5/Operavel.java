public interface Operavel {
    void depositar(double valor) throws OperacaoInvalidaException;
    void sacar(double valor) throws OperacaoInvalidaException;
    double getSaldo();
}
