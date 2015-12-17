package dados.repositorios;

import negocios.classesBasicas.Entrega;
import negocios.classesBasicas.Produto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

/**
 * Classe que representa o repositorio de Entregas implementado em Excel(Dados
 * continuos)
 * 
 * @author lfs
 *
 */
public class RepositorioEntregasExcel implements RepositorioEntregas, Iterator {

	private HSSFWorkbook workbook;
	private Sheet planilhaPendentes;
	private Sheet planilhaEnviadas;
	private int indiceIterator;
	private Entrega[] iterator;

	public RepositorioEntregasExcel(HSSFWorkbook workbook) {
		this.workbook = workbook;
		if (workbook.getSheet("Entregas - Pendentes") != null) {
			this.planilhaPendentes = workbook.getSheet("Entregas - Pendentes");
		} else {
			this.planilhaPendentes = workbook.createSheet("Entregas - Pendentes");
		}
		if (workbook.getSheet("Entregas - Enviadas") != null) {
			this.planilhaEnviadas = workbook.getSheet("Entregas - Enviadas");
		} else {
			this.planilhaEnviadas = workbook.createSheet("Entregas - Enviadas");
		}
	}
	private RepositorioEntregasExcel(Entrega[] itr) {
		this.indiceIterator = 0;
		this.iterator = itr;
	}

	/**
	 * Recebe e insere uma entrega na planilha a partir da criacao de uma linha para a mesma. 
	 * Cada celula da linha corresponde a um dado(atributo) de Entrega.
	 * Dessa forma, dentro do repositorio, cada linha armazena os dados de uma unico objeto Entrega.
	 * @param entrega Entrega - Objeto a ser inserido
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void inserir(Entrega entrega) throws FileNotFoundException, IOException {
		Cell celula;
		Row linha = planilhaPendentes.createRow(getIndicePend());
		celula = linha.createCell(1);
		celula.setCellValue(entrega.getId());
		celula = linha.createCell(2);
		celula.setCellValue(entrega.getIdCliente());
		celula = linha.createCell(3);
		celula.setCellValue(entrega.getIdProduto());
		
		setIndicePend(getIndicePend() + 1);
	}

	/**
	 * Recebe um objeto Entrega e o insere na planilha de enviadas.
	 * Utilizado no método enviar().
	 * @param entrega Entrega
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void inserirEnviadas(Entrega entrega) throws FileNotFoundException, IOException {
		Cell celula;
		Row linha = planilhaEnviadas.createRow(getIndiceEnv());
		celula = linha.createCell(1);
		celula.setCellValue(entrega.getId());
		celula = linha.createCell(2);
		celula.setCellValue(entrega.getIdCliente());
		celula = linha.createCell(3);
		celula.setCellValue(entrega.getIdProduto());
		
		setIndiceEnv(getIndiceEnv() + 1);
	}

	/** 
	 * Metodo que recebe um ID e procura, na coluna referente ao id das Entregas,
	 * um id que se iguale a esse, a fim de retornar os dados da devida Entrega
	 * em forma de objeto.
	 * Ao encontrar o ID, o metodo capta os dados contidos naquela linha para 'montar'
	 * o objeto Entrega a partir daquela linha, e retorna esse objeto.
	 * @param id String - Id do objeto a ser procurado
	 * @return entrega Entrega - Objeto Entrega
	 */
	public Entrega procurar(String id) {
		String celula = "";
		Row linha = null;
		Entrega resposta = null;

		if(!isEmpty()) {
			for (int i = 0; i < this.getIndicePend(); i++) {
				linha = planilhaPendentes.getRow(i);
				celula = linha.getCell(1).toString();
				if (id.equalsIgnoreCase(celula)) {
					String idCliente = linha.getCell(2).toString();
					String idProduto = linha.getCell(3).toString();
					resposta = new Entrega(id, idCliente, idProduto);

				}
			}
		}

		return resposta;
	}

	/**
	 * Metodo que atualiza um cliente do repositorio com novos dados inseridos pelo usuario.
	 * @param id String - ID do objeto a ser atualizado
	 * @param clientes Cliente - Objeto com dados atualizados a ser inserido no lugar do antigo.
	 */
	public void atualizar(String id, Entrega entrega) {
		int aux = this.procurarLinha(id);
		planilhaPendentes.getRow(aux).getCell(1).setCellValue(entrega.getId());
		planilhaPendentes.getRow(aux).getCell(2).setCellValue(entrega.getIdCliente());
		planilhaPendentes.getRow(aux).getCell(3).setCellValue(entrega.getIdProduto());

	}

