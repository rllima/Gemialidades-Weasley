package negocios.classesBasicas;

public class Guloseimas extends Produtos {
	
	private String sabor;
	
	public Guloseimas(String nome, String codigo, String sabor, String descricao) {
		super(nome, codigo, descricao);
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

}
