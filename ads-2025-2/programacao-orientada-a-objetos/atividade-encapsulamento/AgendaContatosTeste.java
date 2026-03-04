public class AgendaContatosTeste {

    public static void main(String[] args) {

        System.out.println("=== Criando Agenda ===");
        AgendaContatos agenda = new AgendaContatos();

        System.out.println("\n=== Adicionando Contatos ===");
        System.out.println("Adicionar João: " + agenda.adicionarContato("João", "12345678")); // true
        System.out.println("Adicionar Maria: " + agenda.adicionarContato("Maria", "87654321")); // true
        System.out.println("Adicionar João novamente: " + agenda.adicionarContato("joÃO", "99999999")); // false

        System.out.println("\n=== Listando Contatos ===");
        for (Contato c : agenda.listarContatos())
            System.out.println(c.getNome() + " - " + c.getTelefone());

        System.out.println("\n=== Buscando Contatos ===");
        Contato copia = agenda.buscarPorNome("maria");
        System.out.println("Encontrado: " + copia.getNome() + " - " + copia.getTelefone());

        // Teste do encapsulamento (alterar cópia não altera original)
        copia.setTelefone("00000000");
        System.out.println("Telefone da cópia alterado. Telefone original:");
        System.out.println(agenda.buscarPorNome("Maria").getTelefone()); // deve continuar 87654321

        System.out.println("\n=== Removendo Contatos ===");
        System.out.println("Remover João: " + agenda.removerContato("joao")); // true
        System.out.println("Remover Alguém inexistente: " + agenda.removerContato("Carlos")); // false

        System.out.println("\n=== Listando após remoções ===");
        for (Contato c : agenda.listarContatos())
            System.out.println(c.getNome() + " - " + c.getTelefone());
    }
}
