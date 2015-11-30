package fachada;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import dados.repositorios.RepositorioClientesArray;
import dados.repositorios.RepositorioProdutos;
import dados.repositorios.RepositorioProdutosArray;
import dados.repositorios.RepositoriosClientes;
import negocios.cadastro.CadastroClientes;
import negocios.cadastro.CadastroProdutos;

public class GemialidadesLoja {
	private CadastroClientes repClientes;
	private RepositoriosClientes clientes;
	private CadastroProdutos repProdutos;
	private RepositorioProdutos produtos;
	private CadastroEntregas repEntregas;
	private RepositorioEntregas entregas;
	private File file;
	private FileInputStream in;
	private char tipoRep;
	
	public GemialidadesLoja( File file) throws IOException{
		this.file = new File("config.txt");
		this.in = new FileInputStream(file);
		this.tipoRep = (char) in.read();
		
		if(tipoRep == 'A'){
			this.clientes = new RepositorioClientesArray();
			this.produtos = new RepositorioProdutosArray();
			this.entregas = new RepositorioEntregasArray();
		}
		
		
		
		
		
	}
	
	

}
