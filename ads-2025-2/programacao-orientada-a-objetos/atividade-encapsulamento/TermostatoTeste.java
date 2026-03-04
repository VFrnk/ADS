public class TermostatoTeste {

    public static void main(String[] args) {
        Termostato t1 = new Termostato(16, 30, 20);

        System.out.println("=== Teste de Inicialização ===");
        System.out.println("Temperatura inicial ajustada: " + t1.getTemperaturaAtual());

        System.out.println("\n=== Teste de Ajuste ===");
        System.out.println("Ajustar para 25: " + t1.ajustarTemperatura(25));
        System.out.println("Atual: " + t1.getTemperaturaAtual());

        System.out.println("Ajustar para 40 (inválido): " + t1.ajustarTemperatura(40));
        System.out.println("Atual: " + t1.getTemperaturaAtual());

        System.out.println("\n=== Teste de Aumentar ===");
        for (int i = 0; i < 20; i++) t1.aumentar();
        System.out.println("Atual (limite máximo 30): " + t1.getTemperaturaAtual());

        System.out.println("\n=== Teste de Diminuir ===");
        for (int i = 0; i < 40; i++) t1.diminuir();
        System.out.println("Atual (limite mínimo 16): " + t1.getTemperaturaAtual());

        System.out.println("\n=== Teste de Construtor com Temperatura Fora da Faixa ===");
        Termostato t2 = new Termostato(16, 30, 50);
        System.out.println("Temperatura inicial t2 (deve ser 23): " + t2.getTemperaturaAtual());
    }
}
