package dados.repositorios;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.*;

import org.apache.poi.ss.usermodel.Workbook;

import negocios.classesBasicas.*;

/**
 * Classe que representa o repositorio de clientes implementado em Excel(Dados continuos)
 * @author lfs
 *
 */
public class RepositorioClientesExcel implements RepositoriosClientes, Iterator {

	private HSSFWorkbook workbook;
	private Sheet planilha;
	private File excel;
	private int indice;
	private int indiceIterator;
	private Cliente[] iterator;

	public RepositorioClientesExcel (HSSFWorkbook workbook) throws FileNotFoundException{
		if(workbook.getSheet("Clientes") != null) {
			this.planilha = workbook.getSheet("Clientes");
		} else {
			this.planilha = workbook.createSheet("Clientes");
		}
		this.indice = workbook.getSheet("Clientes").getPhysicalNumberOfRows();
	}
	
	private RepositorioClientesExcel(Cliente[] itr) {
		this.indiceIterator = 0;
		this.iterator = itr;
	}

	/**
	 * Recebe e insere um cliente na planilha a partir da criacao de uma linha para o mesmo. 
	 * Cada celula da linha corresponde a um dado(atributo) de Cliente.
	 * Dessa forma, dentro do repositorio, cada linha armazena os dados de um unico cliente.
	 * @param clientes Cliente - Objeto Cliente a ser inserido
	 */
	public void inserir(Cliente clientes) throws IOException {
		Cell celula;
		Row linha = planilha.createRow(indice);
		celula = linha.createCell(1);
		celula.setCellValue(clientes.getNome());
		celula = linha.createCell(2);
		celula.setCellValue(clientes.getIdade());
		celula = linha.createCell(3);
		celula.setCellValue(clientes.getId());
		celula = linha.createCell(4);
		celula.setCellValue(clientes.getEndereco().getCidade());
		celula = linha.createCell(5);
		celula.setCellValue(clientes.getEndereco().getLogradouro());
		celula = linha.createCell(6);
		celula.setCellValue(clientes.getEndereco().getNumero());
		celula = linha.createCell(7);
		celula.setCellValue(clientes.getEndereco().getCep());
		celula = linha.createCell(8);
		celula.setCellValue(clientes.getEndereco().getComplemento());
		celula = linha.createCell(9);
		celula.setCellValue(clientes.getSenha());

		indice++;
		this.write();
	}

	/** 
	 * Metodo que recebe um ID e procura, na coluna referente ao id dos Clientes,
	 * um id que se iguale a esse, a fim de retornar os dados do devido Cliente
	 * em forma de objeto.
	 * Ao encontrar o ID, o metodo capta os dados contidos naquela linha para 'montar'
	 * o objeto Cliente a partir daquela linha, e retorna esse objeto.
	 * @param id String - Id do cliente a ser procurado
	 * @return cliente Clientes - Objeto Cliente
	 */
	public Cliente procurar(String id) {
		String celula = "";
		Row linha = null;
		Cliente resposta = null;
		Endereco endereco = null;
		for(int i = 0; i <= this.indice; i++){
			linha = planilha.getRow(i);
			celula = linha.getCell(3).toString();
			if(id.equalsIgnoreCase(celula)){
				String nome = linha.getCell(1).toString();
				String idade =  linha.getCell(2).toString();
				String id1 = linha.getCell(3).toString();
				String cidade = linha.getCell(4).toString();
				String logradouro = linha.getCell(5).toString();
				String numero = linha.getCell(6).toString();
				String cep = linha.getCell(7).toString();
				String cpmt = linha.getCell(8).toString();
				String senha = linha.getCell(9).toString();
				endereco = new Endereco(cidade, logradouro, numero, cep, cpmt);
				resposta = new Cliente(nome,idade, endereco, id1, senha);

			}
		}
		return resposta;
	}
	
	/**
	 * Metodo que atualiza um cliente do repositorio com novos dados inseridos pelo usuario.
	 * @param id String - ID do objeto a ser atualizado
	 * @param clientes Cliente - Objeto com dados atualizados a ser inserido no lugar do antigo.
	 */

