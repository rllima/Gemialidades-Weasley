package negocios.exceptions;

public class ClienteJaExisteException extends Exception {
	public ClienteJaExisteException() {
		super("O cliente ja esta cadastrado");
	}
}
