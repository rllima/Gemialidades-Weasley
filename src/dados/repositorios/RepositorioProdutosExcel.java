package dados.repositorios;

import negocios.classesBasicas.*;
import negocios.exceptions.EmptyListException;

import java.io.*;

import org.apache.poi.ss.usermodel.*;

/**
 * Classe que representa o repositorio de Produtos implementado em Excel(Dados
 * continuos)
 * 
 * @author lfs
 *
 */
public class RepositorioProdutosExcel implements RepositorioProdutos, Iterator {

	private Sheet planilha;
	private int indice;
	private int indiceIterator;
	private Produto[] iterator;

	public RepositorioProdutosExcel(Workbook workbook) {
		if (workbook.getSheet("Produtos") != null) {
			this.planilha = workbook.getSheet("Produtos");
		} else {
			this.planilha = workbook.createSheet("Produtos");
		}
		this.indice = 0;
	}
	private RepositorioProdutosExcel(Produto[] itr) {
		this.indiceIterator = 0;
		this.iterator = itr;
	}

	/**
	 * Recebe e insere um produto na planilha a partir da criacao de uma linha
	 * para o mesmo. Cada celula da linha corresponde a um dado(atributo) de
	 * Produto. Dessa forma, dentro do repositorio, cada linha armazena os dados
	 * de um unico produto.
	 * 
	 * @param produto
	 *            Produto - Objeto Produto a ser inserido
	 */
	public void inserir(Produto produto) {
		Cell celula;
		Row linha = planilha.createRow(indice);
		celula = linha.createCell(1);
		celula.setCellValue(produto.getNome());
		celula = linha.createCell(2);
		celula.setCellValue(produto.getCodigo());
		celula = linha.createCell(3);
		celula.setCellValue(produto.getDescricao());

		if (produto instanceof Guloseimas) {
			celula = linha.createCell(0);
			celula.setCellValue("Guloseima");
			celula = linha.createCell(4);
			celula.setCellValue(((Guloseimas) produto).getSabor());
			celula = linha.createCell(5);
			celula.setCellValue(produto.getPreco());
		} else if (produto instanceof Travessuras) {
			celula = linha.createCell(0);
			celula.setCellValue("Travessura");
			celula = linha.createCell(4);
			celula.setCellValue(((Travessuras) produto).getNivelPericulosidade());
			celula = linha.createCell(5);
			celula.setCellValue(((Travessuras) produto).getCensura());
			celula = linha.createCell(6);
			celula.setCellValue(produto.getPreco());
		}
		indice++;
	}

	/** 
	 * Metodo que recebe um ID e procura, na coluna referente ao id dos Produtos,
	 * um id que se iguale a esse, a fim de retornar os dados do devido Cliente
	 * em forma de objeto.
	 * Ao encontrar o ID, o metodo capta os dados contidos naquela linha para 'montar'
	 * o objeto Produto a partir daquela linha, e retorna esse objeto.
	 * @param id String - Id do produto a ser procurado
	 * @return produto Produto - Produto a ser procurado
	 */
	public Produto procurar(String codigo) {
		String celula = "";
		Row linha = null;
		Produto resposta = null;

		for (int i = 0; i <= this.indice; i++) {
			if (planilha.getRow(i) != null) {
				linha = planilha.getRow(i);
				celula = linha.getCell(2).toString();
				if (codigo.equalsIgnoreCase(celula)) {
					if (linha.getCell(0).toString().equalsIgnoreCase("Guloseima")) {
						String nome = linha.getCell(1).toString();
						String descricao = linha.getCell(3).toString();
						String sabor = linha.getCell(4).toString();
						double preco = linha.getCell(5).getNumericCellValue();
						resposta = new Guloseimas(nome, codigo, descricao, sabor, preco);
					} else {
						String nome = linha.getCell(1).toString();
						String descricao = linha.getCell(3).toString();
						double nivel = linha.getCell(4).getNumericCellValue();
						int censura = (int) Math.round(linha.getCell(5).getNumericCellValue());
						double preco = linha.getCell(6).getNumericCellValue();
						resposta = new Travessuras(nome, codigo, descricao, nivel, censura, preco);
					}

				}
			}
		}
		return resposta;
	}
	/**
	 * Metodo que atualiza um produto do repositorio com novos dados inseridos pelo usuario.
	 * @param id String - ID do objeto a ser atualizado
	 * @param produto Produto - Objeto com dados atualizados a ser inserido no lugar do antigo.
	 */
	public void atualizar(String codigo, Produto produtos) {

		int aux = this.procurarLinha(codigo);
		planilha.getRow(aux).getCell(1).setCellValue(produtos.getNome());
		planilha.getRow(aux).getCell(2).setCellValue(codigo);
		planilha.getRow(aux).getCell(3).setCellValue(produtos.getDescricao());
		if (planilha.getRow(aux).getCell(0).toString().equalsIgnoreCase("Guloseima")) {
			planilha.getRow(aux).getCell(4).setCellValue(((Guloseimas) produtos).getSabor());
		} else {
			planilha.getRow(aux).getCell(4).setCellValue(((Travessuras) produtos).getNivelPericulosidade());
			planilha.getRow(aux).getCell(5).setCellValue(((Travessuras) produtos).getCensura());

		}

	}
	/**
	 * Remove um produto(Linha) do repositorio a partir de seu ID.
	 * Ele usa o metodo procurar linha pra encontrar a localização do produto no repositorio.
	 * Depois, remove aquela linha e realoca todas as outras abaixo dela uma posicao acima.
	 * @param id String - id do Produto a ser removido do repositorio.
	 */
	public void remover(String id) {
		int posicao = this.procurarLinha(id);
		Row aux = planilha.getRow(posicao);
		planilha.removeRow(aux);
		planilha.shiftRows((posicao + 1), (indice - 1), -1);
		indice--;

	}

