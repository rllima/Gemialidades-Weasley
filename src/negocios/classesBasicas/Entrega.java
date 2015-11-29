package negocios.classesBasicas;

public class Entrega {
	
	
	private String idCliente;
	private String idProduto;
	
	public Entrega(String id, Produto produto, Cliente cliente) {
		this.idCliente = idCliente;
		this.idProduto = idProduto;
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

	
	

}
