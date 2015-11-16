package dados.repositorios;

import negocios.classesBasicas.Produto;
import negocios.exceptions.ProdutoNaoEncontradoException;

public interface RepositorioProdutos {
	
	void inserir(Produto produto);
	Produto procurar(String codigo) throws ProdutoNaoEncontradoException;
	void atualizar();
	void remover(String id) throws ProdutoNaoEncontradoException;

}
