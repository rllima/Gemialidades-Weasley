package negocios.cadastro;

import negocios.classesBasicas.Cliente;
import negocios.classesBasicas.Produto;
import negocios.exceptions.ClienteJaExisteException;
import negocios.exceptions.ClienteNaoEncontradoException;
import negocios.exceptions.ProdutoNaoEncontradoException;
import dados.repositorios.RepositorioClientesArray;
import dados.repositorios.RepositorioProdutos;
import dados.repositorios.RepositoriosClientes;

public class CadastroClientes {
	private RepositoriosClientes cliente;
	
	public CadastroClientes(RepositoriosClientes cliente) {
		this.cliente = cliente;
	}

	public void inserir(Cliente clientes) throws ClienteJaExisteException {
		if(cliente.procurar(clientes.getId()) == null) {
			cliente.inserir(clientes);
			
		}else {
			throw new ClienteJaExisteException();
		}
	
	}

	public Cliente procurar(String id) throws ClienteNaoEncontradoException {
		Cliente resposta = cliente.procurar(id);
		if(resposta == null) {
			throw new ClienteNaoEncontradoException();
		}
		return resposta;
	}

	public void atualizar(String id, Cliente clientes) throws ClienteNaoEncontradoException {
		if(cliente.procurar(id) == null) {
			throw new ClienteNaoEncontradoException();
		} else {
			cliente.atualizar(id, clientes);
		}
	}
	

	public void remover(String id) throws ClienteNaoEncontradoException {
		if(cliente.procurar(id) != null) {
			cliente.remover(id);
		} else {
			throw new ClienteNaoEncontradoException();
		}
	}


}