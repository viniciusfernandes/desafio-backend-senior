package br.com.brytecnologia.desafio.backend.service.exception;

public class BadFormatDataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3376010575755655085L;

	public BadFormatDataException(String mensagem) {
		super(mensagem);
	}

	public BadFormatDataException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
