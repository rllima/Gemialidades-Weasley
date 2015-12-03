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
		celula = linha.createCell(9);
		celula.setCellValue(clientes.getSenha());
		
		indice++;
		
	}

	public Cliente procurar(String id) {
		String celula = "";
		Row linha = null;
		Cliente resposta = null;
		Endereco endereco = null;
		for(int i = 0; i < this.indice; i++){
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

	public void atualizar(String id, Cliente clientes) {
		int aux = this.procurarLinha(id);
		planilha.getRow(aux).getCell(1).setCellValue(clientes.getNome());
		planilha.getRow(aux).getCell(3).setCellValue(id);
		planilha.getRow(aux).getCell(2).setCellValue(clientes.getIdade());
		planilha.getRow(aux).getCell(4).setCellValue(clientes.getEndereco().getCidade());
		planilha.getRow(aux).getCell(5).setCellValue(clientes.getEndereco().getLogradouro());
		planilha.getRow(aux).getCell(5).setCellValue(clientes.getEndereco().getNumero());
		planilha.getRow(aux).getCell(6).setCellValue(clientes.getEndereco().getCep());
		planilha.getRow(aux).getCell(7).setCellValue(clientes.getEndereco().getComplemento());
		
		

		
	}

	public void remover(String id) {
		int posicao = this.procurarLinha(id);
		Row aux = planilha.getRow(posicao);
		planilha.removeRow(aux);
		planilha.shiftRows((posicao+1), (indice-1), -1);
		indice--;
		
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



