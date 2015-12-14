package negocios.classesBasicas;

import java.io.Serializable;

public abstract class Produto implements Serializable {
	
	private String nome;
	private String codigo;
	private String descricao;
	private double preco;
	

	public Produto(String nome, String codigo, String descricao, double preco) {
		this.nome = nome;
		this.codigo = codigo;
		this.descricao = descricao;
		this.preco = preco;
	}
	
	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	} 
	
	public abstract boolean aplicaDescontoDe( double porcentagem );
	

}
