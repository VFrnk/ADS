class Produto {
    String nome;
    double preco;
    int qtd;
    
    Produto(String nome, double preco, int qtd){
        this.nome = nome;
        this.preco = preco;
        this.qtd = qtd;
    }
    
    double total(){
        return this.preco * this.qtd;
    }
    
    String etiqueta(){
        return String.format("%s - %d x R$%.2f = R$%.2f", 
            this.nome,
            this.qtd,
            this.preco,
            this.total()
        );
    }
}

public class TabelaProdutos {
    Produto[] itens;
    
    TabelaProdutos(Produto[] itens){
        this.itens = itens;
    }
    
    double somaTotal(){
        double soma = 0;
        
        for(Produto item : itens)
            soma += item.total();
        
        return soma;
    }
    
    Produto maisCaro(){
        Produto produtoMaisCaro = itens[0];
        
        for(Produto item : itens)
            if(item.preco > produtoMaisCaro.preco)
                produtoMaisCaro = item;
        
        return produtoMaisCaro;
    }
    
    String listaEtiquetas(){
        String[] etiquetas = new String[itens.length];
        
        for(int i = 0; i < itens.length; i++)
            etiquetas[i] = itens[i].etiqueta();
        
        return String.join("\n", etiquetas);
    }
    
    public static void main(String[] args){
        Produto p1 = new Produto("Cafe", 15.50, 2);
        Produto p2 = new Produto("Pao", 0.75, 10);
        Produto p3 = new Produto("Leite", 5.50, 4);
        
        Produto[] lista = {p1, p2, p3};
        
        TabelaProdutos tabela = new TabelaProdutos(lista);
        
        System.out.println("--- Lista de Produtos ---");
        System.out.println(tabela.listaEtiquetas());
        
        System.out.println("\n");
        
        System.out.printf("Soma Total da Compra: R$%.2f\n", tabela.somaTotal());
        
        Produto maisCaro = tabela.maisCaro();
        System.out.println("\n--- Produto Mais Caro ---");
        System.out.println("Nome: " + maisCaro.nome);
        System.out.printf("Preço: R$%.2f\n", maisCaro.preco);
    }
}

