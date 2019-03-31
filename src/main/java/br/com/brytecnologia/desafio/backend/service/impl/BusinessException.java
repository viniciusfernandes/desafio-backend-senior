package br.com.brytecnologia.desafio.backend.service.impl;

public class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3376010575755655085L;

	public BusinessException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

	public BusinessException(String mensagem) {
		super(mensagem);
	}

	

}
