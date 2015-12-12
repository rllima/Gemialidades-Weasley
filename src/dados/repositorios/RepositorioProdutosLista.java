package dados.repositorios;

import negocios.classesBasicas.*;
import negocios.exceptions.EmptyListException;

/**
 * Classe que representa o repositorio de produtos implementado em Lista.
 * 
 * @author lfs
 *
 */
public class RepositorioProdutosLista implements RepositorioProdutos, Iterator{

	private Node<Produto> primeiroNo;
	private Node<Produto> ultimoNo;
	private int indiceIterator;
	private Produto[] iterator;

	// Construtor que cria lista vazia com um nome.
	public RepositorioProdutosLista() {
		this.primeiroNo = this.ultimoNo = null;
	}
	private RepositorioProdutosLista(Produto[] itr) {
		this.indiceIterator = 0;
		this.iterator = itr;
	}

	/**
	 * Metodo que cria um novo No, adiciona a informacao(Produto) nesse n� e
	 * liga ele � Lista.
	 * 
	 * @param produto
	 *            Produto - Produto a ser inserido na Lista.
	 */
	public void inserir(Produto produto) {
		if(isEmpty()) {
			primeiroNo = ultimoNo = new Node<Produto>(produto);
		} else {
			Node<Produto> aux = ultimoNo;
			Node<Produto> novo = new Node<Produto>(ultimoNo, produto, null);
			ultimoNo = novo;
			if (aux == null){
				primeiroNo = novo;
			} else {
				aux.setProximo(novo);
			}
		}
	}

	/**
	 * Metodo que recebe um ID e procura, na lista, um objeto No que contenha,
	 * como dado, um objeto Produto que possua um ID igual ao que se procura. Ao
	 * encontrar, retorna o objeto.
	 * 
	 * @param id
	 *            String - ID do produto a ser procurado
	 * @return resposta Produto  - Produto que possui ID igual ao procurado.
	 */
	public Produto procurar(String codigo) {
		Produto resposta = null;
		boolean achou = false;
		Node<Produto> atual = primeiroNo;
		while(atual != null && achou == false) {
			if(atual.getDado().getCodigo().equals(codigo)){
				resposta = atual.getDado();
				achou = true;
			}
			atual = atual.getProximo();
		}
		return resposta;
	}
	/**
	 * Recebe um ID e, usando o metodo procurarNode(), encontra o N� que contem aquele produto como dado.
	 * Depois, simplesmente desvincula aquele n� da lista.
	 * Mais tarde, ele ser� pego pelo GarbageCollector
	 * @param id String - ID do Produto a ser removido da lista.
	 */
	public void remover(String codigo){
		if(this.procurarNode(codigo) == null) {
			System.out.println("Nao existe na lista");
		} else {
			final Node<Produto> atual = this.procurarNode(codigo);
			final Node<Produto> proximo = atual.getProximo();
			final Node<Produto> anterior = atual.getAnterior();

			if(anterior == null) {
				primeiroNo = proximo;
			} else {
				anterior.setProximo(proximo);
			}

			if(proximo == null) {
				ultimoNo = anterior;
			} else {
				proximo.setAnterior(anterior);
			}
		}
	}

	/**
	 * Recebe um ID e um Produto, a fim de trocar as informacoes do produto que possui aquele ID.
	 * @param id String - ID do produto a ser atualizado.
	 * @param produto Produto - Novo objeto atualizado a ser alocado no lugar do antigo.
	 */
	public void atualizar(String codigo, Produto produto) {
		Node<Produto> atual = this.procurarNode(codigo);
		atual.setDado(produto);
	}

	/**
	 * Recebe uma String nome e procura, no repositorio, um objeto que tenha este nome.
	 * Caso encontre, retorna esse objeto.
	 * @param produto Produto - Produto a ser dado como retorno.
	 */
	public Produto procurarNome(String nome) {
		Produto resposta = null;
		boolean achou = false;
		Node<Produto> atual = primeiroNo;
		while(atual != null && achou == false) {
			if(atual.getDado().getNome().equals(nome)){
				resposta = atual.getDado();
				achou = true;
			}
			atual = atual.getProximo();
		}
		return resposta;
	}
	/**
	 * Recebe um obejto Produto e procura, n� a n�, um objeto id�ntico.
	 * Caso encontre, retorna true. Caso n�o, retorna false.
	 * @param produto
	 * @return boolean - True se existir, False se n�o.
	 */
	public boolean existe(Produto produto) {
		Node<Produto> atual = this.primeiroNo;

		while(atual != null) {
			if(atual.getDado().equals(produto)) {
				return true;
			}
			atual = atual.getProximo();
		}
		return false;
	}

	/**
	 * Metodo que retorna a informa��o de se a lista est� ou n�o vazia.
	 * @return boolean - True se tiver vazia, False se n�o.
	 */
	public boolean isEmpty() {
		return primeiroNo == null;
	}

	// Retorna uma string com os dados de todos objetos contidos na lista
	public String toString() {
		String resposta = "";
		if(isEmpty()) {
			resposta = "Lista de produtos vazia";
		} else {
			Node<Produto> atual = primeiroNo;
			// Enquanto nao chegar ao fim, coloca os dados de cada objeto na resposta
			while(atual != null) {
				resposta = resposta + atual.getDado().toString() + "\n\n";
				atual = atual.getProximo();
			}
		}
		return resposta;
	}

	/**
	 * Metodo que recebe um ID e procura, n� a n�, um objeto Produto que contenha aquele ID.
	 * Caso encontre, ele retorna o n� que cont�m o Produto possuidor daquele ID.
	 * @param id String - ID do produto que se procura
	 * @return Node<Produto> - N� que contem, como dado, o produto procurado.
	 */
	private Node<Produto> procurarNode(String codigo)/* throws EmptyListException*/ {
		Node<Produto> resposta = null;
		boolean achou = false;
		Node<Produto> atual = primeiroNo;
		while(atual != null && achou == false) {
			if(atual.getDado().getCodigo().equals(codigo)){
				resposta = atual;
				achou = true;
			}
			atual = atual.getProximo();
		}
		return resposta;
	}

	public Produto next() {
		Produto resposta = null;
		resposta = this.iterator[this.indiceIterator];
		this.indiceIterator++;
		return resposta;
	}

	public boolean hasNext() {
		return this.iterator[this.indiceIterator + 1] != null;
	}
	public Iterator<Produto> getIterator() throws EmptyListException {
		if (isEmpty()) {
			throw new EmptyListException();
		}
		int count = 0;
		Produto[] itr = new Produto[this.size()];
		Node<Produto> atual = primeiroNo;
		while (atual != null) {
			if( atual.getDado() instanceof Guloseimas){
		
			itr[count] = ((Guloseimas)atual.getDado()).clone();
			}else{
			itr[count] = ((Travessuras)atual.getDado()).clone();
			
			}
			atual = atual.getProximo();
			count++;
		}
		RepositorioProdutosLista iterator =  new RepositorioProdutosLista(itr);
		return iterator;
	}
	
	public int size() {
		int count = 0;
		Node<Produto> atual = primeiroNo;
		while (atual != null) {
			count++;
			atual = atual.getProximo();
		}
		return count;
	}

}
