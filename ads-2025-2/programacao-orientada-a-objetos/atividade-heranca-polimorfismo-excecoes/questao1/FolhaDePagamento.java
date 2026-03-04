import java.util.List;

public class FolhaDePagamento {
    public static double calcularTotal(List<Funcionario> funcionarios) {
        double total = 0.0;

        for (Funcionario funcionario : funcionarios)
            if (funcionario != null)
                total += funcionario.calcularPagamento();

        return total;
    }
}
