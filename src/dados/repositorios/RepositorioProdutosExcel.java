package dados.repositorios;

import negocios.classesBasicas.*;

import java.io.*;

import org.apache.poi.ss.usermodel.*;

public class RepositorioProdutosExcel implements RepositorioProdutos {

	private Sheet planilha;
	private int indice;

	public RepositorioProdutosExcel(Workbook workbook) {
		this.planilha = workbook.createSheet("Produtos");
		this.indice = 0;
	}

	public void inserir(Produto produto) {
		Cell celula;
		Row linha = planilha.createRow(indice); 
		celula = linha.createCell(1);
		celula.setCellValue(produto.getNome());
		celula = linha.createCell(2);
		celula.setCellValue(produto.getCodigo());
		celula = linha.createCell(3);
		celula.setCellValue(produto.getDescricao());
		

		if(produto instanceof Guloseimas) {
			celula = linha.createCell(0);
			celula.setCellValue("Guloseima");
			celula = linha.createCell(4);
			celula.setCellValue(((Guloseimas)produto).getSabor());
			celula = linha.createCell(5);
			celula.setCellValue(produto.getPreco());
		} else if (produto instanceof Travessuras) {
			celula = linha.createCell(0);
			celula.setCellValue("Travessura");
			celula = linha.createCell(4);
			celula.setCellValue(((Travessuras)produto).getNivelPericulosidade());
			celula = linha.createCell(5);
			celula.setCellValue(((Travessuras)produto).getCensura());
			celula = linha.createCell(6);
			celula.setCellValue(produto.getPreco());
		}
		indice++;
	}

	public Produto procurar(String codigo) {
		String celula = "";
		Row linha = null;
		Produto resposta = null;

		for(int i = 0; i < this.indice; i++) {
			linha = planilha.getRow(i);
			celula = linha.getCell(2).toString();
			if (codigo.equalsIgnoreCase(celula)) {
				if(linha.getCell(0).toString().equalsIgnoreCase("Guloseima")) {
					String nome = linha.getCell(1).toString();
					String descricao = linha.getCell(3).toString();
					String sabor = linha.getCell(4).toString();
					double preco = linha.getCell(5).getNumericCellValue();
					resposta = new Guloseimas(nome, codigo, descricao, sabor, preco);
				} else {
					String nome = linha.getCell(1).toString();
					String descricao = linha.getCell(3).toString();
					double nivel =  linha.getCell(4).getNumericCellValue();
					int censura = (int) Math.round(linha.getCell(5).getNumericCellValue());
					double preco = linha.getCell(6).getNumericCellValue();
					resposta = new Travessuras(nome, codigo, descricao, nivel, censura, preco);
				}

			}
		}
		return resposta;
	}

	public void atualizar(String codigo, Produto produtos) {

		int aux = this.procurarLinha(codigo);
		planilha.getRow(aux).getCell(1).setCellValue(produtos.getNome());
		planilha.getRow(aux).getCell(2).setCellValue(codigo);
		planilha.getRow(aux).getCell(3).setCellValue(produtos.getDescricao());
		if( planilha.getRow(aux).getCell(0).toString().equalsIgnoreCase("Guloseima")){
			planilha.getRow(aux).getCell(4).setCellValue(((Guloseimas)produtos).getSabor());
		}else {
			planilha.getRow(aux).getCell(4).setCellValue(((Travessuras)produtos).getNivelPericulosidade());
			planilha.getRow(aux).getCell(5).setCellValue(((Travessuras)produtos).getCensura());

		}



	}

	public void remover(String id) {
		int posicao = this.procurarLinha(id);
		Row aux = planilha.getRow(posicao);
		planilha.removeRow(aux);
		planilha.shiftRows((posicao+1), (indice-1), -1);
		indice--;


	}

	public Produto procurarNome(String nome) {
		String celula = "";
		Row linha = null;
		Produto resposta = null;

		for(int i = 0; i < this.indice; i++) {
			linha = planilha.getRow(i);
			celula = linha.getCell(1).toString();
			if (nome.equalsIgnoreCase(celula)) {
				if(linha.getCell(0).toString().equalsIgnoreCase("Guloseima")) {
					String descricao = linha.getCell(3).toString();
					String sabor = linha.getCell(4).toString();
					String codigo = linha.getCell(2).toString();
					double preco = linha.getCell(5).getNumericCellValue();
					resposta = new Guloseimas(nome, codigo, descricao, sabor, preco);
				} else {
					String codigo = linha.getCell(2).toString();
					String descricao = linha.getCell(3).toString();
					double nivel =  linha.getCell(4).getNumericCellValue();
					int censura = (int) Math.round(linha.getCell(5).getNumericCellValue());
					double preco = linha.getCell(6).getNumericCellValue();
					resposta = new Travessuras(nome, codigo, descricao, nivel, censura, preco);
				}

			}
		}
		return resposta;
	}
	
	public int procurarLinha(String codigo){
		String celula = "";
		int posicao = -1;
		Row linha = null;
		Produto resposta = null;

		for(int i = 0; i < this.indice; i++) {
			linha = planilha.getRow(i);
			celula = linha.getCell(2).toString();
			if (codigo.equalsIgnoreCase(celula)){
				posicao = i;
			}

		}
		return posicao;

	}

}
