package fachada;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;

import dados.repositorios.*;
import negocios.cadastro.*;
import negocios.classesBasicas.Cliente;
import negocios.classesBasicas.Produto;
import negocios.exceptions.ClienteJaExisteException;
import negocios.exceptions.ClienteNaoEncontradoException;
import negocios.exceptions.ProdutoJaExisteException;
import negocios.exceptions.ProdutoNaoEncontradoException;

public class GemialidadesLoja {
	private CadastroClientes repClientes;
	private CadastroProdutos repProdutos;
	private CadastroEntregas repEntregas;
	private File file;
	private FileInputStream in;
	private char tipoRep;
	private Workbook workbook;
	
	public GemialidadesLoja( File file) throws IOException, FileNotFoundException{
		this.file = new File("config.txt");
		this.in = new FileInputStream(file);
		this.tipoRep = (char) in.read();
		
		if(tipoRep == 'A' || tipoRep == 'a') {
			this.repClientes = new CadastroClientes(new RepositorioClientesArray());
			this.repProdutos = new CadastroProdutos(new RepositorioProdutosArray());
			this.repEntregas = new CadastroEntregas(new RepositorioEntregasArray());
			
		} else if (tipoRep == 'L' || tipoRep == 'b') {
			this.repClientes = new CadastroClientes(new RepositorioClientesLista());
			this.repProdutos = new CadastroProdutos(new RepositorioProdutosLista());
			this.repEntregas = new CadastroEntregas(new RepositorioEntregasFila());
		} else if (tipoRep == 'E' || tipoRep == 'e') {
			this.repClientes = new CadastroClientes(new RepositorioClientesExcel(workbook));
			this.repProdutos = new CadastroProdutos(new RepositorioProdutosExcel(workbook));
			this.repEntregas = new CadastroEntregas(new RepositorioEntregasExcel(workbook));
		}
						
	}

	public void atualizarCliente(String id, Cliente clientes)
			throws ClienteNaoEncontradoException {
		repClientes.atualizar(id, clientes);
	}

	public void inserirCliente(Cliente clientes) throws ClienteJaExisteException {
		repClientes.inserir(clientes);
	}

	public Cliente procurarCliente(String id) throws ClienteNaoEncontradoException {
		return repClientes.procurar(id);
	}

	public void removerCliente(String id) throws ClienteNaoEncontradoException {
		repClientes.remover(id);
	}

	
	
	//Produtos

	
	public void cadastrarProduto(Produto produto) throws ProdutoJaExisteException {
		repProdutos.cadastrar(produto);
	}

	public void removerProduto(String codigo) throws ProdutoNaoEncontradoException {
		repProdutos.remover(codigo);
	}

	public Produto procurarProduto(String codigo) throws ProdutoNaoEncontradoException {
		return repProdutos.procurar(codigo);
	}

	public void atualizarProduto(String codigo, Produto produto)
			throws ProdutoNaoEncontradoException {
		repProdutos.atualizar(codigo, produto);
	}
	
	

}
