package de.h_da.library.datamanagement.usecase;

import javax.ejb.Remote;

import de.h_da.library.datamanagement.entity.Book;

@Remote
public interface BookManagementRemote 
{
	public int addBook(Book book, int numberOfBooksOnStock);
	public void modifyBook(Book book);
	public void addBooksOnStock(int bookId, int numberOfBooksOnStock);
}
