package heberleandro.github.desafiodiobanco.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public abstract class Conta {
	
	private static final int AGENCIA_PADRAO = 1;
	private static int SEQUENCIAL = 1;
	
	@Getter
	protected int agencia;
	
	@Getter 
	protected int numero;
	
	@Getter
	protected double saldo;
	
	@Getter
	protected Cliente cliente;
	
	
	protected List<String> historicoTransferencias;
	
	public Conta(Cliente cliente) {
		
		this.agencia = AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
		this.historicoTransferencias = new ArrayList<>();
		
		if (cliente.getConta() == null) {
			cliente.setConta(this);
		}
	}
	
	public void sacar(double valor) {
		
		if (valor <= saldo) {
			saldo-= valor;
		} else {
			System.out.println("Saldo insuficiente para o saque!");
		}
	}
	
	public void depositar(double valor) {
		
		saldo += valor;
	}
	
	public void transferir(double valor, Conta contaDestino) {
		
		if (valor <= saldo) {
			
			saldo -= valor;
			contaDestino.depositar(valor);
			atualizarHistoricoTransferencias(valor, contaDestino);
		} else {
			
			System.out.println("Saldo insuficiente para a transferencia!");
		}
	}
	
	protected void atualizarHistoricoTransferencias(double valor, Conta contaDestino) {
		
		String transacao = "";
		transacao += LocalDateTime.now() + " || ";
		transacao += "Transferencia para Cliente: " + contaDestino.getCliente().getNome();
		transacao += ", Conta: " + contaDestino.getNumero() + ", Valor: R$" + valor;
		
		if (historicoTransferencias.size() == 10) {
			
			historicoTransferencias.remove(0);
		}
		
		historicoTransferencias.add(transacao);
	}
	
	public void imprimirHistoricoTransferencias() {
		
		System.out.println(String.format("=== Transferencias Realizadas Partindo da Conta: %d ===", this.numero));
		for (String string : historicoTransferencias) {
			System.out.println(string);
		}
		System.out.println();
	}
	
	protected void imprimirInfos() {
		
		System.out.println(String.format("Cliente: %s", this.cliente.getNome()));
		System.out.println(String.format("Agencia: %d", this.agencia));
		System.out.println(String.format("Numero %d", this.numero));
		System.out.println(String.format("Saldo: %.2f", this.saldo));
		System.out.println();
	}
	
	public abstract void imprimirExtrato();
}
