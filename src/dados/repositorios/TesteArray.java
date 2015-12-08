package dados.repositorios;
import negocios.classesBasicas.*;
public class TesteArray {

	public static void main(String[] args) {
		RepositorioClientesArray clientes = new RepositorioClientesArray();
		
		Cliente clintes = new Cliente("Rodrigo", "15", null, "552", "552");
		Cliente clintes1 = new Cliente("Rodrigo", "15", null, "53", "53");
		Cliente clients = new Cliente("Rodrigo", "15", null, "511152", "511152");
		clientes.inserir(clintes);
		clientes.inserir(clintes1);
		clientes.inserir(clients);
		clientes.remover("552");

	}

}