	public Produto procurarNome(String nome) {
		String celula = "";
		Row linha = null;
		Produto resposta = null;

		for (int i = 0; i < this.indice; i++) {
			linha = planilha.getRow(i);
			celula = linha.getCell(1).toString();
			if (nome.equalsIgnoreCase(celula)) {
				if (linha.getCell(0).toString().equalsIgnoreCase("Guloseima")) {
					String descricao = linha.getCell(3).toString();
					String sabor = linha.getCell(4).toString();
					String codigo = linha.getCell(2).toString();
					double preco = linha.getCell(5).getNumericCellValue();
					resposta = new Guloseimas(nome, codigo, descricao, sabor, preco);
				} else {
					String codigo = linha.getCell(2).toString();
					String descricao = linha.getCell(3).toString();
					double nivel = linha.getCell(4).getNumericCellValue();
					int censura = (int) Math.round(linha.getCell(5).getNumericCellValue());
					double preco = linha.getCell(6).getNumericCellValue();
					resposta = new Travessuras(nome, codigo, descricao, nivel, censura, preco);
				}

			}
		}
		return resposta;
	}
	/**
	 * Metodo que retorna a linha(Indice) na qual se encontra um Produto especifico, a partir
	 * de seu ID
	 * @param id String - id do produto a ser procurado no repositorio
	 * @return indice int - posicao da linha na qual se encontram os dados do produto procurado.
	 */
	public int procurarLinha(String codigo) {
		String celula = "";
		int posicao = -1;
		Row linha = null;
		Produto resposta = null;

		for (int i = 0; i <= this.indice; i++) {
			linha = planilha.getRow(i);
			celula = linha.getCell(2).toString();
			if (codigo.equalsIgnoreCase(celula)) {
				posicao = i;
			}

		}
		return posicao;

	}

	public Produto next() {
		Produto resposta = null;
		resposta = this.iterator[this.indiceIterator];
		this.indiceIterator++;
		return resposta;
	}

	public boolean hasNext() {
		return this.iterator[this.indiceIterator + 1] != null;
	}

	public Iterator getIterator() throws EmptyListException {
		Produto[] itr = new Produto[this.size()];
		for(int i = 0; i < size(); i++) {
			itr[i] = this.clone(planilha.getRow(i));
		}
		RepositorioProdutosExcel iterator = new RepositorioProdutosExcel(itr);
		return iterator;
	}
	public int size() {
		return planilha.getPhysicalNumberOfRows();
	}
	private Produto clone(Row linha) {
		Produto resposta = null;
		if (linha.getCell(0).toString().equalsIgnoreCase("Guloseima")) {
			String nome = linha.getCell(1).toString();
			String codigo = linha.getCell(2).toString();
			String descricao = linha.getCell(3).toString();
			String sabor = linha.getCell(4).toString();
			double preco = linha.getCell(5).getNumericCellValue();
			resposta = new Guloseimas(nome, codigo, descricao, sabor, preco);
		} else {
			String nome = linha.getCell(1).toString();
			String codigo = linha.getCell(2).toString();
            String descricao = linha.getCell(3).toString();
			double nivel = linha.getCell(4).getNumericCellValue();
			int censura = (int) Math.round(linha.getCell(5).getNumericCellValue());
			double preco = linha.getCell(6).getNumericCellValue();
			resposta = new Travessuras(nome, codigo, descricao, nivel, censura, preco);
		}
		return resposta;

	}

}
