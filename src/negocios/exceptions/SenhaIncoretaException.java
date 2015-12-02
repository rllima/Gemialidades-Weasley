package negocios.exceptions;

public class SenhaIncoretaException extends Exception {
	public SenhaIncoretaException() {
		super("Senha incorreta!");
	}
}
