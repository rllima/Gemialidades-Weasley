package negocios.cadastro;

import negocios.classesBasicas.Entrega;
import negocios.exceptions.EmptyListException;
import negocios.exceptions.EntregaJaExisteException;
import negocios.exceptions.EntregaNaoEncontradaException;
import dados.repositorios.Iterator;
import dados.repositorios.RepositorioEntregas;

public class CadastroEntregas {
	
	private RepositorioEntregas repositorioEntregas;
	
	public CadastroEntregas(RepositorioEntregas repositorioEntregas) {
		this.repositorioEntregas = repositorioEntregas;
	}
	public void cadastrar(Entrega entrega) throws EntregaJaExisteException {
		if(repositorioEntregas.procurar(entrega.getId()) == null){
			repositorioEntregas.inserir(entrega);
		}else {
			throw new EntregaJaExisteException();
		}
	
		
	} 
	public void remover(String id) throws EntregaNaoEncontradaException, EmptyListException{
		if(repositorioEntregas.procurar(id) != null){
			repositorioEntregas.remover(id);
		}else{
			throw new EntregaNaoEncontradaException();
		}
	}
	public Entrega procurar(String id) throws EntregaNaoEncontradaException, EmptyListException {
		Entrega resposta = repositorioEntregas.procurar(id);
		if(resposta == null){
			throw new EntregaNaoEncontradaException();
		}
		return resposta;
		
	}
	public void atualizar(String id, Entrega entrega) throws EntregaNaoEncontradaException, EmptyListException{
		if(repositorioEntregas.procurar(id) == null){
			throw new EntregaNaoEncontradaException();
		}else{
			repositorioEntregas.atualizar(id, entrega);
		}
	}
	
	public Iterator getIteratorPendentes() {
		return repositorioEntregas.getIteratorPendentes();
	}
	
	public Iterator getIteratorEnviadas() {
		return repositorioEntregas.getIteratorEnviadas();
	}

}
