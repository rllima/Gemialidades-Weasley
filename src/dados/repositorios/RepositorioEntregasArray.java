package dados.repositorios;

import negocios.classesBasicas.Cliente;
import negocios.classesBasicas.Entrega;
import negocios.classesBasicas.Produto;
import negocios.exceptions.EntregaNaoEncontradaException;

public class RepositorioEntregasArray implements RepositorioEntregas {

	private Entrega[] repositorioEntregas;
	private int indice;

	public RepositorioEntregasArray() {
		this.repositorioEntregas = new Entrega[100];
		this.indice = 0;
	}

	public void inserir(Entrega entrega) {

		if(indice > this.repositorioEntregas.length) {
			Entrega[] aux = new Entrega[repositorioEntregas.length * 2];
			for(int i = 0; i < indice; i++) {
				aux[i] = this.repositorioEntregas[i];
			}
			aux[indice] = entrega;
		} else {
			this.repositorioEntregas[indice] = entrega;
		}
		indice++;
	}

	public Entrega procurar(String id) throws EntregaNaoEncontradaException {
		Entrega resposta = null;
		int indice = this.buscarCodigo(id);
		if(indice > -1) {
			resposta = this.repositorioEntregas[indice];
		}
		return resposta;
	}

	public void atualizar(String id, Entrega entrega) {
		int indice = this.buscarCodigo(id);
		if(indice != -1){
			this.repositorioEntregas[indice] = entrega;
		}

	}

	public void remover(String id) throws EntregaNaoEncontradaException {
		int b = this.buscarCodigo(id);
		if( b >= 0){
			for (int i = 0; i < indice; i++) {
				Entrega aux = this.repositorioEntregas[i];
				if (aux.getId().equals(id)) {
					this.repositorioEntregas[i] = this.repositorioEntregas[indice--];
					indice = indice--;

				}
			}
		}
	}

	private int buscarCodigo(String id){
		int resposta = -1;
		boolean achou = false;
		for (int i = 0; i < this.indice && !achou; i++) {
			Entrega aux = this.repositorioEntregas[i];
			if (aux.getId().equalsIgnoreCase(id)) {
				resposta = i;
				achou = true;
			}
		}
		return resposta;

	}


}
