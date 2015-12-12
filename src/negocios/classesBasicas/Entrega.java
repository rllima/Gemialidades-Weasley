package negocios.classesBasicas;

public class Entrega {
	
	
	private String idCliente;
	private String idProduto;
	private String id;
	
	public Entrega(String id, String idCliente, String idProduto) {
		this.idCliente = idCliente;
		this.idProduto = idProduto;
		this.id = id;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(String idProduto) {
		this.idProduto = idProduto;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Entrega clone(){
		Entrega entrega = new Entrega(this.idCliente, this.idProduto, this.id);
		return entrega;
	}

	
	

}
