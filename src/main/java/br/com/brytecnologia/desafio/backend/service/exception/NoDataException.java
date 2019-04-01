package br.com.brytecnologia.desafio.backend.service.exception;

public class NoDataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3376010575755655085L;

	public NoDataException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

	public NoDataException(String mensagem) {
		super(mensagem);
	}

	

}
