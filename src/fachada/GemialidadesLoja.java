package fachada;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import dados.repositorios.*;
import negocios.cadastro.*;
import negocios.classesBasicas.Cliente;
import negocios.classesBasicas.Entrega;
import negocios.classesBasicas.Produto;
import negocios.exceptions.ClienteJaExisteException;
import negocios.exceptions.ClienteNaoEncontradoException;
import negocios.exceptions.EntregaJaExisteException;
import negocios.exceptions.EntregaNaoEncontradaException;
import negocios.exceptions.ProdutoJaExisteException;
import negocios.exceptions.ProdutoNaoEncontradoException;
import negocios.exceptions.SenhaIncoretaException;

public class GemialidadesLoja {
	private CadastroClientes repClientes;
	private CadastroProdutos repProdutos;
	private CadastroEntregas repEntregas;
	private File config;
	private File excel;
	private FileInputStream in;
	private char tipoRep;
	private Workbook workbook;

	public GemialidadesLoja () throws IOException, FileNotFoundException{
		this.config = new File("config.txt");
		this.in = new FileInputStream(config);
		this.tipoRep = (char) in.read();

		if(tipoRep == 'A' || tipoRep == 'a') {
			this.repClientes = new CadastroClientes(new RepositorioClientesArray());
			this.repProdutos = new CadastroProdutos(new RepositorioProdutosArray());
			this.repEntregas = new CadastroEntregas(new RepositorioEntregasArray());

		} else if (tipoRep == 'L' || tipoRep == 'l') {
			this.repClientes = new CadastroClientes(new RepositorioClientesLista());
			this.repProdutos = new CadastroProdutos(new RepositorioProdutosLista());
			this.repEntregas = new CadastroEntregas(new RepositorioEntregasFila());
			
		} else if (tipoRep == 'E' || tipoRep == 'e') {
			this.excel = new File("planilha.xls");
			excel.createNewFile();
			if(!excel.exists()) {
				FileOutputStream saidaArquivo = new FileOutputStream(excel);
				workbook = new HSSFWorkbook();
			} else {
				FileInputStream entradaArquivo = new FileInputStream(excel);
				workbook = new HSSFWorkbook(entradaArquivo);
				
			}
			this.repClientes = new CadastroClientes(new RepositorioClientesExcel(workbook));
			this.repProdutos = new CadastroProdutos(new RepositorioProdutosExcel(workbook));
			this.repEntregas = new CadastroEntregas(new RepositorioEntregasExcel(workbook));
		}
		
		/* Lembrar de colocar IF's nos repositorios pra conferir se ja existe a planilha(aba) */

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


	//Entregas

	public void cadastrarEntrega(Entrega entrega) throws EntregaJaExisteException {
		repEntregas.cadastrar(entrega);
	}

	public void removerEntrega(String id) throws EntregaNaoEncontradaException {
		repEntregas.remover(id);
	}

	public Entrega entregaEntrega(String id) throws EntregaNaoEncontradaException {
		return repEntregas.entrega(id);
	}

	public void atualizarEntrega(String id, Entrega entrega)
			throws EntregaNaoEncontradaException {
		repEntregas.atualizar(id, entrega);
	}
	
	//Login
	
	public boolean login(String id, String senha) throws ClienteNaoEncontradoException, SenhaIncoretaException {
		boolean resposta = false;
		Cliente cliente = this.procurarCliente(id);
		if(senha.equals(cliente.getSenha())) {
			resposta = true;
		} else {
			throw new SenhaIncoretaException();
		}
		return resposta;
	}



}
