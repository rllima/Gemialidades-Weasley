package negocios.classesBasicas;

public class Cliente {
	
	private String nome;
	private int idade;
	private Endereco endereco;
	private String id;
	private String senha;


	public Cliente(String nome, int idade, Endereco endereco, String id) {
		this.nome = nome;
		this.idade = idade;
		this.endereco = endereco;
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
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

}
