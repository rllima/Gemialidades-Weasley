package negocios.classesBasicas;

public class Guloseimas extends Produto {
	
	private String sabor;
	
	public Guloseimas(String nome, String codigo, String descricao, String sabor, double preco) {
		super(nome, codigo, descricao, preco);
		this.sabor = sabor;
	}

	public void venda() {	
		
	}

	public String getSabor() {
		return sabor;
	}

	public void setSabor(String sabor) {
		this.sabor = sabor;
	}
	
	public String toString() {
		return "Nome: " + this.getNome() + "\nCodigo: " + this.getCodigo() + "\nDescricao: " + this.getDescricao() + "\nSabor: " + this.getSabor() + "\nPreco: " + this.getPreco();
	}

}
