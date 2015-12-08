package gui;

import java.io.FileNotFoundException;
import java.io.IOException;

import fachada.GemialidadesLoja;
import negocios.classesBasicas.Cliente;
import negocios.classesBasicas.Guloseimas;
import negocios.classesBasicas.Travessuras;
import negocios.exceptions.ClienteJaExisteException;
import negocios.exceptions.ProdutoJaExisteException;

public class Programa {

	/** Classe que testa a fachada, apenas. */

	public static void main(String[] args) {

		try {
			GemialidadesLoja loja = new GemialidadesLoja();

			Cliente clintes = new Cliente("Rodrigo", "15", null, "552", "552");
			Cliente clintes1 = new Cliente("Rodrigo", "15", null, "53", "53");
			Cliente clients = new Cliente("Rodrigo", "15", null, "511152", "511152");
			
			loja.inserirCliente(clients);
			loja.inserirCliente(clintes);
			loja.inserirCliente(clintes1);
			
			loja.cadastrarProduto(new Travessuras("Orelha Extensível", "666", "Ouvir conversa alheia", 2, 15, 15.2));
			loja.cadastrarProduto(new Guloseimas("Sapo de chocolate", "555", "Feijoeszinhos com sabores diversos",
					"Nunca saberás", 15.2));
			loja.cadastrarProduto(new Travessuras("Kit mata-aula", "777", "Kit fugir-de-ricardo", 0, 0, 6.8));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ProdutoJaExisteException e) {
			e.printStackTrace();
		} catch (ClienteJaExisteException e) {
			e.printStackTrace();
		}
	}

}
