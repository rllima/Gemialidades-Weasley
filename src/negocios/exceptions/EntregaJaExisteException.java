package negocios.exceptions;

public class EntregaJaExisteException extends Exception {
	public EntregaJaExisteException() {
		super("J� existe uma entrega com este ID!");
	}
}
