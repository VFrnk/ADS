public class AnaliseFrase {
    String frase;
    
    AnaliseFrase(String frase){
        this.frase = frase;
    }
    
    int contarVogais(){
        String vogais = "aeiou";
        int contador = 0;
        
        for(int i = 0; i < frase.length(); i++)
            if(vogais.indexOf(frase.charAt(i)) != -1)
                contador++;
        
        return contador;
    }
    
    int contarPalavras(){
        return frase.replaceAll("[^a-zA-Z | \\s]+", "").split("\\s+").length;
    }
    
    String inverterPalavras(){
        String[] inverter = frase.split("\\s+");
        
        int len = inverter.length;
        
        for(int i = 0; i < (int)(len / 2); i++){
            String temp = inverter[i];
            inverter[i] = inverter[len - i - 1];
            inverter[len - 1 - i] = temp;
        }
            
        return String.join(" ", inverter);
    }
    
    boolean isPalindromo(){
        String palindromo = frase.toLowerCase().replaceAll("[^a-z0-9]+", "");
        
        int inicio = 0, fim = palindromo.length() - 1;
        
        while(inicio <= fim){
            if(palindromo.charAt(inicio) != palindromo.charAt(fim))
                return false;
            
            inicio++;
            fim--;
        }
        
        return true;
    }
    
    public static void main(String[] args){
        var analise = new AnaliseFrase("Socorram-me subi no onibus em Marrocos");
        
        System.out.println(analise.contarVogais());
        System.out.println(analise.contarPalavras());
        System.out.println(analise.inverterPalavras());
        System.out.println(analise.isPalindromo());
    }
}