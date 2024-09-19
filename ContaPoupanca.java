public class ContaPoupanca extends ContaBancaria {
    private static final double TAXA_RENDIMENTO = 0.02; // Rendimento de 2% ao mês

    public ContaPoupanca(Cliente cliente, int numeroConta) {
        super(cliente, numeroConta);
    }

    @Override
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Depósito de R$" + valor + " realizado na conta poupança.");
        } else {
            System.out.println("Valor de depósito inválido.");
        }
    }

    @Override
    public void sacar(double valor) throws Exception {
        if (saldo >= valor) {
            saldo -= valor;
            System.out.println("Saque de R$" + valor + " realizado na conta poupança.");
        } else {
            throw new Exception("Saldo insuficiente.");
        }
    }

    public void aplicarRendimento() {
        saldo += saldo * TAXA_RENDIMENTO;
        System.out.println("Rendimento aplicado. Novo saldo: R$ " + saldo);
    }
}
