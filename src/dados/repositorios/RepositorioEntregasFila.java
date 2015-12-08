package dados.repositorios;

import negocios.classesBasicas.Entrega;
import negocios.classesBasicas.Produto;
import negocios.exceptions.EntregaNaoEncontradaException;

/**
 * Classe que representa o repositorio de Entregas implementado em Lista.
 * 
 * @author lfs
 *
 */
public class RepositorioEntregasFila implements RepositorioEntregas {

	private Node<Entrega> primeiroNo;
	private Node<Entrega> ultimoNo;

	public RepositorioEntregasFila() {
		this.primeiroNo = null;
		this.ultimoNo = null;
	}

	/**
	 * Metodo que cria um novo No, adiciona a informacao(Entrega) nesse n� e
	 * liga ele � Lista.
	 * 
	 * @param entrega
	 *            Entrega - Entrega a ser listada
	 */
	public void inserir(Entrega entrega) {
		if (isEmpty()) {
			primeiroNo = ultimoNo = new Node<Entrega>(entrega);
		} else {
			Node<Entrega> aux = ultimoNo;
			Node<Entrega> novo = new Node<Entrega>(ultimoNo, entrega, null);
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
		Node<Entrega> atual = primeiroNo;
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
	 * Recebe um ID e, usando o metodo procurarNode(), encontra o N� que contem
	 * aquela entrega como dado. Depois, simplesmente desvincula aquele n� da
	 * lista. Mais tarde, ele ser� pego pelo GarbageCollector
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

	// NAO FINALIZADO
	public void enviar() {
		Entrega removida = primeiroNo.getDado();

		if (primeiroNo == ultimoNo) {
			primeiroNo = ultimoNo = null;
		} else {
			primeiroNo = primeiroNo.getProximo();
		}

	}

	/**
	 * Recebe um obejto Entrega e procura, n� a n�, um objeto id�ntico.
	 * Caso encontre, retorna true. Caso n�o, retorna false.
	 * @param entrega Entrega
	 * @return boolean - True se existir, False se n�o.
	 */
	public boolean existe(Entrega entrega) {
		Node<Entrega> atual = this.primeiroNo;

		while (atual != null) {
			if (atual.getDado().equals(entrega)) {
				return true;
			}
			atual = atual.getProximo();
		}
		return false;
	}

	/**
	 * Metodo que retorna a informa��o de se a lista est� ou n�o vazia.
	 * 
	 * @return boolean - True se tiver vazia, False se n�o.
	 */
	public boolean isEmpty() {
		return primeiroNo == null;
	}

	/**
	 * Metodo que recebe um ID e procura, n� a n�, um objeto Entrega que
	 * contenha aquele ID. Caso encontre, ele retorna o n� que cont�m a Entrega
	 * possuidora daquele ID.
	 * 
	 * @param id
	 *            String - ID da entrega que se procura
	 * @return Node<Entrega> - N� que contem, como dado, a entrega procurada.
	 */
	private Node<Entrega> procurarNode(String id){
		Node<Entrega> resposta = null;
		boolean achou = false;
		Node<Entrega> atual = primeiroNo;
		while (atual != null && achou == false) {
			if (atual.getDado().getId().equals(id)) {
				resposta = atual;
				achou = true;
			}
			atual = atual.getProximo();
		}
		return resposta;
	}

}
