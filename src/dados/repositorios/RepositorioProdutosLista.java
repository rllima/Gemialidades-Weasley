package dados.repositorios;

import negocios.classesBasicas.*;
import negocios.exceptions.EmptyListException;

public class RepositorioProdutosLista implements RepositorioProdutos{

	private Node<Produto> primeiroNo;
	private Node<Produto> ultimoNo;
	private String nome;

	// Construtor que cria lista vazia com um nome.
	public RepositorioProdutosLista(String nome) {
		this.nome = nome;
		this.primeiroNo = this.ultimoNo = null;
	}

	// Insere objeto no fim da lista
	public void inserir(Produto produto)/* throws ProdutoJaExisteException */ {
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

	public void remover(String codigo) /*throws EmptyListException, ProdutoNaoEncontradoException*/ {
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

	public void atualizar(String codigo, Produto produto) /*throws EmptyListException, ProdutoNaoEncontradoException*/{
		Node<Produto> atual = this.procurarNode(codigo);
		atual.setDado(produto);
	}

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

}
