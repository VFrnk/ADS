public class SistemaBancarioTeste {

    public static void main(String[] args) {

        try {
            // Criando contas
            Conta contaCorrente = new ContaCorrente("001");
            contaCorrente.depositar(100);
            Conta contaPoupanca = new ContaPoupanca("002");
            contaPoupanca.depositar(200);
            Conta contaInvestimento = new ContaInvestimento("003");
            contaInvestimento.depositar(500);

            System.out.println("=== Saldo Inicial ===");
            imprimirSaldo(contaCorrente);
            imprimirSaldo(contaPoupanca);
            imprimirSaldo(contaInvestimento);

            System.out.println("\n=== Teste questao5.Conta Corrente ===");
            contaCorrente.depositar(50);
            imprimirSaldo(contaCorrente);

            contaCorrente.sacar(30);
            imprimirSaldo(contaCorrente);

            // Testar taxa gerar exceção
            try {
                System.out.println("Tentando sacar valor quase igual ao saldo (vai falhar por causa da taxa)...");
                contaCorrente.sacar(contaCorrente.getSaldo()); // deve lançar exceção
            } catch (OperacaoInvalidaException e) {
                System.out.println("Exceção esperada: " + e.getMessage());
            }

            System.out.println("\n=== Teste questao5.Conta Poupança ===");
            contaPoupanca.depositar(100);
            imprimirSaldo(contaPoupanca);

            contaPoupanca.sacar(50);
            imprimirSaldo(contaPoupanca);

            System.out.println("\n=== Teste questao5.Conta Investimento ===");
            contaInvestimento.depositar(200);
            imprimirSaldo(contaInvestimento);

            // Deve falhar porque precisa manter 10%
            try {
                System.out.println("Tentando sacar quase todo o saldo...");
                contaInvestimento.sacar(650); // não pode deixar menos de 10%
            } catch (OperacaoInvalidaException e) {
                System.out.println("Exceção esperada: " + e.getMessage());
            }

            // Saque válido
            contaInvestimento.sacar(100);
            imprimirSaldo(contaInvestimento);

        } catch (OperacaoInvalidaException e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    private static void imprimirSaldo(Conta conta) {
        System.out.println(conta.getClass().getSimpleName() + " (" + conta.getNumero() + ") -> Saldo: " + conta.getSaldo());
    }
}
