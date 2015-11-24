package dados.repositorios;

import negocios.classesBasicas.*;
import negocios.exceptions.EmptyListException;

public class RepositorioProdutosList implements RepositorioProdutos{

	private Node<Produto> primeiroNo;
	private Node<Produto> ultimoNo;
	private String nome;

	// Construtor que cria lista vazia com um nome.
	public RepositorioProdutosList(String nome) {
		this.nome = nome;
		this.primeiroNo = this.ultimoNo = null;
	}

	// Insere objeto no fim da lista
	public void inserir(Produto produto) {
		if(isEmpty()) {
			primeiroNo = ultimoNo = new Node<Produto>(produto);
		} else {
			ultimoNo = new Node<Produto>(produto);
		}
	}

	// Procura objeto a partir do codigo
	public Produto procurar(String codigo)/* throws EmptyListException, ProdutoNaoEncontradoException*/ {
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
	
	public void remover(String codigo) /*Throws ProdutoNaoEncontradoException*/ {
		Node<Produto> anterior = this.procurarNode(codigo).getAnterior();
		Node<Produto> atual = anterior.getProximo();
		Node<Produto> proximo = atual.getProximo();
		
		anterior.setProximo(proximo);
		proximo.setAnterior(anterior);
	}
	
	public void atualizar(String codigo, Produto produto){
		
	}
	
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

	// Insere objeto no comeco da lista
	public void inserirNoComeco(Produto produto) {
		if (isEmpty()) {
			primeiroNo = ultimoNo = new Node<Produto>(produto);
		} else {
			primeiroNo = new Node<Produto>(produto, primeiroNo);
		}
	}


	// Remove o primeiro objeto da lista
	public Produto removerPelaFrente() throws EmptyListException {
		Produto itemRemovido = null; // Objeto que vai ser retirado
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
	public Produto removerUltimo() throws EmptyListException {
		Produto itemRemovido = null;
		if(isEmpty()) {
			throw new EmptyListException();

		} else { // Localiza o novo ultimo no da lista
			itemRemovido = ultimoNo.getDado();

			if(primeiroNo == ultimoNo) {
				primeiroNo = ultimoNo = null;
			} else { // Varre lista do primeiro ate encontrar o ultimo
				Node<Produto> atual = primeiroNo;

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
			Node<Produto> atual = primeiroNo;
			// Enquanto nao chegar ao fim, coloca os dados de cada objeto na resposta
			while(atual != null) {
				resposta = resposta + atual.getDado().toString() + "\n\n";
				atual = atual.getProximo();
			}
		}
		return resposta;
	}
	
	// Retorna o no em que se encontra o produto do codigo passado
	public Node<Produto> procurarNode(String codigo)/* throws EmptyListException*/ {
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

}
