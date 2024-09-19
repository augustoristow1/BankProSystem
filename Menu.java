import java.util.Scanner;

public class Menu {
    private AgenciaBancaria agencia;
    private Scanner scanner;
    private LoginSystem loginSystem;
    private Cliente clienteLogado;
    private Admin adminLogado;

    public Menu(AgenciaBancaria agencia, LoginSystem loginSystem) {
        this.agencia = agencia;
        this.scanner = new Scanner(System.in);
        this.loginSystem = loginSystem;
    }

    public void exibirMenuPrincipal() {
        int opcao;
        do {
            System.out.println("==== Bem-vindo ====");
            System.out.println("1. Login como Admin");
            System.out.println("2. Login como Usuário");
            System.out.println("3. Sair do Sistema");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    loginAdmin();
                    break;
                case 2:
                    loginUsuario();
                    break;
                case 3:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 3);
    }

    private void loginAdmin() {
        System.out.print("Username: ");
        String username = scanner.next();
        System.out.print("Senha: ");
        String password = scanner.next();

        adminLogado = loginSystem.loginAdmin(username, password);
        if (adminLogado != null) {
            exibirMenuAdmin();
        }
    }

    private void loginUsuario() {
        System.out.print("Username: ");
        String username = scanner.next();
        System.out.print("Senha: ");
        String password = scanner.next();

        clienteLogado = loginSystem.loginUsuario(username, password);
        if (clienteLogado != null) {
            exibirMenuUsuario();
        }
    }

    private void exibirMenuAdmin() {
        int opcao;
        do {
            System.out.println("==== Menu Admin ====");
            System.out.println("1. Cadastrar Conta");
            System.out.println("2. Exibir Todas as Contas");
            System.out.println("3. Sair para o Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarConta();
                    break;
                case 2:
                    agencia.exibirContas();
                    break;
                case 3:
                    System.out.println("Saindo do menu de Admin e voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 3);
    }

    private void exibirMenuUsuario() {
        int opcao;
        do {
            System.out.println("==== Menu Usuário ====");
            System.out.println("1. Consultar Saldo");
            System.out.println("2. Realizar Depósito");
            System.out.println("3. Realizar Saque");
            System.out.println("4. Transferir Dinheiro");
            System.out.println("5. Sair para o Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    consultarSaldo();
                    break;
                case 2:
                    realizarDeposito();
                    break;
                case 3:
                    realizarSaque();
                    break;
                case 4:
                    realizarTransferencia();
                    break;
                case 5:
                    System.out.println("Saindo do menu de Usuário e voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 5);
    }

    private void cadastrarConta() {
        System.out.print("Nome do cliente: ");
        String nome = scanner.next();
        System.out.print("CPF do cliente: ");
        String cpf = scanner.next();
        System.out.print("Username do cliente: ");
        String username = scanner.next();
        System.out.print("Senha do cliente: ");
        String password = scanner.next();
        System.out.print("Número da conta: ");
        int numeroConta = scanner.nextInt();
        System.out.print("Tipo de conta (corrente/poupanca): ");
        String tipoConta = scanner.next();

        Cliente novoCliente = new Cliente(nome, cpf, username, password);
        loginSystem.cadastrarUsuario(novoCliente);
        adminLogado.cadastrarConta(agencia, novoCliente, numeroConta, tipoConta);
    }

    private void consultarSaldo() {
        System.out.print("Número da conta para consultar saldo: ");
        int numeroConta = scanner.nextInt(); // O usuário deve inserir o número da conta
        ContaBancaria conta = agencia.buscarConta(numeroConta); // Buscar a conta pelo número
        if (conta != null) {
            conta.exibirSaldo();
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    private void realizarDeposito() {
        System.out.print("Número da conta para depósito: ");
        int numeroConta = scanner.nextInt(); // O usuário deve inserir o número da conta
        System.out.print("Valor do depósito: ");
        double valor = scanner.nextDouble();
        ContaBancaria conta = agencia.buscarConta(numeroConta); // Buscar a conta pelo número
        if (conta != null) {
            conta.depositar(valor);
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    private void realizarSaque() {
        System.out.print("Número da conta para saque: ");
        int numeroConta = scanner.nextInt(); // O usuário deve inserir o número da conta
        System.out.print("Valor do saque: ");
        double valor = scanner.nextDouble();
        ContaBancaria conta = agencia.buscarConta(numeroConta); // Buscar a conta pelo número
        if (conta != null) {
            try {
                conta.sacar(valor);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    private void realizarTransferencia() {
        System.out.print("Número da conta de origem: ");
        int numeroContaOrigem = scanner.nextInt(); // O usuário deve inserir o número da conta de origem
        System.out.print("Número da conta de destino: ");
        int numeroContaDestino = scanner.nextInt(); // O usuário deve inserir o número da conta de destino
        System.out.print("Valor da transferência: ");
        double valor = scanner.nextDouble();

        System.out.println("Escolha o tipo de transferência:");
        System.out.println("1. TED (Taxa R$10,00)");
        System.out.println("2. DOC (Taxa R$7,00)");
        System.out.println("3. PIX (Sem Taxa)");
        System.out.print("Escolha uma opção: ");
        int tipoTransferencia = scanner.nextInt();

        String tipo = "";
        switch (tipoTransferencia) {
            case 1:
                tipo = "TED";
                break;
            case 2:
                tipo = "DOC";
                break;
            case 3:
                tipo = "PIX";
                break;
            default:
                System.out.println("Opção inválida.");
                return;
        }


        ContaBancaria contaOrigem = agencia.buscarConta(numeroContaOrigem);
        ContaBancaria contaDestino = agencia.buscarConta(numeroContaDestino);

        if (contaDestino != null && contaOrigem != null) {
            try {
                contaOrigem.transferir(contaDestino, valor, tipo);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Conta de destino ou origem não encontrada.");
        }
    }
}