	public void remover(String id) throws FileNotFoundException, IOException {
		int posicao = this.procurarLinha(id);
		Row aux = planilhaPendentes.getRow(posicao);
		planilhaPendentes.removeRow(aux);
		if(getIndicePend() >= 1 && !(posicao + 1 == getIndicePend())) {
		planilhaPendentes.shiftRows((posicao + 1), (getIndicePend() - 1), -1);
		}
		setIndicePend(getIndicePend() - 1);
	}

	/**
	 * Remove uma entrega do comeco da lista de pendentes e a coloca na lista de enviadas,
	 * a partir do metodo inserirEnviadas()
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void enviar() throws FileNotFoundException, IOException {
		Row aux = planilhaPendentes.getRow(0);
		String id = planilhaPendentes.getRow(0).getCell(1).toString();
		String idCliente = planilhaPendentes.getRow(0).getCell(2).toString();
		String idProduto = planilhaPendentes.getRow(0).getCell(3).toString();
		Entrega temp = new Entrega(id, idCliente, idProduto);
		this.inserirEnviadas(temp);
		this.remover(id);

	}

	/**
	 * Metodo que retorna a linha(Indice) na qual se encontra uma Entrega especifica no
	 * repositorio de Pendentes, a partir do seu ID.
	 * @param id String - id da entrega a ser procurada no repositorio
	 * @return indice int - posicao da linha na qual se encontram os dados dda entrega procurada.
	 */
	public int procurarLinha(String codigo) {
		String celula = "";
		int posicao = -1;
		Row linha = null;
		Produto resposta = null;

		for (int i = 0; i < getIndicePend(); i++) {
			linha = planilhaPendentes.getRow(i);
			celula = linha.getCell(1).toString();
			if (codigo.equalsIgnoreCase(celula)) {
				posicao = i;
			}

		}
		return posicao;
	}
	
	public int getIndicePend() {
		Sheet indices = workbook.getSheet("Indices");
		int size = (int)indices.getRow(2).getCell(1).getNumericCellValue();
		return size;
	}
	
	public int getIndiceEnv() {
		Sheet indices = workbook.getSheet("Indices");
		int size = (int)indices.getRow(3).getCell(1).getNumericCellValue();
		return size;
	}
	
	public void setIndicePend(int indice) throws FileNotFoundException, IOException {
		Sheet indices = workbook.getSheet("Indices");
		indices.getRow(2).getCell(1).setCellValue(indice);
		workbook.write(new FileOutputStream(new File("planilha.xls")));
	}
	
	public void setIndiceEnv(int indice) throws FileNotFoundException, IOException {
		Sheet indices = workbook.getSheet("Indices");
		indices.getRow(3).getCell(1).setCellValue(indice);
		workbook.write(new FileOutputStream(new File("planilha.xls")));
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

	public Iterator<Entrega> getIteratorPendentes() {
		Entrega[] itr = new Entrega[this.getIndicePend()];
		for(int i = 0; i < getIndicePend(); i++) {
			itr[i] = this.clone(planilhaPendentes.getRow(i), planilhaPendentes);
		}
		RepositorioEntregasExcel iterator = new RepositorioEntregasExcel(itr);
		return iterator;
	}

	public Iterator<Entrega> getIteratorEnviadas() {
		Entrega[] itr = new Entrega[this.getIndiceEnv()];
		for(int i = 0; i < getIndiceEnv(); i++) {
			itr[i] = this.clone(planilhaEnviadas.getRow(i), planilhaEnviadas);
		}
		RepositorioEntregasExcel iterator = new RepositorioEntregasExcel(itr);
		return iterator;
	}


	private Entrega clone(Row linha, Sheet planilha) {
		String id = planilha.getRow(0).getCell(1).toString();
		String idCliente = planilha.getRow(0).getCell(2).toString();
		String idProduto = planilha.getRow(0).getCell(3).toString();
		Entrega temp = new Entrega(id, idCliente, idProduto);
		return temp;
	}
	public boolean isEmpty() {
		
		return this.getIndicePend() == 0;
	}


}
