package heberleandro.github.desafiodiobanco.models;

public class ContaPoupanca extends Conta {
	
	public ContaPoupanca(Cliente cliente, double valor) {
		super(cliente);
		super.saldo = valor;
	}
	
	@Override
	public void transferir(double valor, Conta contaDestino) {
		
		if (valor > 500) {
			System.out.println("Seu tipo de Conta não permite transferencias maiores que R$500,00 !");
			return;
		}
		
		if (valor <= saldo) {
			
			saldo -= valor;
			contaDestino.depositar(valor);
			atualizarHistoricoTransferencias(valor, contaDestino);
		} else {
			
			System.out.println("Saldo insuficiente para a transferencia!");
		}
	}

	@Override
	public void imprimirExtrato() {
		
		System.out.println("=== Extrato Conta Poupança ===");
		super.imprimirInfos();
	}
}
