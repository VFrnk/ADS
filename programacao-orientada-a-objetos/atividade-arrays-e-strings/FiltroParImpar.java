import java.util.Arrays; 

public class FiltroParImpar {
  int[] numeros;
  
  FiltroParImpar(int[] numeros){
    this.numeros = numeros;
  }
  
  int[] pares(){
    int contador = 0;
    
    for(int n : numeros)
      if(n % 2 == 0)
        contador++;
        
    int[] pares = new int[contador];
    
    int i = 0;
    for(int n : numeros)
      if(n % 2 == 0)
        pares[i++] = n;
        
    return pares;
  }
  
  int[] impares(){
    int contador = 0;
    
    for(int n : numeros)
      if(n % 2 == 1)
        contador++;
        
    int[] impares = new int[contador];
    
    int i = 0;
    for(int n : numeros)
      if(n % 2 == 1)
        impares[i++] = n;
        
    return impares;
  }
  
  void relatorio(){
    System.out.printf("Quantidade de pares: %d %s\n", pares().length, Arrays.toString(pares()));
    System.out.printf("Quantidade de impares: %d %s\n", impares().length, Arrays.toString(impares()));
  }
  
  public static void main(String[] args){
    int[] vetor = {1,2,3,4,5,6,7,8,9};
    var filtro = new FiltroParImpar(vetor);
    
    filtro.relatorio();
  }
}