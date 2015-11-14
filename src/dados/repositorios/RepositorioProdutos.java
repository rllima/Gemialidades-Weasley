package dados.repositorios;

import negocios.classesBasicas.Produto;

public interface RepositorioProdutos {
	
	void inserir(Produto produto);
	Produto procurar(String codigo);
	void atualizar();
	void remover(String id);

}
