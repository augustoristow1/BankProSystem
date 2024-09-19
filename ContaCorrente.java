public class ContaCorrente extends ContaBancaria {
    private static final double TAXA_SAQUE = 5.0;

    public ContaCorrente(Cliente cliente, int numeroConta) {
        super(cliente, numeroConta);
    }

    @Override
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Depósito de R$" + valor + " realizado na conta corrente.");
        } else {
            System.out.println("Valor de depósito inválido.");
        }
    }

    @Override
    public void sacar(double valor) throws Exception {
        if (saldo >= valor + TAXA_SAQUE) {
            saldo -= valor + TAXA_SAQUE;
            System.out.println("Saque de R$" + valor + " realizado na conta corrente. Taxa: R$" + TAXA_SAQUE);
        } else {
            throw new Exception("Saldo insuficiente.");
        }
    }
}
