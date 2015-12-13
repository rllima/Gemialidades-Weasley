package dados.repositorios;

import negocios.classesBasicas.Cliente;
import negocios.exceptions.ClienteNaoEncontradoException;

/**
 * Classe que representa o repositorio de clientes implementado em Array.
 * @author lfs
 *
 */

public class RepositorioClientesArray implements RepositoriosClientes, Iterator {

	private Cliente[] clientes;
	private int indice;
	private int indiceIterator;

	public RepositorioClientesArray() {
		this.clientes = new Cliente[100];
		this.indice = 0;

	}
	
	private RepositorioClientesArray(Cliente[] itr) {
		this.clientes = itr;
		this.indiceIterator = 0;
	}
	
	/**
	 * Insere, no repositorio, um cliente.
	 * @param cliente Cliente - cliente
	 */

	public void inserir(Cliente clientes) {
		if (indice > this.clientes.length) {
			Cliente[] aux = new Cliente[2 * (this.clientes.length)];
			for (int i = 0; i < this.clientes.length; i++) {
				aux[i] = this.clientes[i];
			}
			aux[indice] = clientes;
			this.clientes = aux;
			indice++;

		} else {
			this.clientes[indice] = clientes;
			indice++;
		}

	}
	
	/**
	 * Recebe um ID e procura, no repositorio, o cliente referente aquele ID.
	 * @return cliente Cliente - Cliente procurado a ser retornado (Caso encontrado)
	 * @param id String - Id do objeto que se procura
	 */
	public Cliente procurar(String id) {
		Cliente resposta = null;
		int b = this.buscarId(id);

		if (b != -1) {

			resposta = this.clientes[b];
		}
		return resposta;
	}

	/**
	 * Atualiza um objeto a partir de seu ID
	 * @param id String - Id do objeto a ser atualizado
	 * @param cliente Cliente - Objeto atualizado a ser inserido no lugar do antigo
	 */
	public void atualizar(String id, Cliente clientes) {
		int b = this.buscarId(id);

		if (b != -1) {
			this.clientes[b] = clientes;

		}

	}

	/**
	 * Remove um objeto a partir de seu ID
	 * @param id - ID do objeto a ser removido
	 */
	public void remover(String id) {
		int b = this.buscarId(id);
		if (b != -1) {
			for (int i = b; i < this.indice; i++) {
				this.clientes[i] = this.clientes[i + 1];
			}
		}
	}
/**
 * Recebe um id e retorna a localizacao(indice) do objeto que aquele id identifica.	
 * @param id String - Id do objeto que se quer encontrar
 * @return resposta int - Indice do objeto referente ao ID
 */
	public int buscarId(String id) {
		int resposta = -1;
		boolean achou = false;
		for (int i = 0; i < this.indice && !achou; i++) {
			Cliente aux = clientes[i];
			if (aux.getId().equals(id)) {
				resposta = i;
				achou = true;
			}
		}
		return resposta;

	}

	public Cliente next() {
		Cliente resposta = null;
		resposta = this.clientes[this.indiceIterator];
		this.indiceIterator++;
		return resposta;
	}

	public boolean hasNext() {
		return this.clientes[this.indiceIterator] != null; // LEMBRA DE TIRAR O +1
	}

	public Iterator<Cliente> getIterator() {
		Cliente[] aux = new Cliente[clientes.length];
		for(int i = 0; i < this.indice; i++) {
			aux[i] = this.clientes[i].clone();
		}
		RepositorioClientesArray iterator = new RepositorioClientesArray(aux);
		return iterator;
	}
}
