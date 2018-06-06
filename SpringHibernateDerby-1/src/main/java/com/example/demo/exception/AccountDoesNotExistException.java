package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountDoesNotExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AccountDoesNotExistException(String exception) {
		super(exception);
	}
}
