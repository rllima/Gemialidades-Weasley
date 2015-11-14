package dados.repositorios;

import negocios.classesBasicas.Cliente;
import negocios.exceptions.ClienteNaoEncontradoException;

public interface RepositoriosClientes {
	
	void inserir(Cliente clientes);
	Cliente procurar(String id) throws ClienteNaoEncontradoException;
	void atualizar(String id);
	void remover(String id) throws ClienteNaoEncontradoException;
	

}
