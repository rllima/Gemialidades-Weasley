package dados.repositorios;

import negocios.classesBasicas.Cliente;
import negocios.exceptions.ClienteNaoEncontradoException;

public class RepositorioClientesArray implements RepositoriosClientes, Iterator {

	private Cliente[] clientes;
	private int indice;
	private int indiceIterator;

	public RepositorioClientesArray() {
		this.clientes = new Cliente[100];
		this.indice = 0;

	}

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

	public Cliente procurar(String id) {
		Cliente resposta = null;
		int b = this.buscarId(id);

		if (b != -1) {

			resposta = this.clientes[b];
		}
		return resposta;
	}

	public void atualizar(String id, Cliente clientes) {
		int b = this.buscarId(id);

		if (b != -1) {
			this.clientes[b] = clientes;

		}

	}

	public void remover(String id) {
		int b = this.buscarId(id);
		if (b != -1) {
			for (int i = b; i < this.indice; i++) {
				this.clientes[i] = this.clientes[i + 1];
			}
		}
	}

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

	public Object next() {
		Cliente resposta = null;
		resposta = this.clientes[this.indiceIterator];
		this.indiceIterator++;
		return resposta;
	}

	public boolean hasNext() {
		boolean resposta = false;
		if (this.clientes[this.indiceIterator + 1] != null) {
			resposta = true;
		} else {
			resposta = false;
		}
		return resposta;
	}

	public Iterator getIterator() {

		Cliente respota = null;
		this.indiceIterator = 0;
		Cliente[] iterator = (Cliente[]) clientes.clone();
		return (Iterator) this;
	}
}
