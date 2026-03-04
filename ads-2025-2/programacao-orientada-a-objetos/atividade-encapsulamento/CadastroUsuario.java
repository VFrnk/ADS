// Exercício 3 - CadastroUsuario
public class CadastroUsuario {

    // - String login (5 a 15 caracteres, sem espaços)
    // - String email (conter '@' e um '.' depois do '@')
    // - String senha (mínimo 8 caracteres, pelo menos 1 dígito)
    private String login;
    private String email;
    private String senha;

    /**
     * Construtor deve aplicar validações usando lógica interna ou setters privados.
     *
     * @param login login do usuário
     * @param email email do usuário
     * @param senha senha do usuário
     */
    public CadastroUsuario(String login, String email, String senha) {
        if (!this.setLogin(login))
            throw new IllegalArgumentException("Login deve ter entre 5 e 15 caracteres, sem espaços.");

        if (!this.setEmail(email))
            throw new IllegalArgumentException("Email Invalido.");

        if (!this.setSenha(senha))
            throw new IllegalArgumentException("Senha deve possuir 8 ou mais caracteres e um digito.");
    }

     private boolean setLogin(String login){
        if (login == null || login.length() < 5 || login.length() > 15 || login.contains(" "))
            return false;

        this.login = login;
        return true;
     }

     private boolean setEmail(String email) {
         if (!email.matches("[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.+[a-zA-Z]+"))
             return false;

         this.email = email;
         return true;
     }

     private boolean setSenha(String senha){
        if (senha.length() < 8 || !senha.matches(".*\\d.*") || senha.contains(" "))
            return false;

         this.senha = senha;
         return true;
    }

    /**
     * Altera o e-mail do usuário.
     * Regra: novoEmail deve ser válido.
     *
     * @param novoEmail novo e-mail
     * @return true se alterou, false se inválido
     */
    public boolean alterarEmail(String novoEmail) {
        return this.setEmail(novoEmail);
    }

    /**
     * Altera a senha do usuário.
     * Regras:
     * - senhaAtual deve coincidir com a senha armazenada
     * - novaSenha deve atender às regras de senha
     *
     * @param senhaAtual senha atual
     * @param novaSenha  nova senha desejada
     * @return true se alterou, false caso contrário
     */
    public boolean alterarSenha(String senhaAtual, String novaSenha) {
        if (!senhaAtual.equals(this.senha))
            return false;

        return this.setSenha(novaSenha);
    }
}
