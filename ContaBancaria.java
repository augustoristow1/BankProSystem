public abstract class ContaBancaria {
    private Cliente cliente;
    private int numeroConta;
    protected double saldo;

    public ContaBancaria(Cliente cliente, int numeroConta) {
        this.cliente = cliente;
        this.numeroConta = numeroConta;
        this.saldo = 0.0;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public abstract void depositar(double valor);

    public abstract void sacar(double valor) throws Exception;

    public void exibirSaldo() {
        System.out.println("Saldo da conta " + numeroConta + ": R$ " + saldo);
    }

    public void exibirInformacoesConta() {
        cliente.exibirInformacoes();
        System.out.println("Número da Conta: " + numeroConta + " | Saldo: R$ " + saldo);
    }

    public void transferir(ContaBancaria contaDestino, double valor, String tipoTransferencia) throws Exception {
        double taxa = 0;

        switch (tipoTransferencia.toUpperCase()) {
            case "TED":
                taxa = 10.0;
                System.out.println("Iniciando transferência TED... (Taxa: R$10.00)");
                break;
            case "DOC":
                taxa = 7.0;
                System.out.println("Iniciando transferência DOC... (Taxa: R$7.00)");
                break;
            case "PIX":
                taxa = 0.0;
                System.out.println("Iniciando transferência PIX... (Sem taxa)");
                break;
            default:
                throw new Exception("Tipo de transferência inválido.");
        }

        System.out.println("Carregando... Aguarde.");
        simularProcesso();

        if (saldo >= valor + taxa) {
            saldo -= (valor + taxa);
            contaDestino.depositar(valor);
            System.out.println("Transferência de R$" + valor + " concluída com sucesso!");
        } else {
            System.out.println("Erro: Saldo insuficiente para realizar a transferência.");
        }
    }


    private void simularProcesso() {
        try {
            Thread.sleep(2000); // Pausa de 2 segundos
            limparTerminal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void limparTerminal() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
