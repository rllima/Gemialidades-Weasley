package dados.repositorios;

import negocios.exceptions.EmptyListException;

public class List<T> {

	private Node<T> primeiroNo;
	private Node<T> ultimoNo;
	private String nome;

	// Construtor que cria lista vazia com um nome.
	public List(String nome) {
		this.nome = nome;
		this.primeiroNo = this.ultimoNo = null;
	}

	// Insere objeto no comeco da lista
	public void inserirNoComeco(T objeto) {
		if (isEmpty()) {
			primeiroNo = ultimoNo = new Node<T>(objeto);
		} else {
			primeiroNo = new Node<T>(objeto, primeiroNo);
		}
	}

	// Insere objeto no fim da lista
	public void inserirNoFim(T objeto) {
		if(isEmpty()) {
			primeiroNo = ultimoNo = new Node<T>(objeto);
		} else {
			ultimoNo = new Node<T>(objeto);
		}
	}

	// Remove o primeiro objeto da lista
	public T removerPelaFrente() throws EmptyListException {
		T itemRemovido = null; // Objeto que vai ser retirado
		if(isEmpty()) {
			throw new EmptyListException();
		} else {
			itemRemovido = primeiroNo.getDado(); // Pega o objeto removido pra retornar
			if(primeiroNo == ultimoNo) { // Ou seja, se apenas ha um objeto {
				primeiroNo = ultimoNo = null; // Lista fica vazia
			} else {
				primeiroNo = primeiroNo.getProximo();
			}
		}
		return itemRemovido;
	}
	
	// Remove o ultimo objeto da lista
	public T removerUltimo() throws EmptyListException {
		T itemRemovido = null;
		if(isEmpty()) {
			throw new EmptyListException();
			
		} else { // Localiza o novo ultimo no da lista
			itemRemovido = ultimoNo.getDado();
			
			if(primeiroNo == ultimoNo) {
				primeiroNo = ultimoNo = null;
			} else { // Varre lista do primeiro ate encontrar o ultimo
				Node<T> atual = primeiroNo;
				
				while(atual.getProximo() != ultimoNo) {
					atual = atual.getProximo();
				} 
				ultimoNo = atual;
				atual.setProximo(null);
			}
		}
		return itemRemovido;
	}

	// Retorna true se a lista esta vazia. (Se o primeiro no esta vazio, a lista esta vazia.
	public boolean isEmpty() {
		return primeiroNo == null;
	}
	
	// Retorna uma string com os dados de todos objetos contidos na lista
	public String toString() {
		String resposta = "";
		if(isEmpty()) {
			resposta = "Lista" + this.nome + "vazia";
		} else {
			Node<T> atual = primeiroNo;
			// Enquanto nao chegar ao fim, coloca os dados de cada objeto na resposta
			while(atual != null) {
				resposta = resposta + atual.getDado().toString() + "\n";
				atual = atual.getProximo();
			}
		}
		return resposta;
	}
	
	

}