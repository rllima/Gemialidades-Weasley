package fachada;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import dados.repositorios.*;
import negocios.cadastro.*;
import negocios.classesBasicas.Cliente;
import negocios.classesBasicas.Entrega;
import negocios.classesBasicas.Produto;
import negocios.exceptions.ClienteJaExisteException;
import negocios.exceptions.ClienteNaoEncontradoException;
import negocios.exceptions.EmptyListException;
import negocios.exceptions.EntregaJaExisteException;
import negocios.exceptions.EntregaNaoEncontradaException;
import negocios.exceptions.NaoHaEntregasException;
import negocios.exceptions.ProdutoJaExisteException;
import negocios.exceptions.ProdutoNaoEncontradoException;
import negocios.exceptions.SenhaIncoretaException;

public class GemialidadesLoja {
	private CadastroClientes repClientes;
	private CadastroProdutos repProdutos;
	private CadastroEntregas repEntregas;
	private File config;
	private File excel;
	private char tipoRep;
	private HSSFWorkbook workbook;
	private static GemialidadesLoja instance;

	public static GemialidadesLoja getInstance() throws FileNotFoundException, IOException {
		if (instance == null) {
			instance = new GemialidadesLoja();
		}
		return instance;
	}

	public GemialidadesLoja() throws IOException, FileNotFoundException {
		this.config = new File("config\\config.txt");
		FileInputStream in = new FileInputStream(config);
		this.tipoRep = (char) in.read();
		in.close();

		if (tipoRep == 'A' || tipoRep == 'a') {
			this.repClientes = new CadastroClientes(new RepositorioClientesArray());
			this.repProdutos = new CadastroProdutos(new RepositorioProdutosArray());
			this.repEntregas = new CadastroEntregas(new RepositorioEntregasArray());

		} else if (tipoRep == 'L' || tipoRep == 'l') {
			this.repClientes = new CadastroClientes(new RepositorioClientesLista());
			this.repProdutos = new CadastroProdutos(new RepositorioProdutosLista());
			this.repEntregas = new CadastroEntregas(new RepositorioEntregasFila());

		} else if (tipoRep == 'E' || tipoRep == 'e') {
			this.excel = new File("planilha.xls");
			if (!excel.exists()) {
				excel.createNewFile();
				FileOutputStream out = new FileOutputStream(excel);
				workbook = new HSSFWorkbook();
				workbook.write(out);
				out.close();
			} else {
				FileInputStream entradaArquivo = new FileInputStream(excel);
				workbook = new HSSFWorkbook(entradaArquivo);
				entradaArquivo.close();

			}
			if(workbook.getSheet("Indices") == null) {
				Sheet indices = workbook.createSheet("Indices");
				Row linha = indices.createRow(0);
				Cell celula = linha.createCell(0);
				celula.setCellValue("Indice Clientes");
				linha.createCell(1).setCellValue(0);
				linha = indices.createRow(1);
				linha.createCell(0).setCellValue("Indice Produtos");
				linha.createCell(1).setCellValue(0);
				linha = indices.createRow(2);
				linha.createCell(0).setCellValue("Indice Entregas Pendentes");
				linha.createCell(1).setCellValue(0);
				linha = indices.createRow(3);
				linha.createCell(0).setCellValue("Indice Entregas Enviadas");
				linha.createCell(1).setCellValue(0);
				workbook.write(new FileOutputStream(new File("planilha.xls")));
			}
			this.repClientes = new CadastroClientes(new RepositorioClientesExcel(workbook));
			this.repProdutos = new CadastroProdutos(new RepositorioProdutosExcel(workbook));
			this.repEntregas = new CadastroEntregas(new RepositorioEntregasExcel(workbook));
		}

		/*
		 * Lembrar de colocar IF's nos repositorios pra conferir se ja existe a
		 * planilha(aba)
		 */

	}

	public void atualizarCliente(String id, Cliente clientes)
			throws ClienteNaoEncontradoException, IOException, EmptyListException {
		repClientes.atualizar(id, clientes);
		workbook.write(new FileOutputStream(new File("planilha.xls")));
	}

	public void inserirCliente(Cliente clientes) throws ClienteJaExisteException, IOException {
		repClientes.inserir(clientes);
		workbook.write(new FileOutputStream(new File("planilha.xls")));
		
	}

	public Cliente procurarCliente(String id) throws ClienteNaoEncontradoException, EmptyListException {
		return repClientes.procurar(id);
	}

