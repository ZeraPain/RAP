/*
 * UseCase1Bean.java
 *
 * Created on 10. September 2007, 15:11
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.h_da.library.datamanagement.usecase.impl;

import de.h_da.library.GlobalInterceptor;
import de.h_da.library.LibraryException;
import de.h_da.library.LibraryRuntimeException;
import de.h_da.library.datamanagement.entity.Book;
import de.h_da.library.datamanagement.entity.BookOnStock;
import de.h_da.library.datamanagement.manager.BookManager;
import de.h_da.library.datamanagement.manager.BookOnStockManager;
import de.h_da.library.datamanagement.usecase.BookManagement;
import de.h_da.library.datamanagement.usecase.BookManagementRemote;


import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

@Stateless
@Interceptors(GlobalInterceptor.class)
public class BookManagementImpl implements BookManagement, BookManagementRemote {
    
    @EJB
    BookManager bookManager;
    
    @EJB
    BookOnStockManager bookOnStockManager;
    
    /** Creates a new instance of UseCase1Bean */
    public BookManagementImpl() {
    }
    
    @Override
	public int addBook(Book book, int numberOfBooksOnStock) throws LibraryException
	{
    	if (null == book)
    	{
    		 throw new LibraryException("Book must be set.");
    	}
    	
		Book newBook = bookManager.create(book);
		if (null == newBook)
		{
			throw new LibraryRuntimeException("Book could not be created.");
		}
		long bookId = newBook.getId();
		if (0 == bookId)
		{
			throw new LibraryException("Book could not be created.");
		}
		
		addBooksOnStock((int)bookId, numberOfBooksOnStock);
		return (int)bookId;
	}

    @Override
	public void modifyBook(Book book) throws LibraryException
	{
    	if (null == book)
    	{
    		 throw new LibraryException("Book must be set");
    	}
    	
    	boolean isModified = false;
		List<Book> books = bookManager.findAll();
		if(book != null) 
		{
			for (Book b : books)
			{
				if (b.getId() == book.getId())
				{
					b.setTitle(book.getTitle());
					b.setAuthors(book.getAuthors());
					bookManager.edit(b);
					isModified = true;
				}
			}							
		}
		
		if (!isModified)
		{
			throw new LibraryException("Could not find book.");
		}
		
	}
	
    @Override
	public void addBooksOnStock(int bookId, int numberOfBooksOnStock) throws LibraryException
	{
    	Book book = bookManager.findById((long)bookId);
    	if (null == book)
    	{
    		 throw new LibraryException("Could not find book");
    	}
    	
		for (int i = 0; i < numberOfBooksOnStock; ++i)
		{
			BookOnStock bookOnStock = new BookOnStock();	
			bookOnStock.setBook(book);
			bookOnStockManager.create(bookOnStock);
		}
	}
    
}
