package com.jinqiu.app.exceptions;

public class UserServiceException extends RuntimeException {
	private static final long serialVersionUID = 5341554473520516919L;

	public UserServiceException(String message) {
		super(message);
	}
}
