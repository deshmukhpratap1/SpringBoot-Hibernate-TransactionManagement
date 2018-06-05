package com.example.demo.exception;

public class NoEnoughBalanceException extends Exception {

	private static final long serialVersionUID = 6693921340890185733L;

	public NoEnoughBalanceException(String message) {
		super(message);
	}
}
