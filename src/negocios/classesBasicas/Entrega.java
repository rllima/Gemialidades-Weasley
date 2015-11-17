package negocios.classesBasicas;

public class Entrega {
	
	private Produto produto;
	private Cliente cliente;
	private String id;
	
	public Entrega(String id, Produto produto, Cliente cliente) {
		this.produto = produto;
		this.cliente = cliente;
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	

}
