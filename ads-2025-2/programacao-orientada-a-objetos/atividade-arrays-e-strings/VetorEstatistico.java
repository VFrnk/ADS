import java.util.Arrays;

public class VetorEstatistico {
  double[] dados;
  
  VetorEstatistico(double[] dados){
    this.dados = dados;
  }
  
  double soma(){
    double soma = 0;
    for(double e : dados)
      soma += e;
    return soma;
  }
  
  double media(){
    return soma() / dados.length;
  }
  
  double max(){
    double max = Double.MIN_VALUE;
    for(double e: dados)
      max = Math.max(e, max);
    return max;
  }
  
  double min(){
    double min = Double.MAX_VALUE;
    for(double e : dados)
      min = Math.min(e, min);
    return min;
  }
  
  String comoString(){
    return Arrays.toString(dados);
  }
  
  public static void main(String[] args) {
      double[] vetorBase = {2, 4, 7, 18, 3};
      var vetor = new VetorEstatistico(vetorBase);
      
      System.out.println(vetor.soma());
      System.out.println(vetor.media());
      System.out.println(vetor.max());
      System.out.println(vetor.min());
      System.out.println(vetor.comoString());
  }
}