	public void removerCliente(String id) throws ClienteNaoEncontradoException, IOException, EmptyListException {
		repClientes.remover(id);
		workbook.write(new FileOutputStream(new File("planilha.xls")));
	}

	public Iterator getIteratorCliente() throws EmptyListException {
		return repClientes.getIterator();
	}
	// Produtos

	public void cadastrarProduto(Produto produto) throws ProdutoJaExisteException, IOException {
		repProdutos.cadastrar(produto);
		workbook.write(new FileOutputStream(new File("planilha.xls")));

	}

	public void removerProduto(String codigo) throws ProdutoNaoEncontradoException, EmptyListException, FileNotFoundException, IOException {
		repProdutos.remover(codigo);
		workbook.write(new FileOutputStream(new File("planilha.xls")));
	}

	public Produto procurarProduto(String codigo) throws ProdutoNaoEncontradoException, EmptyListException {
		return repProdutos.procurar(codigo);
	}
	
	public Produto procurarProdNome(String nome) throws ProdutoNaoEncontradoException, EmptyListException {
		return repProdutos.procurarNome(nome);
	}

	public void atualizarProduto(String codigo, Produto produto)
			throws ProdutoNaoEncontradoException, EmptyListException, FileNotFoundException, IOException {
		repProdutos.atualizar(codigo, produto);
		workbook.write(new FileOutputStream(new File("planilha.xls")));
	}

	public Iterator getIteratorProduto() throws EmptyListException {
		return repProdutos.getIterator();
	}

	// Entregas

	public void cadastrarEntrega(Entrega entrega) throws EntregaJaExisteException, FileNotFoundException, IOException {
		repEntregas.cadastrar(entrega);
		workbook.write(new FileOutputStream(new File("planilha.xls")));
	}

	public void removerEntrega(String id) throws EntregaNaoEncontradaException, EmptyListException, FileNotFoundException, IOException {
		repEntregas.remover(id);
		workbook.write(new FileOutputStream(new File("planilha.xls")));
	}

	public Entrega procurarEntrega(String id) throws EntregaNaoEncontradaException, EmptyListException {
		Entrega resposta = repEntregas.procurar(id);
		if(resposta == null) {
			throw new EntregaNaoEncontradaException();
		}
		return resposta;
	}

	public void atualizarEntrega(String id, Entrega entrega) throws EntregaNaoEncontradaException, EmptyListException, FileNotFoundException, IOException {
		repEntregas.atualizar(id, entrega);
		workbook.write(new FileOutputStream(new File("planilha.xls")));
	}
	
	public void enviar() throws NaoHaEntregasException, FileNotFoundException, IOException {
		repEntregas.enviar();
		workbook.write(new FileOutputStream(new File("planilha.xls")));
	}

	public Iterator getIteratorEntPendentes() throws EmptyListException {
		return repEntregas.getIteratorPendentes();
	}

	public Iterator getIteratorEntEnviadas() throws EmptyListException {
		return repEntregas.getIteratorEnviadas();
	}

	// Login

	public boolean login(String id, String senha)
			throws ClienteNaoEncontradoException, SenhaIncoretaException, EmptyListException {
		boolean resposta = false;
		Cliente cliente = this.procurarCliente(id);
		if ((cliente.getSenha().equals(senha))) {
			resposta = true;
		} else {
			throw new SenhaIncoretaException();
		}
		return resposta;
	}

	public void closeWorkbook() throws IOException {
		workbook.close();
	}

	public void vender(String idProduto, String idCliente) throws EntregaJaExisteException,
			EmptyListException, EntregaNaoEncontradaException, ProdutoNaoEncontradoException, ClienteNaoEncontradoException, IOException {
		String idEntrega = Integer.toString((int) Math.random());
		while(GemialidadesLoja.getInstance().getIteratorEntPendentes().hasNext()) {
			idEntrega = Integer.toString((int) Math.random());
		}
		Entrega entrega = new Entrega(idEntrega, idCliente, idProduto);
		GemialidadesLoja.getInstance().cadastrarEntrega(entrega);
		Cliente cliente = GemialidadesLoja.getInstance().procurarCliente(idCliente);
		cliente.addEntrega(idEntrega);
		GemialidadesLoja.getInstance().atualizarCliente(idCliente, cliente);
		GemialidadesLoja.getInstance().removerProduto(idProduto);
		
	}
	
}
