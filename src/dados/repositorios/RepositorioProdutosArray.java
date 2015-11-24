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
		if (this.indice > this.repositorioProdutos.length) {
			Produto[] aux = new Produto[repositorioProdutos.length * 2];
			for (int i = 0; i < indice; i++) {
				aux[i] = this.repositorioProdutos[i];
			}
			aux[indice] = produto;
			repositorioProdutos = aux;
		} else {
			this.repositorioProdutos[indice] = produto;
		}
		indice++;
	}

	public Produto procurar(String codigo)  {
		Produto resposta = null;
		int indice = this.buscarCodigo(codigo);
		if (indice > -1) {
			resposta = this.repositorioProdutos[indice];
		}
		return resposta;
	}

	public void atualizar(String codigo, Produto produtos) {
		
	}

	public void remover(String id)  {
		
	}
	
	public int buscarCodigo(String codigo){
		int resposta = -1;
		boolean achou = false;
		for (int i = 0; i < this.indice && !achou; i++) {
			Produto aux = this.repositorioProdutos[i];
			if (aux.getCodigo().equalsIgnoreCase(codigo)) {
				resposta = i;
				achou = true;
			}
		}
		return resposta;

	}

	

}
