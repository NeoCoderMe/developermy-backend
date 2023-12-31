package com.developermy.crosscutting.exceptions;

import java.io.Serial;

public class BadRequestException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 4512149716061648471L;

	public BadRequestException(String message) {
		super(message);
	}

}