	public void atualizar(String id, Cliente clientes) throws IOException {
		int aux = this.procurarLinha(id);
		planilha.getRow(aux).getCell(1).setCellValue(clientes.getNome());
		planilha.getRow(aux).getCell(3).setCellValue(id);
		planilha.getRow(aux).getCell(2).setCellValue(clientes.getIdade());
		planilha.getRow(aux).getCell(4).setCellValue(clientes.getEndereco().getCidade());
		planilha.getRow(aux).getCell(5).setCellValue(clientes.getEndereco().getLogradouro());
		planilha.getRow(aux).getCell(5).setCellValue(clientes.getEndereco().getNumero());
		planilha.getRow(aux).getCell(6).setCellValue(clientes.getEndereco().getCep());
		planilha.getRow(aux).getCell(7).setCellValue(clientes.getEndereco().getComplemento());

		this.write();


	}

	/**
	 * Remove um cliente(Linha) do repositorio a partir de seu ID.
	 * Ele usa o metodo procurar linha pra encontrar a localização do cliente no repositorio.
	 * Depois, remove aquela linha e realoca todas as outras abaixo dela uma posicao acima.
	 * @param id String - id do Cliente a ser removido do repositorio.
	 */
	public void remover(String id) throws IOException {
		int posicao = this.procurarLinha(id);
		Row aux = planilha.getRow(posicao);
		planilha.removeRow(aux);
		planilha.shiftRows((posicao+1), (indice-1), -1);
		indice--;
		this.write();

	}
	
	/**
	 * Metodo que retorna a linha(Indice) na qual se encontra um Cliente especifico, a partir
	 * de seu ID
	 * @param id String - id do cliente a ser procurado no repositorio
	 * @return indice int - posicao da linha na qual se encontram os dados do cliente procurado.
	 */
	public int procurarLinha(String id){
		String celula = "";
		int posicao = -1;
		Row linha = null;
		Cliente resposta = null;

		for(int i = 0; i <= this.indice; i++) {
			linha = planilha.getRow(i);
			celula = linha.getCell(3).toString();
			if (id.equalsIgnoreCase(celula)){
				posicao = i;
			}

		}
		return posicao;

	}
	
	/**
	 * Metodo que escreve as informacoes adicionadas ao workbook no arquivo planilha.
	 * Basicamente, ele salva as novas informações após as mesmas serem incluidas no repositorio.
	 * @throws IOException
	 */
	
	private void write() throws IOException {
		FileOutputStream saidaArquivo = new FileOutputStream(excel);
		this.workbook.write(saidaArquivo);
		saidaArquivo.close();
	}
	
	public int size() {
		return planilha.getPhysicalNumberOfRows();
	}
	
	public Iterator<Cliente> getIterator() {
		Cliente[] itr = new Cliente[this.size()];
		for(int i = 0; i < size(); i++) {
			itr[i] = this.clone(planilha.getRow(i));
		}
		RepositorioClientesExcel iterator = new RepositorioClientesExcel(itr);
		return iterator;
	}

	public Cliente next() {
		Cliente resposta = null;
		resposta = this.iterator[this.indiceIterator];
		this.indiceIterator++;
		return resposta;
	}

	public boolean hasNext() {
		return this.iterator[this.indiceIterator + 1] != null;
	}
	
	private Cliente clone(Row linha) {
		String nome = linha.getCell(1).toString();
		String idade =  linha.getCell(2).toString();
		String id1 = linha.getCell(3).toString();
		String cidade = linha.getCell(4).toString();
		String logradouro = linha.getCell(5).toString();
		String numero = linha.getCell(6).toString();
		String cep = linha.getCell(7).toString();
		String cpmt = linha.getCell(8).toString();
		String senha = linha.getCell(9).toString();
		Endereco endereco = new Endereco(cidade, logradouro, numero, cep, cpmt);
		return new Cliente(nome, idade, endereco, id1, senha);
	}

}


	
