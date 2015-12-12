package dados.repositorios;

import negocios.classesBasicas.Entrega;
import negocios.exceptions.EntregaNaoEncontradaException;

public interface RepositorioEntregas {
	
	void inserir(Entrega entrega);
	Entrega procurar(String id);
	void atualizar(String id, Entrega entrega);
	void remover(String id);
	void enviar(); 
	Iterator getIteratorPendentes();
	Iterator getIteratorEnviadas();

}
