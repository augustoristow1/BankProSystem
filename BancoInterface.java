import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BancoInterface extends JFrame {
    private AgenciaBancaria agencia;
    private LoginSystem loginSystem;

    public BancoInterface() {
        setTitle("Sistema Bancário");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Admin admin = new Admin("Augusto", "Java");
        agencia = new AgenciaBancaria();
        loginSystem = new LoginSystem(admin);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Usuário:");
        userLabel.setBounds(50, 50, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(130, 50, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Senha:");
        passwordLabel.setBounds(50, 90, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(130, 90, 165, 25);
        panel.add(passwordText);

        JButton loginAdminButton = new JButton("Login Admin");
        loginAdminButton.setBounds(50, 150, 150, 25);
        panel.add(loginAdminButton);

        JButton loginUserButton = new JButton("Login Usuário");
        loginUserButton.setBounds(200, 150, 150, 25);
        panel.add(loginUserButton);

        loginAdminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());

                Admin adminLogado = loginSystem.loginAdmin(username, password);
                if (adminLogado != null) {
                    JOptionPane.showMessageDialog(null, "Login Admin bem-sucedido!");
                    abrirMenuAdmin();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha de Admin incorretos!");
                }
            }
        });

        loginUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());

                Cliente clienteLogado = loginSystem.loginUsuario(username, password);
                if (clienteLogado != null) {
                    JOptionPane.showMessageDialog(null, "Login Usuário bem-sucedido!");
                    abrirMenuUsuario(clienteLogado);
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!");
                }
            }
        });

        add(panel);
        setVisible(true);
    }

    private void abrirMenuAdmin() {
        JFrame adminFrame = new JFrame("Menu Admin");
        adminFrame.setSize(400, 300);
        adminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JButton cadastrarContaButton = new JButton("Cadastrar Conta");
        cadastrarContaButton.setBounds(100, 50, 200, 25);
        panel.add(cadastrarContaButton);

        JButton exibirContasButton = new JButton("Exibir Todas as Contas");
        exibirContasButton.setBounds(100, 100, 200, 25);
        panel.add(exibirContasButton);

        cadastrarContaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = JOptionPane.showInputDialog("Digite o nome do cliente:");
                String cpf = JOptionPane.showInputDialog("Digite o CPF do cliente:");
                String username = JOptionPane.showInputDialog("Digite o username do cliente:");
                String senha = JOptionPane.showInputDialog("Digite a senha do cliente:");
                String numeroConta = JOptionPane.showInputDialog("Digite o número da conta:");
                String tipoConta = JOptionPane.showInputDialog("Digite o tipo de conta (corrente/poupanca):");

                Cliente novoCliente = new Cliente(nome, cpf, username, senha);
                agencia.abrirConta(novoCliente, Integer.parseInt(numeroConta), tipoConta);
                loginSystem.cadastrarUsuario(novoCliente);
                JOptionPane.showMessageDialog(null, "Conta cadastrada com sucesso!");
            }
        });

        exibirContasButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder contasInfo = new StringBuilder();
                for (ContaBancaria conta : agencia.getContas()) {
                    contasInfo.append("Conta: ").append(conta.getNumeroConta())
                            .append(" - Cliente: ").append(conta.getCliente().getNome()).append("\n");
                }
                JOptionPane.showMessageDialog(null, contasInfo.toString());
            }
        });

        adminFrame.add(panel);
        adminFrame.setVisible(true);
    }

    private void abrirMenuUsuario(Cliente clienteLogado) {
        JFrame userFrame = new JFrame("Menu Usuário");
        userFrame.setSize(400, 300);
        userFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        userFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JButton consultarSaldoButton = new JButton("Consultar Saldo");
        consultarSaldoButton.setBounds(100, 50, 200, 25);
        panel.add(consultarSaldoButton);

        JButton realizarDepositoButton = new JButton("Realizar Depósito");
        realizarDepositoButton.setBounds(100, 100, 200, 25);
        panel.add(realizarDepositoButton);

        JButton realizarSaqueButton = new JButton("Realizar Saque");
        realizarSaqueButton.setBounds(100, 150, 200, 25);
        panel.add(realizarSaqueButton);

        JButton transferirButton = new JButton("Transferir Dinheiro"); // Botão de transferência
        transferirButton.setBounds(100, 200, 200, 25);
        panel.add(transferirButton);

        consultarSaldoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String numeroConta = JOptionPane.showInputDialog("Digite o número da conta para consultar saldo:");
                ContaBancaria conta = agencia.buscarConta(Integer.parseInt(numeroConta));
                if (conta != null) {
                    JOptionPane.showMessageDialog(null, "Saldo: R$ " + conta.getSaldo());
                } else {
                    JOptionPane.showMessageDialog(null, "Conta não encontrada.");
                }
            }
        });

        realizarDepositoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String numeroConta = JOptionPane.showInputDialog("Digite o número da conta para depósito:");
                String valorDeposito = JOptionPane.showInputDialog("Digite o valor do depósito:");
                ContaBancaria conta = agencia.buscarConta(Integer.parseInt(numeroConta));
                if (conta != null) {
                    conta.depositar(Double.parseDouble(valorDeposito));
                    JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Conta não encontrada.");
                }
            }
        });

        realizarSaqueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String numeroConta = JOptionPane.showInputDialog("Digite o número da conta para saque:");
                String valorSaque = JOptionPane.showInputDialog("Digite o valor do saque:");
                ContaBancaria conta = agencia.buscarConta(Integer.parseInt(numeroConta));
                if (conta != null) {
                    try {
                        conta.sacar(Double.parseDouble(valorSaque));
                        JOptionPane.showMessageDialog(null, "Saque realizado com sucesso!");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Conta não encontrada.");
                }
            }
        });


        transferirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String numeroContaOrigem = JOptionPane.showInputDialog("Digite o número da conta de origem:");
                String numeroContaDestino = JOptionPane.showInputDialog("Digite o número da conta de destino:");
                String valorTransferencia = JOptionPane.showInputDialog("Digite o valor da transferência:");

                String[] tipos = {"TED", "DOC", "PIX"};
                String tipoTransferencia = (String) JOptionPane.showInputDialog(
                        null, "Selecione o tipo de transferência:", "Tipo de Transferência",
                        JOptionPane.QUESTION_MESSAGE, null, tipos, tipos[0]);

                ContaBancaria contaOrigem = agencia.buscarConta(Integer.parseInt(numeroContaOrigem));
                ContaBancaria contaDestino = agencia.buscarConta(Integer.parseInt(numeroContaDestino));

                if (contaOrigem != null && contaDestino != null) {
                    try {
                        contaOrigem.transferir(contaDestino, Double.parseDouble(valorTransferencia), tipoTransferencia);
                        JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso!");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Conta de origem ou destino não encontrada.");
                }
            }
        });

        userFrame.add(panel);
        userFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new BancoInterface();
    }
}
