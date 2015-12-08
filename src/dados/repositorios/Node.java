package dados.repositorios;

/** Classe que representa um no. Utilizada na implementacao de LISTA.
 * 
 * @author lfs
 *
 * @param <T>
 */

public class Node<T> {
	
	private T dado;
	private Node<T> proximo;
	private Node<T> anterior;
	
	/**
	 * Construtor que cria no e prepara a lista pra receber o
	 * proximo objeto. É usado quando a lista está vazia.
	 * @param objeto
	 */
	public Node(T objeto){
		this(objeto, null);
	}

	/**
	 * Construtor que cria um no com um objeto(dado) e referencia o proximo da lista.
	 * @param objeto
	 * @param proximo
	 */
	public Node(T objeto, Node<T> proximo) {
		this.dado = objeto;
		this.proximo = proximo;
	}
	
	public Node(Node<T> anterior, T objeto, Node<T> proximo) {
		this.anterior = anterior;
		this.dado = objeto;
		this.proximo = proximo;
	}

	public void setDado(T dado) {
		this.dado = dado;
	}

	/** 
	 * Retorna os dados contidos naquele objeto no.
	 * @return
	 */
	public T getDado() {
		return dado;
	}

	/**
	 * Retorna a referencia ao proximo da lista 
	 * @return
	 */
	public Node<T> getProximo() {
		return proximo;
	}

	public void setProximo(Node<T> proximo) {
		this.proximo = proximo;
	}

	public Node<T> getAnterior() {
		return anterior;
	}
	
	public void setAnterior(Node<T> anterior) {
		this.anterior = anterior;
	}

}
