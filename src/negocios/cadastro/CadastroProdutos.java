package negocios.cadastro;

import negocios.classesBasicas.Produto;
import negocios.exceptions.ProdutoJaExisteException;
import negocios.exceptions.ProdutoNaoEncontradoException;
import dados.repositorios.RepositorioProdutos;

public class CadastroProdutos {
	
	private RepositorioProdutos repositorioProdutos;
	
	public CadastroProdutos(RepositorioProdutos repositorioProdutos) {
		this.repositorioProdutos = repositorioProdutos;
	}
	
	public void cadastrar(Produto produto) throws ProdutoJaExisteException {
		if(repositorioProdutos.procurar(produto.getCodigo()) == null) {
			repositorioProdutos.inserir(produto);
		} else {
			throw new ProdutoJaExisteException();
		}
	}
	
	public void remover(String codigo) throws ProdutoNaoEncontradoException {
		if(repositorioProdutos.procurar(codigo) != null) {
			repositorioProdutos.remover(codigo);
		} else {
			throw new ProdutoNaoEncontradoException();
		}
	}
	
	public Produto procurar(String codigo) throws ProdutoNaoEncontradoException {
		Produto resposta = repositorioProdutos.procurar(codigo);
		if(resposta == null) {
			throw new ProdutoNaoEncontradoException();
		}
		return resposta;
	}
	
	public void atualizar(String codigo, Produto produto) throws ProdutoNaoEncontradoException {
		if(repositorioProdutos.procurar(codigo) == null) {
			throw new ProdutoNaoEncontradoException();
		} else {
			repositorioProdutos.atualizar(codigo, produto);
		}
	}

}
