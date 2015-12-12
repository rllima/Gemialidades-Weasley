package negocios.classesBasicas;

public class Endereco {
	
	private String cidade;
	private String logradouro;
	private String numero;
	private String cep;
	private String complemento;
	
	public Endereco(String cidade, String logradouro, String numero,
			String cep, String complemento) {	
		this.cidade = cidade;
		this.logradouro = logradouro;
		this.numero = numero;
		this.cep = cep;
		this.complemento = complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String toString(){
		return "Cidade: " + this.getCidade() + "\nLogradouro: " + this.getLogradouro() + "Número: " + this.getNumero() + "CEP: " + this.getCep() + "Complemento: " + this.getComplemento();
		
	}
	public Endereco clone(){
		Endereco endereco = new Endereco(this.cidade, this.logradouro, this.numero, this.cep, this.complemento);
		return endereco;
	}
	
	

}
