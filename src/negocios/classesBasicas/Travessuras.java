package negocios.classesBasicas;

public class Travessuras extends Produto {
	
	private double nivelPericulosidade;
	private int censura;
	
	public Travessuras(String nome, String codigo, String descricao, double nivelPericulosidade, int censura) {
		super(nome, codigo, descricao);
		this.nivelPericulosidade = nivelPericulosidade;
		this.censura = censura;
	}
	
	public double getNivelPericulosidade() {
		return nivelPericulosidade;
	}

	public void setNivelPericulosidade(double nivelPericulosidade) {
		this.nivelPericulosidade = nivelPericulosidade;
	}

	public int getCensura() {
		return censura;
	}

	public void setCensura(int censura) {
		this.censura = censura;
	}

	public void venda() {
		
		
	}
	

}
