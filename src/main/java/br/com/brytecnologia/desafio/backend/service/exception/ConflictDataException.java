package br.com.brytecnologia.desafio.backend.service.exception;

public class ConflictDataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3376010575755655085L;

	public ConflictDataException(String mensagem) {
		super(mensagem);
	}

	public ConflictDataException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
