import java.util.ArrayList;
import java.util.List;

public class SistemaImpostosTeste {

    public static void main(String[] args) {

        try {
            // Criação dos veículos
            Tributavel carro = new Carro("Sedan LX", 50000);         // 5% = 2500
            Tributavel moto = new Moto("Street 250", 20000);         // 3% = 600
            Tributavel caminhao1 = new Caminhao("Truck A", 150000);  // 8% = 12000
            Tributavel caminhao2 = new Caminhao("Truck B", 300000);  // 10% = 30000

            // Lista com itens válidos e um null (que deve ser ignorado)
            List<Tributavel> lista = new ArrayList<>();
            lista.add(carro);
            lista.add(moto);
            lista.add(caminhao1);
            lista.add(caminhao2);
            lista.add(null); // ignorado pelo sistema

            // Calculando total
            double total = SistemaImpostos.somarImpostos(lista);

            System.out.println("Total de impostos: R$ " + total);

        } catch (DadoInvalidoException e) {
            System.out.println("Erro ao criar veículo: " + e.getMessage());
        }
    }
}
