package negocios.exceptions;

public class NaoHaEntregasException extends Exception {
	public NaoHaEntregasException(){
		super ("Não há entregas a serem enviadas!");
	}

}
