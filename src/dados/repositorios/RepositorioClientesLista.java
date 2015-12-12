package dados.repositorios;

import negocios.classesBasicas.Cliente;
import negocios.classesBasicas.Produto;

/**
 * Classe que representa o repositorio de clientes implementado em Lista.
 * 
 * @author lfs
 *
 */
public class RepositorioClientesLista implements RepositoriosClientes {

	private Node<Cliente> primeiroNo;
	private Node<Cliente> ultimoNo;
	private String nome;
	private int indiceIterator;

	public RepositorioClientesLista() {
		this.nome = nome;
		this.primeiroNo = this.ultimoNo = null;
	}
	
	private RepositorioClientesLista(Cliente[] itr) {
		this.indiceIterator = 0;
		
	}

	/**
	 * Metodo que cria um novo No, adiciona a informacao(Cliente) nesse n� e
	 * liga ele � Lista.
	 * 
	 * @param clientes
	 *            Cliente - Cliente a ser inserido na Lista.
	 */
	public void inserir(Cliente clientes) {
		if (isEmpty()) {
			primeiroNo = ultimoNo = new Node<Cliente>(clientes);
		} else {
			Node<Cliente> aux = ultimoNo;
			Node<Cliente> novo = new Node<Cliente>(ultimoNo, clientes, null);
			ultimoNo = novo;
			if (aux == null) {
				primeiroNo = novo;
			} else {
				aux.setProximo(novo);
			}
		}

	}

	/**
	 * Metodo que recebe um ID e procura, na lista, um objeto No que contenha,
	 * como dado, um objeto Cliente que possua um ID igual ao que se procura. Ao
	 * encontrar, retorna o objeto Cliente.
	 * 
	 * @param id
	 *            String - ID do cliente a ser procurado
	 * @return resposta Cliente - Cliente que possui ID igual ao procurado.
	 */
	public Cliente procurar(String id) {
		Cliente resposta = null;
		boolean achou = false;
		Node<Cliente> atual = primeiroNo;
		while (atual != null && achou == false) {
			if (atual.getDado().getId().equals(id)) {
				resposta = atual.getDado();
				achou = true;
			}
			atual = atual.getProximo();
		}
		return resposta;
	}
	
	/**
	 * Recebe um ID e um Cliente, a fim de trocar as informacoes do cliente que possui aquele ID.
	 * @param id String - ID do cliente a ser atualizado.
	 * @param clientes Cliente - Novo objeto atualizado a ser alocado no lugar do antigo.
	 */
	public void atualizar(String id, Cliente clientes) {
		Node<Cliente> atual = this.procurarNode(id);
		atual.setDado(clientes);

	}

	/**
	 * Recebe um ID e, usando o metodo procurarNode(), encontra o N� que contem aquele cliente como dado.
	 * Depois, simplesmente desvincula aquele n� da lista.
	 * Mais tarde, ele ser� pego pelo GarbageCollector
	 * @param id String - ID do cliente a ser removido da lista.
	 */
	public void remover(String id) {
		if (this.procurarNode(id) == null) {
			System.out.println("Nao existe na lista");
		} else {
			final Node<Cliente> atual = this.procurarNode(id);
			final Node<Cliente> proximo = atual.getProximo();
			final Node<Cliente> anterior = atual.getAnterior();

			if (anterior == null) {
				primeiroNo = proximo;
			} else {
				anterior.setProximo(proximo);
			}

			if (proximo == null) {
				ultimoNo = anterior;
			} else {
				proximo.setAnterior(anterior);
			}
		}

	}

	/**
	 * Metodo que retorna a informa��o de se a lista est� ou n�o vazia.
	 * @return boolean - True se tiver vazia, False se n�o.
	 */
	public boolean isEmpty() {
		return primeiroNo == null;
	}

	/**
	 * Metodo que recebe um ID e procura, n� a n�, um objeto Cliente que contenha aquele ID.
	 * Caso encontre, ele retorna o n� que cont�m o Cliente possuidor daquele ID.
	 * @param id String - ID do cliente que se procura
	 * @return Node<Cliente> - N� que contem, como dado, o cliente procurado.
	 */
	private Node<Cliente> procurarNode(String id) {
		Node<Cliente> resposta = null;
		boolean achou = false;
		Node<Cliente> atual = primeiroNo;
		while (atual != null && achou == false) {
			if (atual.getDado().getId().equals(id)) {
				resposta = atual;
				achou = true;
			}
			atual = atual.getProximo();
		}
		return resposta;
	}

	/**
	 * Recebe um obejto Cliente e procura, n� a n�, um objeto id�ntico.
	 * Caso encontre, retorna true. Caso n�o, retorna false.
	 * @param clientes
	 * @return boolean - True se existir, False se n�o.
	 */
	public boolean existe(Cliente clientes) {
		Node<Cliente> atual = this.primeiroNo;

		while (atual != null) {
			if (atual.getDado().equals(clientes)) {
				return true;
			}
			atual = atual.getProximo();
		}
		return false;
	}

	public String toString() {
		String resposta = "";
		if (isEmpty()) {
			resposta = "Lista" + this.nome + "vazia";
		} else {
			Node<Cliente> atual = primeiroNo;
			// Enquanto nao chegar ao fim, coloca os dados de cada objeto na
			// resposta
			while (atual != null) {
				resposta = resposta + atual.getDado().toString() + "\n\n";
				atual = atual.getProximo();
			}
		}
		return resposta;
	}
	
	public Iterator
	
	

}
