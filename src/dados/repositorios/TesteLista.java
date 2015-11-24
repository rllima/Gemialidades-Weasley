package dados.repositorios;

import negocios.classesBasicas.*;

public class TesteLista {
	
	public static void main(String[] args) {
		RepositorioProdutosList listaProdutos = new RepositorioProdutosList("Lista de Produtos");
		
		Produto sapochocolate = new Guloseimas("Sapinhos de Choc", "666", "Sapinhos", "Chocolade Dããã");
		Produto travessura = new Travessuras("Orelhas Extensiveis", "555", "Ouvir legalzinho", 2, 11);
		Produto sapoe = new Guloseimas("Sapinhos de Choculate", "777", "Sapinhos", "Chocolade Dããã");
		
		listaProdutos.inserir(sapochocolate);
		listaProdutos.inserir(travessura);
		listaProdutos.inserir(sapoe);
		
		listaProdutos.remover("555");
		
		System.out.println(listaProdutos.toString());
	}

}
