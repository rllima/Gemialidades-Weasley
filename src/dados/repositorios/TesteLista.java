package dados.repositorios;

import negocios.classesBasicas.*;

public class TesteLista {
	
	public static void main(String[] args) {
		RepositorioProdutosLista listaProdutos = new RepositorioProdutosLista("Lista de Produtos");
		
		Produto sapochocolate = new Guloseimas("Sapinhos de Choc", "666", "Sapinhos", "Chocolade Dããã");
		Produto travessura = new Travessuras("Orelhas Extensiveis", "555", "Ouvir legalzinho", 2, 11);
		Produto sapoe = new Guloseimas("Sapinhos de Choculate", "777", "Sapinhos", "Chocolade Dããã");
		
		listaProdutos.inserir(sapochocolate);
		listaProdutos.inserir(travessura);
		listaProdutos.inserir(sapoe);
		
		listaProdutos.remover("555");
		
		Produto aux = listaProdutos.procurar("777");
		System.out.printf("\nOi, eu sou o goku! \n%s", aux);
		
		Produto kitMataAula = new Travessuras("Kit mata aula", "777", "Pra matar Ricardo, ops, a aula de Ricardo* :D", 0, 0);
		listaProdutos.atualizar("777", kitMataAula);
		
		System.out.println("\n\n" + listaProdutos.procurar("777") + "\n");
		
		//System.out.println(listaProdutos.toString());
	}

}
