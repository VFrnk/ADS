import java.util.ArrayList;
import java.util.List;

public class FolhaPagamentoTeste {

    public static void main(String[] args) {

        try {
            Funcionario f1 = new FuncionarioAssalariado("Ana", "123", 3000);
            Funcionario f2 = new FuncionarioHorista("Carlos", "234", 50, 160);
            Funcionario f3 = new FuncionarioComissionado("Marcos", "345", 20000, 0.10);

            List<Funcionario> lista = new ArrayList<>();
            lista.add(f1);
            lista.add(f2);
            lista.add(f3);
            lista.add(null); // deve ser ignorado

            double total = FolhaDePagamento.calcularTotal(lista);

            System.out.println("Total da folha: R$ " + total);

        } catch (DadoInvalidoException e) {
            System.out.println("Erro ao criar funcionário: " + e.getMessage());
        }
    }
}
