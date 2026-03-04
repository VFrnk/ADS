public class CadastroUsuarioTeste {

    public static void main(String[] args) {

        System.out.println("=== Teste de Construção ===");
        CadastroUsuario u1 = new CadastroUsuario("usuario1", "teste@example.com", "senha123");
        System.out.println("Usuário criado com sucesso!");

        System.out.println("\n=== Teste de E-mail ===");
        System.out.println("Alterar e-mail para valido: " + u1.alterarEmail("novoemail@site.com")); // true
        System.out.println("Alterar e-mail para invalido: " + u1.alterarEmail("email_invalido")); // false

        System.out.println("\n=== Teste de Senha ===");
        System.out.println("Alterar senha com atual correta: " + u1.alterarSenha("senha123", "novaSenha1")); // true
        System.out.println("Alterar senha com atual incorreta: " + u1.alterarSenha("errada", "abc12345")); // false

        System.out.println("\n=== Teste de Validações do Construtor ===");
        try {
            new CadastroUsuario("abc", "email@site.com", "senha123");
        } catch (Exception e) {
            System.out.println("Erro esperado (login curto): " + e.getMessage());
        }

        try {
            new CadastroUsuario("usuario2", "email_errado", "senha123");
        } catch (Exception e) {
            System.out.println("Erro esperado (email inválido): " + e.getMessage());
        }

        try {
            new CadastroUsuario("usuario3", "valido@mail.com", "curta1");
        } catch (Exception e) {
            System.out.println("Erro esperado (senha inválida): " + e.getMessage());
        }
    }
}
