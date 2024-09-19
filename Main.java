import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Admin admin = new Admin("Augusto", "Java");

                AgenciaBancaria agencia = new AgenciaBancaria();
                LoginSystem loginSystem = new LoginSystem(admin);

                new BancoInterface();
            }
        });
    }
}
