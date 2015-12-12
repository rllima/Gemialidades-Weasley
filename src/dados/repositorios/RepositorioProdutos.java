package dados.repositorios;

import negocios.classesBasicas.Produto;
import negocios.exceptions.ProdutoNaoEncontradoException;

public interface RepositorioProdutos {
	
	void inserir(Produto produto);
	Produto procurar(String codigo);
	Produto procurarNome(String nome);
	void atualizar(String codigo, Produto produtos);
	void remover(String codigo);
	Iterator getIterator();

}
