package negocios.classesBasicas;

public class Cliente {
	
	private String nome;
	private String idade;
	private Endereco endereco;
	private String[] entregas;
	private int indice;

	private String id;
	private String senha;


	public Cliente(String nome, String idade, Endereco endereco, String id, String senha) {
		this.nome = nome;
		this.idade = idade;
		this.endereco = endereco;
		this.id = id;
		this.senha = senha;
		this.entregas = new String[10];
	}
	
	public Cliente(String nome, String idade, Endereco endereco, String id, String senha, String[] entregas) {
		this.nome = nome;
		this.idade = idade;
		this.endereco = endereco;
		this.id = id;
		this.senha = senha;
		this.entregas = entregas;
	}

	public String[] getEntregas() {
		return entregas;
	}
	
	public void setEntregas(String[] entregas) {
		this.entregas = entregas;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String toString() {
       return "Nome: " + this.nome + "\nIdade: " + this.idade + "\nEndereço: " + this.endereco + "\nId: " + this.id;
	}
	public Cliente clone(){
		Cliente cliente = new Cliente(this.nome, this.idade, this.getEndereco().clone(), this.id, this.senha);
		return cliente;
	}
	
	public void addEntrega(String idEntrega) {
		if (indice > this.entregas.length) {
			String[] aux = new String[entregas.length * 2];
			for (int i = 0; i < indice; i++) {
				aux[i] = this.entregas[i];
			}
			aux[indice] = idEntrega;
			entregas = aux;
		} else {
			this.entregas[indice] = idEntrega;
		}
		indice++;
	}
	
	public String getStringEntregas() {
		String resposta = "";
		for(int i = 0; i < indice; i++) {
			resposta += entregas[i] + " ";
		}
		return resposta;
	}

}
