package dados.repositorios;

import negocios.classesBasicas.Cliente;
import negocios.classesBasicas.Entrega;
import negocios.classesBasicas.Produto;
import negocios.exceptions.EmptyListException;
import negocios.exceptions.EntregaNaoEncontradaException;

/**
 * Classe que representa o repositorio de Entregas implementado em Lista.
 * 
 * @author lfs
 *
 */
public class RepositorioEntregasFila implements RepositorioEntregas, Iterator {

	private Node<Entrega> primeiroNoPend;
	private Node<Entrega> ultimoNoPend;
	private Node<Entrega> primeiroNoEnv;
	private Node<Entrega> ultimoNoEnv;
	
	private int indiceIterator;
	private Entrega[] iterator;

	public RepositorioEntregasFila() {
		this.primeiroNoPend = null;
		this.ultimoNoPend = null;
	}
	private RepositorioEntregasFila(Entrega[] itr) {
		this.indiceIterator = 0;
		this.iterator = itr;
	}

	/**
	 * Metodo que cria um novo No, adiciona a informacao(Entrega) nesse nó e
	 * liga ele à Lista.
	 * 
	 * @param entrega
	 *            Entrega - Entrega a ser listada
	 */
	public void inserir(Entrega entrega) {
		if (isEmpty()) {
			primeiroNoPend = ultimoNoPend = new Node<Entrega>(entrega);
		} else {
			Node<Entrega> aux = ultimoNoPend;
			Node<Entrega> novo = new Node<Entrega>(ultimoNoPend, entrega, null);
			ultimoNoPend = novo;
			if (aux == null) {
				primeiroNoPend = novo;
			} else {
				aux.setProximo(novo);
			}
		}
	}
	

	/**
	 * Metodo que recebe um ID e procura, na lista, um objeto No que contenha,
	 * como dado, um objeto Etrega que possua um ID igual ao que se procura. Ao
	 * encontrar, retorna o objeto.
	 * 
	 * @param id
	 *            String - ID da entrega a ser procurada
	 * @return resposta Entrega - Entrega que possui ID igual ao procurado.
	 */
	public Entrega procurar(String id) {
		Entrega resposta = null;
		boolean achou = false;
		Node<Entrega> atual = primeiroNoPend;
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
	 * Recebe um ID e uma Entrega, a fim de trocar as informacoes da entrega que
	 * possui aquele ID.
	 * 
	 * @param id
	 *            String - ID da entrega a ser atualizada.
	 * @param entrega
	 *            Entrega - Novo objeto atualizado a ser alocado no lugar do
	 *            antigo.
	 */
	public void atualizar(String id, Entrega entrega) {
		Node<Entrega> atual = this.procurarNode(id);
		atual.setDado(entrega);
	}

	/**
	 * Recebe um ID e, usando o metodo procurarNode(), encontra o Nó que contem
	 * aquela entrega como dado. Depois, simplesmente desvincula aquele nó da
	 * lista. Mais tarde, ele será pego pelo GarbageCollector
	 * 
	 * @param id
	 *            String - ID da entrega a ser removida da lista.
	 */
	public void remover(String id) {
		if (this.procurarNode(id) == null) {
			System.out.println("Nao existe na lista");
		} else {
			final Node<Entrega> atual = this.procurarNode(id);
			final Node<Entrega> proximo = atual.getProximo();
			final Node<Entrega> anterior = atual.getAnterior();

			if (anterior == null) {
				primeiroNoPend = proximo;
			} else {
				anterior.setProximo(proximo);
			}

			if (proximo == null) {
				ultimoNoPend = anterior;
			} else {
				proximo.setAnterior(anterior);
			}
		}
	}

	/**
	 * Método que retira a primeira entrega da lista de entregas pendentes e a insere na de enviadas.
	 */
	public void enviar() {
		Entrega removida = primeiroNoPend.getDado();

		if (primeiroNoPend == ultimoNoPend) {
			primeiroNoPend = ultimoNoPend = null;
		} else {
			primeiroNoPend = primeiroNoPend.getProximo();
		}
		this.inserirEnv(removida);
	}

	/**
	 * Recebe um obejto Entrega e procura, nó a nó, um objeto idêntico.
	 * Caso encontre, retorna true. Caso não, retorna false.
	 * @param entrega Entrega
	 * @return boolean - True se existir, False se não.
	 */
	public boolean existe(Entrega entrega) {
		Node<Entrega> atual = this.primeiroNoPend;

		while (atual != null) {
			if (atual.getDado().equals(entrega)) {
				return true;
			}
			atual = atual.getProximo();
		}
		return false;
	}
	
	private void inserirEnv(Entrega entrega) {
		if (isEmpty()) {
			primeiroNoEnv = ultimoNoEnv = new Node<Entrega>(entrega);
		} else {
			Node<Entrega> aux = ultimoNoEnv;
			Node<Entrega> novo = new Node<Entrega>(ultimoNoEnv, entrega, null);
			ultimoNoPend = novo;
			if (aux == null) {
				primeiroNoEnv = novo;
			} else {
				aux.setProximo(novo);
			}
		}
	}

	/**
	 * Metodo que retorna a informação de se a lista está ou não vazia.
	 * 
	 * @return boolean - True se tiver vazia, False se não.
	 */
	public boolean isEmpty() {
		return primeiroNoPend == null;
	}

	/**
	 * Metodo que recebe um ID e procura, nó a nó, um objeto Entrega que
	 * contenha aquele ID. Caso encontre, ele retorna o nó que contém a Entrega
	 * possuidora daquele ID.
	 * 
	 * @param id
	 *            String - ID da entrega que se procura
	 * @return Node<Entrega> - Nó que contem, como dado, a entrega procurada.
	 */
	private Node<Entrega> procurarNode(String id){
		Node<Entrega> resposta = null;
		boolean achou = false;
		Node<Entrega> atual = primeiroNoPend;
		while (atual != null && achou == false) {
			if (atual.getDado().getId().equals(id)) {
				resposta = atual;
				achou = true;
			}
			atual = atual.getProximo();
		}
		return resposta;
	}

	public Entrega next() {
		Entrega resposta = null;
		resposta = this.iterator[this.indiceIterator];
		this.indiceIterator++;
		return resposta;
	}

	public boolean hasNext() {
		if(indiceIterator >= iterator.length) {
			return false;
		}
		return this.iterator[this.indiceIterator] != null;
	}
	public Iterator<Entrega> getIteratorPendentes() throws EmptyListException {
		if (isEmpty()) {
			throw new EmptyListException();
		}
		int count = 0;
		Entrega[] itr = new Entrega[this.size()];
		Node<Entrega> atual = primeiroNoPend;
		while (atual != null) {
			itr[count] = atual.getDado().clone();
			atual = atual.getProximo();
			count++;
		}
		RepositorioEntregasFila iterator = new RepositorioEntregasFila(itr);
		return iterator;
	}
	
	public Iterator <Entrega> getIteratorEnviadas() throws EmptyListException {
		if (isEmpty()) {
			throw new EmptyListException();
		}
		int count = 0;
		Entrega[] itr = new Entrega[this.size()];
		Node<Entrega> atual = primeiroNoEnv;
		while (atual != null) {
			itr[count] = atual.getDado().clone();
			atual = atual.getProximo();
			count++;
		}
		RepositorioEntregasFila iterator = new RepositorioEntregasFila(itr);
		return iterator;
	}
	
	public int size() {
		int count = 0;
		Node<Entrega> atual = primeiroNoPend;
		while (atual != null) {
			count++;
			atual = atual.getProximo();
		}
		return count;
	}

}
