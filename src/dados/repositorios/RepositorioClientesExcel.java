package dados.repositorios;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.*;

import org.apache.poi.ss.usermodel.Workbook;

import negocios.classesBasicas.*;

public class RepositorioClientesExcel implements RepositoriosClientes {
	
	
	private Sheet planilha;
	private int indice;
	
	public RepositorioClientesExcel (Workbook workbook){
		this.planilha = workbook.createSheet("Clientes");
		this.indice = 0;
		
	}
	

	public void inserir(Cliente clientes) {
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
		
		indice++;
		
	}

	public Cliente procurar(String id) {
		String celula = "";
		Row linha = null;
		Cliente resposta = null;
		for(int i = 0; i < this.indice; i++){
			linha = planilha.getRow(i);
			celula = linha.getCell(3).toString();
			if(id.equalsIgnoreCase(celula)){
				String nome = linha.getCell(1).toString();
				int idade = linha.getCell(arg0)
			}
		}
		
		return null;
	}

	public void atualizar(String id) {
		// TODO Auto-generated method stub
		
	}

	public void remover(String id) {
		// TODO Auto-generated method stub
		
	}
	public int procurarLinha(String id){
		String celula = "";
		int posicao = -1;
		Row linha = null;
		Cliente resposta = null;

		for(int i = 0; i < this.indice; i++) {
			linha = planilha.getRow(i);
			celula = linha.getCell(3).toString();
			if (id.equalsIgnoreCase(celula)){
				posicao = i;
			}

		}
		return posicao;

	}

}
