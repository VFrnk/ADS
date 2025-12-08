import java.util.ArrayList;
import java.util.List;

public class Pasta extends NodeSistema {
    private List<NodeSistema> filhos;

    Pasta(String nome) throws ArquivoInvalidoException {
        super(nome);

        this.filhos = new ArrayList<NodeSistema>();
    }

    public void adicionar(NodeSistema n) throws ArquivoInvalidoException {
        String nomeNode = n.getNome();

        for (NodeSistema filho : filhos)
            if (filho.getNome().equalsIgnoreCase(nomeNode))
                throw new ArquivoInvalidoException("arquivo repetido.");

        this.filhos.add(n);
    }

    @Override
    public long getTamanho() {
        long tamanhoTotal = 0;

        for (NodeSistema filho : filhos)
            tamanhoTotal += filho.getTamanho();

        return tamanhoTotal;
    }
}
