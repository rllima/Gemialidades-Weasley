package dados.repositorios;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import negocios.classesBasicas.Produto;

public class RepositorioProdutosLista implements RepositorioProdutos {
	private List<Produto> produtos = new LinkedList<Produto>();
	public RepositorioProdutosLista(){
		this.produtos = produtos;
	}

	public void inserir(Produto produto) {
		this.produtos.add(produto);

	}

	public Produto procurar(String codigo) {
		ListIterator<Produto> iterator = produtos.listIterator(0);
		boolean achou = false;
		Produto resposta = null;
		while(iterator.hasNext() && achou){
			if(iterator.next().getCodigo().equalsIgnoreCase(codigo)){
				resposta = iterator.next();
				achou = true;

			}

		}

		return resposta;
	}

	public void atualizar(String codigo, Produto produtos) {
		Produto aux = this.procurar(codigo);
		int posicao = this.produtos.indexOf(aux);
		this.produtos.add(posicao, produtos);

	}

	public void remover(String id) {
		produtos.remove(this.procurar(id));

	}

}