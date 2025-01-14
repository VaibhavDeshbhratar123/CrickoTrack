package com.tka.crickotrack.exception;

public class TeamNotFoundException extends RuntimeException {

	public TeamNotFoundException(String message) {
		super(message);
	}

	public TeamNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}