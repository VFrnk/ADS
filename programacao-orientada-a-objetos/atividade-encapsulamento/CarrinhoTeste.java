import java.util.List;

public class CarrinhoTeste {

    public static void main(String[] args) {

        System.out.println("=== Criando Carrinho ===");
        Carrinho c = new Carrinho();

        System.out.println("\n=== Adicionando Itens ===");
        c.adicionarItem("Mouse", 50.0);
        c.adicionarItem("Teclado", 120.0);
        c.adicionarItem("Monitor", 950.0);

        System.out.println("Itens: " + c.getItens());
        System.out.println("Preços: " + c.getPrecos());
        System.out.println("Total: " + c.getTotal()); // 1120.0

        System.out.println("\n=== Removendo Itens ===");
        System.out.println("Remover índice 1: " + c.removerItemPorIndice(1)); // true
        System.out.println("Itens: " + c.getItens());
        System.out.println("Preços: " + c.getPrecos());
        System.out.println("Total: " + c.getTotal()); // 50 + 950 = 1000

        System.out.println("\nRemover índice inválido (10): " + c.removerItemPorIndice(10)); // false

        System.out.println("\n=== Teste de Encapsulamento ===");
        List<String> itens = c.getItens();
        itens.clear(); // não deve afetar o carrinho real

        System.out.println("Itens reais após clear externo: " + c.getItens());
        System.out.println("Preços reais: " + c.getPrecos());

        System.out.println("\n=== Testes de Validação ===");
        try {
            c.adicionarItem("", 10.0);
        } catch (Exception e) {
            System.out.println("Erro esperado (nome vazio): " + e.getMessage());
        }

        try {
            c.adicionarItem("Item", -5.0);
        } catch (Exception e) {
            System.out.println("Erro esperado (preço inválido): " + e.getMessage());
        }
    }
}
