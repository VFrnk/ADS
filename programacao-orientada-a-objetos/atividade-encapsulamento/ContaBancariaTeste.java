public class ContaBancariaTeste {

    public static void main(String[] args) {
        ContaBancaria c1 = new ContaBancaria("João", "12345");
        ContaBancaria c2 = new ContaBancaria("Maria", "67890");

        System.out.println("=== Teste de Depósito ===");
        System.out.println("Depósito 100 em c1: " + c1.depositar(100)); // true
        System.out.println("Saldo c1: " + c1.getSaldo()); // 100

        System.out.println("\n=== Teste de Saque ===");
        System.out.println("Sacar 50 de c1: " + c1.sacar(50)); // true
        System.out.println("Saldo c1: " + c1.getSaldo()); // 50

        System.out.println("Sacar 100 de c1: " + c1.sacar(100)); // false
        System.out.println("Saldo c1: " + c1.getSaldo()); // 50

        System.out.println("\n=== Teste de Transferência ===");
        System.out.println("Transferir 30 de c1 para c2: " + c1.transferir(c2, 30)); // true
        System.out.println("Saldo c1: " + c1.getSaldo()); // 20
        System.out.println("Saldo c2: " + c2.getSaldo()); // 30

        System.out.println("Transferir 50 de c1 para c2: " + c1.transferir(c2, 50)); // false
        System.out.println("Saldo c1: " + c1.getSaldo()); // 20
        System.out.println("Saldo c2: " + c2.getSaldo()); // 30

        System.out.println("\n=== Teste de Validação de Titular ===");
        try {
            c1.setTitular("");
        } catch (Exception e) {
            System.out.println("Erro esperado ao definir titular vazio: " + e.getMessage());
        }
    }
}
