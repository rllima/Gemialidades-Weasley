package dados.repositorios;

import negocios.classesBasicas.Entrega;
import negocios.exceptions.EntregaNaoEncontradaException;

public class RepositorioEntregasArray implements RepositorioEntregas {
	
	private Entrega[] repositorioEntregas;
	private int indice;

	public RepositorioEntregasArray() {
		this.repositorioEntregas = new Entrega[100];
		this.indice = 0;
	}
	
	public void inserir(Entrega entrega) {
		
	}

	public Entrega procurar(String id) throws EntregaNaoEncontradaException {
		return null;
	}

	public void atualizar(String id) {
		
	}

	public void remover(String id) throws EntregaNaoEncontradaException {
		
	}

}
