package negocios.exceptions;

public class ClienteNaoEncontradoException extends Exception {
	public ClienteNaoEncontradoException(){
		super ("Cliente não encontrado!");
	}
}
