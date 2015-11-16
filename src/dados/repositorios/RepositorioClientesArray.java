package dados.repositorios;

import negocios.classesBasicas.Cliente;
import negocios.exceptions.ClienteNaoEncontradoException;

public class RepositorioClientesArray implements RepositoriosClientes {
	
	private Cliente [] clientes;
	private int indice;

	public RepositorioClientesArray(){
		this.clientes = new Cliente[100];
		this.indice = 0;

	}


	
	public void inserir(Cliente clientes) {
		if(indice > this.clientes.length){
			Cliente[] aux = new Cliente[ 2*(this.clientes.length)];
			for(int i = 0; i < this.clientes.length; i++){
				aux[i] = this.clientes[i];
			}
			aux[indice] = clientes;
			this.clientes = aux;
			indice++;

		}
		else{
			this.clientes[indice] = clientes;
			indice++;
		}

	}

	public Cliente procurar(String id) throws ClienteNaoEncontradoException {
		Cliente resposta = null;
		int b = this.buscarId(id);

		if (b >= 0) {
			
			resposta = this.clientes[b];
		}else{
			
			throw new ClienteNaoEncontradoException();

		}
		return resposta;
	}

	public void atualizar(String id) {
		

	}

	public void remover(String id) throws ClienteNaoEncontradoException {
		int b = this.buscarId(id);
		if( b >= 0){
			for (int i = 0; i < indice; i++) {
				Cliente aux = this.clientes[i];
				if (aux.getId().equals(id)) {
					this.clientes[i] = this.clientes[indice--];
					indice = indice--;

				}
			}
		}
		else{
			throw new ClienteNaoEncontradoException();
		}
	}
	public int buscarId(String id){
		int resposta = -1;
		for (int i = 0; i < indice; i++) {
			Cliente aux = this.clientes[i];
			if (aux.getId().equals(id)) {
				resposta = i;

			}
		}
		return resposta;

	}

}
