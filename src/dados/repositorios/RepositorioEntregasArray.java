package dados.repositorios;

import negocios.classesBasicas.Cliente;
import negocios.classesBasicas.Entrega;
import negocios.classesBasicas.Produto;
import negocios.exceptions.EntregaNaoEncontradaException;

/**
 * Classe que representa o repositorio de Entregas implementado em Array.
 * 
 * @author lfs
 *
 */
public class RepositorioEntregasArray implements RepositorioEntregas, Iterator {

	private Entrega[] repositorioEntregas;
	private Entrega[] repositorioEnviadas;
	private int indice;
	private int indiceEnviadas;
	private int indiceIterator;
	

	public RepositorioEntregasArray() {
		this.repositorioEntregas = new Entrega[100];
		this.indice = 0;
		this.repositorioEnviadas = new Entrega[100];
		this.indiceEnviadas = 0;
	}
	private RepositorioEntregasArray(Entrega[] itr) {
		this.repositorioEntregas = itr;
		this.indiceIterator = 0;
	}


	/**
	 * Insere, no repositorio, uma Entrega
	 * 
	 * @param entrega
	 *            Entrega - Entrega a ser inserida;
	 */
	public void inserir(Entrega entrega) {

		if (indice > this.repositorioEntregas.length) {
			Entrega[] aux = new Entrega[repositorioEntregas.length * 2];
			for (int i = 0; i < indice; i++) {
				aux[i] = this.repositorioEntregas[i];
			}
			aux[indice] = entrega;
			repositorioEntregas = aux;
		} else {
			this.repositorioEntregas[indice] = entrega;
		}
		indice++;
	}

	/**
	 * Passa uma entrega para o array de entregas enviadas, utilizado no método
	 * enviar()
	 * 
	 * @param entrega
	 *            Entrega - Entrega que será passada pro array de entregas
	 *            enviadas.
	 */
	public void inserirEntregues(Entrega entrega) {
		if (indiceEnviadas > this.repositorioEnviadas.length) {
			Entrega[] aux = new Entrega[repositorioEnviadas.length * 2];
			for (int i = 0; i < indiceEnviadas; i++) {
				aux[i] = this.repositorioEnviadas[i];
			}
			aux[indiceEnviadas] = entrega;
			repositorioEnviadas = aux;
		} else {
			this.repositorioEnviadas[indiceEnviadas] = entrega;
		}
		indiceEnviadas++;
	}

	/**
	 * Recebe um ID e procura, no repositorio, a entrega referente aquele ID.
	 * @return entrega Entrega - Entrega procurada a ser retornada (Caso encontrada)
	 * @param id String - Id do objeto que se procura
	 */
	public Entrega procurar(String id) {
		Entrega resposta = null;
		int indice = this.buscarCodigo(id);
		if (indice > -1) {
			resposta = this.repositorioEntregas[indice];
		}
		return resposta;
	}

	/**
	 * Atualiza uma entrega a partir de seu ID
	 * @param id String - Id do objeto a ser atualizado
	 * @param cliente Cliente - Objeto atualizado a ser inserido no lugar do antigo
	 */
	public void atualizar(String id, Entrega entrega) {
		int indice = this.buscarCodigo(id);
		if (indice != -1) {
			this.repositorioEntregas[indice] = entrega;
		}

	}

	/**
	 * Remove um objeto a partir de seu ID
	 * @param id - ID do objeto a ser removido
	 */
	public void remover(String id) {
		int b = this.buscarCodigo(id);
		if (b != -1) {
			for (int i = b; i < indice; i++) {
				this.repositorioEntregas[i] = this.repositorioEntregas[i + 1];
			}
		}
	}

	/**
	 * Remove Entrega do comeco e utiliza do método inserirEnviadas() para inserir essa
	 * entrega no array de enviadas.
	 */
	public void enviar() {
		Entrega enviada = this.repositorioEntregas[0];
		String codigo = enviada.getId();
		this.remover(codigo);
		this.inserirEntregues(enviada);
		indice = indice - 1;

	}
	/**
	 * Recebe um id e retorna a localizacao(indice) do objeto que aquele id identifica.	
	 * @param id String - Id do objeto que se quer encontrar
	 * @return resposta int - Indice do objeto referente ao ID
	 */
	private int buscarCodigo(String id) {
		int resposta = -1;
		boolean achou = false;
		for (int i = 0; i < this.indice && !achou; i++) {
			Entrega aux = this.repositorioEntregas[i];
			if (aux.getId().equalsIgnoreCase(id)) {
				resposta = i;
				achou = true;
			}
		}
		return resposta;

	}
	
	public boolean isEmpty() {
		return this.repositorioEntregas[0] == null; 
	}

	public Entrega next() {
		Entrega resposta = null;
		resposta = this.repositorioEntregas[this.indiceIterator];
		this.indiceIterator++;
		return resposta;
		
	}

	public boolean hasNext() {
		if(indiceIterator >= repositorioEntregas.length) {
			return false;
		}
		return this.repositorioEntregas[this.indiceIterator] != null;
		
	}
	
	public Iterator<Entrega> getIteratorPendentes() {
		Entrega[] aux = new Entrega[repositorioEntregas.length];
		for(int i = 0; i < this.indice; i++) {
			aux[i] = this.repositorioEntregas[i].clone();
		}
		RepositorioEntregasArray iterator = new RepositorioEntregasArray(aux);
		return iterator;
	}
	public Iterator<Entrega> getIteratorEnviadas() {
		Entrega[] aux = new Entrega[repositorioEnviadas.length];
		for(int i = 0; i < this.indice; i++) {
			aux[i] = this.repositorioEnviadas[i].clone();
		}
		RepositorioEntregasArray iterator = new RepositorioEntregasArray(aux);
		return iterator;
	}

}
