package com.acebook.exceptions;

public class AuthenticationException extends AcebookAPIException {

	@Override
	public int getStatusCode() {
		return 401;
	}

	
}
