package br.com.brytecnologia.desafio.backend.service.exception;

public class InvalidDataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3376010575755655085L;

	public InvalidDataException(String mensagem) {
		super(mensagem);
	}

	public InvalidDataException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
