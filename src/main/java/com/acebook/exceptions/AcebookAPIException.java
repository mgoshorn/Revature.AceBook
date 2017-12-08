package com.acebook.exceptions;

public abstract class AcebookAPIException extends Exception {
	
	public abstract int getStatusCode();
}
