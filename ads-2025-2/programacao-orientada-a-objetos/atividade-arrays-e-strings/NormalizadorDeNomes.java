import java.util.ArrayList;
import java.util.List;

public class NormalizadorDeNomes {
    String original;
    
    NormalizadorDeNomes(String original){
        this.original = original;
    }
    
    String somenteUmEspaco(){
        return original.trim().replaceAll("\\s+", " ");
    }
    
    String capitalizar(){
        String[] nomes = somenteUmEspaco().toLowerCase().split(" ");
        
        for(int i = 0; i < nomes.length; i++)
            nomes[i] = Character.toUpperCase(nomes[i].charAt(0)) + nomes[i].substring(1);
            
        return String.join(" ", nomes);
    }
    
    String capitalizarComStopWords(){
        ArrayList<String> stopWords = new ArrayList<>(List.of("da", "de", "do", "das", "dos", "e"));
        
        String[] nomes = somenteUmEspaco().toLowerCase().split(" ");
        
        for(int i = 0; i < nomes.length; i++)
            if(!stopWords.contains(nomes[i]))
                nomes[i] = Character.toUpperCase(nomes[i].charAt(0)) + nomes[i].substring(1);
                
        return String.join(" ", nomes);
    }
    
    String slug(){
        return original.trim().toLowerCase().replaceAll("[^a-z0-9]+", "-");
    }
    
    public static void main(String[] args) {
        var nome = new NormalizadorDeNomes("Joao    da silva e \n filho");
        
        System.out.println(nome.somenteUmEspaco());
        System.out.println(nome.capitalizar());
        System.out.println(nome.capitalizarComStopWords());
        System.out.println(nome.slug());
    }
}