public class EtiquetaProduto {
    String nome;
    int quantidade;
    double precoUnit;
    
    EtiquetaProduto(String nome, int quantidade, double precoUnit){
        this.nome = nome;
        this.quantidade = quantidade;
        this.precoUnit = precoUnit;
    }
    
    double total(){
        return quantidade * precoUnit;
    }
    
    String etiquetaSimples(){
        return String.format("%s (%d x R$%.2f) = R$%.2f", nome, quantidade, precoUnit, total());
    }
    
    String etiquetaCSV() {
        return String.format("%s;%d;%.2f;%.2f", nome, quantidade, precoUnit, total());
    }
    
    String etiquetaAlinhada(int larguraNome){
        String nomeAlinhado = nome;
        int larguraAtual = nome.length();
        
        while(larguraAtual++ < larguraNome)
            nomeAlinhado += "_";
        
        return nomeAlinhado + String.format(" (%d x R$%.2f) = R$%.2f", quantidade, precoUnit, total());
    }
    
    String etiquetaJSON() {
        return String.format("{\"nome\":\"%s\", \"quantidade\":%d, \"precoUnit\": %.2f, \"total\": %.2f}", nome, quantidade, precoUnit, total());
    }
    
    public static void main(String[] args) {
        var etiqueta = new EtiquetaProduto("Sabao em Barra", 8, 2.25);
        
        System.out.println(etiqueta.etiquetaSimples());
        System.out.println(etiqueta.etiquetaCSV());
        System.out.println(etiqueta.etiquetaAlinhada(30));
        System.out.println(etiqueta.etiquetaJSON());
    }
}