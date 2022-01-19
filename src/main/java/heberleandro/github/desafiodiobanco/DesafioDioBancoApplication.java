package heberleandro.github.desafiodiobanco;

import heberleandro.github.desafiodiobanco.models.Banco;
import heberleandro.github.desafiodiobanco.models.Cliente;
import heberleandro.github.desafiodiobanco.models.Conta;
import heberleandro.github.desafiodiobanco.models.ContaCorrente;
import heberleandro.github.desafiodiobanco.models.ContaPoupanca;

public class DesafioDioBancoApplication {
	
	public static void main(String[] args) {
		
		Banco banco = new Banco("DIO - Bank");
		
		Cliente clt1, clt2;
		clt1 = new Cliente();
		clt2 = new Cliente();
		
		clt1.setNome("José");
		clt2.setNome("Maria");
		
		Conta c1, c2;
		c1 = new ContaCorrente(clt1);
		c2 = new ContaPoupanca(clt2, 100);
		
		banco.addConta(c1);
		banco.addConta(c2);
		
		c1.depositar(10);
		
		c2.transferir(90, c1);
		c2.depositar(1000);
		c2.transferir(550, c1);
		c2.sacar(550);
		
		c1.depositar(500);
		c1.transferir(600, c2);
		
		c1.imprimirHistoricoTransferencias();
		c2.imprimirHistoricoTransferencias();
		
		banco.listarContas();
	}
}
