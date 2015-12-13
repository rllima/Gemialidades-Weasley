package dados.repositorios;

import negocios.classesBasicas.Cliente;
import negocios.classesBasicas.Guloseimas;
import negocios.classesBasicas.Travessuras;

import negocios.classesBasicas.Produto;

/**
 * Classe que representa o repositorio de Produtos implementado em Array
 * @author lfs
 *
 */
public class RepositorioProdutosArray implements RepositorioProdutos, Iterator {

	private Produto[] repositorioProdutos;
	private int indice;
	private int indiceIterator;

	public RepositorioProdutosArray() {
		this.indice = 0;
		this.repositorioProdutos = new Produto[100];
	}
	private RepositorioProdutosArray(Produto[] itr) {
		this.repositorioProdutos = itr;
		this.indiceIterator = 0;
	}

	/**
	 * Insere, no repositorio, um produto.
	 * 
	 * @param produto
	 *            Produto - Produto a ser inserido
	 */
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

	/**
	 * Recebe um ID e procura, no repositorio, o produto referente aquele ID.
	 * 
	 * @return produto Produto - Produto procurado a ser retornado (Caso
	 *         encontrado)
	 * @param id
	 *            String - Id do objeto que se procura
	 */
	public Produto procurar(String codigo) {
		Produto resposta = null;
		int indice = this.buscarCodigo(codigo);
		if (indice > -1) {
			resposta = this.repositorioProdutos[indice];
		}
		return resposta;
	}

	/**
	 * Atualiza um objeto a partir de seu ID
	 * 
	 * @param id
	 *            String - Id do objeto a ser atualizado
	 * @param produto
	 *            Produto - Objeto atualizado a ser inserido no lugar do antigo
	 */
	public void atualizar(String codigo, Produto produtos) {
		int b = this.buscarCodigo(codigo);

		if (b != -1) {
			this.repositorioProdutos[b] = produtos;

		}

	}

	/**
	 * Remove um objeto a partir de seu ID
	 * 
	 * @param id
	 *            - ID do objeto a ser removido
	 */
	public void remover(String codigo) {
		int b = this.buscarCodigo(codigo);
		if (b != -1) {
			for (int i = b; i < indice; i++) {
				this.repositorioProdutos[i] = this.repositorioProdutos[i + 1];
			}
		}
		indice--;
	}

	/**
	 * Recebe uma String nome e procura, no repositorio, um objeto que tenha este nome.
	 * Caso encontre, retorna esse objeto.
	 * @param produto Produto - Produto a ser dado como retorno.
	 */
	public Produto procurarNome(String nome) {
		Produto aux = null;
		for (int i = 0; i < indice; i++) {
			if (repositorioProdutos[i].getNome().equalsIgnoreCase(nome)) {
				aux = repositorioProdutos[i];
				this.remover(aux.getCodigo());
			}
		}
		return aux;
	}
	/**
	 * Recebe um id e retorna a localizacao(indice) do objeto que aquele id identifica.	
	 * @param id String - Id do objeto que se quer encontrar
	 * @return resposta int - Indice do objeto referente ao ID
	 */
	public int buscarCodigo(String codigo) {
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
	public Produto next() {
		Produto resposta = null;
		resposta = this.repositorioProdutos[this.indiceIterator];
		this.indiceIterator++;
		return resposta;
	}
	public boolean hasNext() {
		return this.repositorioProdutos[this.indiceIterator] != null;
	}
	public Iterator<Produto> getIterator() {
		Produto[] aux = new Produto[repositorioProdutos.length];
		for(int i = 0; i < this.indice; i++) {
			if(this.repositorioProdutos[i] instanceof Guloseimas){
				aux[i] = ((Guloseimas) this.repositorioProdutos[i]).clone();
			}else{
				aux[i] = ((Travessuras) this.repositorioProdutos[i]).clone();
			}
			
			
		}
		RepositorioProdutosArray iterator = new RepositorioProdutosArray(aux);
		return iterator;
	}
}
