import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AgenciaBancaria {
    private Map<Integer, ContaBancaria> bancoDeDadosContas;

    public AgenciaBancaria() {
        this.bancoDeDadosContas = new HashMap<>();
    }

    public void abrirConta(Cliente cliente, int numeroConta, String tipoConta) {
        ContaBancaria novaConta = null;
        if (tipoConta.equalsIgnoreCase("corrente")) {
            novaConta = new ContaCorrente(cliente, numeroConta);
        } else if (tipoConta.equalsIgnoreCase("poupanca")) {
            novaConta = new ContaPoupanca(cliente, numeroConta);
        }

        if (novaConta != null) {
            bancoDeDadosContas.put(numeroConta, novaConta);
            System.out.println("Conta " + tipoConta + " aberta com sucesso para " + cliente.getNome() + " | Número da Conta: " + numeroConta);
        } else {
            System.out.println("Erro ao abrir conta.");
        }
    }

    public ContaBancaria buscarConta(int numeroConta) {
        return bancoDeDadosContas.get(numeroConta); // Busca a conta diretamente no HashMap
    }


    public Collection<ContaBancaria> getContas() {
        return bancoDeDadosContas.values(); // Retorna a coleção de contas
    }

    public void exibirContas() {
        if (bancoDeDadosContas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
        } else {
            for (ContaBancaria conta : bancoDeDadosContas.values()) {
                conta.exibirInformacoesConta();
            }
        }
    }
}
// mas que porra dificil de conf com a interface em