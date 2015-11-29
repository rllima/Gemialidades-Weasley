package dados.repositorios;

import negocios.classesBasicas.Cliente;
import negocios.classesBasicas.Produto;


public class RepositorioClientesLista implements RepositoriosClientes {
	
	private Node<Cliente> primeiroNo;
	private Node<Cliente> ultimoNo;
	private String nome;
	
	public RepositorioClientesLista(){
		this.nome = nome;
		this.primeiroNo = this.ultimoNo = null;
		
		
	}
	
	public void inserir(Cliente clientes) {
		if(isEmpty()) {
			primeiroNo = ultimoNo = new Node<Cliente>(clientes);
		} else {
			Node<Cliente> aux = ultimoNo;
			Node<Cliente> novo = new Node<Cliente>(ultimoNo, clientes, null);
			ultimoNo = novo;
			if (aux == null){
				primeiroNo = novo;
			} else {
				aux.setProximo(novo);
			}
		}
		
	}
	public Cliente procurar(String id) {
		Cliente resposta = null;
		boolean achou = false;
		Node<Cliente> atual = primeiroNo;
		while(atual != null && achou == false) {
			if(atual.getDado().getId().equals(id)){
				resposta = atual.getDado();
				achou = true;
			}
			atual = atual.getProximo();
		}
		return resposta;
	}
	public void atualizar(String id, Cliente clientes) {
		Node<Cliente> atual = this.procurarNode(id);
		atual.setDado(clientes);
		
		
	}
	public void remover(String id) {
		if(this.procurarNode(id) == null) {
			System.out.println("Nao existe na lista");
		} else {
			final Node<Cliente> atual = this.procurarNode(id);
			final Node<Cliente> proximo = atual.getProximo();
			final Node<Cliente> anterior = atual.getAnterior();

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
	public boolean isEmpty() {
		return primeiroNo == null;
	}
	private Node<Cliente> procurarNode(String id)/* throws EmptyListException*/ {
		Node<Cliente> resposta = null;
		boolean achou = false;
		Node<Cliente> atual = primeiroNo;
		while(atual != null && achou == false) {
			if(atual.getDado().getId().equals(id)){
				resposta = atual;
				achou = true;
			}
			atual = atual.getProximo();
		}
		return resposta;
	}
	public boolean existe(Cliente clientes) {
		Node<Cliente> atual = this.primeiroNo;

		while(atual != null) {
			if(atual.getDado().equals(clientes)) {
				return true;
			}
			atual = atual.getProximo();
		}
		return false;
	}
	public String toString() {
		String resposta = "";
		if(isEmpty()) {
			resposta = "Lista" + this.nome + "vazia";
		} else {
			Node<Cliente> atual = primeiroNo;
			// Enquanto nao chegar ao fim, coloca os dados de cada objeto na resposta
			while(atual != null) {
				resposta = resposta + atual.getDado().toString() + "\n\n";
				atual = atual.getProximo();
			}
		}
		return resposta;
	}


}
