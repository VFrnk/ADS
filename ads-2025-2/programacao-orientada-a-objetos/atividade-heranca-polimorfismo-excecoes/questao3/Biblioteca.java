import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<ItemDigital> itens;

    Biblioteca() {
        this.itens = new ArrayList<ItemDigital>();
    }

    public void adicionar(ItemDigital item) throws ItemInvalidoException {
        if (item == null)
            throw new ItemInvalidoException("nulo nao pode ser adicionado a lista");

        String tituloNormalizado = item
                .getTitulo()
                .trim()
                .replaceAll("\\s+", " ")
                .toLowerCase();

        for (ItemDigital i : this.itens){
            String titulo = i.getTitulo().trim().replaceAll("\\s+", " ").toLowerCase();

            if (titulo.equals(tituloNormalizado))
                throw new ItemInvalidoException("titulos duplicados nao sao permitidos.");
        }

        this.itens.add(item);
    }

    public List<ItemDigital> buscarPorPrefixo(String prefixo) {
        List<ItemDigital> novaLista = new ArrayList<ItemDigital>();

        if(prefixo == null || prefixo.isBlank())
            return novaLista;

        String prefixoBusca = prefixo.toLowerCase();

        for (ItemDigital item : this.itens) {
            String titulo = item.getTitulo().toLowerCase();

            if(titulo.startsWith(prefixoBusca))
                novaLista.add(item);
        }

        return novaLista;
    }

    public String gerarRelatorio() {
        StringBuilder relatorio = new StringBuilder();

        for (ItemDigital item : this.itens) {
            relatorio.append(item.getDescricao());
            relatorio.append("\n");
        }

        return relatorio.toString();
    }
}
