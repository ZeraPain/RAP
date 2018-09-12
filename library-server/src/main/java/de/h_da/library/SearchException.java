package de.h_da.library;

public class SearchException extends LibraryRuntimeException{
	public SearchException() {
		super();
	}

	public SearchException(String message, Throwable cause) {
		super(message, cause);
	}

	public SearchException(String message) {
		super(message);
	}

	public SearchException(Throwable cause) {
		super(cause);
	}
}
