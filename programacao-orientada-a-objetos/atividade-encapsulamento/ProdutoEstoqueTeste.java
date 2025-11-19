public class ProdutoEstoqueTeste {

    public static void main(String[] args) {

        ProdutoEstoque p = new ProdutoEstoque("Mouse", 100.0, 10);

        System.out.println("=== Teste de Reposição ===");
        System.out.println("Repor 5: " + p.repor(5));        // true
        System.out.println("Repor -2: " + p.repor(-2));      // false

        System.out.println("\n=== Teste de Venda ===");
        System.out.println("Vender 3: " + p.vender(3));      // true
        System.out.println("Vender 50: " + p.vender(50));    // false

        System.out.println("\n=== Teste de Desconto ===");
        System.out.println("Aplicar 20%: " + p.aplicarDesconto(20)); // true
        System.out.println("Aplicar 70% (inválido): " + p.aplicarDesconto(70)); // false

        System.out.println("\n=== Teste de Valor em Estoque ===");
        System.out.println("Valor total: " + p.calcularValorEmEstoque());

        System.out.println("\n=== Teste de Validações do Construtor ===");
        try {
            new ProdutoEstoque("", 10.0, 5);
        } catch (Exception e) {
            System.out.println("Erro esperado (nome vazio): " + e.getMessage());
        }

        try {
            new ProdutoEstoque("Item", -10.0, 5);
        } catch (Exception e) {
            System.out.println("Erro esperado (preço inválido): " + e.getMessage());
        }

        try {
            new ProdutoEstoque("Item", 10.0, -1);
        } catch (Exception e) {
            System.out.println("Erro esperado (quantidade negativa): " + e.getMessage());
        }
    }
}
