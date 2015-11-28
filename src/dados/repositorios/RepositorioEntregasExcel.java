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
		this.planilhaPendentes = workbook.createSheet("Entregas - Pendentes");
		this.planilhaEnviadas = workbook.createSheet("Entregas - Enviadas");
		this.indicePendentes = 0;
		this.indiceEnviadas = 0;
	}

	public void inserir(Entrega entrega) {
		Cell celula;
		Row linha = planilhaPendentes.createRow(indicePendentes);
		celula = linha.createCell(1);
		celula.setCellValue(entrega.getCliente().getId());
		celula = linha.createCell(2);
		celula.setCellValue(entrega.getId());
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
		indicePendentes++;
	}
    
	// Procura entrega pendente
	public Entrega procurar(String id) {
		String celula = "";
		Row linha = null;
		Entrega resposta = null;
		
		for(int i = 0; i < this.indicePendentes; i++) {
			linha = planilhaPendentes.getRow(i);
			celula = linha.getCell(2).getStringCellValue();
			if (id.equalsIgnoreCase(celula)) {
				String idCliente = linha.getCell(1).getStringCellValue();
				String idProduto = linha.getCell(1).getStringCellValue();
				// oontinua...
			}
		}
		
		return resposta;
	}

	public void atualizar(String id, Entrega entrega) {
		// TODO Auto-generated method stub
		
	}

	public void remover(String id) {
		int posicao = this.procurarLinha(id);
		Row aux = planilhaPendentes.getRow(posicao);
		planilhaPendentes.removeRow(aux);
		planilhaPendentes.shiftRows((posicao+1), (indicePendentes-1), -1);
		indicePendentes--;
		
	}
	
	// Remove Entrega do comeco e retorna o objeto p/ ser inserido na lista de enviados
	public Entrega enviar() {
		return null;
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
