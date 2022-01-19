package heberleandro.github.desafiodiobanco.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


public class Banco {
	
	@Getter
	private String nome;
	
	private List<Conta> contas;
	
	public Banco(String nome) {
		this.nome = nome;
		this.contas = new ArrayList<>();
	}
	
	public void listarContas() {
		
		System.out.println(String.format("===== Contas do %s =====", this.nome));
		for (Conta conta : contas) {
			conta.imprimirExtrato();
		}
	}
	
	public void addConta(Conta conta) {
		
		contas.add(conta);
	}
}
