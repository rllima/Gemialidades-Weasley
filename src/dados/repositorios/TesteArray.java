package dados.repositorios;
import negocios.classesBasicas.*;
public class TesteArray {

	public static void main(String[] args) {
		RepositorioClientesArray clientes = new RepositorioClientesArray();
		
		Cliente clintes = new Cliente("Rodrigo", "15", null, "552", "552");
		clientes.inserir(clintes);
		Cliente cliente = clientes.procurar("552");
		System.out.println(clintes.toString());

	}

}
