package dados.repositorios;

import negocios.classesBasicas.Entrega;
import negocios.classesBasicas.Produto;
import negocios.exceptions.EntregaNaoEncontradaException;

public class RepositorioEntregasFila implements RepositorioEntregas {

	private Node<Entrega> primeiroNo;
	private Node<Entrega> ultimoNo;
    
	public RepositorioEntregasFila() {
		this.primeiroNo = null;
		this.ultimoNo = null;
	}

	// Insere no final da fila
	public void inserir(Entrega entrega) {
		if(isEmpty()) {
			primeiroNo = ultimoNo = new Node<Entrega>(entrega);
		} else {
			Node<Entrega> aux = ultimoNo;
			Node<Entrega> novo = new Node<Entrega>(ultimoNo, entrega, null);
			ultimoNo = novo;
			if (aux == null){
				primeiroNo = novo;
			} else {
				aux.setProximo(novo);
			}
		}
	}

	// Recebe ID e retorna objeto referente ao id, caso exista
	public Entrega procurar(String id) /* throws EmptyListException, EntregaNaoEncontradaException*/ {
		Entrega resposta = null;
		boolean achou = false;
		Node<Entrega> atual = primeiroNo;
		while(atual != null && achou == false) {
			if(atual.getDado().getId().equals(id)){
				resposta = atual.getDado();
				achou = true;
			}
			atual = atual.getProximo();
		}
		return resposta;
	}

	public void atualizar(String id, Entrega entrega) {
		Node<Entrega> atual = this.procurarNode(id);
		atual.setDado(entrega);
	}
	
	public void remover(String id) {
		if(this.procurarNode(id) == null) {
			System.out.println("Nao existe na lista");
		} else {
			final Node<Entrega> atual = this.procurarNode(id);
			final Node<Entrega> proximo = atual.getProximo();
			final Node<Entrega> anterior = atual.getAnterior();

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

	// Remove Entrega do comeco e retorna o objeto p/ ser inserido na lista de enviados
	public void enviar(){
		Entrega removida = primeiroNo.getDado();
		
		if(primeiroNo == ultimoNo) {
			primeiroNo = ultimoNo = null;
		} else {
			primeiroNo = primeiroNo.getProximo();
		}
		
		
	}

	public boolean existe(Entrega entrega) {
		Node<Entrega> atual = this.primeiroNo;

		while(atual != null) {
			if(atual.getDado().equals(entrega)) {
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

	// Retorna o no em que se encontra o produto do codigo passado
	private Node<Entrega> procurarNode(String id)/* throws EmptyListException*/ {
		Node<Entrega> resposta = null;
		boolean achou = false;
		Node<Entrega> atual = primeiroNo;
		while(atual != null && achou == false) {
			if(atual.getDado().getId().equals(id)){
				resposta = atual;
				achou = true;
			}
			atual = atual.getProximo();
		}
		return resposta;
	}


}
