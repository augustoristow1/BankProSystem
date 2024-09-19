import java.util.HashMap;
import java.util.Map;

public class LoginSystem {
    private Map<String, Cliente> usuarios;
    private Admin admin;

    public LoginSystem(Admin admin) {
        this.usuarios = new HashMap<>();
        this.admin = admin;
    }

    public void cadastrarUsuario(Cliente cliente) {
        usuarios.put(cliente.getUsername(), cliente);
    }

    public Cliente loginUsuario(String username, String password) {
        Cliente cliente = usuarios.get(username);
        if (cliente != null && cliente.validarSenha(password)) {
            return cliente;
        }
        System.out.println("Usuário ou senha incorretos.");
        return null;
    }

    public Admin loginAdmin(String username, String password) {
        if (admin.getUsername().equals(username) && admin.validarSenha(password)) {
            return admin;
        }
        System.out.println("Admin ou senha incorretos.");
        return null;
    }
}
