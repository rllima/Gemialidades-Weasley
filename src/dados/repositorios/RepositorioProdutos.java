package dados.repositorios;

import java.io.FileNotFoundException;
import java.io.IOException;

import negocios.classesBasicas.Produto;
import negocios.exceptions.EmptyListException;
import negocios.exceptions.ProdutoNaoEncontradoException;

public interface RepositorioProdutos {
	
	void inserir(Produto produto) throws FileNotFoundException, IOException;
	Produto procurar(String codigo);
	Produto procurarNome(String nome);
	void atualizar(String codigo, Produto produtos);
	void remover(String codigo) throws IOException;
	Iterator getIterator() throws EmptyListException;

}
