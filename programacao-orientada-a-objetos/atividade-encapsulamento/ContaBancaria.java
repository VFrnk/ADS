// Exercício 1 - ContaBancaria
public class ContaBancaria {

    // - String titular (não pode ser vazio)
    // - String numero
    // - double saldo (nunca negativo)
    private String titular;
    private String numero;
    private double saldo;

    /**
     * Construtor recebendo titular e número.
     * Saldo inicial deve ser 0.
     */
    public ContaBancaria(String titular, String numero) {
        if (!numero.matches("\\d+"))
            throw new IllegalArgumentException("Numero deve ser composto por apenas numeros.");

        this.numero = numero;
        this.setTitular(titular);
        this.saldo = 0.0;
    }

    /**
     * Deposita um valor na conta.
     * Regra: só aceita valores > 0.
     *
     * @param valor valor a ser depositado
     * @return true se conseguiu depositar, false caso contrário
     */
    public boolean depositar(double valor) {
        if (valor <= 0)
            return false;

        this.saldo += valor;
        return true;
    }

    /**
     * Realiza um saque na conta.
     * Regras: valor > 0 e saldo - valor >= 0.
     *
     * @param valor valor a ser sacado
     * @return true se conseguiu sacar, false caso contrário
     */
    public boolean sacar(double valor) {
        if (valor <= 0 || this.saldo < valor)
            return false;

        this.saldo -= valor;
        return true;
    }

    /**
     * Transfere um valor desta conta para outra ContaBancaria.
     * Regras: valor > 0 e saldo - valor >= 0.
     *
     * @param destino conta que irá receber o valor
     * @param valor   valor a ser transferido
     * @return true se conseguiu transferir, false caso contrário
     */
    public boolean transferir(ContaBancaria destino, double valor) {
        if (!this.sacar(valor))
            return false;

        return destino.depositar(valor);
    }

    /**
     * Getter de saldo (somente leitura).
     *
     * @return saldo atual
     */
    public double getSaldo() {
        return this.saldo;
    }

    /**
     * Getter de titular.
     */
    public String getTitular() {
        return this.titular;
    }

    /**
     * Setter de titular com validação (não aceitar null ou vazio).
     */
    public void setTitular(String titular) {
        if (titular == null || titular.isBlank())
            throw new IllegalArgumentException("Titular não pode ser null ou vazio.");

        this.titular = titular;
    }

    /**
     * Getter de número da conta.
     */
    public String getNumero() {
        return this.numero;
    }
}
