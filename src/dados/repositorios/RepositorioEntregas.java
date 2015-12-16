package dados.repositorios;

import java.io.FileNotFoundException;
import java.io.IOException;

import negocios.classesBasicas.Entrega;
import negocios.exceptions.EmptyListException;
import negocios.exceptions.EntregaNaoEncontradaException;

public interface RepositorioEntregas {
	
	void inserir(Entrega entrega) throws FileNotFoundException, IOException;
	Entrega procurar(String id);
	void atualizar(String id, Entrega entrega);
	void remover(String id) throws FileNotFoundException, IOException;
	void enviar() throws FileNotFoundException, IOException; 
	boolean isEmpty();
	Iterator getIteratorPendentes() throws EmptyListException;
	Iterator getIteratorEnviadas() throws EmptyListException;

}
