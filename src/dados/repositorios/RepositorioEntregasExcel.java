package dados.repositorios;

import negocios.classesBasicas.Entrega;
import negocios.classesBasicas.Guloseimas;
import negocios.classesBasicas.Produto;
import negocios.classesBasicas.Travessuras;
import negocios.exceptions.EntregaNaoEncontradaException;

import org.apache.poi.ss.usermodel.*;

public class RepositorioEntregasExcel implements RepositorioEntregas {

	private Sheet planilhaPendentes;
	private Sheet planilhaEnviadas;
	private int indicePendentes;
	private int indiceEnviadas;
	
	public RepositorioEntregasExcel(Workbook workbook) {
		if(workbook.getSheet("Entregas - Pendentes") != null) {
			this.planilhaPendentes = workbook.getSheet("Entregas - Pendentes");
		} else {
			this.planilhaPendentes = workbook.createSheet("Entregas - Pendentes");
		}
		if(workbook.getSheet("Entregas - Enviadas") != null) {
			this.planilhaEnviadas = workbook.getSheet("Entregas - Enviadas");
		} else {
			this.planilhaEnviadas = workbook.createSheet("Entregas - Enviadas");
		}
		this.indicePendentes = 0;
		this.indiceEnviadas = 0;
	}

	public void inserir(Entrega entrega) {
		Cell celula;
		Row linha = planilhaPendentes.createRow(indicePendentes);
		celula = linha.createCell(1);
		celula.setCellValue(entrega.getId());
		celula = linha.createCell(2);
		celula.setCellValue(entrega.getIdCliente());
		celula = linha.createCell(3);
		celula.setCellValue(entrega.getIdProduto());
		indicePendentes++;
	}
	public void Entregues(Entrega entrega) {
		Cell celula;
		Row linha = planilhaEnviadas.createRow(indiceEnviadas);
		celula = linha.createCell(1);
		celula.setCellValue(entrega.getId());
		celula = linha.createCell(2);
		celula.setCellValue(entrega.getIdCliente());
		celula = linha.createCell(3);
		celula.setCellValue(entrega.getIdProduto());
		indiceEnviadas++;
		
		
	}
    
	// Procura entrega pendente
	public Entrega procurar(String id) {
		String celula = "";
		Row linha = null;
		Entrega resposta = null;
		
		for(int i = 0; i < this.indicePendentes; i++) {
			linha = planilhaPendentes.getRow(i);
			celula = linha.getCell(1).getStringCellValue();
			if (id.equalsIgnoreCase(celula)) {
				String idCliente = linha.getCell(2).getStringCellValue();
				String idProduto = linha.getCell(3).getStringCellValue();
				resposta = new Entrega(id, idCliente, idProduto);
				
			}
		}
		
		return resposta;
	}

	public void atualizar(String id, Entrega entrega) {
		int aux = this.procurarLinha(id);
		planilhaPendentes.getRow(aux).getCell(1).setCellValue(entrega.getId());
		planilhaPendentes.getRow(aux).getCell(2).setCellValue(entrega.getIdCliente());
		planilhaPendentes.getRow(aux).getCell(3).setCellValue(entrega.getIdProduto());
		
		
		
	}

	public void remover(String id) {
		int posicao = this.procurarLinha(id);
		Row aux = planilhaPendentes.getRow(posicao);
		planilhaPendentes.removeRow(aux);
		planilhaPendentes.shiftRows((posicao+1), (indicePendentes-1), -1);
		indicePendentes--;
		
	}
	
	// Remove Entrega do comeco e retorna o objeto p/ ser inserido na lista de enviados
	public void enviar() {
		Row aux = planilhaPendentes.getRow(0);
		String id = planilhaPendentes.getRow(0).getCell(1).toString();
		String idCliente = planilhaPendentes.getRow(0).getCell(2).toString();
		String idProduto = planilhaPendentes.getRow(0).getCell(3).toString();
		Entrega temp = new Entrega(id, idCliente, idProduto);
		this.remover(id);
		this.Entregues(temp);

	}
	
	// Recebe identificador e retorna a linha a qual ele se encontra (PENDENTES)
	public int procurarLinha(String codigo){
		String celula = "";
		int posicao = -1;
		Row linha = null;
		Produto resposta = null;

		for(int i = 0; i < this.indicePendentes; i++) {
			linha = planilhaPendentes.getRow(i);
			celula = linha.getCell(2).toString();
			if (codigo.equalsIgnoreCase(celula)){
				posicao = i;
			}

		}
		return posicao;

	}

}
