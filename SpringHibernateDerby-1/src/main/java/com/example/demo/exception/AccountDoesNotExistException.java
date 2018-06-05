package com.example.demo.exception;

public class AccountDoesNotExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AccountDoesNotExistException(String exception) {
		super(exception);
	}
}
