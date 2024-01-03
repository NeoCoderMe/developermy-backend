package com.developermy.crosscutting.exceptions;

import java.io.Serial;

public class NotFoundException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 4512149716061648471L;

	public NotFoundException(String message) {
		super(message);
	}

}
