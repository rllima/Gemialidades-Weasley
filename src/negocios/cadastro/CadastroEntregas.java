package negocios.cadastro;

import negocios.classesBasicas.Entrega;
import negocios.exceptions.EntregaJaExisteException;
import negocios.exceptions.EntregaNaoEncontradaException;
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
	public void remover(String id) throws EntregaNaoEncontradaException{
		if(repositorioEntregas.procurar(id) != null){
			repositorioEntregas.remover(id);
		}else{
			throw new EntregaNaoEncontradaException();
		}
	}
	public Entrega entrega(String id) throws EntregaNaoEncontradaException {
		Entrega resposta = repositorioEntregas.procurar(id);
		if(resposta == null){
			throw new EntregaNaoEncontradaException();
		}
		return resposta;
		
	}
	public void atualizar(String id, Entrega entrega) throws EntregaNaoEncontradaException {
		if(repositorioEntregas.procurar(id) == null){
			throw new EntregaNaoEncontradaException();
		}else{
			repositorioEntregas.atualizar(id, entrega);
		}
	}

}
