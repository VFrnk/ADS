public class LojaVirtualTeste {

    public static void main(String[] args) {
        try {
            // Criar um pedido
            Pedido pedido = new Pedido();

            // Criar produtos
            Entregavel p1 = new ProdutoFisico("Notebook", 2.5, 4500.0);
            Entregavel p2 = new ProdutoFisico("Livro Java", 1.0, 120.0);
            Entregavel p3 = new ProdutoDigital("E-book Python", 50);

            // Adicionar ao pedido
            pedido.adicionar(p1);
            pedido.adicionar(p2);
            pedido.adicionar(p3);

            System.out.println("=== Itens do questao6.Pedido ===");
            System.out.println(pedido.listarItens());

            System.out.println("=== Fretes Individuais ===");
            System.out.println(p1.getDescricao() + " -> Frete: R$" + p1.calcularFrete());
            System.out.println(p2.getDescricao() + " -> Frete: R$" + p2.calcularFrete());
            System.out.println(p3.getDescricao() + " -> Frete: R$" + p3.calcularFrete());

            System.out.println("\n=== Total de Frete ===");
            System.out.println("R$ " + pedido.calcularTotalFrete());

            // Teste de exceção
            try {
                System.out.println("\nTentando adicionar item nulo...");
                pedido.adicionar(null);
            } catch (ProdutoInvalidoException e) {
                System.out.println("Exceção esperada: " + e.getMessage());
            }

            // Teste de validação de produto digital inválido
            try {
                System.out.println("\nTentando criar questao6.ProdutoDigital inválido...");
                new ProdutoDigital("", -5);
            } catch (ProdutoInvalidoException e) {
                System.out.println("Exceção esperada: " + e.getMessage());
            }

        } catch (ProdutoInvalidoException e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }
}
