package br.com.brytecnologia.desafio.backend.service.impl;

public class BlanckDataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3376010575755655085L;

	public BlanckDataException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

	public BlanckDataException(String mensagem) {
		super(mensagem);
	}

	

}
