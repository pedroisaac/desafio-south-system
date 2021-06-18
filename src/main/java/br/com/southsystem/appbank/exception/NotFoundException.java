package br.com.southsystem.appbank.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class NotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public NotFoundException(String mensagem) {
		super(mensagem);
	}
	
	@SuppressWarnings("unused")
	public NotFoundException(String mensagem, Throwable throwable) {
		super(mensagem, throwable);
	}
}
