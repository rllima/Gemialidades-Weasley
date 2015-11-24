package dados.repositorios;

import negocios.classesBasicas.Entrega;
import negocios.classesBasicas.Guloseimas;
import negocios.classesBasicas.Travessuras;
import negocios.exceptions.EntregaNaoEncontradaException;

import org.apache.poi.ss.usermodel.*;

public class RepositorioEntregasExcel implements RepositorioEntregas {

	private Sheet planilha;
	private int indice;
	
	public RepositorioEntregasExcel(Workbook workbook) {
		this.planilha = workbook.createSheet("Produtos");
		this.indice = 0;
	}

	public void inserir(Entrega entrega) {
		Cell celula;
		Row linha = planilha.createRow(indice);
		
		celula = linha.createCell(1);
		celula.setCellValue(entrega.getProduto().getNome());
		celula = linha.createCell(2);
		celula.setCellValue(entrega.getProduto().getCodigo());
		celula = linha.createCell(3);
		celula.setCellValue(entrega.getProduto().getDescricao());
		
		if(entrega.getProduto() instanceof Guloseimas) {
			celula = linha.createCell(0);
			celula.setCellValue("Guloseima");
			celula = linha.createCell(4);
			celula.setCellValue(((Guloseimas)entrega.getProduto()).getSabor());
		} else if (entrega.getProduto() instanceof Travessuras) {
			celula = linha.createCell(0);
			celula.setCellValue("Travessura");
			celula = linha.createCell(4);
			celula.setCellValue(((Travessuras)entrega.getProduto()).getNivelPericulosidade());
			celula = linha.createCell(5);
			celula.setCellValue(((Travessuras)entrega.getProduto()).getCensura());
		}
		
	}

	public Entrega procurar(String id) throws EntregaNaoEncontradaException {
		// TODO Auto-generated method stub
		return null;
	}

	public void atualizar(String id, Entrega entrega) {
		// TODO Auto-generated method stub
		
	}

	public void remover(String id) throws EntregaNaoEncontradaException {
		// TODO Auto-generated method stub
		
	}
	

}
