package negocios.classesBasicas;

public class Travessuras extends Produto {

	private double nivelPericulosidade;
	private int censura;

	public Travessuras(String nome, String codigo, String descricao, double nivelPericulosidade, int censura, double preco) {
		super(nome, codigo, descricao, preco);
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

	public String toString() {
		String resposta = "";
		resposta = "Nome: " + this.getNome() + "\nCodigo: " + this.getCodigo() + "\nDescricao: " + this.getDescricao()
				+ "\nNivel de Periculosidade: " + this.getNivelPericulosidade() + "\nCensura: " + this.getCensura()  + "\nPreco: " + this.getPreco();
		return resposta;
	}

	public void venda() {

	}

}
