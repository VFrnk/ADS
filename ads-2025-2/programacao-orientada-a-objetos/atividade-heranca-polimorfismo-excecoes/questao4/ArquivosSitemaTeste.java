public class ArquivosSitemaTeste {
    public static void main(String[] args) {
        try {
            Pasta raiz = new Pasta("Documentos");
            Arquivo a1 = new Arquivo("foto.png", 2000);
            Arquivo a2 = new Arquivo("texto.txt", 500);
            Pasta sub = new Pasta("Imagens");

            raiz.adicionar(a1);
            raiz.adicionar(a2);
            raiz.adicionar(sub);

            Arquivo a3 = new Arquivo("wallpaper.jpg", 3000);
            sub.adicionar(a3);

            System.out.println("Tamanho total da pasta raiz: " + raiz.getTamanho());
            System.out.println("Tamanho da pasta 'Imagens': " + sub.getTamanho());

            // Teste de nome repetido
            Arquivo repetido = new Arquivo("foto.png", 100);
            raiz.adicionar(repetido);

        } catch (ArquivoInvalidoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
