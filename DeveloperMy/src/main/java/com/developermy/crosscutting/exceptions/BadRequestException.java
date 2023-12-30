package com.developermy.crosscutting.exceptions;

public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 4512149716061648471L;

	public BadRequestException(String message) {
		super(message);
	}

}
