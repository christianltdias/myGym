package com.exercisetracker.services.exceptions;

public class DataIntegrityException extends RuntimeException {
	private static final long serialVersionUID = 1L;


	public DataIntegrityException(String msg) {
		super(msg);
	}
	
	public DataIntegrityException(String msg, Throwable cause) {
		throw new DataIntegrityException("Não é possível excluir uma Categoria que possui Produtos.");
	}
	
}
