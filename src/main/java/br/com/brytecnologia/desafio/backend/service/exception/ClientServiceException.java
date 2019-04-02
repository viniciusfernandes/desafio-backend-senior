package br.com.brytecnologia.desafio.backend.service.exception;

public class ClientServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3376010575755655085L;

	public ClientServiceException(String mensagem) {
		super(mensagem);
	}

	public ClientServiceException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
