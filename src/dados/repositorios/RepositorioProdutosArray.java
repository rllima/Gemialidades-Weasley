package dados.repositorios;

import negocios.classesBasicas.Cliente;
import negocios.classesBasicas.Produto;
import negocios.exceptions.ProdutoNaoEncontradoException;

public class RepositorioProdutosArray implements RepositorioProdutos {

	private Produto[] repositorioProdutos;
	private int indice;

	public RepositorioProdutosArray() {
		this.indice = 0;
		this.repositorioProdutos = new Produto[100];
	}

	public void inserir(Produto produto) {
		if (this.indice > repositorioProdutos.length) {
			Produto[] aux = new Produto[repositorioProdutos.length * 2];
			for (int i = 0; i < indice; i++) {
				aux[i] = repositorioProdutos[i];
			}
			aux[indice] = produto;
			repositorioProdutos = aux;
		} else {
			repositorioProdutos[indice] = produto;
		}
		indice++;
	}

	public Produto procurar(String codigo) throws ProdutoNaoEncontradoException {
		Produto resposta = null;
		for(int i = 0; i < this.indice; i++) {
			if (repositorioProdutos[i].getCodigo().equals(codigo)) {
				resposta = repositorioProdutos[i];
			}
		}
		return resposta;
	}

	public void atualizar() {
		
	}

	public void remover(String id) throws ProdutoNaoEncontradoException {
		
	}
	
	public int buscarCodigo(String codigo){
		int resposta = -1;
		for (int i = 0; i < indice; i++) {
			Produto aux = this.repositorioProdutos[i];
			if (aux.getCodigo().equals(codigo)) {
				resposta = i;

			}
		}
		return resposta;

	}

}
