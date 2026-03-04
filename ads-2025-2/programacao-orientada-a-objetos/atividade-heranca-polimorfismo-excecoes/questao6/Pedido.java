import java.util.ArrayList;
import java.util.List;

public class Pedido {
    List<Entregavel> itens;

    Pedido() {
        this.itens = new ArrayList<Entregavel>();
    }

    public void adicionar(Entregavel e) throws ProdutoInvalidoException {
        if (e == null)
            throw new ProdutoInvalidoException("produto nao deve ser nulo.");

        this.itens.add(e);
    }

    public double calcularTotalFrete() {
        double total = 0;

        for (Entregavel item : this.itens)
            total += item.calcularFrete();

        return total;
    }

    public String listarItens() {
        StringBuilder lista = new StringBuilder();

        for (Entregavel item : this.itens){
            lista.append(item.getDescricao());
            lista.append("\n");
        }

        return lista.toString();
    }
}
