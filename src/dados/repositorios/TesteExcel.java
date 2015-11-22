package dados.repositorios;

import java.io.*;

import negocios.classesBasicas.*;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

public class TesteExcel {
	
	public static void main(String[] args) {
		try {
			File file = new File("planilha.xls");
			file.createNewFile();
			
			FileOutputStream saidaArquivo = new FileOutputStream(file);
			Workbook workbook = new HSSFWorkbook();
			
			RepositorioProdutosExcel repositorio = new RepositorioProdutosExcel(workbook);
			
			repositorio.inserir(new Travessuras("Orelha Extensível", "666", "Ouvir conversa alheia", 2, 15));
			repositorio.inserir(new Guloseimas("Sapo de chocolate", "555", "Feijoeszinhos com sabores diversos", "Nunca saberás"));
			repositorio.inserir(new Travessuras("Kit mata-aula", "777", "Kit fugir-de-ricardo", 0, 0));
			
			System.out.println(repositorio.procurar("555").toString());
			repositorio.remover("666");
			Produto produtos = new Guloseimas("Feijoes Magicos", "555", "Feijoeszinhos com sabores diversos", "Nunca saberás");
			repositorio.atualizar("555", produtos);
			
			workbook.write(saidaArquivo);
			saidaArquivo.close();
			workbook.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
