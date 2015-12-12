package negocios.exceptions;

public class EntregaJaExisteException extends Exception {
	public EntregaJaExisteException() {
		super("Já existe uma entrega com este ID!");
	}
}
