package de.h_da.library;

public class BorrowingException extends LibraryException{
	public BorrowingException() {
		super();
	}

	public BorrowingException(String message, Throwable cause) {
		super(message, cause);
	}

	public BorrowingException(String message) {
		super(message);
	}

	public BorrowingException(Throwable cause) {
		super(cause);
	}

}