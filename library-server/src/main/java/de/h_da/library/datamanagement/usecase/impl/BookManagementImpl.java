/*
 * UseCase1Bean.java
 *
 * Created on 10. September 2007, 15:11
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.h_da.library.datamanagement.usecase.impl;

import de.h_da.library.LibraryRuntimeException;
import de.h_da.library.datamanagement.entity.Book;
import de.h_da.library.datamanagement.entity.BookOnStock;
import de.h_da.library.datamanagement.manager.BookManager;
import de.h_da.library.datamanagement.manager.BookOnStockManager;
import de.h_da.library.datamanagement.usecase.BookManagement;
import de.h_da.library.datamanagement.usecase.BookManagementRemote;
import de.h_da.library.datamanagement.usecase.interceptor.BookManagementInterceptor;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

@Stateless
@Interceptors(BookManagementInterceptor.class)
public class BookManagementImpl implements BookManagement, BookManagementRemote {
    
    @EJB
    BookManager bookManager;
    
    @EJB
    BookOnStockManager bookOnStockManager;
    
    /** Creates a new instance of UseCase1Bean */
    public BookManagementImpl() {
    }
    
    @Override
	public int addBook(Book book, int numberOfBooksOnStock)
	{
    	if (null == book)
    	{
    		 throw new LibraryRuntimeException("Book must be set");
    	}
    	
		Book newBook = bookManager.create(book);
		long bookId = newBook.getId();
		
		addBooksOnStock((int)bookId, numberOfBooksOnStock);
		return (int)bookId;
	}

    @Override
	public void modifyBook(Book book)
	{
    	if (null == book)
    	{
    		 throw new LibraryRuntimeException("Book must be set");
    	}
    	
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
				}
			}							
		}
		
	}
	
    @Override
	public void addBooksOnStock(int bookId, int numberOfBooksOnStock)
	{
    	Book book = bookManager.findById((long)bookId);
    	if (null == book)
    	{
    		 throw new LibraryRuntimeException("Could not find book");
    	}
    	
		for (int i = 0; i < numberOfBooksOnStock; ++i)
		{
			BookOnStock bookOnStock = new BookOnStock();	
			bookOnStock.setBook(book);
			bookOnStockManager.create(bookOnStock);
		}
	}
    
}
