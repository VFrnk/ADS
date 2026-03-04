// Exercício 4 - ProdutoEstoque
public class ProdutoEstoque {

    // - String nome (não vazio)
    // - double preco (> 0)
    // - int quantidade (>= 0)
    private String nome;
    private double preco;
    private int quantidade;

    /**
     * Construtor para inicializar o produto em estoque.
     *
     * @param nome       nome do produto
     * @param preco      preço do produto
     * @param quantidade quantidade inicial em estoque
     */
    public ProdutoEstoque(String nome, double preco, int quantidade) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome nao pode ser null ou vazio.");

        if (preco <= 0)
            throw new IllegalArgumentException("Preco deve ser maior que zero.");

        if (quantidade < 0)
            throw new IllegalArgumentException("Quantidade nao pode ser negativa.");

        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    /**
     * Reposição de estoque.
     * Regra: quantidade > 0.
     *
     * @param quantidade quantidade a repor
     * @return true se conseguiu repor, false caso contrário
     */
    public boolean repor(int quantidade) {
        if (quantidade <= 0)
            return false;

        this.quantidade += quantidade;
        return true;
    }

    /**
     * Venda de produtos.
     * Regra: só vende se houver estoque suficiente.
     *
     * @param quantidade quantidade a vender
     * @return true se conseguiu vender, false caso contrário
     */
    public boolean vender(int quantidade) {
        if (quantidade <= 0 || this.quantidade < quantidade)
            return false;

        this.quantidade -= quantidade;
        return true;
    }

    /**
     * Aplica desconto sobre o preço atual.
     * Regra: 0 < percentual <= 50.
     *
     * @param percentual percentual de desconto
     * @return true se conseguiu aplicar, false caso contrário
     */
    public boolean aplicarDesconto(double percentual) {
        if (percentual <= 0 || percentual > 50)
            return false;

        this.preco *= (1.0 - percentual / 100.0);
        return true;
    }

    /**
     * Calcula o valor total em estoque (preço * quantidade).
     *
     * @return valor total em estoque
     */
    public double calcularValorEmEstoque() {
        return this.preco * this.quantidade;
    }
}
