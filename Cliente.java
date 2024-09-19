public class Cliente {
    private String nome;
    private String cpf;
    private String username;
    private String password;

    public Cliente(String nome, String cpf, String username, String password) {
        this.nome = nome;
        this.cpf = cpf;
        this.username = username;
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getUsername() {
        return username;
    }

    public boolean validarSenha(String password) {
        return this.password.equals(password);
    }

    public void exibirInformacoes() {
        System.out.println("Cliente: " + nome + " | CPF: " + cpf);
    }
}
