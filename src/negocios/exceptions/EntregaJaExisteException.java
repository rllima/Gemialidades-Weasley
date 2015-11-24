package negocios.exceptions;

public class EntregaJaExisteException extends Exception {
	public EntregaJaExisteException() {
		super("Entrega ja esta cadastrada");
	}
}
