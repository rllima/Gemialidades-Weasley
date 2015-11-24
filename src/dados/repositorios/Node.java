package dados.repositorios;

public class Node<T> {
	
	private T dado;
	private Node<T> proximo;
	
	/* Construtor que cria no e prepara a lista pra receber o
	 * proximo objeto. É usado quando a lista está vazia
	 */
	public Node(T objeto){
		this(objeto, null);
	}

	// Construtor que cria um no com um objeto(dado) e referencia o proximo da lista.
	public Node(T objeto, Node<T> proximo) {
		this.dado = objeto;
		this.proximo = proximo;
	}

	// Retorna os dados daquele no
	public T getDado() {
		return dado;
	}

	// Retorna a referencia ao proximo da lista
	public Node<T> getProximo() {
		return proximo;
	}

	public void setProximo(Node<T> proximo) {
		this.proximo = proximo;
	}


}
