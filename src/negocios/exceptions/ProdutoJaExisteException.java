package negocios.exceptions;

public class ProdutoJaExisteException extends Exception {
	public ProdutoJaExisteException() {
		super ("O produto j� est� cadastrado");
	}

}
